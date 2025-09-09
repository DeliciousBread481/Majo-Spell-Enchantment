package com.rinko1231.majospellenchantment.init;

import com.rinko1231.majospellenchantment.entity.DeathChainLightning;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.rinko1231.majospellenchantment.MajoSpellEnchantment.MOD_ID;

public class EntityRegistry {
    private static final DeferredRegister<EntityType<?>> ENTITIES;

    public static final DeferredHolder<EntityType<?>, EntityType<DeathChainLightning>> DEATH_CHAIN_LIGHTNING;


    public EntityRegistry() {
    }

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }

    static {
        ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE, MOD_ID);
        DEATH_CHAIN_LIGHTNING = ENTITIES.register("death_chain_lightning", () -> EntityType.Builder.<DeathChainLightning>of(DeathChainLightning::new, MobCategory.MISC).sized(1.0F, 1.0F).clientTrackingRange(64).build(ResourceLocation.fromNamespaceAndPath(MOD_ID, "death_chain_lightning").toString()));
         }
}
