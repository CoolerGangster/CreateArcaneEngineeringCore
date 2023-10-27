package com.pino.cae;

import com.mojang.logging.LogUtils;
import com.pino.cae.events.forgebus;
import com.pino.cae.init.FluidInit;
import com.pino.cae.init.ModEntityTypes;
import com.pino.cae.entity.OrbRender;
import com.pino.cae.init.BlockInit;
import com.pino.cae.init.ItemInit;
import com.pino.cae.init.blockEntity.ModBlockEntities;
import com.pino.cae.sounds.soundinit;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.Registrate;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistry;
import org.intellij.lang.annotations.Identifier;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import slimeknights.mantle.registration.RegistrationHelper;
import software.bernie.geckolib3.GeckoLib;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;

import java.util.AbstractMap;
import java.util.HashMap;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Cae.MOD_ID)

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class Cae
{





    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "cae";
    public static final CreateRegistrate REGISTRATEKJ = CreateRegistrate.create(MOD_ID);
    public static HashMap<String, String> fixerMap = new HashMap<>();
    public Cae() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ItemInit.ITEMS.register(modEventBus);
        REGISTRATEKJ.registerEventListeners(modEventBus);
        BlockInit.BLOCKS.register(modEventBus);
        FluidInit.FLUIDS.register(modEventBus);
        ModEntityTypes.ENTITIES.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        soundinit.register(modEventBus);
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::curioPresent);
        GeckoLib.initialize();
        fixerMap.put("kubejs:gold_casing","cae:gold_casing");


    }





    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    private void setup(final FMLCommonSetupEvent event) {
        ModidPacketHandler.register();

    }
    private void clientSetup(final FMLClientSetupEvent event){
        EntityRenderers.register(ModEntityTypes.ESSENTIALORB.get(), OrbRender::new);
        ItemBlockRenderTypes.setRenderLayer(BlockInit.TIME_CRYSTAL.get(), RenderType.translucent());
    }
    public void curioPresent(final InterModEnqueueEvent event) {
        SlotTypePreset[] types = {
                SlotTypePreset.CURIO,
                SlotTypePreset.CHARM,
        };
        for (SlotTypePreset preset : types) {
            InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, ()-> preset.getMessageBuilder().build());
        }
        CurioSlots[] modTypes = {
                CurioSlots.GEMS
        };
        for(CurioSlots slot : modTypes) {
            InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, ()-> slot.getMessageBuilder().build());
        }
    }





}

