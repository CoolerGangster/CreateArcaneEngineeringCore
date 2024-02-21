package com.pino.cae.mixin;
import appeng.worldgen.meteorite.MeteoriteBlockPutter;
import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.MaterialColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import vazkii.quark.base.block.QuarkBlock;
import vazkii.quark.base.block.QuarkGlassBlock;
import vazkii.quark.base.module.QuarkModule;
import vazkii.quark.content.world.block.CorundumBlock;
import vazkii.quark.content.world.module.CorundumModule;
import org.slf4j.Logger;
@Mixin(value =CorundumBlock.class, remap = false)
public abstract class QuarkMixin extends CorundumBlock {

    @Shadow
    final boolean waxed;
    @Shadow
    private MeteoriteBlockPutter putter;
    @Shadow
    private BlockPos pos;

    public QuarkMixin(String regname, int color, QuarkModule module, MaterialColor materialColor, boolean waxed, boolean waxed1) {
        super(regname, color, module, materialColor, waxed);
        this.waxed = waxed1;
    }
    private static final Logger LOGGER = LogUtils.getLogger();

    @Shadow


    @Overwrite
    private boolean canGrow(Level world, BlockPos pos) {

        if(!waxed && CorundumModule.caveCrystalGrowthChance >= 1 && world.isEmptyBlock(pos.above())) {
            int i;
            LOGGER.debug("Fortnite");
            for(i = 1; world.getBlockState(pos.below(i)).getBlock() == ((CorundumBlock)(QuarkGlassBlock)(QuarkBlock)this).getBlock(); ++i);
            //return i < 4;
            return true;
        }
        LOGGER.debug("Fortnite");
        return true;

    }
}