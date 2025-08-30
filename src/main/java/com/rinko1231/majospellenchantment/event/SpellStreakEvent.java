package com.rinko1231.majospellenchantment.event;

import com.rinko1231.majospellenchantment.config.MajoSpellEnchantmentConfig;

import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.capabilities.magic.CooldownInstance;
import io.redspace.ironsspellbooks.capabilities.magic.PlayerCooldowns;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;


import java.util.HashMap;
import java.util.Map;

import static com.rinko1231.majospellenchantment.init.ModEnchantments.SPELL_STREAK_ENCHANT;

public class SpellStreakEvent {

    @SubscribeEvent
    public void onMobDeath(LivingDeathEvent event) {
        if (!(event.getSource().getEntity() instanceof ServerPlayer serverPlayer)) return;
        if (event.getEntity().getMaxHealth() < MajoSpellEnchantmentConfig.spellStreakEntityMinHealth.get()) {
            return;
        }
        ItemStack heldItem = serverPlayer.getMainHandItem();


        int enchantLevel = heldItem.getEnchantmentLevel(SPELL_STREAK_ENCHANT.get());
        if (enchantLevel <= 0) return;

       String entityIdStr = ForgeRegistries.ENTITY_TYPES.getKey(event.getEntity().getType()).toString();
        boolean useBlacklist = MajoSpellEnchantmentConfig.spellStreakBlacklistOrWhitelist.get();
        if (useBlacklist) {
            // 黑名单模式
            if (MajoSpellEnchantmentConfig.spellStreakEntityBlacklist.get().contains(entityIdStr)) {
                return;
            }
        } else {
            // 白名单模式
            if (!MajoSpellEnchantmentConfig.spellStreakEntityWhitelist.get().contains(entityIdStr)) {
                return;
            }
        }

        double reduction = enchantLevel * MajoSpellEnchantmentConfig.spellStreakCDReductionPerLevel.get();
        MagicData magicData = MagicData.getPlayerMagicData(serverPlayer);
        PlayerCooldowns cooldowns = magicData.getPlayerCooldowns();

        Map<String, CooldownInstance> cooldownMap = cooldowns.getSpellCooldowns();

        // 遍历冷却列表并重写 cooldownRemaining
        for (Map.Entry<String, CooldownInstance> entry : new HashMap<>(cooldownMap).entrySet()) {
            String spellId = entry.getKey();
            CooldownInstance oldInstance = entry.getValue();
            int remaining = oldInstance.getCooldownRemaining();

            if (remaining > 0) {
                int newRemaining = (int) Math.max(0, remaining - remaining * reduction);
                CooldownInstance newInstance = new CooldownInstance(oldInstance.getSpellCooldown(), newRemaining);
                cooldownMap.put(spellId, newInstance);
            }
        }

        cooldowns.syncToPlayer(serverPlayer);
    }
}
