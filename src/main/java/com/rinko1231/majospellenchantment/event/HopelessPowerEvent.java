package com.rinko1231.majospellenchantment.event;

import com.rinko1231.majospellenchantment.config.MajoSpellEnchantmentConfig;

import io.redspace.ironsspellbooks.api.events.SpellPreCastEvent;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraftforge.eventbus.api.SubscribeEvent;


import java.util.UUID;

import static com.rinko1231.majospellenchantment.init.ModEnchantments.HOPELESS_POWER_ENCHANT;

public class HopelessPowerEvent {

    private static final UUID HOPELESS_POWER_BONUS =
            UUID.fromString("dc93f448-edb0-5cf2-83af-8b12cb49b1a7");

    @SubscribeEvent
    public void onSpellCast(SpellPreCastEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        AttributeInstance spellPower = player.getAttribute(AttributeRegistry.SPELL_POWER.get());
        if (spellPower == null) {
            return;
        }
        spellPower.removeModifier(HOPELESS_POWER_BONUS);

        int level = player.getItemBySlot(EquipmentSlot.CHEST)
                .getEnchantmentLevel(HOPELESS_POWER_ENCHANT.get());

        if (level <= 0) return;


        // 从配置读取
        double thresholdStart = MajoSpellEnchantmentConfig.hopelessPowerThresholdStart.get();   // e.g. 0.5
        double thresholdMax = MajoSpellEnchantmentConfig.hopelessPowerThresholdMax.get();       // e.g. 0.3
        double maxBonusPerLevel = MajoSpellEnchantmentConfig.hopelessPowerBonusPerLevel.get();

        // 计算生命百分比
        double hpPercent = player.getHealth() / player.getMaxHealth();

        // 未达到起始阈值 → 无加成
        if (hpPercent > thresholdStart) return;

        // 计算最大加成
        double maxBonus = maxBonusPerLevel * level;
        double bonusMultiplier;

        if (hpPercent <= thresholdMax) {
            // 低于最大触发血量 → 满加成
            bonusMultiplier = maxBonus;
        } else {
            // 按比例缩放：thresholdStart 到 thresholdMax 之间线性映射
            double range = thresholdStart - thresholdMax;
            double scale = (thresholdStart - hpPercent) / range;
            bonusMultiplier = maxBonus * scale;
        }

        // 添加临时 Buff
        if (bonusMultiplier > 0) {
            spellPower.removeModifier(HOPELESS_POWER_BONUS);
            spellPower.addTransientModifier(
                    new AttributeModifier(
                            HOPELESS_POWER_BONUS,
                            "hopeless_power_bonus",
                            bonusMultiplier,
                            AttributeModifier.Operation.MULTIPLY_BASE
                    )
            );
        }
    }
}
