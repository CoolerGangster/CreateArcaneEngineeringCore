package com.pino.cae.events;

import com.pino.cae.Cae;
import com.pino.cae.idk.DumbClass;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
@Mod.EventBusSubscriber(modid = Cae.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class forgebus {
    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event){
        final DumbClass provider = new DumbClass();
        if (event.getObject() != null){
            event.addCapability(DumbClass.IDENTIFIER, provider);
        }
    }
}
