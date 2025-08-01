package com.rinko1231.majospellenchantment.config;


import net.neoforged.neoforge.common.ModConfigSpec;

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

        SPEC = BUILDER.build();
    }

}
