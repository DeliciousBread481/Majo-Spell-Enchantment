package com.rinko1231.majospellenchantment.enchantment;

import com.rinko1231.majospellenchantment.config.MajoSpellEnchantmentConfig;
import com.rinko1231.majospellenchantment.init.BaseEnchantment;
import com.rinko1231.majospellenchantment.init.ModEnchantments;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

import static com.rinko1231.majospellenchantment.init.TagsRegistry.MANA_REAPER_COMPATIBLE_TAG;

public class ManaReaper extends BaseEnchantment {
    public ManaReaper() {
        super(Rarity.UNCOMMON, EnchantmentCategory.WEAPON , new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }


    @Override
    public boolean canEnchant(ItemStack stack) {
        return  BaseEnchantment.canUseAsWeapon(stack.getItem())&& super.canEnchant(stack);

    }
    @Override
    public boolean isDisabled() {
        return MajoSpellEnchantmentConfig.manaReaperDisabled.get();
    }
}