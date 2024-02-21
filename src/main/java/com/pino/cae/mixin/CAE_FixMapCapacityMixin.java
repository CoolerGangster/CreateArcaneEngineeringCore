package com.pino.cae.mixin;

import cofh.lib.util.flags.FlagManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FlagManager.class)
public class CAE_FixMapCapacityMixin {
    // guess who wrote it, omga wrote it
    @Inject(
            method = "<init>",
            at = @At("HEAD")
    )
    public void constructorHead(String modId, String path, CallbackInfo ci) {
        // let's just hope it works lmao
        ((Object2ObjectOpenHashMapInvoker)(CAE_FlagManagerAccessor.getFlags())).invokeEnsureCapacity(512);
    }

}