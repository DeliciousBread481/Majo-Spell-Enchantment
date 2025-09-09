package com.rinko1231.majospellenchantment.event;

import com.rinko1231.majospellenchantment.config.MajoSpellEnchantmentConfig;
import com.rinko1231.majospellenchantment.entity.DeathChainLightning;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.damage.DamageSources;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;
import java.util.Objects;

import static com.rinko1231.majospellenchantment.init.ModEnchantments.NECROVOLT_ENCHANT;

public class NecrovoltEvent {

    @SubscribeEvent
    public void onLivingDeath(LivingDeathEvent event) {
        if(MajoSpellEnchantmentConfig.NecrovoltDisabled.get()) return;
        LivingEntity dead = event.getEntity();
        if (dead.level().isClientSide()) return;

        DamageSource source = event.getSource();
        Entity trueAttacker = source.getEntity(); // 或 getDirectEntity / getOwner 视 API 而定
        if (!(trueAttacker instanceof Player player)) return;
        if(source.getDirectEntity() instanceof DeathChainLightning) return;

        // 检查玩家“手持武器”是否带 DeathChain
        ItemStack weapon = player.getMainHandItem().isEmpty() ? player.getOffhandItem() : player.getMainHandItem();
        if (weapon.isEmpty()) return;

        int enchantmentLevel =weapon.getEnchantmentLevel(NECROVOLT_ENCHANT.get());
        if (enchantmentLevel <= 0) return;


        // —— 预检测：范围内是否存在可命中的敌人 —— //
        Level levelObj = dead.level();
        double range = MajoSpellEnchantmentConfig.getNecrovoltRadius(enchantmentLevel);// 与链闪基础半径一致
        List<LivingEntity> nearby = levelObj.getEntitiesOfClass(
                LivingEntity.class,
                dead.getBoundingBox().inflate(range),
                e -> e != player && e != dead && e.isAlive() && !DamageSources.isFriendlyFireBetween(e, player)
        );
        if (nearby.isEmpty()) return; // 没有敌人就不生成
        AttributeInstance attr = player.getAttribute(AttributeRegistry.LIGHTNING_SPELL_POWER.get());
        double lightningPower =0;
        if(attr != null)
            lightningPower = attr.getValue();
        // 生成“死亡连锁”投射物
        DeathChainLightning proj = new DeathChainLightning(levelObj, player, dead)
                .asDeathChain(
                        /* baseConnections */ 2, // 初始可传次数，可按附魔等级调整
                        /* baseRange      */ (float)range,
                        /* extraDamage    */ 0.0F,                     // 初始额外伤害
                        /* minLingerTicks */ 10,                         // 至少 1 秒滞留
                        /* bonusDamageGrowth*/ enchantmentLevel * MajoSpellEnchantmentConfig.NecrovoltBonusDamageGrowthPerLevel.get().floatValue()
                );
        // 你也可以给它设置基础 spell 伤害：proj.damage = 基础值
        proj.setDamage((float)(MajoSpellEnchantmentConfig.getNecrovoltRawDamage(enchantmentLevel)+MajoSpellEnchantmentConfig.NecrovoltLightningSpellPowerScaling.get() * lightningPower)) ; // 示例：初始随等级略增

        proj.setPos(dead.getX(), dead.getY() + dead.getBbHeight()/2.0F, dead.getZ());
        levelObj.addFreshEntity(proj);
    }



    @SubscribeEvent
    public void onLivingDeath2(LivingDeathEvent event) {
        if(MajoSpellEnchantmentConfig.NecrovoltDisabled.get()) return;
        LivingEntity dead = event.getEntity();
        DamageSource src = event.getSource();

        if (src.getDirectEntity() instanceof DeathChainLightning lightning) {
            lightning.onKill(dead);
        }
    }


}
