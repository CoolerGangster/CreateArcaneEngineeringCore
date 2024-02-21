package com.pino.cae.mixin;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
@Mixin(Object2ObjectOpenHashMap.class)
public interface Object2ObjectOpenHashMapInvoker {
    @Invoker("ensureCapacity")
    public void invokeEnsureCapacity(int capacity);
}