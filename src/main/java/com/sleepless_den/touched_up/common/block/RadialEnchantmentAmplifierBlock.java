package com.sleepless_den.touched_up.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EnchantingTableBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

public class RadialEnchantmentAmplifierBlock extends Block {

    public static final List<BlockPos> ORIGINAL_OFFSETS = new ArrayList<>(EnchantingTableBlock.BOOKSHELF_OFFSETS);
    static {
        EnchantingTableBlock.BOOKSHELF_OFFSETS =
                BlockPos.betweenClosedStream(-4, -1, -4, 4, 2, 4)
                .filter(p_341357_ -> Math.abs(p_341357_.getX()) >= 2 || Math.abs(p_341357_.getZ()) >= 2)
                .map(BlockPos::immutable)
                .toList();
    }

    public RadialEnchantmentAmplifierBlock(Properties properties) {
        super(properties);
    }

    public static boolean isAmplified(Level level, BlockPos enchantingTablePos) {
        return level.getBlockState(enchantingTablePos.below()).getBlock() instanceof RadialEnchantmentAmplifierBlock;
    }

    public static boolean isValidBookshelfWhenAmplified(Level level, BlockPos enchantingTablePos, BlockPos bookshelfPos) {
        BlockState bookshelf = level.getBlockState(enchantingTablePos.offset(bookshelfPos));
        return bookshelf.getEnchantPowerBonus(level, enchantingTablePos.offset(bookshelfPos)) != 0;
    }

    public static boolean correctNormalBookshelfSearch(BlockPos bookshelfPos) {
        return ORIGINAL_OFFSETS.contains(bookshelfPos);
    }
}
