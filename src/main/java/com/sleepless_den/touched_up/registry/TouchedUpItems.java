package com.sleepless_den.touched_up.registry;

import com.sleepless_den.touched_up.TouchedUpMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import team.lodestar.lodestone.systems.item.LodestoneItemProperties;

import java.util.*;
import java.util.function.Function;

public class TouchedUpItems {

    public static final Map<ResourceKey<CreativeModeTab>, List<ResourceLocation>> TAB_SORTING = new HashMap<>();
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM, TouchedUpMod.TOUCHED_UP);

    public static Item.Properties DEFAULT_PROPERTIES() {
        return new LodestoneItemProperties(CreativeModeTabs.FUNCTIONAL_BLOCKS);
    }

    public static final DeferredHolder<Item, Item> RADIAL_ENCHANTMENT_AMPLIFIER = register("radial_enchantment_amplifier", DEFAULT_PROPERTIES(), (p) -> new BlockItem(TouchedUpBlocks.RADIAL_ENCHANTMENT_AMPLIFIER.get(), p));


    public static void populateItemGroups(BuildCreativeModeTabContentsEvent event) {
        final ResourceKey<CreativeModeTab> tabKey = event.getTabKey();
        if (TAB_SORTING.containsKey(tabKey)) {
            TAB_SORTING.get(tabKey).stream().map(BuiltInRegistries.ITEM::get)
                    .filter(Objects::nonNull)
                    .forEach(event::accept);
        }
    }

    public static <T extends Item> DeferredHolder<Item, T> register(String name, Item.Properties properties, Function<Item.Properties, T> function) {
        if (properties instanceof LodestoneItemProperties lodestoneItemProperties) {
            TAB_SORTING.computeIfAbsent(lodestoneItemProperties.tab, (key) -> new ArrayList<>()).add(TouchedUpMod.path(name));
        }
        return ITEMS.register(name, () -> function.apply(properties));
    }
}
