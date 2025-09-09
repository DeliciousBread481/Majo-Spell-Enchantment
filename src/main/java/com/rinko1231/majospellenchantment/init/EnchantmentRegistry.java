package com.rinko1231.majospellenchantment.init;


import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentAttributeEffect;

import static com.rinko1231.majospellenchantment.MajoSpellEnchantment.MOD_ID;

public class EnchantmentRegistry {

    public static final ResourceKey<Enchantment> MAX_MANA_ENCHANT =
            ResourceKey.create(Registries.ENCHANTMENT,
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "max_mana_enchant"));
    public static final ResourceKey<Enchantment> CD_REDUCTION_ENCHANT =
            ResourceKey.create(Registries.ENCHANTMENT,
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "cd_reduction_enchant"));
    public static final ResourceKey<Enchantment> MANA_REAPER_ENCHANT =
            ResourceKey.create(Registries.ENCHANTMENT,
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "mana_reaper_enchant"));
    public static final ResourceKey<Enchantment> SPELL_STREAK_ENCHANT =
            ResourceKey.create(Registries.ENCHANTMENT,
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "spell_streak_enchant"));
    public static final ResourceKey<Enchantment> NETHER_HEART_ENCHANT =
            ResourceKey.create(Registries.ENCHANTMENT,
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "nether_heart_enchant"));
    public static final ResourceKey<Enchantment> HOPELESS_POWER_ENCHANT =
            ResourceKey.create(Registries.ENCHANTMENT,
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "hopeless_power_enchant"));
    public static final ResourceKey<Enchantment> OCEAN_GRACE_ENCHANT =
            ResourceKey.create(Registries.ENCHANTMENT,
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "ocean_grace_enchant"));
    public static final ResourceKey<Enchantment> PHASE_DASHED_ENCHANT =
            ResourceKey.create(Registries.ENCHANTMENT,
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "phase_dashed_enchant"));
    public static final ResourceKey<Enchantment> NOCTURNE_ARIA_ENCHANT =
            ResourceKey.create(Registries.ENCHANTMENT,
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "nocturne_aria_enchant"));
    public static final ResourceKey<Enchantment> DAYLIGHT_ANTHEM_ENCHANT =
            ResourceKey.create(Registries.ENCHANTMENT,
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "daylight_anthem_enchant"));
    public static final ResourceKey<Enchantment> BLOOD_MANA_ENCHANT =
            ResourceKey.create(Registries.ENCHANTMENT,
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "blood_mana_enchant"));
    public static final ResourceKey<Enchantment> PARTY_LEADER_ENCHANT =
            ResourceKey.create(Registries.ENCHANTMENT,
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "party_leader_enchant"));
    public static final ResourceKey<Enchantment> VLAD_TEPES_ENCHANT =
            ResourceKey.create(Registries.ENCHANTMENT,
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "vlad_tepes_enchant"));
    public static final ResourceKey<Enchantment> RED_LOTUS_ENCHANT =
            ResourceKey.create(Registries.ENCHANTMENT,
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "red_lotus_enchant"));
    public static final ResourceKey<Enchantment> MANA_MENDING_ENCHANT =
            ResourceKey.create(Registries.ENCHANTMENT,
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "mana_mending_enchant"));
    public static final ResourceKey<Enchantment> GENTLE_GRAVEYARD_KEEPER_ENCHANT =
            ResourceKey.create(Registries.ENCHANTMENT,
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "gentle_graveyard_keeper_enchant"));
    public static final ResourceKey<Enchantment> EXCITATIO_ARCANA_ENCHANT =
            ResourceKey.create(Registries.ENCHANTMENT,
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "excitatio_arcana_enchant"));
    public static final ResourceKey<Enchantment> NECROVOLT_ENCHANT =
            ResourceKey.create(Registries.ENCHANTMENT,
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "necrovolt_enchant"));
    public static final ResourceKey<Enchantment> ICE_FIRE_SONG_ENCHANT =
            ResourceKey.create(Registries.ENCHANTMENT,
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "ice_fire_song_enchant"));
    public static final ResourceKey<Enchantment> DOMINION_HELM_ENCHANT =
            ResourceKey.create(Registries.ENCHANTMENT,
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "dominion_helm_enchant"));


    public static final ResourceKey<Enchantment> POWER_IS_POWER_ENCHANT =
            ResourceKey.create(Registries.ENCHANTMENT,
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "power_is_power_enchant"));

    public static final ResourceKey<Enchantment> ZOOPHONY_ENCHANT =
            ResourceKey.create(Registries.ENCHANTMENT,
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "zoophony_enchant")
    );
    public static final TagKey<Item> MANA_REAPER_COMPATIBLE_TAG =
            TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MOD_ID, "mana_reaper_compatible"));
    public static TagKey<Item> STAFF_TAG = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("irons_spellbooks", "staff"));
    public static TagKey<Item> SPELLBOOK_TAG = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("curios", "spellbook"));
    public static TagKey<Item> ARCHERY_TAG = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("majospellenchantment", "archery"));

    public static TagKey<Enchantment> DAY_OR_NIGHT_TAG = TagKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath("majospellenchantment", "exclusive_set/dayornight"));
    public static TagKey<Enchantment> TEPES_OR_LOTUS_TAG = TagKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath("majospellenchantment", "exclusive_set/tepes_or_lotus"));
    public static TagKey<Enchantment> MENDING_EXCLUSIVE_TAG = TagKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath("majospellenchantment", "exclusive_set/mending_exclusive"));
    public static TagKey<Enchantment> EXCITATIO_EXCLUSIVE_TAG = TagKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath("majospellenchantment", "exclusive_set/excitatio_exclusive"));
    public static TagKey<EntityType<?>> LICH_KING_TURNING_TAG = TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "lich_king_turning"));

    public static void register(BootstrapContext<Enchantment> context, ResourceKey<Enchantment> enchantment, Enchantment.Builder builder) {
        context.register(enchantment, builder.build(enchantment.location()));
    }

    public static void bootstrap(BootstrapContext<Enchantment> context) {
        HolderGetter<Item> items = context.lookup(Registries.ITEM);

        registerMaxMana(context, items);
        registerCD(context, items);
        registerReaper(context, items);
        registerStreak(context, items);
        registerNetherHeart(context, items);
        registerHopelessPower(context, items);
        registerOceanGrace(context, items);
        registerPhaseDashed(context, items);
        registerNocturneAria(context, items);
        registerDaylightAnthem(context, items);
        registerBloodMana(context, items);
        registerPartyLeader(context,items);
        registerVladTepes(context,items);
        registerRedLotus(context,items);
        registerManaMending(context, items);
        registerGentleGraveyardKeeper(context, items);
        registerExcitatioArcana(context, items);
        registerNecrovolt(context,items);
        registerIceFireSong(context, items);
        registerLichKing(context, items);

        registerZoophony(context,items);
        registerPowerPower(context, items);

    }

    private static void registerMaxMana(BootstrapContext<Enchantment> context, HolderGetter<Item> items) {

        Enchantment.EnchantmentDefinition definition = Enchantment.definition(
                items.getOrThrow(ItemTags.HEAD_ARMOR_ENCHANTABLE),
                8,
                5,  // 最大等级
                Enchantment.dynamicCost(1, 10),
                Enchantment.constantCost(80),
                1,
                EquipmentSlotGroup.HEAD

        );

        Enchantment.Builder builder = Enchantment.enchantment(definition)
                .withEffect(
                        EnchantmentEffectComponents.ATTRIBUTES, // 注意这里直接用原版属性组件
                        new EnchantmentAttributeEffect(
                                ResourceLocation.fromNamespaceAndPath(MOD_ID, "max_mana_bonus"),
                                AttributeRegistry.MAX_MANA, // 绑定铁魔法的 MAX_MANA
                                LevelBasedValue.perLevel(40.0F, 40),
                                AttributeModifier.Operation.ADD_VALUE
                        )
                );

        register(context, MAX_MANA_ENCHANT, builder);
    }

    private static void registerCD(BootstrapContext<Enchantment> context, HolderGetter<Item> items) {
        //HolderSet<Item> staffItems = items.getOrThrow(STAFF_TAG);
        HolderSet<Item> compatibleItems = items.getOrThrow(MANA_REAPER_COMPATIBLE_TAG);
        Enchantment.EnchantmentDefinition definition = Enchantment.definition(
                compatibleItems,
                6,
                5,  // 最大等级
                Enchantment.dynamicCost(1, 10),
                Enchantment.constantCost(80),
                1,
                EquipmentSlotGroup.MAINHAND
        );

        Enchantment.Builder builder = Enchantment.enchantment(definition)
                .withEffect(
                        EnchantmentEffectComponents.ATTRIBUTES,
                        new EnchantmentAttributeEffect(
                                ResourceLocation.fromNamespaceAndPath(MOD_ID, "cd_reduction_bonus"),
                                AttributeRegistry.COOLDOWN_REDUCTION,
                                LevelBasedValue.perLevel(0.05F, 0.05F),
                                AttributeModifier.Operation.ADD_VALUE
                        )
                );

        register(context, CD_REDUCTION_ENCHANT, builder);
    }

    private static void registerReaper(BootstrapContext<Enchantment> context, HolderGetter<Item> items) {
        //HolderSet<Item> staffItems = items.getOrThrow(STAFF_TAG);

        HolderSet<Item> compatibleItems = items.getOrThrow(MANA_REAPER_COMPATIBLE_TAG);
        Enchantment.EnchantmentDefinition definition = Enchantment.definition(
                compatibleItems,
                5, 5,  // 最大等级
                Enchantment.dynamicCost(1, 10),
                Enchantment.constantCost(80),
                1,
                EquipmentSlotGroup.MAINHAND,
                EquipmentSlotGroup.OFFHAND
        );

        Enchantment.Builder builder = Enchantment.enchantment(definition);

        register(context, MANA_REAPER_ENCHANT, builder);
    }

    private static void registerStreak(BootstrapContext<Enchantment> context, HolderGetter<Item> items) {
        //HolderSet<Item> staffItems = items.getOrThrow(STAFF_TAG);

        HolderSet<Item> compatibleItems = items.getOrThrow(MANA_REAPER_COMPATIBLE_TAG);
        Enchantment.EnchantmentDefinition definition = Enchantment.definition(
                compatibleItems,
                3, 5,  // 最大等级
                Enchantment.dynamicCost(1, 10),
                Enchantment.constantCost(80),
                1,
                EquipmentSlotGroup.MAINHAND
        );

        Enchantment.Builder builder = Enchantment.enchantment(definition);

        register(context, SPELL_STREAK_ENCHANT, builder);
    }

    private static void registerNetherHeart(BootstrapContext<Enchantment> context, HolderGetter<Item> items) {

        HolderSet<Item> compatibleItems = items.getOrThrow(SPELLBOOK_TAG);
        Enchantment.EnchantmentDefinition definition = Enchantment.definition(
                compatibleItems,
                5, 3,  // 最大等级
                Enchantment.dynamicCost(1, 10),
                Enchantment.constantCost(80),
                1
        );

        Enchantment.Builder builder = Enchantment.enchantment(definition);

        register(context, NETHER_HEART_ENCHANT, builder);
    }

    private static void registerHopelessPower(BootstrapContext<Enchantment> context, HolderGetter<Item> items) {

        HolderSet<Item> compatibleItems = items.getOrThrow(ItemTags.CHEST_ARMOR_ENCHANTABLE);
        Enchantment.EnchantmentDefinition definition = Enchantment.definition(
                compatibleItems,
                5, 3,  // 最大等级
                Enchantment.dynamicCost(1, 10),
                Enchantment.constantCost(80),
                2,
                EquipmentSlotGroup.CHEST
        );

        Enchantment.Builder builder = Enchantment.enchantment(definition);

        register(context, HOPELESS_POWER_ENCHANT, builder);
    }

    private static void registerOceanGrace(BootstrapContext<Enchantment> context, HolderGetter<Item> items) {

        HolderSet<Item> compatibleItems = items.getOrThrow(ItemTags.LEG_ARMOR_ENCHANTABLE);
        Enchantment.EnchantmentDefinition definition = Enchantment.definition(
                compatibleItems,
                8, 4,  // 最大等级
                Enchantment.dynamicCost(1, 10),
                Enchantment.constantCost(80),
                2,
                EquipmentSlotGroup.LEGS
        );

        Enchantment.Builder builder = Enchantment.enchantment(definition);

        register(context, OCEAN_GRACE_ENCHANT, builder);
    }

    private static void registerPhaseDashed(BootstrapContext<Enchantment> context, HolderGetter<Item> items) {

        HolderSet<Item> compatibleItems = items.getOrThrow(ItemTags.FOOT_ARMOR_ENCHANTABLE);
        Enchantment.EnchantmentDefinition definition = Enchantment.definition(
                compatibleItems,
                2, 3,  // 最大等级
                Enchantment.dynamicCost(1, 10),
                Enchantment.constantCost(80),
                2,
                EquipmentSlotGroup.FEET
        );

        Enchantment.Builder builder = Enchantment.enchantment(definition);

        register(context, PHASE_DASHED_ENCHANT, builder);

    }

    private static void registerNocturneAria(BootstrapContext<Enchantment> context, HolderGetter<Item> items) {
        HolderGetter<Enchantment> holdergetter1 = context.lookup(Registries.ENCHANTMENT);
        HolderSet<Item> compatibleItems = items.getOrThrow(SPELLBOOK_TAG);
        Enchantment.EnchantmentDefinition definition = Enchantment.definition(
                compatibleItems,
                5, 3,  // 最大等级
                Enchantment.dynamicCost(1, 10),
                Enchantment.constantCost(80),
                1
        );

        Enchantment.Builder builder = Enchantment.enchantment(definition).exclusiveWith(holdergetter1.getOrThrow(DAY_OR_NIGHT_TAG));

        register(context, NOCTURNE_ARIA_ENCHANT, builder);
    }

    private static void registerDaylightAnthem(BootstrapContext<Enchantment> context, HolderGetter<Item> items) {
        HolderGetter<Enchantment> holdergetter1 = context.lookup(Registries.ENCHANTMENT);
        HolderSet<Item> compatibleItems = items.getOrThrow(SPELLBOOK_TAG);
        Enchantment.EnchantmentDefinition definition = Enchantment.definition(
                compatibleItems,
                5, 3,  // 最大等级
                Enchantment.dynamicCost(1, 10),
                Enchantment.constantCost(80),
                1
        );

        Enchantment.Builder builder = Enchantment.enchantment(definition).exclusiveWith(holdergetter1.getOrThrow(DAY_OR_NIGHT_TAG));

        register(context, DAYLIGHT_ANTHEM_ENCHANT, builder);
    }

    private static void registerBloodMana(BootstrapContext<Enchantment> context, HolderGetter<Item> items) {

        HolderSet<Item> compatibleItems = items.getOrThrow(ItemTags.CHEST_ARMOR_ENCHANTABLE);
        Enchantment.EnchantmentDefinition definition = Enchantment.definition(
                compatibleItems,
                3, 3,  // 最大等级
                Enchantment.dynamicCost(1, 10),
                Enchantment.constantCost(80),
                2,
                EquipmentSlotGroup.CHEST
        );

        Enchantment.Builder builder = Enchantment.enchantment(definition);

        register(context, BLOOD_MANA_ENCHANT, builder);
    }
    private static void registerPartyLeader(BootstrapContext<Enchantment> context, HolderGetter<Item> items) {

        HolderSet<Item> compatibleItems = items.getOrThrow(STAFF_TAG);
        Enchantment.EnchantmentDefinition definition = Enchantment.definition(
                compatibleItems,
                3, 2,  // 最大等级
                Enchantment.dynamicCost(1, 10),
                Enchantment.constantCost(80),
                3
        );

        Enchantment.Builder builder = Enchantment.enchantment(definition);

        register(context, PARTY_LEADER_ENCHANT, builder);
    }
    private static void registerVladTepes(BootstrapContext<Enchantment> context, HolderGetter<Item> items) {
        HolderGetter<Enchantment> holdergetter1 = context.lookup(Registries.ENCHANTMENT);
        HolderSet<Item> compatibleItems = items.getOrThrow(ARCHERY_TAG);
        Enchantment.EnchantmentDefinition definition = Enchantment.definition(
                compatibleItems,
                2, 4,  // 最大等级
                Enchantment.dynamicCost(1, 10),
                Enchantment.constantCost(80),
                4,
                EquipmentSlotGroup.MAINHAND
        );

        Enchantment.Builder builder = Enchantment.enchantment(definition);//.exclusiveWith(holdergetter1.getOrThrow(TEPES_INFINITE_TAG));

        register(context, VLAD_TEPES_ENCHANT, builder);
    }
    private static void registerRedLotus(BootstrapContext<Enchantment> context, HolderGetter<Item> items) {
        HolderGetter<Enchantment> holdergetter1 = context.lookup(Registries.ENCHANTMENT);
        HolderSet<Item> compatibleItems = items.getOrThrow(ARCHERY_TAG);
        Enchantment.EnchantmentDefinition definition = Enchantment.definition(
                compatibleItems,
                2, 3,  // 最大等级
                Enchantment.dynamicCost(1, 10),
                Enchantment.constantCost(80),
                4,
                EquipmentSlotGroup.MAINHAND
        );

        Enchantment.Builder builder = Enchantment.enchantment(definition).exclusiveWith(holdergetter1.getOrThrow(TEPES_OR_LOTUS_TAG));

        register(context, RED_LOTUS_ENCHANT, builder);
    }
    private static void registerManaMending(BootstrapContext<Enchantment> context, HolderGetter<Item> items) {
        HolderGetter<Enchantment> holdergetter1 = context.lookup(Registries.ENCHANTMENT);
        HolderSet<Item> compatibleItems = items.getOrThrow(ItemTags.DURABILITY_ENCHANTABLE);
        Enchantment.EnchantmentDefinition definition = Enchantment.definition(
                compatibleItems,
                1, 1,  // 最大等级
                Enchantment.dynamicCost(25, 25),
                Enchantment.dynamicCost(75, 25),
                4,
                EquipmentSlotGroup.ANY
        );

        Enchantment.Builder builder = Enchantment.enchantment(definition).exclusiveWith(holdergetter1.getOrThrow(MENDING_EXCLUSIVE_TAG));

        register(context, MANA_MENDING_ENCHANT, builder);
    }
    private static void registerGentleGraveyardKeeper(BootstrapContext<Enchantment> context, HolderGetter<Item> items) {
        HolderGetter<Enchantment> holdergetter1 = context.lookup(Registries.ENCHANTMENT);
        HolderSet<Item> compatibleItems = items.getOrThrow(SPELLBOOK_TAG);
        Enchantment.EnchantmentDefinition definition = Enchantment.definition(
                compatibleItems,
                4, 3,  // 最大等级
                Enchantment.dynamicCost(25, 25),
                Enchantment.dynamicCost(75, 25),
                3
        );

        Enchantment.Builder builder = Enchantment.enchantment(definition).exclusiveWith(holdergetter1.getOrThrow(EXCITATIO_EXCLUSIVE_TAG));
        register(context, GENTLE_GRAVEYARD_KEEPER_ENCHANT, builder);
    }

    private static void registerNecrovolt(BootstrapContext<Enchantment> context, HolderGetter<Item> items) {
        HolderGetter<Enchantment> holdergetter1 = context.lookup(Registries.ENCHANTMENT);
        HolderSet<Item> compatibleItems = items.getOrThrow(ItemTags.SWORD_ENCHANTABLE);
        Enchantment.EnchantmentDefinition definition = Enchantment.definition(
                compatibleItems,
                2, 3,  // 最大等级
                Enchantment.dynamicCost(1, 10),
                Enchantment.constantCost(80),
                4,
                EquipmentSlotGroup.MAINHAND
        );

        Enchantment.Builder builder = Enchantment.enchantment(definition);//.exclusiveWith(holdergetter1.getOrThrow(TEPES_INFINITE_TAG));

        register(context, NECROVOLT_ENCHANT, builder);
    }

    private static void registerExcitatioArcana(BootstrapContext<Enchantment> context, HolderGetter<Item> items) {
        HolderGetter<Enchantment> holdergetter1 = context.lookup(Registries.ENCHANTMENT);
        HolderSet<Item> compatibleItems = items.getOrThrow(SPELLBOOK_TAG);
        Enchantment.EnchantmentDefinition definition = Enchantment.definition(
                compatibleItems,
                4, 3,  // 最大等级
                Enchantment.dynamicCost(25, 25),
                Enchantment.dynamicCost(75, 25),
                3
        );

        Enchantment.Builder builder = Enchantment.enchantment(definition).exclusiveWith(holdergetter1.getOrThrow(EXCITATIO_EXCLUSIVE_TAG));

        register(context, EXCITATIO_ARCANA_ENCHANT, builder);
    }
    private static void registerIceFireSong(BootstrapContext<Enchantment> context, HolderGetter<Item> items) {
        HolderGetter<Enchantment> holdergetter1 = context.lookup(Registries.ENCHANTMENT);
        HolderSet<Item> compatibleItems = items.getOrThrow(SPELLBOOK_TAG);
        Enchantment.EnchantmentDefinition definition = Enchantment.definition(
                compatibleItems,
                2, 3,  // 最大等级
                Enchantment.dynamicCost(25, 25),
                Enchantment.dynamicCost(75, 25),
                4
        );

        Enchantment.Builder builder = Enchantment.enchantment(definition).exclusiveWith(holdergetter1.getOrThrow(EXCITATIO_EXCLUSIVE_TAG));

        register(context, ICE_FIRE_SONG_ENCHANT, builder);
    }
    private static void registerLichKing(BootstrapContext<Enchantment> context, HolderGetter<Item> items) {

        Enchantment.EnchantmentDefinition definition = Enchantment.definition(
                items.getOrThrow(ItemTags.HEAD_ARMOR_ENCHANTABLE),
                1,
                3,  // 最大等级
                Enchantment.dynamicCost(1, 10),
                Enchantment.constantCost(80),
                4,
                EquipmentSlotGroup.HEAD

        );

        Enchantment.Builder builder = Enchantment.enchantment(definition);

        register(context, DOMINION_HELM_ENCHANT, builder);
    }


    private static void registerZoophony(BootstrapContext<Enchantment> context, HolderGetter<Item> items) {

        HolderSet<Item> compatibleItems = items.getOrThrow(ItemTags.HEAD_ARMOR_ENCHANTABLE);
        Enchantment.EnchantmentDefinition definition = Enchantment.definition(
                compatibleItems,
                1, 2,  // 最大等级
                Enchantment.dynamicCost(1, 10),
                Enchantment.constantCost(80),
                1,
                EquipmentSlotGroup.HEAD
        );

        Enchantment.Builder builder = Enchantment.enchantment(definition);

        register(context, ZOOPHONY_ENCHANT, builder);
    }

    private static void registerPowerPower(BootstrapContext<Enchantment> context, HolderGetter<Item> items) {

        Enchantment.EnchantmentDefinition definition = Enchantment.definition(
                items.getOrThrow(STAFF_TAG),
                1,
                1,  // 最大等级
                Enchantment.dynamicCost(1, 10),
                Enchantment.constantCost(80),
                1,
                EquipmentSlotGroup.HAND

        );

        Enchantment.Builder builder = Enchantment.enchantment(definition)
                .withEffect(
                        EnchantmentEffectComponents.ATTRIBUTES, // 注意这里直接用原版属性组件
                        new EnchantmentAttributeEffect(
                                ResourceLocation.fromNamespaceAndPath(MOD_ID, "power_power_mana_loss"),
                                AttributeRegistry.MAX_MANA, // 绑定铁魔法的 MAX_MANA
                                LevelBasedValue.perLevel(-1.0F, 0),
                                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                        )
                )
                .withEffect(
                        EnchantmentEffectComponents.ATTRIBUTES, // 注意这里直接用原版属性组件
                        new EnchantmentAttributeEffect(
                                ResourceLocation.fromNamespaceAndPath(MOD_ID, "power_power_spell_power_loss"),
                                AttributeRegistry.SPELL_POWER,
                                LevelBasedValue.perLevel(-2.0F, 0),
                                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                        )
                )
                .withEffect(
                        EnchantmentEffectComponents.ATTRIBUTES, // 注意这里直接用原版属性组件
                        new EnchantmentAttributeEffect(
                                ResourceLocation.fromNamespaceAndPath(MOD_ID, "power_power_attack_damage"),
                                Attributes.ATTACK_DAMAGE, // 绑定铁魔法的 MAX_MANA
                                LevelBasedValue.perLevel(7.0F, 0),
                                AttributeModifier.Operation.ADD_VALUE
                        )
                )
                .withEffect(
                        EnchantmentEffectComponents.ATTRIBUTES, // 注意这里直接用原版属性组件
                        new EnchantmentAttributeEffect(
                                ResourceLocation.fromNamespaceAndPath(MOD_ID, "power_power_armor"),
                                Attributes.ARMOR, // 绑定铁魔法的 MAX_MANA
                                LevelBasedValue.perLevel(3.0F, 0),
                                AttributeModifier.Operation.ADD_VALUE
                        )
                )
                .withEffect(
                        EnchantmentEffectComponents.ATTRIBUTES, // 注意这里直接用原版属性组件
                        new EnchantmentAttributeEffect(
                                ResourceLocation.fromNamespaceAndPath(MOD_ID, "power_power_attack_speed"),
                                Attributes.ATTACK_SPEED, // 绑定铁魔法的 MAX_MANA
                                LevelBasedValue.perLevel(0.8F, 0),
                                AttributeModifier.Operation.ADD_VALUE
                        )
                );

        register(context, POWER_IS_POWER_ENCHANT, builder);
    }

}
