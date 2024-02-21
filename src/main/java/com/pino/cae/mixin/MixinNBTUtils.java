/*package com.pino.cae.mixin;

import com.mojang.logging.LogUtils;
import com.pino.cae.Cae;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.block.state.BlockState;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*
@Mixin(NbtUtils.class)
public abstract class MixinNBTUtils {
    private static final Logger LOGGER = LogUtils.getLogger();
    // Backup for if lazy DFU (or alike) is installed
    @Inject(method = "readBlockState", at = @At("HEAD"))
    private static void forceCogwheelUpdate(CompoundTag tag, CallbackInfoReturnable<BlockState> cir) {
        LOGGER.debug("why2");
        if ( tag.contains("Name", Tag.TAG_STRING)) {
            String original = tag.getString("Name");
            if (!original.startsWith("kubejs"))
                return;
            String block = Cae.fixerMap.get(original);
            if (block.equals(original) || Cae.fixerMap.get(original) == null)
                return;
            tag.putString("Name", block);
        }
    }
}*/