package com.rinko1231.majospellenchantment.enchantment;

import com.rinko1231.majospellenchantment.config.MajoSpellEnchantmentConfig;
import com.rinko1231.majospellenchantment.init.BaseEnchantment;
import com.rinko1231.majospellenchantment.init.ModEnchantments;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class CDReduction extends BaseEnchantment {
    public CDReduction() {
        super(Rarity.RARE, EnchantmentCategory.ARMOR_HEAD , new EquipmentSlot[]{EquipmentSlot.HEAD});
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public boolean isDisabled() {
        return MajoSpellEnchantmentConfig.cdReductionDisabled.get();
    }
}