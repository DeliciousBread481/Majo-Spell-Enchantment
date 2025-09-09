package com.rinko1231.majospellenchantment.event;


import io.redspace.ironsspellbooks.api.events.SpellDamageEvent;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.damage.SpellDamageSource;
import io.redspace.ironsspellbooks.entity.spells.magic_missile.MagicMissileProjectile;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.CommonHooks;

import java.util.Objects;

import static com.rinko1231.majospellenchantment.init.EnchantmentRegistry.EXCITATIO_ARCANA_ENCHANT;

public class ExcitatioArcanaEvent {

    // 你自己的注册对象：附魔与投射物

    private static final String CD_KEY = "majospell_excitatio_arcana_cd"; // 每玩家冷却记号（持久化到玩家）

    @SubscribeEvent
    public void onSpellDamage(SpellDamageEvent event) {
        LivingEntity target = event.getEntity();
        if (target == null || target.level().isClientSide()) return;

        SpellDamageSource sds = event.getSpellDamageSource();
        if (sds == null) return;
        Entity src = sds.getEntity();
        if (!(src instanceof Player player)) return;

        // 法书与附魔等级
        ItemStack spellbook = Utils.getPlayerSpellbookStack(player);
        if (spellbook == null || spellbook.isEmpty()) return;

        int enchantmentLevel = spellbook.getEnchantmentLevel(
                Objects.requireNonNull(CommonHooks.resolveLookup(Registries.ENCHANTMENT))
                        .getOrThrow(EXCITATIO_ARCANA_ENCHANT)
        );
        if (enchantmentLevel <= 0) return;


        // 冷却：I=20s, II=16s, III=12s（tick单位）
        int cooldownSeconds = Math.max(8, 20 - 4 * (enchantmentLevel - 1));
        int cooldownTicks = cooldownSeconds * 20;

        long now = target.level().getGameTime();
        long nextAllowed = player.getPersistentData().getLong(CD_KEY);
        if (now < nextAllowed) return; // 冷却中

        // 记录冷却
        player.getPersistentData().putLong(CD_KEY, now + cooldownTicks);

        // 伤害换算（每枚 = 10% 本次事件伤害）
        float shardDamage = Math.max(1.0f, event.getAmount() * 0.10f * enchantmentLevel);

        // 受害者中心略上方作为生成点
        Vec3 origin = target.position().add(0.0, target.getBbHeight() * 0.5, 0.0);

        if (target.level() instanceof ServerLevel server) {
            spawnExcitatioMissiles(server, player, origin, shardDamage);
        }
    }

    private static void spawnExcitatioMissiles(ServerLevel level, LivingEntity owner, Vec3 origin, float damage) {
        final int count = 6;
        final double speed = 0.35;                 // 初速度（可微调）
        final float pitchUp = (float) Math.toRadians(5); // 微微上扬，让重力把它“拉”出抛物线

        for (int i = 0; i < count; i++) {
            float yaw = (float) (i * (Math.PI * 2.0 / count));

            // 基向量 (0,0,1) → 先抬头，再绕Y旋转成环
            Vec3 dir = new Vec3(0, 0, 1)
                    .xRot(pitchUp)
                    .yRot(yaw)
                    .normalize();

            MagicMissileProjectile proj = new MagicMissileProjectile(level, owner);
            proj.setNoGravity(false);                  // 受重力影响
            proj.setDamage(damage);                    // 每枚 = 10% 事件伤害
            proj.setDeltaMovement(dir.scale(speed));   // 径向射出

            // 稍向外偏半格产生
            Vec3 spawn = origin.add(dir.scale(0.25));
            // 朝向
            float yRot = (float) Math.toDegrees(yaw);
            float xRot = (float) -Math.toDegrees(pitchUp);
            proj.moveTo(spawn.x, spawn.y, spawn.z, yRot, xRot);

            level.addFreshEntity(proj);
        }
    }
}
