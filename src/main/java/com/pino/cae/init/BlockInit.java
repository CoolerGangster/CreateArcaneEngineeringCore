package com.pino.cae.init;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

import net.minecraft.world.level.block.Block;

import net.minecraft.world.level.block.Blocks;

import com.pino.cae.Cae;

import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraftforge.fml.common.Mod;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;


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
    private static <T extends Block> RegistryObject<T> registerBlockWithoutBlockItem(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }

    public static final RegistryObject<Block> FAKESKYSTONE = BLOCKS.register("sky_stone",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(1.0F).sound(SoundType.STONE)));
    public static final RegistryObject<Block> VOID_PORTAL = BLOCKS.register("void_portal",
            () -> new Block(BlockBehaviour.Properties.of(Material.PORTAL).noCollission().strength(-1.0F).sound(SoundType.GLASS).lightLevel((state) -> 6)));
    public static final RegistryObject<Block> ARCANE_PORTAL = BLOCKS.register("arcane_portal",
            () -> new Block(BlockBehaviour.Properties.of(Material.PORTAL).noCollission().strength(-1.0F).sound(SoundType.GLASS).lightLevel((state) -> 6)));
    public static final RegistryObject<Block> START_PORTAL = BLOCKS.register("start_portal",
            () -> new Block(BlockBehaviour.Properties.of(Material.PORTAL).noCollission().strength(-1.0F).sound(SoundType.GLASS).lightLevel((state) -> 6)));
    public static final RegistryObject<Block> END_PORTAL = BLOCKS.register("end_portal",
            () -> new Block(BlockBehaviour.Properties.of(Material.PORTAL).noCollission().strength(-1.0F).sound(SoundType.GLASS).lightLevel((state) -> 6)));
    public static final RegistryObject<Block> SKYSTONE_CATALYST = BLOCKS.register("skystone_catalyst",
            () -> new SkystoneCatalyst(Block.Properties.copy(Blocks.BEDROCK).lightLevel((state) -> 15)));
    public static final RegistryObject<Block> VERIDIUM_CATALYST = BLOCKS.register("veridium_catalyst",
            () -> new VeridiumCatalyst(Block.Properties.copy(Blocks.OBSIDIAN).lightLevel((state) -> 15)));
    public static final RegistryObject<Block> OCHRUM_CATALYST = BLOCKS.register("ochrum_catalyst",
            () -> new OchrumCatalyst(Block.Properties.copy(Blocks.OBSIDIAN).lightLevel((state) -> 15)));
    public static final RegistryObject<Block> ASURINE_CATALYST = BLOCKS.register("asurine_catalyst",
            () -> new AsurineCatalyst(Block.Properties.copy(Blocks.OBSIDIAN).lightLevel((state) -> 15)));
    public static final RegistryObject<Block> CRIMSITE_CATALYST = BLOCKS.register("crimsite_catalyst",
            () -> new CrimsiteCatalyst(Block.Properties.copy(Blocks.OBSIDIAN).lightLevel((state) -> 15)));

    public static final RegistryObject<Block> TIME_CRYSTAL = BLOCKS.register("source_time_crystal",
            () -> new SourceTimeBlock(Block.Properties.copy(Blocks.BUDDING_AMETHYST).noOcclusion()));
}
