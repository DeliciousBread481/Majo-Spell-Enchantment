package com.rinko1231.majospellenchantment.enchantment;

import com.rinko1231.majospellenchantment.config.MajoSpellEnchantmentConfig;
import com.rinko1231.majospellenchantment.init.BaseEnchantment;
import com.rinko1231.majospellenchantment.init.ModEnchantments;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class PartyLeader extends BaseEnchantment {
    public PartyLeader() {
        super(Rarity.RARE, EnchantmentCategory.WEARABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }
    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        return stack.is(ModEnchantments.SPELLBOOK_TAG)&& super.canEnchant(stack);
    }


    @Override
    public boolean isDisabled() {
        return MajoSpellEnchantmentConfig.partyLeaderDisabled.get();
    }
}