package com.rinko1231.majospellenchantment.enchantment;

import com.rinko1231.majospellenchantment.config.MajoSpellEnchantmentConfig;
import com.rinko1231.majospellenchantment.init.BaseEnchantment;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class Zoophony extends BaseEnchantment {
    public Zoophony() {
        super(Rarity.VERY_RARE, EnchantmentCategory.ARMOR_HEAD , new EquipmentSlot[]{EquipmentSlot.HEAD});
    }
    @Override
    public int getMaxLevel() {
        return 2;
    }

    public boolean isTreasureOnly() {
        return true;
    }

    public boolean isCurse() {
        return true;
    }

    @Override
    public boolean isDisabled() {
        return MajoSpellEnchantmentConfig.zoophonyDisabled.get();
    }
}