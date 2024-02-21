package com.pino.cae.init.blockEntity;

import com.mojang.logging.LogUtils;
import com.pino.cae.init.BlockInit;
import com.pino.cae.init.SingularityB;
import com.pino.cae.init.SingularityVein;
import dev.architectury.platform.Mod;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jline.utils.Log;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
import java.util.Objects;

public class SingularityE extends BlockEntity  {


    Logger LOGGER = LogUtils.getLogger();
    private final ItemStackHandler itemHandler = new ItemStackHandler(1){
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    public SingularityE(BlockPos p_155229_, BlockState p_155230_) {
        super(ModBlockEntities.SINGULARITY_E.get(), p_155229_, p_155230_);
    }



    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @javax.annotation.Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps()  {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }
    boolean satisfyed = false;
    final String[] Systems = {
            "cataclysmic",
            "computational",
            "progression",
            "plants",
            "multiversal"
    };
    int stage = 0;
    String feeditem = "minecraft:stick";
    String seed = "pcb";
    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        tag.put("inventory", itemHandler.serializeNBT());
        tag.putString("FEED ME", feeditem);
        tag.putBoolean("AM I HAPPY?", satisfyed);
        tag.putInt("Cooldown", constantcooldown);
        tag.putString("SEED",seed);
        tag.putString("Stage",Systems[stage]);
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        nbt.getCompound("FEED ME");
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }
    int anothercoldown = 0;
    public BlockState BlogState(String dir,boolean top,int stager) {
        Direction direc = Direction.NORTH;
        if(Objects.equals(dir, "north")){direc = Direction.NORTH;}
        if(Objects.equals(dir, "east")){direc = Direction.EAST;}
        if(Objects.equals(dir, "south")){direc = Direction.SOUTH;}
        if(Objects.equals(dir, "west")){direc = Direction.WEST;}
        if(Objects.equals(dir, "up")){direc = Direction.UP;}
        if(Objects.equals(dir, "down")){direc = Direction.DOWN;}

        return BlockInit.SINGULARITY_VEIN.get().defaultBlockState().setValue(BlockStateProperties.FACING, direc).setValue(BlockStateProperties.HALF, top ? Half.TOP : Half.BOTTOM).setValue(SingularityVein.stage,stager);
    }
    public BlockState BlogState(String dir,int stager) {
        Direction direc = Direction.NORTH;
        if(Objects.equals(dir, "north")){direc = Direction.NORTH;}
        if(Objects.equals(dir, "east")){direc = Direction.EAST;}
        if(Objects.equals(dir, "south")){direc = Direction.SOUTH;}
        if(Objects.equals(dir, "west")){direc = Direction.WEST;}
        if(Objects.equals(dir, "up")){direc = Direction.UP;}
        if(Objects.equals(dir, "down")){direc = Direction.DOWN;}

        return BlockInit.SINGULARITY_VEIN.get().defaultBlockState().setValue(BlockStateProperties.FACING, direc).setValue(BlockStateProperties.HALF, Half.BOTTOM).setValue(SingularityVein.stage,stager);
    }
    public BlockState BlogState(String dir) {
        Direction direc = Direction.NORTH;
        if(Objects.equals(dir, "north")){direc = Direction.NORTH;}
        if(Objects.equals(dir, "east")){direc = Direction.EAST;}
        if(Objects.equals(dir, "south")){direc = Direction.SOUTH;}
        if(Objects.equals(dir, "west")){direc = Direction.WEST;}
        if(Objects.equals(dir, "up")){direc = Direction.UP;}
        if(Objects.equals(dir, "down")){direc = Direction.DOWN;}

        return BlockInit.SINGULARITY_VEIN.get().defaultBlockState().setValue(BlockStateProperties.FACING, direc).setValue(BlockStateProperties.HALF, Half.BOTTOM).setValue(SingularityVein.stage,1);
    }
    public void fortnite(String pog) {
        LOGGER.debug(pog);
    }
    boolean first = true;

