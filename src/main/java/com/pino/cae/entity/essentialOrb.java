package com.pino.cae.entity;
import appeng.core.definitions.AEBlocks;
import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Tuple;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.StoneButtonBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import java.lang.Iterable;
import java.util.ArrayList;
import java.util.Hashtable;

import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.entity.LivingEntity;
import cofh.thermal.core.entity.explosive.ThermalTNTEntity;
import net.minecraft.world.level.Explosion;

import static com.stal111.forbidden_arcanus.core.init.ModItems.Stacks.ORB_OF_TEMPORARY_FLIGHT;
import static net.minecraft.core.particles.ParticleTypes.HAPPY_VILLAGER;
import static net.minecraft.world.level.gameevent.GameEvent.BLOCK_DESTROY;
import static net.minecraft.world.level.gameevent.GameEvent.EXPLODE;

@Mod.EventBusSubscriber
public class essentialOrb extends Animal implements IAnimatable {
    public int cashout = 0;
    private static final EntityDataAccessor<Integer> STAGE = SynchedEntityData.defineId(essentialOrb.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> CASHOUT = SynchedEntityData.defineId(essentialOrb.class, EntityDataSerializers.INT);
    private static final Logger LOGGER = LogUtils.getLogger();
    public int lifeTime = 420;
    public int stage = 0;
    private AnimationFactory factory = new AnimationFactory(this);
    public essentialOrb(EntityType<? extends Animal> p_27557_, Level p_27558_) {
        super(p_27557_, p_27558_);
    }
    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH,16666.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.01f).build();
    }
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(STAGE, 0);
        this.entityData.define(CASHOUT, 0);
    }
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 8.0F));
    }
    @Override
    public boolean hurt(DamageSource p_27567_, float p_27568_) {
            return false;
    }
    public void aiStep() {

        super.aiStep();
        if (!this.level.isClientSide()){
            this.entityData.set(STAGE, this.stage);
            this.entityData.set(CASHOUT, this.cashout);
        }

        if (this.entityData.get(STAGE) == 1){
            for(Entity entity : level.getEntities(this, (new AABB(this.blockPosition()).inflate(2,1,2)))){
                if (entity.getType().toString().contains("entity.thermal.ender_tnt")){
                    this.setStage(250);
                    entity.kill();
                }
            }
        }
        if (this.stage == 2 && this.getFeetBlockState().toString().contains("Block{forbidden_arcanus:black_hole}") && this.level.getBlockState(this.blockPosition().offset(0,1,0)).toString().contains("Block{cae:lst}")){
            this.level.setBlockAndUpdate(this.blockPosition().offset(0,1,0), Blocks.AIR.defaultBlockState());
            this.level.setBlockAndUpdate(this.blockPosition(), Blocks.AIR.defaultBlockState());
            this.level.playLocalSound(this.getX(),this.getY(),this.getZ(), SoundEvents.ANVIL_BREAK, SoundSource.BLOCKS,1, 1, true);
            this.stage = 3;
            this.lifeTime = 160;
        }
        if (this.stage == 3){
            for(Entity entity : level.getEntities(this, (new AABB(this.blockPosition()).inflate(0.5,0.5,0.5)))){
                if (entity.getType().toString().contains("entity.ae2.singularity") && this.entityData.get(CASHOUT) < 7){
                    entity.kill();
                    this.cashout++;
                }
            }
        }

        if (--this.lifeTime <= 0) {
            if (this.entityData.get(CASHOUT) >= 6 && this.entityData.get(STAGE) == 3){
                spawnAtLocation(ORB_OF_TEMPORARY_FLIGHT, 1);
                this.discard();
            }
            else{
                Explosion.BlockInteraction explosion$blockinteraction = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this) ? Explosion.BlockInteraction.BREAK : Explosion.BlockInteraction.NONE;
                this.level.explode(this, this.getX(), this.getY(), this.getZ(), 1, explosion$blockinteraction);
                this.discard();
            }
        }
    }


    public void setStage(int ticks){

        stage++;
        lifeTime = ticks;

    }
    public int getStage(){
        int r = stage;
        return r;
    }
    public void addAdditionalSaveData(CompoundTag fortnite) {
        super.addAdditionalSaveData(fortnite);
        fortnite.putInt("TimeTillDETH", this.lifeTime);
        fortnite.putInt("Stage", this.stage);
        fortnite.putInt("Cashout",this.cashout);

    }
    @SubscribeEvent
    public static void onLightning(net.minecraftforge.event.entity.EntityStruckByLightningEvent event) {
        if (event.getEntity() instanceof essentialOrb){
            if (((essentialOrb) event.getEntity()).getStage() == 0){
                ((essentialOrb) event.getEntity()).setStage(320);
            }
        }
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return null;
    }
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {

        if (this.entityData.get(STAGE) == 0){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.essentialorb.idle", true));

        }
        if (this.entityData.get(STAGE) == 1){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.essentialorb.one", true));

        }
        if (this.entityData.get(STAGE) == 2){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.essentialorb.two", true));

        }
        if (this.entityData.get(STAGE) == 3){

                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.essentialorb.cashout", false));

        }
        return PlayState.CONTINUE;
    }


    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));

    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
    protected SoundEvent getAmbientSound() {
        return SoundEvents.AMETHYST_BLOCK_CHIME;
    }
}

