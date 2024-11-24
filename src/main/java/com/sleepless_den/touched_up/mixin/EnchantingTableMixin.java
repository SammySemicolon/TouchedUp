package com.sleepless_den.touched_up.mixin;

import com.sleepless_den.touched_up.common.block.RadialEnchantmentAmplifierBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EnchantingTableBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantingTableBlock.class)
public class EnchantingTableMixin {

    @Inject(method = "isValidBookShelf", at = @At("HEAD"), cancellable = true)
    private static void touchedUp$isValidBookShelf(Level level, BlockPos enchantingTablePos, BlockPos bookshelfPos, CallbackInfoReturnable<Boolean> cir) {
        if (RadialEnchantmentAmplifierBlock.isAmplified(level, enchantingTablePos)) {
            cir.setReturnValue(RadialEnchantmentAmplifierBlock.isValidBookshelfWhenAmplified(level, enchantingTablePos, bookshelfPos));
        }
        else {
            if (!RadialEnchantmentAmplifierBlock.correctNormalBookshelfSearch(bookshelfPos)) {
                cir.setReturnValue(false);
            }
        }
    }
}
