package com.rinko1231.majospellenchantment.event;

import com.rinko1231.majospellenchantment.init.ModEnchantments;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import static com.rinko1231.majospellenchantment.MajoSpellEnchantment.MOD_ID;
import static com.rinko1231.majospellenchantment.init.ModEnchantments.MAX_MANA_ENCHANT;

// ModAttributeHandler.java


public class MaxManaEvent {

    // 用你那串固定 UUID（或按“附魔ID+槽位”派生一个）
    private static final UUID MAX_MANA_ENCHANT_HEAD_UUID =
            UUID.fromString("02cdf45f-e5be-50d9-8935-53f146ac0847");

    @SubscribeEvent
    public void onItemAttributeModifiers(ItemAttributeModifierEvent event) {
        ItemStack stack = event.getItemStack();
        if (event.getSlotType() != EquipmentSlot.HEAD) return;

        int level = stack.getEnchantmentLevel(ModEnchantments.MAX_MANA_ENCHANT.get());
        if (level <= 0) return;

        Attribute attr = AttributeRegistry.MAX_MANA.get();
        double amount = 40.0D * level;

        // 关键：使用【带 UUID 的】构造器，UUID 要稳定
        AttributeModifier mod = new AttributeModifier(
                MAX_MANA_ENCHANT_HEAD_UUID,
                "majospellenchantment:max_mana_enchant_head",
                amount,
                AttributeModifier.Operation.ADDITION
        );

        event.addModifier(attr, mod);
    }
}
