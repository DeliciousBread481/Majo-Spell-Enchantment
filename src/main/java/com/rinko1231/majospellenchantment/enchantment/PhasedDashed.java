package com.rinko1231.majospellenchantment.enchantment;

import com.rinko1231.majospellenchantment.config.MajoSpellEnchantmentConfig;
import com.rinko1231.majospellenchantment.init.BaseEnchantment;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class PhasedDashed extends BaseEnchantment {
    public PhasedDashed() {
        super(Rarity.RARE, EnchantmentCategory.ARMOR_FEET , new EquipmentSlot[]{EquipmentSlot.FEET});
    }
    @Override
    public int getMaxLevel() {
        return 3;
    }

    public boolean isTreasureOnly() {
        return MajoSpellEnchantmentConfig.phaseDashedTreasureOnly.get();
    }

    @Override
    public boolean isDisabled() {
        return MajoSpellEnchantmentConfig.phaseDashedDisabled.get();
    }
}