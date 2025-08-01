package com.rinko1231.majospellenchantment.event;

import com.rinko1231.majospellenchantment.config.MajoSpellEnchantmentConfig;
import com.rinko1231.majospellenchantment.init.EnchantmentRegistry;
import io.redspace.ironsspellbooks.api.events.SpellOnCastEvent;
import io.redspace.ironsspellbooks.api.util.Utils;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.bus.api.SubscribeEvent;

public class OceanGraceEvent {

    @SubscribeEvent
    public void onSpellCast(SpellOnCastEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        // 判断是否在水、雨、气泡柱中
        if (!player.isInWaterRainOrBubble()) return;

        // 检查附魔等级（只取主手或副手的 Spellbook）
        ItemStack legs = player.getItemBySlot(EquipmentSlot.LEGS);
        if (legs == null) return;

        HolderLookup.RegistryLookup<Enchantment> enchants = player.level().registryAccess()
                .lookupOrThrow(Registries.ENCHANTMENT);

        int level = legs.getEnchantmentLevel(
                enchants.getOrThrow(EnchantmentRegistry.OCEAN_GRACE_ENCHANT)
        );
        if (level <= 0) return;

        // 减少法力消耗
        double reductionFactor = MajoSpellEnchantmentConfig.oceanGraceManaReductionPerLevel.get() * level;
        int originalManaCost = event.getOriginalManaCost();
        int reducedCost = (int) Math.max(1, originalManaCost * (1 - reductionFactor));

        // 应用修改
        event.setManaCost(reducedCost);
    }
}