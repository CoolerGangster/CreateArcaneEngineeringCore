package com.pino.cae.mixin;/*
package com.pino.cae.mixin;
import com.hollingsworth.arsnouveau.common.block.PortalBlock;
import com.hollingsworth.arsnouveau.common.block.tile.ModdedTile;
import com.hollingsworth.arsnouveau.common.block.tile.PortalTile;
import com.hollingsworth.arsnouveau.common.network.Networking;
import com.hollingsworth.arsnouveau.common.network.PacketWarpPosition;
import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;



import javax.sound.sampled.Port;

@Mixin(PortalTile.class)
public abstract class ArsMixin extends ModdedTile {
    private static final Logger LOGGER = LogUtils.getLogger();

    public ArsMixin(BlockEntityType<?> tileEntityTypeIn, BlockPos pos, BlockState state) {
        super(tileEntityTypeIn, pos, state);
    }

    public void warp(Entity e) {
        if (!this.level.isClientSide && ((PortalTile)(ModdedTile)this).warpPos != null && !(this.level.getBlockState(((PortalTile)(ModdedTile)this).warpPos).getBlock() instanceof PortalBlock) && !(e instanceof ItemEntity)) {
            e.moveTo((double)((PortalTile)(ModdedTile)this).warpPos.getX() + 0.5, (double)((PortalTile)(ModdedTile)this).warpPos.getY(), (double)((PortalTile)(ModdedTile)this).warpPos.getZ() + 0.5, ((PortalTile)(ModdedTile)this).rotationVec != null ? this.rotationVec.y : e.yRot, this.rotationVec != null ? this.rotationVec.x : e.xRot);
            e.xRot = ((PortalTile)(ModdedTile)this).rotationVec != null ? ((PortalTile)(ModdedTile)this).rotationVec.x : e.xRot;
            e.yRot = ((PortalTile)(ModdedTile)this).rotationVec != null ? ((PortalTile)(ModdedTile)this).rotationVec.y : e.yRot;
            Networking.sendToNearby(this.level, e, new PacketWarpPosition(e.getId(), e.getX() + 0.5, e.getY(), e.getZ() + 0.5, e.xRot, e.yRot));
            ((ServerLevel)this.level).sendParticles(ParticleTypes.PORTAL, (double)((PortalTile)(ModdedTile)this).warpPos.getX(), (double)(((PortalTile)(ModdedTile)this).warpPos.getY() + 1), (double)((PortalTile)(ModdedTile)this).warpPos.getZ(), 4, (this.level.random.nextDouble() - 0.5) * 2.0, -this.level.random.nextDouble(), (this.level.random.nextDouble() - 0.5) * 2.0, 0.10000000149011612);
        }

    }
}


import com.hollingsworth.arsnouveau.common.entity.EntityProjectileSpell;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EntityProjectileSpell.class)
public abstract class ArsMixin extends Entity {
    public ArsMixin(EntityType<?> p_19870_, Level p_19871_) {
        super(p_19870_, p_19871_);
    }

    @Shadow
    public void dumbNBTshit(CompoundTag tag){
        tag.putInt("kubejsdoshit",1);
    }
}
*/

import appeng.core.AEConfig;
import appeng.core.definitions.AEBlockEntities;
import appeng.worldgen.meteorite.MeteoriteBlockPutter;
import appeng.worldgen.meteorite.MeteoritePlacer;
import com.pino.cae.init.BlockInit;
import com.pino.cae.init.SkystoneCatalyst;
import io.netty.channel.epoll.EpollDatagramChannel;
import net.minecraft.core.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.world.level.LevelAccessor;

@Mixin(value = MeteoritePlacer.class, remap = false)
public abstract class ArsMixin {

    @Shadow
    private final LevelAccessor level;
    @Shadow
    private MeteoriteBlockPutter putter;
    @Shadow
    private BlockPos pos;

    protected ArsMixin(LevelAccessor level) {
        this.level = level;
    }

    @Overwrite
    private void placeChest() {
        if (AEConfig.instance().isSpawnPressesInMeteoritesEnabled()) {
            this.putter.put(this.level, this.pos, BlockInit.SKYSTONE_CATALYST.get().defaultBlockState());
        }

    }
}