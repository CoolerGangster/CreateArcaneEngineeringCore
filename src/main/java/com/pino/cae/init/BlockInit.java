package com.pino.cae.init;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

import net.minecraft.world.level.block.Block;

import net.minecraft.world.level.block.Blocks;

import com.pino.cae.Cae;

import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraftforge.fml.common.Mod;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Cae.MOD_ID);


    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();

        BLOCKS.getEntries().stream().map(RegistryObject::get).forEach( (block) -> {
            final Item.Properties properties = new Item.Properties().tab(ItemInit.ModCreativeTab.instance);
            final BlockItem blockItem = new BlockItem(block, properties);
            blockItem.setRegistryName(block.getRegistryName());
            registry.register(blockItem);
        });
    }
    public static final RegistryObject<Block> SKYSTONE_CATALYST = BLOCKS.register("skystone_catalyst",
            () -> new SkystoneCatalyst(Block.Properties.copy(Blocks.BEDROCK).lightLevel((state) -> 9)));

    //public static final RegistryObject<Block> TIME_CRYSTAL = BLOCKS.register("source_time_crystal",
    //        () -> new SourceTimeBlock(Block.Properties.copy(Blocks.BUDDING_AMETHYST)));
}
