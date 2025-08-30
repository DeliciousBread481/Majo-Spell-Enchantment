package com.rinko1231.majospellenchantment;




import com.rinko1231.majospellenchantment.event.*;
import com.rinko1231.majospellenchantment.init.*;

import com.rinko1231.majospellenchantment.config.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


@Mod(MajoSpellEnchantment.MOD_ID)
public class MajoSpellEnchantment {
    public static final String MOD_ID = "majospellenchantment";
    public MajoSpellEnchantment() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        //ModEnchantmentComponents.register(modEventBus);
        //itemRegistry.ITEMS.register(modEventBus);
        ModMobEffects.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);
        MajoSpellEnchantmentConfig.setup();
        ModEnchantments.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(new MaxManaEvent());
        MinecraftForge.EVENT_BUS.register(new CDReductionEvent());
        MinecraftForge.EVENT_BUS.register(new ManaReaperEvent());

        MinecraftForge.EVENT_BUS.register(new SpellStreakEvent());
        MinecraftForge.EVENT_BUS.register(new NetherHeartEvent());
        MinecraftForge.EVENT_BUS.register(new HopelessPowerEvent());

        MinecraftForge.EVENT_BUS.register(new OceanGraceEvent());
        MinecraftForge.EVENT_BUS.register(new PhaseDashedEvent());
        MinecraftForge.EVENT_BUS.register(new AriaAnthemEvent());

        MinecraftForge.EVENT_BUS.register(new BloodManaEvent());
        MinecraftForge.EVENT_BUS.register(new PartyLeaderEvent());
        MinecraftForge.EVENT_BUS.register(new ZoophonyEvent());

    }


/*
    public static Holder<Enchantment> getHolder(Level level, ResourceKey<Enchantment> enchantment) {
        return level.holderLookup(enchantment.registryKey()).getOrThrow(enchantment);
    }
    public static Holder<Attribute> getHolder2(Level level, ResourceKey<Attribute> attr) {
        return level.holderLookup(attr.registryKey()).getOrThrow(attr);
    }



    @SubscribeEvent
    public void onEquipmentChange(LivingEquipmentChangeEvent event) {
        LivingEntity entity = event.getEntity();

        Holder<Enchantment> holderMaxManaEnchantment = getHolder(entity.level(), EnchantmentRegistry.MAX_MANA_ENCHANT);
        Holder<Attribute> holderMaxManaAttr = getHolder2(entity.level(), AttributeRegistry.MAX_MANA.getKey());
        int level = EnchantmentHelper.getTagEnchantmentLevel(holderMaxManaEnchantment, entity.getItemBySlot(EquipmentSlot.HEAD));
        AttributeInstance manaAttr = entity.getAttribute(holderMaxManaAttr);

        if (manaAttr != null) {
            manaAttr.removeModifier(ResourceLocation.fromNamespaceAndPath(MOD_ID,"max_mana_bonus"));
            if (level > 0) {
                manaAttr.addTransientModifier(new AttributeModifier(
                        ResourceLocation.fromNamespaceAndPath(MOD_ID,"max_mana_bonus"),
                        level * MajoSpellEnchantmentConfig.maxManaBonusPerLevel.get(),
                        AttributeModifier.Operation.ADD_VALUE
                ));
            }
        }
    }*/

}
