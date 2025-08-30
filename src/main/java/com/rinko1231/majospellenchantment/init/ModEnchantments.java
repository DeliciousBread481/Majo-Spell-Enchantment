package com.rinko1231.majospellenchantment.init;

import com.rinko1231.majospellenchantment.enchantment.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.rinko1231.majospellenchantment.MajoSpellEnchantment.MOD_ID;

public class ModEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, MOD_ID);
    public static TagKey<Item> SPELLBOOK_TAG = TagKey.create(Registries.ITEM, new ResourceLocation("curios", "spellbook"));

    //1.20.1没有
    public static TagKey<Item> STAFF_TAG = TagKey.create(Registries.ITEM, new ResourceLocation("irons_spellbooks", "staff"));
    public static TagKey<Enchantment> DAY_OR_NIGHT_TAG = TagKey.create(Registries.ENCHANTMENT, new ResourceLocation("majospellenchantment", "exclusive_set/dayornight"));

    public static final RegistryObject<Enchantment>
            MAX_MANA_ENCHANT,
            CD_REDUCTION_ENCHANT,
            MANA_REAPER_ENCHANT,
            SPELL_STREAK_ENCHANT,
            NETHER_HEART_ENCHANT,
            //VAMPIRE_NIGHT_ENCHANT,

            HOPELESS_POWER_ENCHANT,
            OCEAN_GRACE_ENCHANT,
            PHASE_DASHED_ENCHANT,
            NOCTURNE_ARIA_ENCHANT,
            DAYLIGHT_ANTHEM_ENCHANT,
            BLOOD_MANA_ENCHANT,
            PARTY_LEADER_ENCHANT,
            ZOOPHONY_ENCHANT;


    static {
        MAX_MANA_ENCHANT = ENCHANTMENTS.register("max_mana_enchant", MaxMana::new);
        CD_REDUCTION_ENCHANT = ENCHANTMENTS.register("cd_reduction_enchant", CDReduction::new);
        MANA_REAPER_ENCHANT = ENCHANTMENTS.register("mana_reaper_enchant", ManaReaper::new);
        SPELL_STREAK_ENCHANT = ENCHANTMENTS.register("spell_streak_enchant", SpellStreak::new);
        NETHER_HEART_ENCHANT = ENCHANTMENTS.register("nether_heart_enchant", NetherHeart::new);

        HOPELESS_POWER_ENCHANT = ENCHANTMENTS.register("hopeless_power_enchant", HopelessPower::new);
        OCEAN_GRACE_ENCHANT = ENCHANTMENTS.register("ocean_grace_enchant", OceanGrace::new);
        PHASE_DASHED_ENCHANT = ENCHANTMENTS.register("phase_dashed_enchant", PhasedDashed::new);

        NOCTURNE_ARIA_ENCHANT = ENCHANTMENTS.register("nocturne_aria_enchant", NocturneAria::new);
        DAYLIGHT_ANTHEM_ENCHANT = ENCHANTMENTS.register("daylight_anthem_enchant", DaylightAnthem::new);

        BLOOD_MANA_ENCHANT = ENCHANTMENTS.register("blood_mana_enchant", BloodMana::new);
        PARTY_LEADER_ENCHANT = ENCHANTMENTS.register("party_leader_enchant", PartyLeader::new);
        ZOOPHONY_ENCHANT = ENCHANTMENTS.register("zoophony_enchant",Zoophony::new);

    }

    public static void register(IEventBus bus) {
        ENCHANTMENTS.register(bus);
    }
}