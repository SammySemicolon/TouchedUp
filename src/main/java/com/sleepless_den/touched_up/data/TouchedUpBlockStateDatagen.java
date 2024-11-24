package com.sleepless_den.touched_up.data;

import com.sleepless_den.touched_up.TouchedUpMod;
import com.sleepless_den.touched_up.registry.TouchedUpBlocks;
import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import team.lodestar.lodestone.helpers.DataHelper;
import team.lodestar.lodestone.systems.datagen.BlockStateSmithTypes;
import team.lodestar.lodestone.systems.datagen.providers.LodestoneBlockStateProvider;
import team.lodestar.lodestone.systems.datagen.providers.LodestoneItemModelProvider;
import team.lodestar.lodestone.systems.datagen.statesmith.AbstractBlockStateSmith;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

public class TouchedUpBlockStateDatagen extends LodestoneBlockStateProvider {
    public TouchedUpBlockStateDatagen(PackOutput output, ExistingFileHelper exFileHelper, LodestoneItemModelProvider itemModelProvider) {
        super(output, TouchedUpMod.TOUCHED_UP, exFileHelper, itemModelProvider);
    }

    @Override
    protected void registerStatesAndModels() {
        Set<Holder<Block>> blocks = new HashSet<>(TouchedUpBlocks.BLOCKS.getEntries());
        AbstractBlockStateSmith.StateSmithData data = new AbstractBlockStateSmith.StateSmithData(this, blocks::remove);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::simpleBlock, this::amplifierModel, TouchedUpBlocks.RADIAL_ENCHANTMENT_AMPLIFIER);
    }


    public ModelFile amplifierModel(Block block) {
        String name = getBlockName(block);
        ResourceLocation side = getBlockTexture(name + "_side");
        ResourceLocation bottom = getBlockTexture(name + "_bottom");
        ResourceLocation top = getBlockTexture(name + "_top");
        return models().cubeBottomTop(name, side, bottom, top);
    }
}
