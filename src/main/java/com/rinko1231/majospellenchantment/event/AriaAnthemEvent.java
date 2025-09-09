package com.rinko1231.majospellenchantment.event;

import com.rinko1231.majospellenchantment.config.MajoSpellEnchantmentConfig;

import io.redspace.ironsspellbooks.api.events.SpellPreCastEvent;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.util.Utils;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.SubscribeEvent;


import java.util.UUID;

import static com.rinko1231.majospellenchantment.init.ModEnchantments.DAYLIGHT_ANTHEM_ENCHANT;
import static com.rinko1231.majospellenchantment.init.ModEnchantments.NOCTURNE_ARIA_ENCHANT;

public class AriaAnthemEvent {

    private static final UUID NOC_BONUS =
            UUID.fromString("026d0dea-6caf-5808-8bbc-83045094bbee");
    private static final UUID DAY_BONUS =
            UUID.fromString("f7de1e78-23f8-5923-bc38-30e6e1ea21ee");

    @SubscribeEvent
    public void onSpellPreCast(SpellPreCastEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        AttributeInstance castTimeReduction = player.getAttribute(AttributeRegistry.CAST_TIME_REDUCTION.get());
        if (castTimeReduction == null) return;

        // 移除旧 Buff，避免叠加冲突
        castTimeReduction.removeModifier(NOC_BONUS);
        castTimeReduction.removeModifier(DAY_BONUS);

        // 获取附魔等级
        ItemStack spellbook = Utils.getPlayerSpellbookStack(player);
        if (spellbook == null) return;

        int nocturneLevel = spellbook.getEnchantmentLevel(NOCTURNE_ARIA_ENCHANT.get());
        int daylightLevel = spellbook.getEnchantmentLevel(DAYLIGHT_ANTHEM_ENCHANT.get());


       // 检查环境条件
        boolean canSeeSky = player.level().canSeeSky(player.blockPosition());
        boolean isNight = player.level().isNight();
        boolean isDay = player.level().isDay();

        // 星夜咏叹调：夜晚或有遮挡
        if (nocturneLevel > 0 && (isNight || (!canSeeSky))) {
            double bonus = MajoSpellEnchantmentConfig.nocturneAriaBonusPerLevel.get() * nocturneLevel;

            castTimeReduction.addTransientModifier(new AttributeModifier(
                    NOC_BONUS, //noc
                    "nocturne_aria_bonus",
                    bonus,
                    AttributeModifier.Operation.MULTIPLY_BASE
            ));
        }

        // 晨曦颂歌：白天且无遮挡
        if (daylightLevel > 0 && (isDay && canSeeSky)) {
            double bonus = MajoSpellEnchantmentConfig.daylightAnthemBonusPerLevel.get() * daylightLevel;

            castTimeReduction.addTransientModifier(new AttributeModifier(
                    DAY_BONUS,
                    "daylight_anthem_bonus",
                    bonus,
                    AttributeModifier.Operation.MULTIPLY_BASE
            ));
        }

    }
}
