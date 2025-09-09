package com.rinko1231.majospellenchantment.event;

import com.rinko1231.majospellenchantment.config.MajoSpellEnchantmentConfig;
import com.rinko1231.majospellenchantment.init.EnchantmentRegistry;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.network.SyncManaPacket;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.network.PacketDistributor;

public class ManaReaperEvent {

    public static Holder<Enchantment> getHolder(Level level, ResourceKey<Enchantment> enchantment) {
        return level.holderLookup(enchantment.registryKey()).getOrThrow(enchantment);
    }
    public static Holder<Attribute> getHolder2(Level level, ResourceKey<Attribute> attr) {
        return level.holderLookup(attr.registryKey()).getOrThrow(attr);
    }

    @SubscribeEvent
    public  void onMobDeath(LivingDeathEvent event) {
        if(!MajoSpellEnchantmentConfig.manaReaperEnabled.get()) return;
        if (!(event.getSource().getEntity() instanceof ServerPlayer serverPlayer)) return;

        ItemStack heldItem1 = serverPlayer.getMainHandItem();
        ItemStack heldItem2 = serverPlayer.getOffhandItem();
        int enchantLevel1 = EnchantmentHelper.getItemEnchantmentLevel(getHolder(serverPlayer.level(),EnchantmentRegistry.MANA_REAPER_ENCHANT), heldItem1);
        int enchantLevel2 = EnchantmentHelper.getItemEnchantmentLevel(getHolder(serverPlayer.level(),EnchantmentRegistry.MANA_REAPER_ENCHANT), heldItem2);
        int enchantLevel = Math.max(enchantLevel1,enchantLevel2);
        if (enchantLevel <= 0) return;


        double mobMaxHealth = event.getEntity().getMaxHealth();

        // 配置
        double factor = MajoSpellEnchantmentConfig.manaReaperRegenFactor.get();
        double capPerLevel = MajoSpellEnchantmentConfig.manaReaperRegenCapPerLevel.get();

        // 计算魔力恢复
        double manaRestore = mobMaxHealth * factor;
        double cap = capPerLevel * enchantLevel;
        manaRestore = Math.min(manaRestore, cap);

        if (manaRestore <= 0) return;

        // 应用恢复效果
        MagicData magicData = MagicData.getPlayerMagicData(serverPlayer);
        magicData.addMana((float)manaRestore);

        PacketDistributor.sendToPlayer(serverPlayer, new SyncManaPacket(magicData));
    }
    }
