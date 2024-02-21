package com.pino.cae.init;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.dimension.DimensionType;

import java.util.Properties;

public class AmongPortal extends Block {


    public AmongPortal(Properties p_54909_) {
        super(p_54909_);
    }
    @Override
    public void entityInside(BlockState status, Level levelus, BlockPos positus, Entity entitus) {
        if(entitus instanceof Player){
            ResourceKey<Level> resourcekey = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation("cae:void"));
            MinecraftServer minecraftserver = levelus.getServer();
            assert minecraftserver != null;
            ServerLevel serverlevel1 = minecraftserver.getLevel(resourcekey);
            assert serverlevel1 != null;
            entitus.changeDimension(serverlevel1);
        }
    }
}
