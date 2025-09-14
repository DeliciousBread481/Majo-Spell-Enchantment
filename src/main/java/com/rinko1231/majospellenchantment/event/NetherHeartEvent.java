package com.rinko1231.majospellenchantment.event;


import io.redspace.ironsspellbooks.api.events.ModifySpellLevelEvent;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.rinko1231.majospellenchantment.init.ModEnchantments.NETHER_HEART_ENCHANT;


public class NetherHeartEvent {
    @SubscribeEvent
    public void onModifySpellLevel(ModifySpellLevelEvent event) {
        LivingEntity caster = event.getEntity();
        if (!(caster instanceof ServerPlayer player)) return;

        // 先判断是否在下界或着火
        if (!(caster.level().dimension() == Level.NETHER || caster.isOnFire())) return;

        // 过滤寒冰法术
        AbstractSpell spell = event.getSpell();
        if (spell.getSchoolType() == SchoolRegistry.ICE.get()) return;

        // 获取附魔等级
        ItemStack spellbook = Utils.getPlayerSpellbookStack(player);
        if (spellbook == null) return;

        int netherHeartLevel = spellbook.getEnchantmentLevel(NETHER_HEART_ENCHANT.get());

        // 如果附魔等级大于 0，则增加等级
        if (netherHeartLevel > 0) {
            event.addLevels(netherHeartLevel);
        }
    }
}
