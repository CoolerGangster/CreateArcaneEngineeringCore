package com.pino.cae.kineticBEs.dependencies;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//



import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedShaftBlock;
import fr.iglee42.createcasing.registries.ModBlockEntities;
import java.util.function.Supplier;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public class CustomEncasedShaftCAE extends EncasedShaftBlock {
    private Supplier<Block> shaft;

    public CustomEncasedShaftCAE(BlockBehaviour.Properties properties, Supplier<Block> casing, Supplier<? extends Block> shaft) {
        super(properties, casing);
        this.shaft = ((Block)shaft.get()).delegate;
    }

    public BlockEntityType<? extends KineticBlockEntity> getBlockEntityType() {
        return (BlockEntityType)ModBlockEntities.CUSTOM_ENCASED_SHAFT.get();
    }

    public Supplier<Block> getShaft() {
        return this.shaft;
    }

    public InteractionResult onSneakWrenched(BlockState state, UseOnContext context) {
        if (context.getLevel().isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            context.getLevel().levelEvent(2001, context.getClickedPos(), Block.getId(state));
            KineticBlockEntity.switchToBlockState(context.getLevel(), context.getClickedPos(), (BlockState)((Block)this.shaft.get()).defaultBlockState().setValue(AXIS, (Direction.Axis)state.getValue(AXIS)));
            return InteractionResult.SUCCESS;
        }
    }

    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter world, BlockPos pos, Player player) {
        if (target instanceof BlockHitResult) {
            return ((BlockHitResult)target).getDirection().getAxis() == this.getRotationAxis(state) ? new ItemStack((ItemLike)this.shaft.get()) : this.getCasing().asItem().getDefaultInstance();
        } else {
            return super.getCloneItemStack(state, target, world, pos, player);
        }
    }
}
