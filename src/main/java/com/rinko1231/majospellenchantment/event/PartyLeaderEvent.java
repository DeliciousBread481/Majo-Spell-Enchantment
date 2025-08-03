package com.rinko1231.majospellenchantment.event;

import com.rinko1231.majospellenchantment.config.MajoSpellEnchantmentConfig;
import com.rinko1231.majospellenchantment.init.EnchantmentRegistry;
import com.rinko1231.majospellenchantment.init.ModMobEffects;
import io.redspace.ironsspellbooks.damage.SpellDamageSource;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class PartyLeaderEvent {

    @SubscribeEvent
    public void onSpellDamage(LivingDamageEvent.Post event) {

        // 攻击者必须是玩家，并且来源是法术
        if (!(event.getSource().getEntity() instanceof ServerPlayer player)) return;
        if (!(event.getSource() instanceof SpellDamageSource)) return;

        // 检查主手附魔
        HolderLookup.RegistryLookup<Enchantment> enchants = player.level().registryAccess()
                .lookupOrThrow(Registries.ENCHANTMENT);

        int enchantmentLevel = player.getMainHandItem()
                .getEnchantmentLevel(enchants.getOrThrow(EnchantmentRegistry.PARTY_LEADER_ENCHANT));
        if (enchantmentLevel <= 0) return;
       LivingEntity target = event.getEntity();
        // 读取配置
        double glowChancePerLevel = MajoSpellEnchantmentConfig.partyLeaderGlowChancePerLevel.get();  // 0.2
        double feverChancePerLevel = MajoSpellEnchantmentConfig.partyLeaderFeverChancePerLevel.get(); // 0.1

        boolean glowTrigger = false;
        boolean feverTrigger = false;

        RandomSource random = player.getRandom();

        // 发光判定
        if (random.nextDouble() < glowChancePerLevel * enchantmentLevel) {
            glowTrigger = true;
        }

        // 狂热判定（狂热必然包含发光效果）
        if (random.nextDouble() < feverChancePerLevel * enchantmentLevel) {
            feverTrigger = true;
            glowTrigger = true;
        }

        // 应用效果
        if (glowTrigger) {
            target.addEffect(new MobEffectInstance(MobEffects.GLOWING, MajoSpellEnchantmentConfig.partyLeaderEffectDuration.get()+MajoSpellEnchantmentConfig.partyLeaderEffectDurationSundayBonus.get()*getSundayFlag(), 0));
        }
        if (feverTrigger) {
            target.addEffect(new MobEffectInstance(ModMobEffects.SUNDAY_FEVER, MajoSpellEnchantmentConfig.partyLeaderEffectDuration.get()+MajoSpellEnchantmentConfig.partyLeaderEffectDurationSundayBonus.get()*getSundayFlag(), 0));
        }
    }

    private int  getSundayFlag() {
        DayOfWeek today = LocalDate.now().getDayOfWeek();
        return today == DayOfWeek.SUNDAY ? 1:0;
    }

}
