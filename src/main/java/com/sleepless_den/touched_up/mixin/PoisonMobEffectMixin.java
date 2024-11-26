package com.sleepless_den.touched_up.mixin;

import net.minecraft.world.effect.PoisonMobEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PoisonMobEffect.class)
public class PoisonMobEffectMixin {

    @Inject(method = "shouldApplyEffectTickThisTick", at = @At("HEAD"), cancellable = true)
    private void touchedUp$modifyPoisonDamageFrequency(int duration, int amplifier, CallbackInfoReturnable<Boolean> cir) {
        int i = 40 >> amplifier;
        cir.setReturnValue(i == 0 || duration % i == 0);
    }
    @ModifyArg(method = "applyEffectTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z"))
    private float touchedUp$modifyPoisonDamage(float amount) {
        return amount * 2f;
    }
}
