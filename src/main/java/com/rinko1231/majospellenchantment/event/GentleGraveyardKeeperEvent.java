package com.rinko1231.majospellenchantment.event;

import com.rinko1231.majospellenchantment.config.MajoSpellEnchantmentConfig;
import io.redspace.ironsspellbooks.api.events.SpellDamageEvent;
import io.redspace.ironsspellbooks.api.events.SpellHealEvent;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.util.Utils;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.CommonHooks;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.Objects;

import static com.rinko1231.majospellenchantment.init.EnchantmentRegistry.GENTLE_GRAVEYARD_KEEPER_ENCHANT;
import static net.minecraft.tags.EntityTypeTags.UNDEAD;


public class GentleGraveyardKeeperEvent {

    @SubscribeEvent
    public void onSpellDamage(SpellDamageEvent event) {
        if(!MajoSpellEnchantmentConfig.gentleGraveyardKeeperEnabled.get()) return;
        LivingEntity target = event.getEntity();
        if (target == null || target.level().isClientSide()) return;

        // 只处理造成了正伤害的情况，避免0或负值
        if (event.getAmount() <= 0f) return;

        // 只对亡灵有效
        if(!target.getType().is(UNDEAD)) return;

        // 必须是“神圣系”法术
        if (event.getSpellDamageSource() == null
                || event.getSpellDamageSource().spell() == null
                || event.getSpellDamageSource().spell().getSchoolType() != SchoolRegistry.HOLY.get()) {
            return;
        }

        // 施法者需要是玩家
        Entity attacker = event.getSpellDamageSource().getEntity(); // 若API不同，可用 getDirectEntity()/getOwner()
        if (!(attacker instanceof Player player)) return;

        // 拿到玩家的法书/法杖（你提供的工具方法）
        ItemStack spellbook = Utils.getPlayerSpellbookStack(player);
        if (spellbook == null || spellbook.isEmpty()) return;

        // 检查是否具有“温柔的守墓人”（举例名）附魔等级
        int level = spellbook.getEnchantmentLevel(
                Objects.requireNonNull(CommonHooks.resolveLookup(Registries.ENCHANTMENT))
                        .getOrThrow(GENTLE_GRAVEYARD_KEEPER_ENCHANT)
        );
        if (level <= 0) return;

        // 施加 5s 虚弱（100 ticks）；不叠加放大，按需可用 level-1 作放大等级
        target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 60+ level*20, 0));
        // 若想随等级增强： new MobEffectInstance(MobEffects.WEAKNESS, 100, Math.max(0, level - 1))
    }


    @SubscribeEvent
    public void onSpellHeal(SpellHealEvent event) {
        if(!MajoSpellEnchantmentConfig.gentleGraveyardKeeperEnabled.get()) return;
        LivingEntity caster = (LivingEntity) event.getEntity();
        LivingEntity target = event.getTargetEntity();
        if (caster == null || target == null) return;
        if (target.level().isClientSide()) return;

        // 仅神圣系治疗时触发
        if (event.getSchoolType() != SchoolRegistry.HOLY.get()) return;

        // 需要玩家施法（若允许非玩家，也可放宽为 LivingEntity 再处理其“法书持有”）
        if (!(caster instanceof Player player)) return;

        // 拿到玩家的法书/法杖（你提供的工具方法）
        ItemStack spellbook = Utils.getPlayerSpellbookStack(player);
        if (spellbook == null || spellbook.isEmpty()) return;

        // 检查是否具有“温柔的守墓人”（举例名）附魔等级
        int level = spellbook.getEnchantmentLevel(
                Objects.requireNonNull(CommonHooks.resolveLookup(Registries.ENCHANTMENT))
                        .getOrThrow(GENTLE_GRAVEYARD_KEEPER_ENCHANT)
        );
        if (level <= 0) return;

        // 施加 5s 虚弱（100 ticks）；不叠加放大，按需可用 level-1 作放大等级
        target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 60+ level*20, 0));
    }


}
