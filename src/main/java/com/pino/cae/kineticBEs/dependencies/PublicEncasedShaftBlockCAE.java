package com.pino.cae.kineticBEs.dependencies;

import com.pino.cae.init.RegistrateBE;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedShaftBlock;

import fr.iglee42.createcasing.registries.ModBlockEntities;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class PublicEncasedShaftBlockCAE extends EncasedShaftBlock {
    public PublicEncasedShaftBlockCAE(BlockBehaviour.Properties properties, Supplier<Block> casing) {
        super(properties, casing);
    }

    public BlockEntityType<? extends KineticBlockEntity> getBlockEntityType() {
        return (BlockEntityType) ModBlockEntities.ENCASED_SHAFT.get();
    }
}
