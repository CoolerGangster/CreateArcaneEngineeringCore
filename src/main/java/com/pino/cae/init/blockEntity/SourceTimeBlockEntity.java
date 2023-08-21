package com.pino.cae.init.blockEntity;

import com.hollingsworth.arsnouveau.api.spell.Spell;
import com.hollingsworth.arsnouveau.client.particle.ParticleColor;
import com.hollingsworth.arsnouveau.common.entity.EntityProjectileSpell;
import com.mojang.logging.LogUtils;
import com.pino.cae.ModidPacketHandler;
import com.pino.cae.S2CPacket;
import com.pino.cae.idk.DumbClassInterface;
import com.pino.cae.idk.MyCapImplemt;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import org.slf4j.Logger;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import net.minecraft.world.level.block.Blocks;
import com.hollingsworth.arsnouveau.common.entity.ModEntities;
import java.security.SecureRandom;
import java.util.Objects;
import java.util.UUID;

public class SourceTimeBlockEntity extends BlockEntity implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);
    private static final SecureRandom rand = new  SecureRandom();
    Logger LOGGER = LogUtils.getLogger();
    public static String[] spells = {"hex","light","gravity","ignite","cold_snap", "intangible","freeze","crush","grow","firework"};
    public String a = spells[rand.nextInt(0, 2)];
    public String b= spells[rand.nextInt(2,4)];
    public String c= spells[rand.nextInt(4,6)];
    public String d= spells[rand.nextInt(6,8)];
    public String e= spells[rand.nextInt(8,10)];
    public String[] list = {a,b,c,d,e};
    public int TimeTillDeth = 200;
    public int Kill = 0;

     int[] Colr = {255,0,255};

    int phase = 0;
    int R = Colr[0];
    int G = Colr[1];
    int B = Colr[2];
    //Valid values are:1
    //3
    //5
    //15
    //17
    //51
    //85
    //255

    int[] values = {255,85,51,17,5,3};
    int amt = 0;

    int lecoldown = 50;
    int FunkyStage = 0;
    public void setFunkyStage(int i){
        FunkyStage = i;
    }
    int Speed = values[amt];
    int colorinc = 255/Speed;
    public int Cooldown = 0;
    ParticleColor.IntWrapper color = new ParticleColor.IntWrapper(R,G,B);
    public static final Capability<DumbClassInterface> INSTANCE = CapabilityManager.get(new CapabilityToken<>() {});
    public SourceTimeBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(ModBlockEntities.TIME_CRYSTAL_ENTITY.get(), p_155229_, p_155230_);
    }
    public void fortnite(String pog) {
        LOGGER.debug(pog);
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<SourceTimeBlockEntity>
                (this, "controller", 0, this::predicate));
    }
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        
        if (FunkyStage == 0){event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.idle", true));}
        if (FunkyStage == 1){event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.v1").addAnimation("animation.model.v1h",true));}
        if (FunkyStage == 2){event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.v2").addAnimation("animation.model.v2h",true));}
        if (FunkyStage == 3){event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.v3").addAnimation("animation.model.v3h",true));}
        if (FunkyStage == 4 ){event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.v4").addAnimation("animation.model.v4h",true));}
        if (FunkyStage == 5 ){event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.v5"));}



        return PlayState.CONTINUE;
    }
    public void changCol(){

        //phases: Purp to red ( 255,0,255 -> 255,0,0 ) Red to yellow ( 255,0,0 -> 255,255,0 ) Yellow green ( 255,255,0  -> 0,255,0 ) Green Turquois  ( 0,255,0  -> 0,255,255 ), Turqoise Blue ( 0,255,255  -> 0,0,255 ) Blue Purp ( 0,0,255  -> 255,0,255 )
        if (phase == 0 && Colr[2] != 0) {
            Colr[2] -= colorinc;
            if (Colr[2] <= 0) {phase = 1; Colr[2] = 0;}
        }
        if (phase == 1 && Colr[1] != 255) {
            Colr[1] += colorinc;
            if (Colr[1] >= 255) {phase = 2; Colr[1] = 255;}
        }
        if (phase == 2 && Colr[0] != 0) {
            Colr[0] -= colorinc;
            if (Colr[0] <= 0) {phase = 3; Colr[0] = 0;}
        }
        if (phase == 3 && Colr[2] != 255) {
            Colr[2] += colorinc;
            if (Colr[2] >= 255) {phase = 4; Colr[2] = 255;}
        }
        if (phase == 4 && Colr[1] != 0) {
            Colr[1] -= colorinc;
            if (Colr[1] <= 0) {phase = 5; Colr[1] = 0;}
        }
        if (phase == 5 && Colr[0] != 255) {
            Colr[0] += colorinc;
            if (Colr[0] >= 255) {phase = 0; Colr[0] = 255;}

        }

        R = Colr[0];
        G = Colr[1];
        B = Colr[2];

        /*LOGGER.debug(Arrays.toString(Colr) +" ColArr");
        LOGGER.debug(phase + "phase");

        LOGGER.debug(color.serialize() + " color");
        LOGGER.debug(String.valueOf(Speed)+ " Speed");
        LOGGER.debug(String.valueOf(colorinc) + "Colorincr");

         */

        color = new ParticleColor.IntWrapper(R,G,B);
    }
    public int setStageBrilliancy(int Funky){
        return 0;
    }
    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    public void setarrnull(int i) {
        list[i] = "1212121212121";
    }
    public void tick(Level level, BlockPos blockPos, BlockState blockState) {

    if(level.isClientSide){
        for(Entity entity : level.getEntities(null, (new AABB(this.getBlockPos()).inflate(3,3,3)))){
            if (entity instanceof AreaEffectCloud) {
                FunkyStage++;
                entity.discard();
               // LOGGER.debug(String.valueOf(FunkyStage));
            }
        }
    }

       // LOGGER.debug(String.valueOf(FunkyStage));
        if (!level.isClientSide()){
            changCol();
            if (
                    Objects.equals(list[0], "1212121212121") && Objects.equals(list[1], "1212121212121") && Objects.equals(list[2], "1212121212121") && Objects.equals(list[3], "1212121212121") && Objects.equals(list[4], "1212121212121")
            ){
                TimeTillDeth--;
            }
            if (TimeTillDeth <= 0){
                for(Entity entity : level.getEntities(null, (new AABB(this.getBlockPos()).inflate(1,1,1)))){
                    if (entity instanceof EntityProjectileSpell) {
                        if (Kill == 0){
                            entity.getCapability(INSTANCE).ifPresent(MyCapImplemt -> MyCapImplemt.setMyValue("dontdoit"));
                            Kill++;
                        }
                        else{
                        entity.discard();
                        }
                    }
                }
                this.level.setBlockAndUpdate(this.getBlockPos(), Blocks.AIR.defaultBlockState());
            }

                for(Entity entity : level.getEntities(null, (new AABB(this.getBlockPos()).inflate(3,3,3)))){
                    if (entity instanceof AreaEffectCloud){
                        if (entity.serializeNBT().getCompound("ForgeCaps").getCompound("cae:properties").getInt("robert") == 0){
                            entity.discard();
                        }
                        if (entity.serializeNBT().getCompound("ForgeCaps").getCompound("cae:properties").getInt("robert") > 0){
                            int d = entity.serializeNBT().getCompound("ForgeCaps").getCompound("cae:properties").getInt("robert");
                            d--;
                            int finalD = d;
                            entity.getCapability(INSTANCE).ifPresent(MyCapImplemt -> MyCapImplemt.setMyInt(finalD));
                        }
                    }
                    if (entity instanceof EntityProjectileSpell) {
                        if (((EntityProjectileSpell) entity).spellResolver != null){
                            ((EntityProjectileSpell) entity).setColor(color);
                            for (int i = 0; i < list.length; i++) {
                                if (((EntityProjectileSpell) entity).spellResolver.spellContext.getSpell().serialize().contains(list[i]) && Cooldown <= 0 && !entity.serializeNBT().getCompound("ForgeCaps").getCompound("cae:properties").getString("kubejsdoshit").contains("doit")){
                                    //LOGGER.debug(((EntityProjectileSpell) entity).spellResolver.spellContext.getSpell().serialize() + "THE SPELL");
                                    setarrnull(i);
                                    amt++;
                                    Speed = values[amt];
                                    colorinc = 255/Speed;

                                    //LOGGER.debug(String.valueOf(i));
                                    Cooldown = lecoldown;
                                    //setFunkyStage(FunkyStage + 1);
                                    entity.getCapability(INSTANCE).ifPresent(MyCapImplemt -> MyCapImplemt.setMyValue("doit"));
                                    ((EntityProjectileSpell) entity).spellResolver.spellContext.withSpell(Spell.EMPTY);
                                    ModidPacketHandler.sendToPlayer(new S2CPacket(),level.getChunkAt(blockPos));

                                    entity.serializeNBT().getCompound("ForgeCaps").getCompound("cae:properties").putString("kubejsdoshit", "doit");
                                    //LOGGER.debug(entity.serializeNBT().getCompound("ForgeCaps").getCompound("cae:properties").getString("kubejsdoshit") + "NBT");
                                    //LOGGER.debug(Arrays.toString(list) + "the ARRAY");

                                }
                            }
                        }
                    }
                }
                if (Cooldown >= 1) {
                    Cooldown--;
                }
            //LOGGER.debug(String.valueOf(Cooldown));
        }
    }


}
