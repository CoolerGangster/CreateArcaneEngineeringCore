package com.pino.cae.idk;

import com.pino.cae.Cae;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Cae.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class myCapa {
    public static final Capability<DumbClassInterface> INSTANCE = CapabilityManager.get(new CapabilityToken<>() {});
    @SubscribeEvent
    public static void register(RegisterCapabilitiesEvent event) {
        event.register(DumbClassInterface.class);
    }

    private myCapa() {
    }
}
