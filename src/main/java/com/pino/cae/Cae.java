package com.pino.cae;

import com.mojang.logging.LogUtils;
import com.pino.cae.entity.ModEntityTypes;
import com.pino.cae.entity.OrbRender;
import com.pino.cae.init.BlockInit;
import com.pino.cae.init.ItemInit;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import com.pino.cae.init.BlockInit;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import software.bernie.geckolib3.GeckoLib;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Cae.MOD_ID)
public class Cae
{
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "cae";


    public Cae() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        BlockInit.BLOCKS.register(modEventBus);
        ItemInit.ITEMS.register(modEventBus);
        modEventBus.addListener(this::setup);
        ModEntityTypes.register(modEventBus);
        EntityRenderers.register(ModEntityTypes.ESSENTIALORB.get(), OrbRender::new);
        GeckoLib.initialize();
    }

    private void setup(final FMLCommonSetupEvent event) {

    }

}

