package com.rinko1231.majospellenchantment.event;

import com.rinko1231.majospellenchantment.config.MajoSpellEnchantmentConfig;
import com.rinko1231.majospellenchantment.init.EnchantmentRegistry;
import io.redspace.ironsspellbooks.api.events.SpellOnCastEvent;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.neoforged.bus.api.SubscribeEvent;

import java.util.List;

public class ZoophonyEvent {

    private static final List<SoundEvent> RANDOM_ANIMAL_SOUNDS = List.of(
            SoundEvents.CHICKEN_AMBIENT,
            SoundEvents.CHICKEN_HURT,
            SoundEvents.CHICKEN_DEATH,
            SoundEvents.COW_AMBIENT,
            SoundEvents.COW_HURT,
            SoundEvents.COW_DEATH,
            SoundEvents.PIG_DEATH,
            SoundEvents.SHEEP_AMBIENT,
            SoundEvents.SHEEP_HURT,
            SoundEvents.PARROT_IMITATE_GHAST,
            SoundEvents.LLAMA_AMBIENT,
            SoundEvents.LLAMA_ANGRY
    );

    @SubscribeEvent
    public void onSpellCast(SpellOnCastEvent event) {
        if(!MajoSpellEnchantmentConfig.zoophonyEnabled.get()) return;
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        if (player==null) return;

        // 检查是否有诅咒
        int curseLevel = player.getItemBySlot(EquipmentSlot.HEAD)
                .getEnchantmentLevel(
                        player.level().registryAccess()
                                .lookupOrThrow(Registries.ENCHANTMENT)
                                .getOrThrow(EnchantmentRegistry.ZOOPHONY_ENCHANT)
                );
        if (curseLevel <= 0) return;

        // 随机选一个动物音效
        SoundEvent randomSound = RANDOM_ANIMAL_SOUNDS.get(
                player.level().random.nextInt(RANDOM_ANIMAL_SOUNDS.size())
        );

        // 播放音效（大音量）
        player.level().playSound(
                null,
                player.blockPosition(),
                randomSound,
                SoundSource.PLAYERS,
                4.0F + curseLevel*2, // 音量随等级增加
                0.8F + player.level().random.nextFloat() * 0.4F // 音调微变
        );
    }
}
