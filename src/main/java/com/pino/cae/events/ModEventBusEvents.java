package com.pino.cae.events;
import com.mojang.logging.LogUtils;
import com.pino.cae.Cae;
import com.pino.cae.idk.DumbClass;
import com.pino.cae.init.BlockInit;
import com.pino.cae.init.ModEntityTypes;
import com.pino.cae.entity.essentialOrb;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import slimeknights.mantle.registration.RegistrationHelper;

import static com.pino.cae.Cae.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {


    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.ESSENTIALORB.get(), essentialOrb.setAttributes());
    }





}