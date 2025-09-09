package com.rinko1231.majospellenchantment.enchantment;

import com.rinko1231.majospellenchantment.config.MajoSpellEnchantmentConfig;
import com.rinko1231.majospellenchantment.init.BaseEnchantment;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class Necrovolt extends BaseEnchantment {
    public Necrovolt() {
        super(Rarity.VERY_RARE, EnchantmentCategory.WEAPON , new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    public boolean isTreasureOnly() {
        return MajoSpellEnchantmentConfig.NecrovoltTreasureOnly.get();
    }

    @Override
    public boolean isDisabled() {
        return MajoSpellEnchantmentConfig.NecrovoltDisabled.get();
    }
}