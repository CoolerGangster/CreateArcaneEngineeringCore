package com.pino.cae.entity;

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
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITIES, Cae.MOD_ID);

    public static final RegistryObject<EntityType<essentialOrb>> ESSENTIALORB =
            ENTITY_TYPES.register("essentialorb",
                    () -> EntityType.Builder.of(essentialOrb::new, MobCategory.CREATURE)
                            .sized(0.25f, 0.31f)
                            .build(new ResourceLocation(Cae.MOD_ID, "essentialorb").toString()));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}