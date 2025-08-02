package com.rinko1231.majospellenchantment.event;

import com.rinko1231.majospellenchantment.config.MajoSpellEnchantmentConfig;
import com.rinko1231.majospellenchantment.init.EnchantmentRegistry;
import io.redspace.ironsspellbooks.api.events.SpellPreCastEvent;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.CommonHooks;

import static com.rinko1231.majospellenchantment.MajoSpellEnchantment.MOD_ID;

public class AriaAnthemEvent {

    @SubscribeEvent
    public void onSpellPreCast(SpellPreCastEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        AttributeInstance castTimeReduction = player.getAttribute(AttributeRegistry.CAST_TIME_REDUCTION);
        if (castTimeReduction == null) return;

        // 移除旧 Buff，避免叠加冲突
        castTimeReduction.removeModifier(ResourceLocation.fromNamespaceAndPath(MOD_ID, "nocturne_aria_bonus"));
        castTimeReduction.removeModifier(ResourceLocation.fromNamespaceAndPath(MOD_ID, "daylight_anthem_bonus"));

        // 获取附魔等级
        ItemStack spellbook = Utils.getPlayerSpellbookStack(player);
        if (spellbook == null) return;

        int nocturneLevel = spellbook.getEnchantmentLevel(
                CommonHooks.resolveLookup(Registries.ENCHANTMENT)
                        .getOrThrow(EnchantmentRegistry.NOCTURNE_ARIA_ENCHANT)
        );
        int daylightLevel = spellbook.getEnchantmentLevel(
                CommonHooks.resolveLookup(Registries.ENCHANTMENT)
                        .getOrThrow(EnchantmentRegistry.DAYLIGHT_ANTHEM_ENCHANT)
        );

       // 检查环境条件
        boolean canSeeSky = player.level().canSeeSky(player.blockPosition());
        boolean isNight = player.level().isNight();
        boolean isDay = player.level().isDay();

        // 星夜咏叹调：夜晚或有遮挡
        if (nocturneLevel > 0 && (isNight || (!canSeeSky))) {
            double bonus = MajoSpellEnchantmentConfig.nocturneAriaBonusPerLevel.get() * nocturneLevel;

            castTimeReduction.addTransientModifier(new AttributeModifier(
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "nocturne_aria_bonus"),
                    bonus,
                    AttributeModifier.Operation.ADD_MULTIPLIED_BASE
            ));
        }

        // 晨曦颂歌：白天且无遮挡
        if (daylightLevel > 0 && (isDay && canSeeSky)) {
            double bonus = MajoSpellEnchantmentConfig.daylightAnthemBonusPerLevel.get() * daylightLevel;

            castTimeReduction.addTransientModifier(new AttributeModifier(
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "daylight_anthem_bonus"),
                    bonus,
                    AttributeModifier.Operation.ADD_MULTIPLIED_BASE
            ));
        }

    }
}
