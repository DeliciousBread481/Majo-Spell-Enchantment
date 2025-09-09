package com.rinko1231.majospellenchantment.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import static com.rinko1231.majospellenchantment.MajoSpellEnchantment.MOD_ID;

@SuppressWarnings("removal")
public class TagsRegistry {



    public static final TagKey<Item> MANA_REAPER_COMPATIBLE_TAG;

    public TagsRegistry() {
    }


    static {

         MANA_REAPER_COMPATIBLE_TAG = TagKey.create(Registries.ITEM, new ResourceLocation(MOD_ID, "mana_reaper_compatible"));

    }
}