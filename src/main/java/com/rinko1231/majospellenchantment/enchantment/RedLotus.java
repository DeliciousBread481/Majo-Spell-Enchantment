package com.rinko1231.majospellenchantment.enchantment;

import com.rinko1231.majospellenchantment.config.MajoSpellEnchantmentConfig;
import com.rinko1231.majospellenchantment.init.BaseEnchantment;
import com.rinko1231.majospellenchantment.init.ModEnchantments;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class RedLotus extends BaseEnchantment {
    public RedLotus() {
        super(Rarity.RARE, EnchantmentCategory.BOW , new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }
    @Override
    protected boolean checkCompatibility(@NotNull Enchantment other) {

        return other!= ModEnchantments.VLAD_TEPES_ENCHANT.get();
    }


    public boolean isTreasureOnly() {
        return MajoSpellEnchantmentConfig.redLotusTreasureOnly.get();
    }

    @Override
    public boolean isDisabled() {
        return MajoSpellEnchantmentConfig.redLotusDisabled.get();
    }
}