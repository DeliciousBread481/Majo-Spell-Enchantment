package com.rinko1231.majospellenchantment.event;

import com.rinko1231.majospellenchantment.MajoSpellEnchantment;
import com.rinko1231.majospellenchantment.config.MajoSpellEnchantmentConfig;
import com.rinko1231.majospellenchantment.init.EnchantmentRegistry;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.capabilities.magic.CooldownInstance;
import io.redspace.ironsspellbooks.capabilities.magic.PlayerCooldowns;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

import java.util.HashMap;
import java.util.Map;

public class SpellStreakEvent {

    @SubscribeEvent
    public void onMobDeath(LivingDeathEvent event) {
        if (!(event.getSource().getEntity() instanceof ServerPlayer serverPlayer)) return;

        ItemStack heldItem = serverPlayer.getMainHandItem();
        int enchantLevel = EnchantmentHelper.getItemEnchantmentLevel(
                serverPlayer.level().holderLookup(EnchantmentRegistry.SPELL_STREAK_ENCHANT.registryKey())
                        .getOrThrow(EnchantmentRegistry.SPELL_STREAK_ENCHANT),
                heldItem
        );
        if (enchantLevel <= 0) return;
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
