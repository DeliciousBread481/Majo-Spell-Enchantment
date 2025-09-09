package com.rinko1231.majospellenchantment.event;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;


import java.util.UUID;

import static com.rinko1231.majospellenchantment.init.ModEnchantments.CD_REDUCTION_ENCHANT;

public class CDReductionEvent {

    private static final UUID CD_REDUCTION_ENCHANT_HEAD_UUID =
            UUID.fromString("556c876e-051c-533b-92ed-7c425e9e1439");
    @SubscribeEvent
    public void onItemAttributeModifiers(ItemAttributeModifierEvent event) {
        ItemStack stack = event.getItemStack();
        EquipmentSlot slot = event.getSlotType();

        // 仅在手上（主/副手）才加伤害（按需改条件）
        if (slot != EquipmentSlot.HEAD) return;

        int level = stack.getEnchantmentLevel(CD_REDUCTION_ENCHANT.get());
        if (level <= 0) return;

        // 你要加的属性：这里示例是伤害，可换成 MAX_HEALTH / ARMOR / MOVEMENT_SPEED 等
        Attribute attr = AttributeRegistry.COOLDOWN_REDUCTION.get();

        // 数值与叠加方式：ADDITION/MULTIPLY_BASE/MULTIPLY_TOTAL
        double amount = 0.05D * level;
        AttributeModifier mod = new AttributeModifier(
                CD_REDUCTION_ENCHANT_HEAD_UUID,
                "majospellenchantment:cd_reduction_head",
                amount,
                AttributeModifier.Operation.ADDITION
        );
        event.addModifier(attr, mod);

    }
}