    public String FaceToString(BlockState state){
        if(state.getValue(BlockStateProperties.FACING).equals(Direction.NORTH)) return "north";
        if(state.getValue(BlockStateProperties.FACING).equals(Direction.EAST)) return "east";
        if(state.getValue(BlockStateProperties.FACING).equals(Direction.SOUTH)) return  "south";
        if(state.getValue(BlockStateProperties.FACING).equals(Direction.WEST)) return  "west";
        if(state.getValue(BlockStateProperties.FACING).equals(Direction.UP)) return  "up";
        if(state.getValue(BlockStateProperties.FACING).equals(Direction.DOWN)) return  "down";
        return null;
    }
    public int getStage(BlockState state){
        return state.getValue(SingularityVein.stage);
    }
    public Vec3i getOffset(BlockState state){
        if(state.getValue(BlockStateProperties.FACING).equals(Direction.NORTH)) return new Vec3i(0,0,-1);
        if(state.getValue(BlockStateProperties.FACING).equals(Direction.EAST)) return new Vec3i(1,0,0);
        if(state.getValue(BlockStateProperties.FACING).equals(Direction.SOUTH)) return new Vec3i(0,0,1);
        if(state.getValue(BlockStateProperties.FACING).equals(Direction.WEST)) return new Vec3i(-1,0,0);
        if(state.getValue(BlockStateProperties.FACING).equals(Direction.UP)) return new Vec3i(0,1,0);
        if(state.getValue(BlockStateProperties.FACING).equals(Direction.DOWN)) return new Vec3i(0,-1,0);
        return null;
    }
    public Vec3i StrToOffset(String state){
        if(Objects.equals(state, "north")) return new Vec3i(0,0,-1);
        if(Objects.equals(state, "east")) return new Vec3i(1,0,0);
        if(Objects.equals(state, "south")) return new Vec3i(0,0,1);
        if(Objects.equals(state, "west")) return new Vec3i(-1,0,0);
        if(Objects.equals(state, "up")) return new Vec3i(0,1,0);
        if(Objects.equals(state, "down")) return new Vec3i(0,-1,0);
        return null;
    }
    Vec3i vector = new Vec3i(1,1,1);
 int funny = 10;
    int veinSizeLim = 7;
    String[] OrientPairs =
            {
                    "north south",
                    "east west",
                    "up down"
            };

    public void VeinDescend(Level level, BlockPos blockPos,int age,BlockPos origin){
        age++;
        BlockState central = level.getBlockState(blockPos);
        if (age >= veinSizeLim){

            level.setBlock(blockPos.offset(getOffset(central)),BlockInit.SINGULARITY_BLOCK.get().defaultBlockState(),0);

            level.setBlock(origin,Blocks.AIR.defaultBlockState(),0);
            /*for (int i = 0; i < 3*age; i++) {
                fortnite(level.getBlockState(blockPos.offset(getOffset(centralp).multiply(-i))).toString());
                if(level.getBlockState(blockPos.offset(getOffset(centralp).multiply(-i))).toString() == "cae:singularity_core")
                    level.setBlock(blockPos.offset(getOffset(centralp).multiply(-i)),Blocks.ACACIA_DOOR.defaultBlockState());
            }*/
            return;
        }


        if (!central.toString().contains("cae:singularity_vein")){
            return;
        }
        if(central.getValue(SingularityVein.stage) >= 5){
            /*if(getStage(central) < 10 ){
                if(funny++ > 9){
                   funny = 0;
                    //level.setBlock(blockPos,BlogState(FaceToString(level.getBlockState(blockPos)), getStage(central) + 1),0);
                }else{*/
                    VeinDescend(level,blockPos.offset(getOffset(central)),FaceToString(central),age,origin);
                }/*
            }else{
                if(OrientPairs[0].contains(FaceToString(central))){
                    VeinDescend(level,blockPos.offset(StrToOffset("east")),"east",age);
                    VeinDescend(level,blockPos.offset(StrToOffset("west")),"west",age);
                    VeinDescend(level,blockPos.offset(StrToOffset("up")),"up",age);
                    VeinDescend(level,blockPos.offset(StrToOffset("down")),"down",age);
                }
                if(OrientPairs[1].contains(FaceToString(central))){
                    VeinDescend(level,blockPos.offset(StrToOffset("north")),"north",age);
                    VeinDescend(level,blockPos.offset(StrToOffset("south")),"south",age);
                    VeinDescend(level,blockPos.offset(StrToOffset("up")),"up",age);
                    VeinDescend(level,blockPos.offset(StrToOffset("down")),"down",age);
                }
                if(OrientPairs[2].contains(FaceToString(central))){
                    VeinDescend(level,blockPos.offset(StrToOffset("east")),"east",age);
                    VeinDescend(level,blockPos.offset(StrToOffset("west")),"west",age);
                    VeinDescend(level,blockPos.offset(StrToOffset("up")),"up",age);
                    VeinDescend(level,blockPos.offset(StrToOffset("down")),"down",age);
                }
            }


        }*/else{
            level.setBlock(blockPos,BlogState(FaceToString(level.getBlockState(blockPos)), getStage(central) + 1),0);
        }


    }
    //TODO: This fails to account for 2 blocks simultaniously getting placed into a loop. Solution => 1 per tick?
    
