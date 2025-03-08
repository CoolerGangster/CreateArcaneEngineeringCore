package com.pino.cae.mixin;

import com.simibubi.create.content.legacy.ChromaticCompoundItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ChromaticCompoundItem.class)
public class ChromaticCompoundItemMixin {
    @ModifyArg(
        method = "Lcom/simibubi/create/content/legacy/ChromaticCompoundItem;onEntityItemUpdate(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/item/ItemEntity;)Z",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/core/BlockPos$MutableBlockPos;<init>(III)V"
        ),
        index = 1,
        remap = false
    )
    private int xxEpicFortniteYCoordinateNuker9001(int y)
    {
        return -1000;
    }
}
