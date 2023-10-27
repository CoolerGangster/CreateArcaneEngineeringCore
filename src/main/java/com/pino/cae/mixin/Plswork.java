/*package com.pino.cae.mixin;

import com.mojang.logging.LogUtils;
import com.pino.cae.Cae;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;

import net.minecraft.world.item.ItemStack;

import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;

import org.spongepowered.asm.mixin.injection.At;

import org.spongepowered.asm.mixin.injection.ModifyVariable;


@Mixin(ItemStack.class)
public class Plswork {
    private static final Logger LOGGER = LogUtils.getLogger();

    @ModifyVariable(method = "of", at = @At("HEAD"), argsOnly = true)
    private static CompoundTag modifyTag(CompoundTag value) {
        LOGGER.debug("why");
        if (value.contains("id", Tag.TAG_STRING)) {
            String item = value.getString("id");
            if (!item.startsWith("kubejs")) return value;
            String fixed = com.pino.cae.Cae.fixerMap.get(item);
            LOGGER.debug(fixed);
            value.putString("id", fixed);
        }
        return value;
    }
}*/