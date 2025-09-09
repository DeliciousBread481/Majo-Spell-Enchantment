package com.rinko1231.majospellenchantment.enchantment;

import com.rinko1231.majospellenchantment.config.MajoSpellEnchantmentConfig;
import com.rinko1231.majospellenchantment.init.BaseEnchantment;
import com.rinko1231.majospellenchantment.init.ModEnchantments;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class NocturneAria extends BaseEnchantment {
    public NocturneAria() {
        super(Rarity.UNCOMMON, EnchantmentCategory.VANISHABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }
    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        return stack.is(ModEnchantments.SPELLBOOK_TAG)&& super.canEnchant(stack);
    }

    @Override
    protected boolean checkCompatibility(Enchantment other) {

        return other!=ModEnchantments.DAYLIGHT_ANTHEM_ENCHANT.get();
    }


    @Override
    public boolean isDisabled() {
        return MajoSpellEnchantmentConfig.nocturneAriaDisabled.get();
    }
}