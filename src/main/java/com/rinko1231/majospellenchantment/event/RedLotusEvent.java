package com.rinko1231.majospellenchantment.event;

import com.rinko1231.majospellenchantment.config.MajoSpellEnchantmentConfig;
import io.redspace.ironsspellbooks.entity.spells.magma_ball.FireField;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import javax.annotation.Nullable;



public class RedLotusEvent {
    public static final String NBT_KEY_TRIGGERED  = "redlotus_triggered"; // 首次命中后标记


    @SubscribeEvent
    public void onProjectileImpact(ProjectileImpactEvent event) {
        if(MajoSpellEnchantmentConfig.redLotusDisabled.get()) return;
        if (!(event.getProjectile() instanceof AbstractArrow arrow)) return;
        if(!hasRedLotus(arrow)) return;
        int redLotusLevel= getRedLotusLevel(arrow);
        if(redLotusLevel<=0) return;
        Level level = arrow.level();
        if (level.isClientSide) return;
        // 只处理“首次命中方块”
        HitResult hit = event.getRayTraceResult();
        if (!(hit instanceof BlockHitResult)) return;

        CompoundTag tag = arrow.getPersistentData();
        if (tag.getBoolean(NBT_KEY_TRIGGERED)) return; // 已触发过，直接退出

        // 二次校验：箭确实是“着火”的（防止中途被熄灭）
        boolean onFire = arrow.isOnFire() || arrow.getRemainingFireTicks() > 0;
        if (!onFire) return;


        // 通过全部校验：首次命中方块 → 生成火场
        Vec3 hitPos = hit.getLocation();
        spawnFireField((ServerLevel) level, arrow.getOwner(), hitPos,
                MajoSpellEnchantmentConfig.getRedLotusDuration(redLotusLevel), (float) MajoSpellEnchantmentConfig.getRedLotusRadius(redLotusLevel), MajoSpellEnchantmentConfig.getRedLotusDamage(redLotusLevel));

        // 打“已触发”标记，避免二次触发
        tag.putBoolean(NBT_KEY_TRIGGERED, true);
    }

    private static void spawnFireField(ServerLevel level, @Nullable Entity owner, Vec3 location,
                                       int durationTicks, float radius, double damage) {
        FireField fire = new FireField(level);
        fire.setOwner(owner);
        fire.setDuration(durationTicks);
        fire.setDamage((float) damage);
        fire.setRadius(radius);
        fire.setCircular();
        fire.moveTo(location);
        level.addFreshEntity(fire);
    }
    public static final String RED_LOTUS_KEY = "RED_LOTUS_ENCHANTED_ARROW";
    public static int getRedLotusLevel(AbstractArrow arrow) {
        CompoundTag tag = arrow.getPersistentData();
        return tag.contains(RED_LOTUS_KEY, Tag.TAG_INT) ? Math.max(0, tag.getInt(RED_LOTUS_KEY)) : 0;
    }
    /** 是否带有 RED_LOTUS 附魔效果 */
    public static boolean hasRedLotus(AbstractArrow arrow) {
        return getRedLotusLevel(arrow) > 0;
    }
}
