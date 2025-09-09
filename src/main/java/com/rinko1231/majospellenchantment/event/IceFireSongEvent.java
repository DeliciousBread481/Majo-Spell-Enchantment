package com.rinko1231.majospellenchantment.event;

import com.rinko1231.majospellenchantment.config.MajoSpellEnchantmentConfig;
import com.rinko1231.majospellenchantment.init.EnchantmentRegistry;
import io.redspace.ironsspellbooks.api.events.SpellDamageEvent;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.SchoolType;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.damage.SpellDamageSource;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.CommonHooks;

import java.util.Objects;

public class IceFireSongEvent {
    @SubscribeEvent
    public void onSpellDamage(SpellDamageEvent event) {
        if(!MajoSpellEnchantmentConfig.iceFireSongEnabled.get()) return;
        LivingEntity target = event.getEntity();
        if (target == null || target.level().isClientSide()) return;

        SpellDamageSource sds = event.getSpellDamageSource();
        if (sds == null || sds.spell()==null) return;

        Entity caster = sds.getEntity();
        if (!(caster instanceof Player player)) return;

        // 法书是否有“冰火之歌”
        ItemStack spellbook = Utils.getPlayerSpellbookStack(player);
        if (spellbook == null || spellbook.isEmpty()) return;
        int enchantmentLevel = spellbook.getEnchantmentLevel(
                Objects.requireNonNull(CommonHooks.resolveLookup(Registries.ENCHANTMENT))
                        .getOrThrow(EnchantmentRegistry.ICE_FIRE_SONG_ENCHANT)
        );
        if (enchantmentLevel <= 0) return;

        SchoolType school = sds.spell().getSchoolType();
        float amount = event.getAmount();
        boolean modified = false;

        float MaxDamageAmountBonus = 1.0F + MajoSpellEnchantmentConfig.iceFireSongExtraDamageRatioPerLevel.get().floatValue() * enchantmentLevel;

        // 冰系命中燃烧目标：1.5x 并灭火
        if (school == SchoolRegistry.ICE.get() && target.isOnFire()
                && !target.getType().is(EntityTypeTags.FREEZE_HURTS_EXTRA_TYPES) ) { //本就会受到额外伤害了
            amount *= MaxDamageAmountBonus;
            target.clearFire();
            modified = true;
        }

        // 火系命中冻结目标：按冻结比例增伤
        if (school == SchoolRegistry.FIRE.get() && target instanceof LivingEntity le) {
            int frozen   = le.getTicksFrozen();                // 当前冻结刻
            int required = le.getTicksRequiredToFreeze();      // 完全冻结所需冻结刻
            if (frozen > 0 && required > 0 && frozen> 0.3 * required) {
                // 倍率 = 1 + 0.5 * (frozen/required)，并封顶 1.5
                double ratio   = (double) frozen / (double) required;
                float  mult    = (float) Math.min(MaxDamageAmountBonus, 1.0 + MajoSpellEnchantmentConfig.iceFireSongExtraDamageRatioPerLevel.get() * ratio);
                amount *= mult;

                int thaw = Math.max(frozen/ 2, required / 2);
                le.setTicksFrozen(Math.max(0, frozen - thaw));
                modified = true;

            }
        }

        if (modified) {
            event.setAmount(amount);
        }
    }

}
