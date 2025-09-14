package com.rinko1231.majospellenchantment.init;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public abstract class BaseEnchantment extends Enchantment {
    public BaseEnchantment(Rarity rarity, EnchantmentCategory category, EquipmentSlot[] applicableSlots) {
        super(rarity, category, applicableSlots);
    }

    public abstract boolean isDisabled();

    @Override
    protected boolean checkCompatibility(Enchantment other) {
        return super.checkCompatibility(other) && !isDisabled();
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        return super.canEnchant(stack) && !isDisabled();
    }

    @Override
    public boolean isTradeable() {
        return super.isTradeable() && !isDisabled();
    }

    @Override
    public boolean isDiscoverable() {
        return super.isDiscoverable() && !isDisabled();
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return super.canApplyAtEnchantingTable(stack) && !isDisabled();
    }

    @Override
    public boolean isAllowedOnBooks() {
        return super.isAllowedOnBooks() && !isDisabled();
    }

    @Override
    public void doPostAttack(LivingEntity attacker, Entity target, int level) {
        if (isDisabled()) this.getSlotItems(attacker).forEach((equipmentSlot, stack) -> removeEnchantment(stack, this));
        super.doPostAttack(attacker, target, level);
    }

    @Override
    public void doPostHurt(LivingEntity target, Entity attacker, int level) {
        if (isDisabled()) this.getSlotItems(target).forEach((equipmentSlot, stack) -> removeEnchantment(stack, this));
        super.doPostHurt(target, attacker, level);
    }

    public static void removeEnchantment(@NotNull ItemStack stack, Enchantment enchantment) {
        if (stack.isEmpty() || !stack.hasTag()) return;

        ResourceLocation targetId = ForgeRegistries.ENCHANTMENTS.getKey(enchantment);
        if (targetId == null) return;

        String tagId = "Enchantments";
        if (stack.getItem().equals(Items.ENCHANTED_BOOK)) tagId = "StoredEnchantments";

        CompoundTag nbt = stack.getOrCreateTag();
        ListTag enchantmentList = nbt.getList(tagId, 10);

        ListTag newList = new ListTag();
        boolean modified = false;

        for (int i = 0; i < enchantmentList.size(); i++) {
            CompoundTag enchantmentTag = enchantmentList.getCompound(i);
            ResourceLocation id = ResourceLocation.tryParse(enchantmentTag.getString("id"));

            if (id == null || !id.equals(targetId)) {
                newList.add(enchantmentTag);
            } else {
                modified = true;
            }
        }

        if (modified) {
            if (newList.isEmpty()) {
                nbt.remove(tagId);
            } else {
                nbt.put(tagId, newList);
            }

            if (nbt.isEmpty()) stack.setTag(null);
        }
    }

    public static boolean canUseAsWeapon(Item item) {
        return item instanceof AxeItem
                || item instanceof SwordItem
                || item instanceof BowItem
                || item instanceof TridentItem;
    }
}