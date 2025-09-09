package com.rinko1231.majospellenchantment.event;


import ca.weblite.objc.Message;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.network.SyncManaPacket;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.CommonHooks;
import net.neoforged.neoforge.event.entity.player.PlayerWakeUpEvent;
import net.neoforged.neoforge.event.level.SleepFinishedTimeEvent;

import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.Objects;

public class MorningHazeEvent {
/*
    @SubscribeEvent
    public void onSleepFinished(SleepFinishedTimeEvent event) {


        ServerLevel level = (ServerLevel) event.getLevel();

        for (ServerPlayer player : level.players() ) {
            // 检查玩家头盔是否带 MORNING_HAZE 附魔
            ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);
            if (helmet.isEmpty()) return;

            int hazeLevel = helmet.getEnchantmentLevel(
                    Objects.requireNonNull(CommonHooks.resolveLookup(Registries.ENCHANTMENT))
                            .getOrThrow(EnchantmentRegistry.MORNING_HAZE_ENCHANT)
            );
            if (hazeLevel <= 0) return;

            // —— 回满魔力 —— //

            MagicData magicData = MagicData.getPlayerMagicData(player);
            AttributeInstance attr = player.getAttribute(AttributeRegistry.MAX_MANA);
            float maxMana;
            if (attr != null)
            { maxMana = (float) attr.getValue();
                magicData.setMana(maxMana);
                PacketDistributor.sendToPlayer((ServerPlayer) player, new SyncManaPacket(magicData));}

        }
            // 可选：给点视觉/音效反馈
            // level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundRegistry.MORNING_HAZE_CHIME.get(), player.getSoundSource(), 1.0F, 1.0F);
            // MagicManager.spawnParticles(level, ParticleHelper.SPARKLE, player.getX(), player.getY() + 1.2, player.getZ(), 20, 0.4, 0.6, 0.4, 0.02, false);

    }*/
}
