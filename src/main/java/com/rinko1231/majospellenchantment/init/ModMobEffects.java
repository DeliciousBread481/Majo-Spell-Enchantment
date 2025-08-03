package com.rinko1231.majospellenchantment.init;

import com.rinko1231.majospellenchantment.effect.SundayFeverEffect;
import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.rinko1231.majospellenchantment.MajoSpellEnchantment.MOD_ID;

public class ModMobEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, MOD_ID);

    public static final DeferredHolder<MobEffect, MobEffect> PHASE_DASHED = MOB_EFFECTS.register("phase_dashed",
            () -> new MagicMobEffect(MobEffectCategory.BENEFICIAL, 0x00FFE0) // 青色粒子
                    .addAttributeModifier(Attributes.MOVEMENT_SPEED,
                            ResourceLocation.fromNamespaceAndPath(MOD_ID, "mobeffect_phase_dashed"),
                            0.15, // 默认加速倍率
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                    )
    );
    public static final DeferredHolder<MobEffect, SundayFeverEffect> SUNDAY_FEVER = MOB_EFFECTS.register("sunday_fever",
            ()-> new SundayFeverEffect(MobEffectCategory.HARMFUL, 0x8A2BE2));


    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}