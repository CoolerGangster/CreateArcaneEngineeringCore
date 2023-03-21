package com.pino.cae.init;

import com.mojang.logging.LogUtils;
import com.pino.cae.Cae;
import com.pino.cae.entity.essentialOrb;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ModEntityTypes {
    private ModEntityTypes(){}

    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITIES, Cae.MOD_ID);

    public static final RegistryObject<EntityType<essentialOrb>> ESSENTIALORB =
            ENTITIES.register("essentialorb",
                    () -> EntityType.Builder.of(essentialOrb::new, MobCategory.CREATURE)
                            .sized(0.25f, 0.31f)
                            .build(new ResourceLocation(Cae.MOD_ID, "essentialorb").toString()));

}   