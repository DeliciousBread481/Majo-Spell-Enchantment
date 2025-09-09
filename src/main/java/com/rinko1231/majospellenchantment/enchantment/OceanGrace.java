package com.rinko1231.majospellenchantment.enchantment;

import com.rinko1231.majospellenchantment.config.MajoSpellEnchantmentConfig;
import com.rinko1231.majospellenchantment.init.BaseEnchantment;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class OceanGrace extends BaseEnchantment {
    public OceanGrace() {
        super(Rarity.COMMON, EnchantmentCategory.ARMOR_LEGS , new EquipmentSlot[]{EquipmentSlot.LEGS});
    }
    @Override
    public int getMaxLevel() {
        return 4;
    }

    @Override
    public boolean isDisabled() {
        return MajoSpellEnchantmentConfig.oceanGraceDisabled.get();
    }
}