package com.rinko1231.majospellenchantment.event;

import com.rinko1231.majospellenchantment.config.MajoSpellEnchantmentConfig;
import com.rinko1231.majospellenchantment.init.EnchantmentRegistry;
import io.redspace.ironsspellbooks.api.events.SpellPreCastEvent;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.bus.api.SubscribeEvent;

import static com.rinko1231.majospellenchantment.MajoSpellEnchantment.MOD_ID;

public class HopelessPowerEvent {

    @SubscribeEvent
    public void onSpellCast(SpellPreCastEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        AttributeInstance spellPower = player.getAttribute(AttributeRegistry.SPELL_POWER);
        if (spellPower == null) {
            return;
        }
        spellPower.removeModifier(ResourceLocation.fromNamespaceAndPath(MOD_ID, "hopeless_power_bonus"));

        HolderLookup.RegistryLookup<Enchantment> enchants = player.level().registryAccess()
                .lookupOrThrow(Registries.ENCHANTMENT);

        int level = player.getItemBySlot(EquipmentSlot.CHEST)
                .getEnchantmentLevel(enchants.getOrThrow(EnchantmentRegistry.HOPELESS_POWER_ENCHANT));

        if (level <= 0) return;


        // 从配置读取
        double thresholdStart = MajoSpellEnchantmentConfig.hopelessPowerThresholdStart.get();   // e.g. 0.5
        double thresholdMax = MajoSpellEnchantmentConfig.hopelessPowerThresholdMax.get();       // e.g. 0.3
        double maxBonusPerLevel = MajoSpellEnchantmentConfig.hopelessPowerBonusPerLevel.get();  // e.g. 0.15

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
            spellPower.removeModifier(ResourceLocation.fromNamespaceAndPath(MOD_ID, "hopeless_power_bonus"));
            spellPower.addTransientModifier(
                    new AttributeModifier(
                            ResourceLocation.fromNamespaceAndPath(MOD_ID, "hopeless_power_bonus"),
                            bonusMultiplier,
                            AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                    )
            );
        }
    }
}
