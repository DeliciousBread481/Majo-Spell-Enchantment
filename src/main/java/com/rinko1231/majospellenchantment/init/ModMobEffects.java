package com.rinko1231.majospellenchantment.init;

import com.rinko1231.majospellenchantment.effect.SundayFeverEffect;
import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;


import static com.rinko1231.majospellenchantment.MajoSpellEnchantment.MOD_ID;

public class ModMobEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, MOD_ID);

    public static final RegistryObject<MobEffect> PHASE_DASHED = MOB_EFFECTS.register("phase_dashed",
            () -> new MagicMobEffect(MobEffectCategory.BENEFICIAL, 0x00FFE0) // 青色粒子
                    .addAttributeModifier(Attributes.MOVEMENT_SPEED,
                            "12287bf3-93ea-56d9-80fd-e0c270c45ba8",
                            0.15, // 默认加速倍率
                            AttributeModifier.Operation.MULTIPLY_TOTAL
                    )
    );
    public static final RegistryObject<MobEffect> SUNDAY_FEVER = MOB_EFFECTS.register("sunday_fever",
            ()-> new SundayFeverEffect(MobEffectCategory.HARMFUL, 0x8A2BE2));


    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}