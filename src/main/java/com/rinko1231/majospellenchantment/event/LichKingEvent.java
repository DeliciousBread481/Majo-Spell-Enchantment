package com.rinko1231.majospellenchantment.event;
import com.rinko1231.majospellenchantment.config.MajoSpellEnchantmentConfig;
import io.redspace.ironsspellbooks.api.events.SpellSummonEvent;
import io.redspace.ironsspellbooks.capabilities.magic.SummonManager;
import io.redspace.ironsspellbooks.capabilities.magic.SummonedEntitiesCastData;
import io.redspace.ironsspellbooks.entity.mobs.SummonedSkeleton;
import io.redspace.ironsspellbooks.entity.mobs.SummonedZombie;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.CommonHooks;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

import java.util.Objects;

import static com.rinko1231.majospellenchantment.init.EnchantmentRegistry.DOMINION_HELM_ENCHANT;
import static com.rinko1231.majospellenchantment.init.EnchantmentRegistry.LICH_KING_TURNING_TAG;

public class LichKingEvent {

        @SubscribeEvent
        public void onLivingDeath(LivingDeathEvent event) {
            if(!MajoSpellEnchantmentConfig.lichKingEnabled.get()) return;
            LivingEntity dead = event.getEntity();
            if (dead.level().isClientSide()) return;

            DamageSource src = event.getSource();
            if(src.getEntity() == null) return;
            if (!(src.getEntity() instanceof Player player)) return;

            // 只接受带标签的生物
            if (!dead.getType().is(LICH_KING_TURNING_TAG)) return;

            ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);
            if (helmet == null|| helmet.isEmpty()) return;

            int enchantmentLevel = helmet.getEnchantmentLevel(
                    Objects.requireNonNull(CommonHooks.resolveLookup(Registries.ENCHANTMENT))
                            .getOrThrow(DOMINION_HELM_ENCHANT)
            );
            if (enchantmentLevel <= 0) return;

            if (!(dead.level() instanceof ServerLevel server)) return;

            if (player.getRandom().nextFloat() > MajoSpellEnchantmentConfig.lichKingUndeadSummonPossibilityPerLevel.get() * enchantmentLevel) return;

            @SuppressWarnings("removal")
            Monster summon = server.random.nextDouble() < 0.3
                    ? new SummonedSkeleton(server, player, true)
                    : new SummonedZombie(server, player, true);

            // 与你 onCast 里一致的生成流程
            DifficultyInstance diff = server.getCurrentDifficultyAt(dead.blockPosition());
            summon.finalizeSpawn(server, diff, MobSpawnType.MOB_SUMMONED, null);

            // 出生在尸体位置，朝向与玩家一致
            summon.setPos(dead.getX(), dead.getY(), dead.getZ());
            summon.setYRot(player.getYRot());
            summon.setOldPosAndRot();
            summon.setHealth(summon.getMaxHealth() * 0.5f);
            // 加入世界
            server.addFreshEntity(summon);

            // 设置召唤持续时间：3 分钟 = 3600 tick
            SummonedEntitiesCastData castData = new SummonedEntitiesCastData();
            SummonManager.initSummon(player, summon, MajoSpellEnchantmentConfig.lichKingUndeadDurationPerLevel.get() * enchantmentLevel, castData);

            // 播放音效
            server.playSound(null, dead.getX(), dead.getY(), dead.getZ(), SoundRegistry.RAISE_DEAD_FINISH.get(), player.getSoundSource(), 2.0F, 1.0F);
        }
    }
