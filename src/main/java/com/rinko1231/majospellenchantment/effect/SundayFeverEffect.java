package com.rinko1231.majospellenchantment.effect;

import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SundayFeverEffect extends MagicMobEffect {

    public SundayFeverEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    // 只包含乐器音效
    private static final NoteBlockInstrument[] MUSIC_INSTRUMENTS = {
            NoteBlockInstrument.HARP, NoteBlockInstrument.BASEDRUM, NoteBlockInstrument.SNARE, NoteBlockInstrument.HAT,
            NoteBlockInstrument.BASS, NoteBlockInstrument.FLUTE, NoteBlockInstrument.BELL, NoteBlockInstrument.GUITAR,
            NoteBlockInstrument.CHIME, NoteBlockInstrument.XYLOPHONE, NoteBlockInstrument.IRON_XYLOPHONE,
            NoteBlockInstrument.COW_BELL, NoteBlockInstrument.DIDGERIDOO, NoteBlockInstrument.BIT,
            NoteBlockInstrument.BANJO, NoteBlockInstrument.PLING
    };

    // 预定义几个短旋律（半音阶，0 表示中央 C，单位为半音偏移）
    private static final int[][] RIFF_PATTERNS = {
            {0, 4, 7, 12},        // 大三和弦上行
            {0, 3, 7, 10},        // 小七和弦
            {0, 5, 7, 12},        // 五声音阶律动
            {0, 2, 4, 5, 7, 9}    // 六音阶快速音阶
    };

    // 用于跟踪每个生物当前选择的旋律与进度
    private static final Map<UUID, int[]> activeRiffs = new HashMap<>();
    private static final Map<UUID, Integer> riffIndexMap = new HashMap<>();

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % 6 == 0; // 每 0.3 秒触发一次
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (!entity.level().isClientSide) {
            UUID id = entity.getUUID();
            RandomSource random = entity.getRandom();

            // 如果这个实体还没有旋律，则随机选择一个
            activeRiffs.computeIfAbsent(id, uuid -> RIFF_PATTERNS[random.nextInt(RIFF_PATTERNS.length)]);
            riffIndexMap.putIfAbsent(id, 0);

            // 获取当前旋律与当前音符
            int[] riff = activeRiffs.get(id);
            int riffIndex = riffIndexMap.get(id);
            int note = riff[riffIndex];
            riffIndexMap.put(id, (riffIndex + 1) % riff.length); // 下一个音符

            // 计算音高
            float pitch = (float) Math.pow(2.0, note / 12.0);

            //  根据音高决定抖动强度
            double baseShake = 0.1 + Math.abs(note) * 0.05; // 音高越极端，抖动越强
            double verticalShake = (note > 0) ? baseShake * 1.5 : baseShake * 0.5; // 高音多竖向

            //  随机转向（与音调同步）
            float minYawChange = 70f;
            float maxYawChange = 180f;
            float yawOffset = (minYawChange + random.nextFloat() * (maxYawChange - minYawChange))
                    * (random.nextBoolean() ? 1 : -1);

            entity.setYRot(entity.getYRot() + yawOffset);
            entity.setYBodyRot(entity.getYRot());
            entity.setYHeadRot(entity.getYRot());
            // 应用抖动动量
            double dx = (random.nextDouble() - 0.5) * baseShake;
            double dy = (random.nextDouble() - 0.5) * verticalShake * 0.8;
            double dz = (random.nextDouble() - 0.5) * baseShake;
            entity.setDeltaMovement(entity.getDeltaMovement().add(dx, dy, dz));

            // 随机乐器
            NoteBlockInstrument instrument = MUSIC_INSTRUMENTS[random.nextInt(MUSIC_INSTRUMENTS.length)];

            // 播放音效
            entity.level().playSound(
                    null,
                    entity.getX(), entity.getY(), entity.getZ(),
                    instrument.getSoundEvent().value(),
                    entity.getSoundSource(),
                    0.08f,
                    pitch
            );

            // 烟花粒子
            if (random.nextFloat() < 0.4f) {
                ((ServerLevel) entity.level()).sendParticles(
                        ParticleTypes.FIREWORK,
                        entity.getX(),
                        entity.getY() + 1.0,
                        entity.getZ(),
                        2 + random.nextInt(4),
                        0.2, 0.4, 0.2,
                        0.05
                );
            }
        }

    }

    @Override
    public void removeAttributeModifiers(@NotNull LivingEntity entity, @NotNull AttributeMap attributes, int amplifier) {
        // 移除旋律记录，避免内存占用
        UUID id = entity.getUUID();
        activeRiffs.remove(id);
        riffIndexMap.remove(id);
    }
}
