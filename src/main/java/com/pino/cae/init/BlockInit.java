package com.pino.cae.init;

import appeng.decorative.solid.SkyStoneBlock;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllSpriteShifts;
import com.simibubi.create.Create;
import com.simibubi.create.content.contraptions.base.CasingBlock;
import com.simibubi.create.foundation.data.BuilderTransformers;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

import net.minecraft.world.level.block.Block;

import net.minecraft.world.level.block.Blocks;

import com.pino.cae.Cae;

import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraftforge.fml.common.Mod;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlockInit{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Cae.MOD_ID);
    private static final CreateRegistrate REGISTRATE = (CreateRegistrate) Create.registrate().creativeModeTab(() -> {
        return Create.BASE_CREATIVE_TAB;
    });

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

    public static final RegistryObject<Block> SINGULARITY_BLOCK = BLOCKS.register("singularity_core",
            () -> new SingularityB(Block.Properties.copy(Blocks.BEDROCK).noOcclusion()));

    public static final RegistryObject<Block> SINGULARITY_VEIN = BLOCKS.register("singularity_vein",
            () -> new SingularityVein(Block.Properties.copy(Blocks.BEDROCK).noOcclusion()));
    public static final BlockEntry<CasingBlock> FORTNITE_CASING;
    static {
        FORTNITE_CASING = ((BlockBuilder)REGISTRATE.block("fortnite_casing", CasingBlock::new).properties((p) -> {
            return p.color(MaterialColor.PODZOL);
        }).transform(BuilderTransformers.casing(() -> {
            return AllSpriteShifts.ANDESITE_CASING;
        }))).register();
    }


}   
