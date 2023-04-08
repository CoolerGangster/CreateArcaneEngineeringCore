package com.pino.cae.entity;

import com.pino.cae.Cae;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class OrbModel extends AnimatedGeoModel<essentialOrb> {
    @Override
    public ResourceLocation getModelLocation(essentialOrb essentialOrb) {
        return new ResourceLocation(Cae.MOD_ID,"geo/orb.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(essentialOrb essentialOrb) {
        return new ResourceLocation(Cae.MOD_ID,"textures/entity/orb/essentialorb.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(essentialOrb essentialOrb) {
        return new ResourceLocation(Cae.MOD_ID,"animations/orb.animation.json");
    }
}