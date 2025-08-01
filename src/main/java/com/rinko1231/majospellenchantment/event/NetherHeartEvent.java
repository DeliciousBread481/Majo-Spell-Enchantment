package com.rinko1231.majospellenchantment.event;

import com.rinko1231.majospellenchantment.init.EnchantmentRegistry;
import io.redspace.ironsspellbooks.api.events.ModifySpellLevelEvent;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.CommonHooks;

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

        int netherHeartLevel = spellbook.getEnchantmentLevel(
                CommonHooks.resolveLookup(Registries.ENCHANTMENT)
                        .getOrThrow(EnchantmentRegistry.NETHER_HEART_ENCHANT)
        );

        // 如果附魔等级大于 0，则增加等级
        if (netherHeartLevel > 0) {
            event.addLevels(netherHeartLevel);
        }
    }
}
