package com.rinko1231.majospellenchantment.init;

import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.rinko1231.majospellenchantment.MajoSpellEnchantment.MOD_ID;
import static com.rinko1231.majospellenchantment.init.EntityRegistry.DEATH_CHAIN_LIGHTNING;

@Mod.EventBusSubscriber(
        modid = MOD_ID,
        bus = Mod.EventBusSubscriber.Bus.MOD,
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