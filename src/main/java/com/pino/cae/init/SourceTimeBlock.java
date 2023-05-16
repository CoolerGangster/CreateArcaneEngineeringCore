package com.pino.cae.init;

import com.pino.cae.init.blockEntity.ModBlockEntities;
import com.pino.cae.init.blockEntity.SourceTimeBlockEntity;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Tuple;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import java.util.*;
    public class SourceTimeBlock extends BaseEntityBlock {
        public SourceTimeBlock(Properties properties) {
            super(properties);
        }

        @Nullable
        @Override
        public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
            return ModBlockEntities.TIME_CRYSTAL_ENTITY.get().create(blockPos,blockState);
        }

        @Override
        public RenderShape getRenderShape(BlockState p_49232_) {
            return RenderShape.ENTITYBLOCK_ANIMATED;
        }


        @Nullable
        @Override
        public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
            return createTickerHelper(blockEntityType, ModBlockEntities.TIME_CRYSTAL_ENTITY.get(), (BetterBlockEntityTicker<? super SourceTimeBlockEntity>) SourceTimeBlockEntity::tick);
        }

        // @SuppressWarnings("unchecked")
        // protected static <E extends BlockEntity, A extends BlockEntity> BetterBlockEntityTicker<A> createTickerHelper(BlockEntityType<A> type, BlockEntityType<E> requiredType, BetterBlockEntityTicker<? super E> ticker) {
        //     return requiredType == type ? (BetterBlockEntityTicker<A>) ticker : null;
        // }

        @FunctionalInterface
        public interface BetterBlockEntityTicker<T extends BlockEntity> extends BlockEntityTicker<T> {
            void tick(T blockEntity, Level level, BlockPos pos, BlockState state);

            @Override
            default void tick(Level level, BlockPos pos, BlockState state, T blockEntity) {
                this.tick(blockEntity, level, pos, state);
            }
        }
    }
