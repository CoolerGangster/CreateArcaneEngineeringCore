package com.pino.cae.init;

import com.pino.cae.init.blockEntity.ModBlockEntities;
import com.pino.cae.init.blockEntity.SingularityE;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class SingularityB extends BaseEntityBlock {
    public SingularityB(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return ModBlockEntities.SINGULARITY_E.get().create(blockPos,blockState);
    }

    @Override
    public RenderShape getRenderShape(BlockState p_49232_) {
        return RenderShape.MODEL;
    }


    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return createTickerHelper(blockEntityType, ModBlockEntities.SINGULARITY_E.get(), (BetterBlockEntityTicker<? super SingularityE>) SingularityE::tick);
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
