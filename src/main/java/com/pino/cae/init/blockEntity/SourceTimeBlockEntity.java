package com.pino.cae.init.blockEntity;

import com.hollingsworth.arsnouveau.api.spell.AbstractAugment;
import com.hollingsworth.arsnouveau.api.spell.AbstractSpellPart;
import com.hollingsworth.arsnouveau.api.spell.Spell;
import com.hollingsworth.arsnouveau.client.particle.ParticleColor;
import com.hollingsworth.arsnouveau.common.entity.EntityProjectileSpell;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentPierce;
import com.mojang.logging.LogUtils;
import com.pino.cae.idk.DumbClassInterface;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import net.minecraft.world.level.block.Blocks;

import java.security.SecureRandom;
import java.util.*;

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

    public List<AbstractSpellPart> piercespam = new ArrayList();

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

   /* AbstractSpellPart pierce = new AbstractSpellPart("projectile","projectile") {
        @Override
        public int getDefaultManaCost() {
            return 0;
        }

        @NotNull
        @Override
        public Set<AbstractAugment> getCompatibleAugments() {
            return Collections.singleton(new AbstractAugment("projectile", "projectile") {
                @Override
                public int getDefaultManaCost() {
                    return 0;
                }
            });
        }
    };
    Spell spll = new Spell();
**/
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
        list[i] = "null";
    }
    public int countArray(String serialization){
        int out = 0;
        for (int i = 0; i < serialization.length(); i++) {
            if(serialization.charAt(i) == ','){
                out++;
            }

        }
        return(out);
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

            Entity AoeCloud = new AreaEffectCloud(level,blockPos.getX(),blockPos.getY(),blockPos.getZ());
            AoeCloud.getCapability(INSTANCE).ifPresent(MyCapImplemt -> MyCapImplemt.setMyValue("IM ALIVE"));

            changCol();
            if (
                    Objects.equals(list[0], "null") && Objects.equals(list[1], "null") && Objects.equals(list[2], "null") && Objects.equals(list[3], "null") && Objects.equals(list[4], "null")
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
                level.addFreshEntity(AoeCloud);
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
                        if(entity.serializeNBT().getCompound("ForgeCaps").getCompound("cae:properties").getString("kubejsdoshit").contains("doit")){
                            ((EntityProjectileSpell) entity).spellResolver.spellContext.withSpell(Spell.deserialize("pierce"));
                        }
                        if (((EntityProjectileSpell) entity).spellResolver != null){
                            /*LOGGER.debug(String.valueOf(((EntityProjectileSpell) entity).spellResolver.spellContext.getSpell().serialize()));
                            LOGGER.debug(String.valueOf(countArray(((EntityProjectileSpell) entity).spellResolver.spellContext.getSpell().serialize())));*/
                            ((EntityProjectileSpell) entity).setColor(color);
                            boolean bool = false;
                            for (int i = 0; i < list.length; i++) {

                                if (((EntityProjectileSpell) entity).spellResolver.spellContext.getSpell().serialize().contains(list[i]) && Cooldown <= 0 && !entity.serializeNBT().getCompound("ForgeCaps").getCompound("cae:properties").getString("kubejsdoshit").contains("doit")){
                                    bool = true;
                                   // LOGGER.debug("true");
                                }
                            }
                            if(!bool && Cooldown <= 0 && !entity.serializeNBT().getCompound("ForgeCaps").getCompound("cae:properties").getString("kubejsdoshit").contains("doit")){
                                Cooldown += 200;
                                //LOGGER.debug("false");
                            }
                            for (int i = 0; i < list.length; i++) {
                                if (((EntityProjectileSpell) entity).spellResolver.spellContext.getSpell().serialize().contains(list[i]) && Cooldown <= 0 && !entity.serializeNBT().getCompound("ForgeCaps").getCompound("cae:properties").getString("kubejsdoshit").contains("doit")){
                                    //LOGGER.debug("wow");

                                    LOGGER.debug(String.valueOf(((EntityProjectileSpell) entity).spellResolver.spellContext.getSpell().serialize()));
                                    if((countArray(((EntityProjectileSpell) entity).spellResolver.spellContext.getSpell().serialize())) <= 1){
                                        //LOGGER.debug(((EntityProjectileSpell) entity).spellResolver.spellContext.getSpell().serialize() + "THE SPELL");
                                        setarrnull(i);
                                        amt++;
                                        Speed = values[amt];
                                        colorinc = 255/Speed;

                                        //LOGGER.debug(String.valueOf(i));
                                        Cooldown = lecoldown;
                                        //setFunkyStage(FunkyStage + 1);
                                        entity.getCapability(INSTANCE).ifPresent(MyCapImplemt -> MyCapImplemt.setMyValue("doit"));
                                        /*((EntityProjectileSpell) entity).spellResolver.spellContext.withSpell(Spell.EMPTY);
                                        LOGGER.debug(((EntityProjectileSpell) entity).spellResolver.spellContext.getSpell().serialize());
                                        ((EntityProjectileSpell) entity).spellResolver.spellContext.getSpell().add(AugmentPierce.INSTANCE,100);
                                        ((EntityProjectileSpell) entity).spellResolver.spellContext.getSpell().recipe.add(0, pierce);
                                        LOGGER.debug(((EntityProjectileSpell) entity).spellResolver.spellContext.getSpell().serialize());*/

                                        //ModidPacketHandler.sendToPlayer(new S2CPacket(),level.getChunkAt(blockPos));

                                        entity.serializeNBT().getCompound("ForgeCaps").getCompound("cae:properties").putString("kubejsdoshit", "doit");
                                        //LOGGER.debug(entity.serializeNBT().getCompound("ForgeCaps").getCompound("cae:properties").getString("kubejsdoshit") + "NBT");
                                        //LOGGER.debug(Arrays.toString(list) + "the ARRAY");
                                        }else{
                                            Cooldown += 200;
                                        }
                                }
                            }
                            //this wont work, cause we have to check this before the thing above
                        }
                    }
                }
                if (Cooldown >= 1) {
                    Cooldown--;
                }
            //LOGGER.debug(String.valueOf(Cooldown));
        }
    }
    @Override
    public void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        ListTag spellarray = new ListTag();
        for (String str : list) {
            spellarray.add(StringTag.valueOf(str));
        }
        nbt.put("RemainingSpells",spellarray);
        nbt.putInt("CoolDown",this.Cooldown);

    }


}
