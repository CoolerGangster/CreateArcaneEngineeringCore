package com.pino.cae.init;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class SkyBlock extends Block {

    public SkyBlock(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    public void playerWillDestroy(Level level, BlockPos blockpos, BlockState p_49854_, Player p_49855_) {
        super.playerWillDestroy(level, blockpos, p_49854_, p_49855_);

    }
}
