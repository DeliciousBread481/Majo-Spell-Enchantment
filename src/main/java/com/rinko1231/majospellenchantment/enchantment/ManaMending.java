package com.rinko1231.majospellenchantment.enchantment;

import com.rinko1231.majospellenchantment.config.MajoSpellEnchantmentConfig;
import com.rinko1231.majospellenchantment.init.BaseEnchantment;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

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
    public boolean isDisabled() {
        return MajoSpellEnchantmentConfig.manaMendingDisabled.get();
    }
}