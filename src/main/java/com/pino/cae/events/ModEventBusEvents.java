package com.pino.cae.events;
import com.pino.cae.Cae;
import com.pino.cae.idk.DumbClass;
import com.pino.cae.init.ModEntityTypes;
import com.pino.cae.entity.essentialOrb;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Cae.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {


    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.ESSENTIALORB.get(), essentialOrb.setAttributes());
    }

}