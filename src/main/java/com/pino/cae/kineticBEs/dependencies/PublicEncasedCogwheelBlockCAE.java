package com.pino.cae.kineticBEs.dependencies;

import com.pino.cae.init.RegistrateBE;
import com.simibubi.create.content.kinetics.simpleRelays.SimpleKineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogwheelBlock;

import fr.iglee42.createcasing.registries.ModBlockEntities;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class PublicEncasedCogwheelBlockCAE extends EncasedCogwheelBlock {
    public PublicEncasedCogwheelBlockCAE(BlockBehaviour.Properties properties, boolean large, Supplier<Block> casing) {
        super(properties, large, casing);
    }

    public BlockEntityType<? extends SimpleKineticBlockEntity> getBlockEntityType() {
        return this.isLargeCog() ? (BlockEntityType) ModBlockEntities.ENCASED_COGWHEEL_LARGE.get() : (BlockEntityType)ModBlockEntities.ENCASED_COGWHEEL.get();
    }

}