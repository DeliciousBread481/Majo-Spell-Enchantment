package com.rinko1231.majospellenchantment.enchantment;

import com.rinko1231.majospellenchantment.config.MajoSpellEnchantmentConfig;
import com.rinko1231.majospellenchantment.init.BaseEnchantment;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class SpellStreak extends BaseEnchantment {
    public SpellStreak() {
        super(Rarity.RARE, EnchantmentCategory.WEAPON , new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    public boolean isTreasureOnly() {
        return MajoSpellEnchantmentConfig.spellStreakTreasureOnly.get();
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        return  BaseEnchantment.canUseAsWeapon(stack.getItem())&& super.canEnchant(stack);
    }
    @Override
    public boolean isDisabled() {
        return MajoSpellEnchantmentConfig.spellStreakDisabled.get();
    }
}