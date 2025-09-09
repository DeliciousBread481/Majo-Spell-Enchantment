package com.rinko1231.majospellenchantment.event;

import com.rinko1231.majospellenchantment.config.MajoSpellEnchantmentConfig;
import io.redspace.ironsspellbooks.entity.spells.magma_ball.FireField;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.ProjectileImpactEvent;

import javax.annotation.Nullable;

import static com.rinko1231.majospellenchantment.init.EnchantmentRegistry.RED_LOTUS_ENCHANT;
import static net.minecraft.world.item.enchantment.Enchantments.FLAME;

public class RedLotusEvent {

    public static final String NBT_KEY_TRIGGERED  = "redlotus_triggered"; // 首次命中后标记


    @SubscribeEvent
    public void onProjectileImpact(ProjectileImpactEvent event) {
        if(!MajoSpellEnchantmentConfig.redLotusEnabled.get()) return;
        if (!(event.getProjectile() instanceof AbstractArrow arrow)) return;
        Level level = arrow.level();
        if (level.isClientSide) return;

        // 只处理“首次命中方块”
        HitResult hit = event.getRayTraceResult();
        if (!(hit instanceof BlockHitResult)) return;

        CompoundTag tag = arrow.getPersistentData();
        if (tag.getBoolean(NBT_KEY_TRIGGERED)) return; // 已触发过，直接退出

        // 从箭上拿到发射武器
        ItemStack weapon = arrow.getWeaponItem();
        if (weapon == null) return;
        else if(weapon.isEmpty()) return;

        // 二次校验：箭确实是“着火”的（防止中途被熄灭）
        boolean onFire = arrow.isOnFire() || arrow.getRemainingFireTicks() > 0;
        if (!onFire) return;

        // 校验 RedLotus 附魔（基于注册表 HolderLookup 的 1.21.1 写法）
        int redLotusLevel = EnchantmentHelper.getItemEnchantmentLevel(
                level.holderLookup(RED_LOTUS_ENCHANT.registryKey()).getOrThrow(RED_LOTUS_ENCHANT),
                weapon
        );
        if (redLotusLevel <= 0) return;

        // 校验原版火矢（Flame），确保是“火矢弓”
        int flameLevel = EnchantmentHelper.getItemEnchantmentLevel(
                level.holderLookup(FLAME.registryKey()).getOrThrow(FLAME),
                weapon
        );
        if (flameLevel <= 0) return;

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
}
