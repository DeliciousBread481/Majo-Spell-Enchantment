package com.rinko1231.majospellenchantment.enchantment;

import com.rinko1231.majospellenchantment.config.MajoSpellEnchantmentConfig;
import com.rinko1231.majospellenchantment.init.BaseEnchantment;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class MaxMana extends BaseEnchantment {
    public MaxMana() {
        super(Rarity.COMMON, EnchantmentCategory.ARMOR_HEAD , new EquipmentSlot[]{EquipmentSlot.HEAD});
    }
    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public boolean isDisabled() {
        return MajoSpellEnchantmentConfig.maxManaDisabled.get();
    }
}