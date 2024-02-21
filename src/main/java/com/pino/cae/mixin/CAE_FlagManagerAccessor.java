package com.pino.cae.mixin;

import cofh.lib.util.flags.FlagManager;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.function.Supplier;
@Mixin(FlagManager.class)
public interface CAE_FlagManagerAccessor {
    @Accessor("FLAGS")
    public static Object2ObjectOpenHashMap<String, Supplier<Boolean>> getFlags() {
        throw new AssertionError();
    }
}