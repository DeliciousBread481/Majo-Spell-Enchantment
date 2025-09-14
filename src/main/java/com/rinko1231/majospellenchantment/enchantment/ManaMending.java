package com.rinko1231.majospellenchantment.enchantment;

import com.rinko1231.majospellenchantment.config.MajoSpellEnchantmentConfig;
import com.rinko1231.majospellenchantment.init.BaseEnchantment;
import com.rinko1231.majospellenchantment.init.ModEnchantments;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

import static net.minecraft.world.item.enchantment.Enchantments.MENDING;

public class ManaMending extends BaseEnchantment {
    public ManaMending() {
        super(Rarity.VERY_RARE, EnchantmentCategory.BREAKABLE, EquipmentSlot.values());
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    public boolean isTreasureOnly() {
        return MajoSpellEnchantmentConfig.manaMendingTreasureOnly.get();
    }
    @Override
    protected boolean checkCompatibility(Enchantment other) {

        if(MajoSpellEnchantmentConfig.manaMendingIncompatibleWithMending.get())
        return other!= MENDING;
        else return super.checkCompatibility(other);
    }
    @Override
    public boolean isDisabled() {
        return MajoSpellEnchantmentConfig.manaMendingDisabled.get();
    }
}