package com.pino.cae;


import com.simibubi.create.compat.curios.CuriosRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;


public class IMCstuff {

    /*
    private static void onInterModEnqueue(final InterModEnqueueEvent event) {
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () ->
                new SlotTypeMessage.Builder("source_amulets").icon(new ResourceLocation(Cae.MOD_ID,"item/source")).priority(100).size(1).build());
    }

    private static void onClientSetup(final FMLClientSetupEvent event) {
        CuriosRenderers.register();
    }*/
}