    public void VeinDescend(Level level, BlockPos blockPos, String dir,int age,BlockPos origin){
        BlockState central = level.getBlockState(blockPos);
        if (age >= veinSizeLim){
            level.setBlock(blockPos,BlockInit.SINGULARITY_BLOCK.get().defaultBlockState(),0);
            level.setBlock(origin,Blocks.AIR.defaultBlockState(),0);
            return;
        }

        age++;
        //fortnite(String.valueOf(age));
        if (!central.toString().contains("cae:singularity_vein") ){
                level.setBlock(blockPos,BlogState(dir),0);
        }else{
            if(Objects.equals(FaceToString(central), dir)){
                VeinDescend(level,blockPos,age,origin);
            }
        }
    }
    final int constantcooldown = 4000;
    public void tick(Level level, BlockPos blockPos, BlockState blockState) {
        if (first) {
            level.setBlock(blockPos.offset(0,0,-1), BlogState("north"),0);
            level.setBlock(blockPos.offset(1,0,0), BlogState("east"),0);
            level.setBlock(blockPos.offset(0,0,1), BlogState("south"),0);
            level.setBlock(blockPos.offset(-1,0,0), BlogState("west"),0);
            level.setBlock(blockPos.offset(0,1,0), BlogState("up"),0);
            level.setBlock(blockPos.offset(0,-1,0), BlogState("down"),0);
            

            first = false;
        }
        if(!level.isClientSide){
            if(anothercoldown-- <= 0 ){
                anothercoldown = constantcooldown;
                VeinDescend(level,blockPos.offset(0,0,-1),0,blockPos);
                VeinDescend(level,blockPos.offset(1,0,0),0,blockPos);
                VeinDescend(level,blockPos.offset(0,0,1),0,blockPos);
                VeinDescend(level,blockPos.offset(-1,0,0),0,blockPos);
                VeinDescend(level,blockPos.offset(0,1,0),0,blockPos);
                VeinDescend(level,blockPos.offset(0,-1,0),0,blockPos);
            }
        }
        if(level.isClientSide){
            if(anothercoldown-- <= 0 ){
                anothercoldown = constantcooldown;
                VeinDescend(level,blockPos.offset(0,0,-1),0,blockPos);
                VeinDescend(level,blockPos.offset(1,0,0),0,blockPos);
                VeinDescend(level,blockPos.offset(0,0,1),0,blockPos);
                VeinDescend(level,blockPos.offset(-1,0,0),0,blockPos);
                VeinDescend(level,blockPos.offset(0,1,0),0,blockPos);
                VeinDescend(level,blockPos.offset(0,-1,0),0,blockPos);

            }
        }


    }
}
