package com.pino.cae.init.blockEntity.client;

import com.pino.cae.Cae;
import com.pino.cae.init.blockEntity.SourceTimeBlockEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class AnimatedBlockModel extends AnimatedGeoModel<SourceTimeBlockEntity> {
    @Override
    public ResourceLocation getModelLocation(SourceTimeBlockEntity object) {
        return new ResourceLocation(Cae.MOD_ID, "geo/fuckyou.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SourceTimeBlockEntity object) {
        return new ResourceLocation(Cae.MOD_ID, "textures/fuckyou.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SourceTimeBlockEntity animatable) {
        return new ResourceLocation(Cae.MOD_ID, "animations/fuckyou.animation.json");
    }

}
