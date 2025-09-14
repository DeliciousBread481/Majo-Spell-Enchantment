package com.rinko1231.majospellenchantment.enchantment;

import com.rinko1231.majospellenchantment.config.MajoSpellEnchantmentConfig;
import com.rinko1231.majospellenchantment.init.BaseEnchantment;
import com.rinko1231.majospellenchantment.init.ModEnchantments;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class VladTepes extends BaseEnchantment {
    public VladTepes() {
        super(Rarity.RARE, EnchantmentCategory.BOW , new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
    }
    @Override
    protected boolean checkCompatibility(@NotNull Enchantment other) {

        return other!= ModEnchantments.RED_LOTUS_ENCHANT.get();
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }

    public boolean isTreasureOnly() {
        return MajoSpellEnchantmentConfig.vladTepesTreasureOnly.get();
    }

    @Override
    public boolean isDisabled() {
        return MajoSpellEnchantmentConfig.vladTepesDisabled.get();
    }
}