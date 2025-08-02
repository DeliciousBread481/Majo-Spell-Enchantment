package com.rinko1231.majospellenchantment;




import com.rinko1231.majospellenchantment.event.*;
import com.rinko1231.majospellenchantment.init.*;
import net.neoforged.bus.api.IEventBus;
import com.rinko1231.majospellenchantment.config.*;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;


@Mod(MajoSpellEnchantment.MOD_ID)
public class MajoSpellEnchantment {
    public static final String MOD_ID = "majospellenchantment";

    public MajoSpellEnchantment(IEventBus modEventBus, ModContainer modContainer) {
        //ModEnchantmentComponents.register(modEventBus);
        //itemRegistry.ITEMS.register(modEventBus);
        ModMobEffects.register(modEventBus);
        modContainer.registerConfig(ModConfig.Type.COMMON, MajoSpellEnchantmentConfig.SPEC,"MajoSpellEnchantmentConfig.toml");
        //NeoForge.EVENT_BUS.register(this);
        NeoForge.EVENT_BUS.register(new ManaReaperEvent());
        NeoForge.EVENT_BUS.register(new SpellStreakEvent());
        NeoForge.EVENT_BUS.register(new NetherHeartEvent());
        NeoForge.EVENT_BUS.register(new HopelessPowerEvent());
        NeoForge.EVENT_BUS.register(new OceanGraceEvent());
        NeoForge.EVENT_BUS.register(new PhaseDashedEvent());
        NeoForge.EVENT_BUS.register(new AriaAnthemEvent());
        NeoForge.EVENT_BUS.register(new BloodManaEvent());
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
