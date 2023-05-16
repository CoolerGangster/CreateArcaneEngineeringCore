package com.pino.cae.init.blockEntity;

import com.pino.cae.Cae;
import com.pino.cae.init.BlockInit;
import com.stal111.forbidden_arcanus.core.init.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Cae.MOD_ID);

    public static final RegistryObject<BlockEntityType<SourceTimeBlockEntity>> TIME_CRYSTAL_ENTITY =
            BLOCK_ENTITIES.register("source_time_crystal_entity", () ->
                    BlockEntityType.Builder.of(SourceTimeBlockEntity::new,
                            BlockInit.TIME_CRYSTAL.get()).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
