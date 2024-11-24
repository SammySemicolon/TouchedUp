package com.sleepless_den.touched_up;

import com.sleepless_den.touched_up.registry.TouchedUpItems;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

import static com.sleepless_den.touched_up.registry.TouchedUpBlocks.BLOCKS;
import static com.sleepless_den.touched_up.registry.TouchedUpItems.ITEMS;

@Mod(TouchedUpMod.TOUCHED_UP)
public class TouchedUpMod
{
    public static final String TOUCHED_UP = "touched_up";
    private static final Logger LOGGER = LogUtils.getLogger();

    public TouchedUpMod(IEventBus modEventBus, ModContainer modContainer) {

        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        modEventBus.addListener(TouchedUpItems::populateItemGroups);
    }

    public static ResourceLocation path(String path) {
        return ResourceLocation.fromNamespaceAndPath(TOUCHED_UP, path);
    }
}
