package com.rinko1231.majospellenchantment.config;




import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

import java.util.List;

public class MajoSpellEnchantmentConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static ForgeConfigSpec SPEC;

    public static ForgeConfigSpec.BooleanValue maxManaDisabled;

    public static ForgeConfigSpec.BooleanValue cdReductionDisabled;

    public static ForgeConfigSpec.BooleanValue manaReaperDisabled;
    public static ForgeConfigSpec.DoubleValue manaReaperRegenFactor;
    public static ForgeConfigSpec.DoubleValue manaReaperRegenCapPerLevel;

    public static ForgeConfigSpec.BooleanValue hopelessPowerDisabled;
    public static ForgeConfigSpec.DoubleValue hopelessPowerThresholdStart;
    public static ForgeConfigSpec.DoubleValue hopelessPowerThresholdMax;
    public static ForgeConfigSpec.DoubleValue hopelessPowerBonusPerLevel;

    public static ForgeConfigSpec.BooleanValue oceanGraceDisabled;
    public static ForgeConfigSpec.DoubleValue oceanGraceManaReductionPerLevel;

    public static ForgeConfigSpec.BooleanValue netherHeartDisabled;

    public static ForgeConfigSpec.BooleanValue phaseDashedDisabled;
    public static ForgeConfigSpec.BooleanValue phaseDashedTreasureOnly;
    public static ForgeConfigSpec.IntValue phaseDashedEffectDuration;

    public static ForgeConfigSpec.BooleanValue spellStreakDisabled;
    public static ForgeConfigSpec.BooleanValue spellStreakTreasureOnly;
    public static ForgeConfigSpec.DoubleValue spellStreakCDReductionPerLevel;
    public static ForgeConfigSpec.DoubleValue spellStreakEntityMinHealth;
    public static ForgeConfigSpec.BooleanValue spellStreakBlacklistOrWhitelist;
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> spellStreakEntityBlacklist;
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> spellStreakEntityWhitelist;

    public static ForgeConfigSpec.BooleanValue nocturneAriaDisabled;
    public static ForgeConfigSpec.DoubleValue nocturneAriaBonusPerLevel;
    public static ForgeConfigSpec.BooleanValue daylightAnthemDisabled;
    public static ForgeConfigSpec.DoubleValue daylightAnthemBonusPerLevel;

    public static ForgeConfigSpec.BooleanValue bloodManaDisabled;
    public static ForgeConfigSpec.BooleanValue bloodManaTreasureOnly;
    public static ForgeConfigSpec.DoubleValue bloodManaRatioPerLevel;
    public static ForgeConfigSpec.DoubleValue bloodManaCapPerLevel;

    public static ForgeConfigSpec.BooleanValue partyLeaderDisabled;
    public static ForgeConfigSpec.DoubleValue partyLeaderGlowChancePerLevel;
    public static ForgeConfigSpec.DoubleValue partyLeaderFeverChancePerLevel;
    public static ForgeConfigSpec.IntValue partyLeaderEffectDuration;
    public static ForgeConfigSpec.IntValue partyLeaderEffectDurationSundayBonus;

    public static ForgeConfigSpec.BooleanValue zoophonyDisabled;

    static {
        BUILDER.comment("Majo's Spell Config");




        BUILDER.push("Vas Mana/Max Mana");

        maxManaDisabled = BUILDER
                .define("maxManaDisabled", false);


        BUILDER.pop();
        BUILDER.push("Cantus Celeris/CD Reduction");

        cdReductionDisabled = BUILDER
                .define("cdReductionDisabled", false);


        BUILDER.pop();
        BUILDER.push("Messor Vitae/Mana Reaper");
        // Mana Reaper Enchantment
        manaReaperDisabled = BUILDER
                .define("manaReaperDisabled", false);
        manaReaperRegenFactor = BUILDER
                .comment("[Messor Vitae/Mana Reaper] Mana restoration factor per point of target's max health")
                .defineInRange("manaReaperRegenFactor", 1.0, 0.0, 100.0);
        manaReaperRegenCapPerLevel = BUILDER
                .comment("[Messor Vitae/Mana Reaper] Maximum mana restored per enchantment level")
                .defineInRange("manaReaperRegenCapPerLevel", 40.0, 0.0, 1000.0);

        BUILDER.pop();

        BUILDER.push("Potentia Desperata/Hopeless Power");

        // Hopeless Power Enchantment
        hopelessPowerDisabled = BUILDER
                .define("hopelessPowerDisabled", false);
        hopelessPowerThresholdStart = BUILDER
                .comment("[Potentia Desperata/Hopeless Power] HP% below this starts increasing spell power (0–1)")
                .defineInRange("hopelessPowerThresholdStart", 0.7, 0.0, 1.0);
        hopelessPowerThresholdMax = BUILDER
                .comment("[Potentia Desperata/Hopeless Power] HP% below this reaches maximum spell power bonus (0–1)")
                .defineInRange("hopelessPowerThresholdMax", 0.3, 0.0, 1.0);
        hopelessPowerBonusPerLevel = BUILDER
                .comment("[Potentia Desperata/Hopeless Power] Maximum spell power bonus per enchantment level (ADD_MULTIPLIED_BASE)")
                .defineInRange("hopelessPowerBonusPerLevel", 0.20, 0.0, 10.0);


        BUILDER.pop();

        BUILDER.push("Gratia Oceani/Ocean Grace");

        // Ocean Grace Enchantment
        oceanGraceDisabled = BUILDER
                .define("oceanGraceDisabled", false);
        oceanGraceManaReductionPerLevel = BUILDER
                .comment("[Gratia Oceani/Ocean Grace] Mana cost reduction per enchantment level")
                .defineInRange("oceanGraceManaReductionPerLevel", 0.1, 0.0, 1);


        BUILDER.pop();

        BUILDER.push("Cor Inferni/Nether Heart");

        netherHeartDisabled = BUILDER
                .define("netherHeartDisabled", false);


        BUILDER.pop();

        BUILDER.push("Cursus Phasium/Phase Dashed");


        // Phase Dashed Enchantment
        phaseDashedDisabled = BUILDER
                .define("phaseDashedDisabled", false);
        phaseDashedTreasureOnly = BUILDER
                .define("phaseDashedTreasureOnly", true);
        phaseDashedEffectDuration = BUILDER
                .comment("[Cursus Phasium/Phase Dashed] Effect duration in ticks after activation")
                .defineInRange("phaseDashedEffectDuration", 80, 0, 114514);


        BUILDER.pop();

        BUILDER.push("Mortis Impetus/Spell Streak");

        // Spell Streak Enchantment
        spellStreakDisabled = BUILDER
                .define("spellStreakDisabled", false);
        spellStreakTreasureOnly = BUILDER
                .define("spellStreakTreasureOnly", true);
        spellStreakCDReductionPerLevel = BUILDER
                .comment("[Mortis Impetus/Spell Streak] Cooldown reduction percentage per enchantment level (0–1)")
                .defineInRange("spellStreakCDReductionPerLevel", 0.1, 0.0, 1);
        spellStreakEntityMinHealth = BUILDER
                .comment("[Mortis Impetus/Spell Streak] Minimum entity max health required to trigger cooldown reduction. ")
                .comment("Entities with max health below this threshold will not trigger the effect.")
                .defineInRange("spellStreakEntityMinHealth", 16.0, 0.0, Integer.MAX_VALUE);
        spellStreakBlacklistOrWhitelist = BUILDER
                .comment("[Mortis Impetus/Spell Streak] Entity filtering mode: true = Blacklist mode (entities in blacklist are ignored), false = Whitelist mode (only entities in whitelist are valid)")
                .define("spellStreakBlacklistOrWhitelist", true);
        spellStreakEntityBlacklist = BUILDER
                .comment("[Mortis Impetus/Spell Streak] Entity blacklist (entity IDs in blacklist will not trigger cooldown reduction when killed). Example: [\"minecraft:squid\", \"minecraft:cod\"]")
                .defineList("spellStreakEntityBlacklist",
                        List.of("minecraft:squid", "minecraft:cod", "minecraft:salmon", "minecraft:horse"),
                        obj -> obj instanceof String);
        spellStreakEntityWhitelist = BUILDER
                .comment("[Mortis Impetus/Spell Streak] Entity whitelist (only entity IDs in whitelist will trigger cooldown reduction when killed). Example: [\"minecraft:zombie\", \"minecraft:skeleton\"]")
                .defineList("spellStreakEntityWhitelist",
                        List.of("minecraft:zombie", "minecraft:skeleton", "minecraft:player"),
                        obj -> obj instanceof String);

        BUILDER.pop();

        BUILDER.push("Aria Noctis/Nocturne Aria");

        // Day Or Night Enchantment
        nocturneAriaDisabled = BUILDER
                .define("nocturneAriaDisabled", false);
        nocturneAriaBonusPerLevel = BUILDER
                .comment("[Aria Noctis/Nocturne Aria] Cast Time Reduction per level at night with clear sky")
                .defineInRange("nocturneAriaBonusPerLevel", 0.20, 0.0, 1.0);

        daylightAnthemDisabled = BUILDER
                .define("daylightAnthemDisabled", false);
        daylightAnthemBonusPerLevel = BUILDER
                .comment("[Hymnus Aurorae/Daylight Anthem] Cast Time Reduction per level during day with clear sky")
                .defineInRange("daylightAnthemBonusPerLevel", 0.20, 0.0, 1.0);


        BUILDER.pop();

        BUILDER.push("Sacramentum Sanguinis/Blood Mana");

        // Blood As Mana Enchantment

        bloodManaDisabled = BUILDER
                .define("bloodManaDisabled", false);
        bloodManaTreasureOnly = BUILDER
                .define("bloodManaTreasureOnly", true);
        bloodManaRatioPerLevel = BUILDER
                .comment("[Sacramentum Sanguinis/Blood Mana] Mana restored per damage point per level (default tuned to be ~2 Gluttony levels stronger)")
                .defineInRange("bloodManaRatioPerLevel", 3.0, 0.0, 114514);

        bloodManaCapPerLevel = BUILDER
                .comment("[Sacramentum Sanguinis/Blood Mana] Max mana restored per enchantment level")
                .defineInRange("bloodManaCapPerLevel", 40.0, 0.0, 1919810);


        BUILDER.pop();

        BUILDER.push("Dux Festivus/Party Leader");

        partyLeaderDisabled = BUILDER
                .define("partyLeaderDisabled", false);

        partyLeaderGlowChancePerLevel = BUILDER
                .comment("[Dux Festivus/Party Leader] Glow chance per enchantment level (0–1)")
                .defineInRange("partyLeaderGlowChancePerLevel", 0.2, 0.0, 1.0);

        partyLeaderFeverChancePerLevel = BUILDER
                .comment("[Dux Festivus/Party Leader] Sunday Fever chance per enchantment level (0–1)")
                .defineInRange("partyLeaderFeverChancePerLevel", 0.1, 0.0, 1.0);

        partyLeaderEffectDuration = BUILDER
                .comment("[Dux Festivus/Party Leader] Glowing/Sunday Fever effect duration (ticks)")
                .defineInRange("partyLeaderEffectDuration", 160, 0, 1919810);

        partyLeaderEffectDurationSundayBonus = BUILDER
                .comment("[Dux Festivus/Party Leader] Glowing/Sunday Fever effect duration bonus on Sunday (ticks)")
                .defineInRange("partyLeaderEffectDurationSundayBonus", 40, 0, 1919810);


        BUILDER.pop();

        BUILDER.push("Zoophony");

        // Zoophony Enchantment
        zoophonyDisabled = BUILDER
                .define("zoophonyDisabled", false);



        SPEC = BUILDER.build();
    }

    public static void setup() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, SPEC, "MajoSpellEnchantmentConfig.toml");
    }
}