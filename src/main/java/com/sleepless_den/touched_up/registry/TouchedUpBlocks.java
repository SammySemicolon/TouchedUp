package com.sleepless_den.touched_up.registry;

import com.sleepless_den.touched_up.TouchedUpMod;
import com.sleepless_den.touched_up.common.block.RadialEnchantmentAmplifierBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import team.lodestar.lodestone.systems.block.LodestoneBlockProperties;

public class TouchedUpBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(BuiltInRegistries.BLOCK, TouchedUpMod.TOUCHED_UP);

    public static final DeferredHolder<Block, Block> RADIAL_ENCHANTMENT_AMPLIFIER = BLOCKS.register("radial_enchantment_amplifier", () -> new RadialEnchantmentAmplifierBlock(LodestoneBlockProperties.copy(Blocks.ENCHANTING_TABLE)));
}
