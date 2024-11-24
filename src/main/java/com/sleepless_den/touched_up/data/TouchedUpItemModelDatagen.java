package com.sleepless_den.touched_up.data;

import com.sleepless_den.touched_up.TouchedUpMod;
import com.sleepless_den.touched_up.registry.TouchedUpItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import team.lodestar.lodestone.systems.datagen.ItemModelSmithTypes;
import team.lodestar.lodestone.systems.datagen.itemsmith.AbstractItemModelSmith;
import team.lodestar.lodestone.systems.datagen.itemsmith.ItemModelSmithData;
import team.lodestar.lodestone.systems.datagen.providers.LodestoneItemModelProvider;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

public class TouchedUpItemModelDatagen extends LodestoneItemModelProvider {
    public TouchedUpItemModelDatagen(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TouchedUpMod.TOUCHED_UP, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        Set<Supplier<? extends Item>> items = new HashSet<>(TouchedUpItems.ITEMS.getEntries());
        ItemModelSmithData data = new ItemModelSmithData(this, items::remove);

        items.removeIf(i -> i.get() instanceof BlockItem);
        ItemModelSmithTypes.GENERATED_ITEM.act(data, items);
    }
}
