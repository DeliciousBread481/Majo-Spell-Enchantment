package com.rinko1231.majospellenchantment.event;


import io.redspace.ironsspellbooks.api.events.SpellOnCastEvent;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.eventbus.api.SubscribeEvent;


import java.util.List;

import static com.rinko1231.majospellenchantment.init.ModEnchantments.ZOOPHONY_ENCHANT;

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
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        // 检查是否有诅咒
        int curseLevel = player.getItemBySlot(EquipmentSlot.HEAD)
                .getEnchantmentLevel(ZOOPHONY_ENCHANT.get());
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
