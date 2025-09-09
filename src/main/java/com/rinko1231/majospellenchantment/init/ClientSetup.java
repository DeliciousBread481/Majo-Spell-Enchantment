package com.rinko1231.majospellenchantment.init;

import com.rinko1231.majospellenchantment.entity.DeathChainLightning;
import io.redspace.ironsspellbooks.registries.EntityRegistry;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.world.entity.EntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

import static com.rinko1231.majospellenchantment.MajoSpellEnchantment.MOD_ID;
import static com.rinko1231.majospellenchantment.init.EntityRegistry.DEATH_CHAIN_LIGHTNING;

@EventBusSubscriber(
        modid = MOD_ID,
        bus = EventBusSubscriber.Bus.MOD,
        value = {Dist.CLIENT}
)
public class ClientSetup {
    public ClientSetup() {
    }



    @SubscribeEvent
    public static void rendererRegister(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(DEATH_CHAIN_LIGHTNING.get(), NoopRenderer::new);
    }
}