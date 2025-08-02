package com.rinko1231.majospellenchantment.config;


import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;

public class MajoSpellEnchantmentConfig {
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static ModConfigSpec SPEC;

    public static ModConfigSpec.DoubleValue manaReaperRegenFactor;
    public static ModConfigSpec.DoubleValue manaReaperRegenCapPerLevel;
    public static ModConfigSpec.DoubleValue hopelessPowerThresholdStart;
    public static ModConfigSpec.DoubleValue hopelessPowerThresholdMax;
    public static ModConfigSpec.DoubleValue hopelessPowerBonusPerLevel;
    public static ModConfigSpec.DoubleValue oceanGraceManaReductionPerLevel;
    public static ModConfigSpec.IntValue phaseDashedEffectDuration;
    public static ModConfigSpec.DoubleValue spellStreakCDReductionPerLevel;
    public static ModConfigSpec.DoubleValue spellStreakEntityMinHealth;
    public static ModConfigSpec.BooleanValue spellStreakBlacklistOrWhitelist;
    public static ModConfigSpec.ConfigValue<List<? extends String>> spellStreakEntityBlacklist;
    public static ModConfigSpec.ConfigValue<List<? extends String>> spellStreakEntityWhitelist;
    public static ModConfigSpec.DoubleValue nocturneAriaBonusPerLevel;
    public static ModConfigSpec.DoubleValue daylightAnthemBonusPerLevel;
    public static ModConfigSpec.DoubleValue bloodManaRatioPerLevel;
    public static ModConfigSpec.DoubleValue bloodManaCapPerLevel;

    static {
        BUILDER.push("Config");

        // Mana Reaper Enchantment
        manaReaperRegenFactor = BUILDER
                .comment("[Mana Reaper] Mana restoration factor per point of target's max health")
                .defineInRange("manaReaperRegenFactor", 1.0, 0.0, 100.0);
        manaReaperRegenCapPerLevel = BUILDER
                .comment("[Mana Reaper] Maximum mana restored per enchantment level")
                .defineInRange("manaReaperRegenCapPerLevel", 40.0, 0.0, 1000.0);

        // Hopeless Power Enchantment
        hopelessPowerThresholdStart = BUILDER
                .comment("[Hopeless Power] HP% below this starts increasing spell power (0–1)")
                .defineInRange("hopelessPowerThresholdStart", 0.7, 0.0, 1.0);
        hopelessPowerThresholdMax = BUILDER
                .comment("[Hopeless Power] HP% below this reaches maximum spell power bonus (0–1)")
                .defineInRange("hopelessPowerThresholdMax", 0.3, 0.0, 1.0);
        hopelessPowerBonusPerLevel = BUILDER
                .comment("[Hopeless Power] Maximum spell power bonus per enchantment level (ADD_MULTIPLIED_BASE)")
                .defineInRange("hopelessPowerBonusPerLevel", 0.20, 0.0, 10.0);

        // Ocean Grace Enchantment
        oceanGraceManaReductionPerLevel = BUILDER
                .comment("[Ocean Grace] Mana cost reduction per enchantment level")
                .defineInRange("oceanGraceManaReductionPerLevel", 0.1, 0.0, 1);

        // Phase Dashed Enchantment
        phaseDashedEffectDuration = BUILDER
                .comment("[Phase Dashed] Effect duration in ticks after activation")
                .defineInRange("phaseDashedEffectDuration", 80, 0, 114514);

        // Spell Streak Enchantment
        spellStreakCDReductionPerLevel = BUILDER
                .comment("[Spell Streak] Cooldown reduction percentage per enchantment level (0–1)")
                .defineInRange("spellStreakCDReductionPerLevel", 0.1, 0.0, 1);
        spellStreakEntityMinHealth = BUILDER
                .comment("[Spell Streak] Minimum entity max health required to trigger cooldown reduction. ")
                .comment("Entities with max health below this threshold will not trigger the effect.")
                .defineInRange("spellStreakEntityMinHealth", 16.0, 0.0, Integer.MAX_VALUE);
        spellStreakBlacklistOrWhitelist = BUILDER
                .comment("[Spell Streak] Entity filtering mode: true = Blacklist mode (entities in blacklist are ignored), false = Whitelist mode (only entities in whitelist are valid)")
                .define("spellStreakBlacklistOrWhitelist", true);
        spellStreakEntityBlacklist = BUILDER
                .comment("[Spell Streak] Entity blacklist (entity IDs in blacklist will not trigger cooldown reduction when killed). Example: [\"minecraft:squid\", \"minecraft:cod\"]")
                .defineList("spellStreakEntityBlacklist",
                        List.of("minecraft:squid", "minecraft:cod", "minecraft:salmon", "minecraft:horse"),
                        obj -> obj instanceof String);
        spellStreakEntityWhitelist = BUILDER
                .comment("[Spell Streak] Entity whitelist (only entity IDs in whitelist will trigger cooldown reduction when killed). Example: [\"minecraft:zombie\", \"minecraft:skeleton\"]")
                .defineList("spellStreakEntityWhitelist",
                        List.of("minecraft:zombie", "minecraft:skeleton", "minecraft:player"),
                        obj -> obj instanceof String);

        // Day Or Night Enchantment
        nocturneAriaBonusPerLevel = BUILDER
                .comment("[Nocturne Aria] Cast Time Reduction per level at night with clear sky")
                .defineInRange("nocturneAriaBonusPerLevel", 0.20, 0.0, 1.0);

        daylightAnthemBonusPerLevel = BUILDER
                .comment("[Daylight Anthem] Cast Time Reduction per level during day with clear sky")
                .defineInRange("daylightAnthemBonusPerLevel", 0.20, 0.0, 1.0);
        // Blood As Mana Enchantment
        bloodManaRatioPerLevel = BUILDER
                .comment("[Blood Mana] Mana restored per damage point per level (default tuned to be ~2 Gluttony levels stronger)")
                .defineInRange("bloodManaRatioPerLevel", 3.0, 0.0, 114514);

        bloodManaCapPerLevel = BUILDER
                .comment("[Blood Mana] Max mana restored per enchantment level")
                .defineInRange("bloodManaCapPerLevel", 40.0, 0.0, 1919810);

        SPEC = BUILDER.build();
    }

}