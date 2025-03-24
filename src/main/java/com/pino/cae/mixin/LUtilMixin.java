package com.pino.cae.mixin;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import snownee.lychee.util.LUtil;

import java.util.function.Consumer;

@Mixin(LUtil.class)
public class LUtilMixin {
    @ModifyVariable(
        method = "Lsnownee/lychee/util/LUtil;dropItemStack(Lnet/minecraft/world/level/Level;DDDLnet/minecraft/world/item/ItemStack;Ljava/util/function/Consumer;)V",
        name = "itementity",
        at = @At("STORE"),
        remap = false
    )
    private static ItemEntity unrandomizeItemPos(ItemEntity item, Level pLevel, double pX, double pY, double pZ, ItemStack pStack, Consumer<ItemEntity> extraStep) {
        item.setPos(pX, pY, pZ);
        return item;
    }
}
