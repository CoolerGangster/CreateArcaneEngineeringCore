package com.pino.cae.init;

import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedShaftBlock;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class BazzarShaft extends EncasedShaftBlock {
    public BazzarShaft(Properties properties, Supplier<Block> casing) {
        super(properties, casing);
    }
}
