package com.pino.cae.events;

import com.pino.cae.Cae;
import com.pino.cae.CurioSlots;
import com.pino.cae.init.blockEntity.ModBlockEntities;
import com.pino.cae.init.blockEntity.client.AnimatedBlockRenderer;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = Cae.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventClientBusEvent {

    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.TIME_CRYSTAL_ENTITY.get(), AnimatedBlockRenderer::new);
    }
    @SubscribeEvent
    public static void textureStitchEventPre(final TextureStitchEvent.Pre event) {
        if(!Objects.equals(event.getAtlas().location(), InventoryMenu.BLOCK_ATLAS)) { return; }
        for(CurioSlots type : CurioSlots.values()) { event.addSprite(type.getIcon()); }
    }


}