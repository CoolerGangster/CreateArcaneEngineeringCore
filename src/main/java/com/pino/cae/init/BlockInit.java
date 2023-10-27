package com.pino.cae.init;


import com.simibubi.create.AllCreativeModeTabs;
import com.simibubi.create.AllSpriteShifts;
import com.simibubi.create.content.decoration.encasing.CasingBlock;
import com.simibubi.create.foundation.data.BuilderTransformers;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

import net.minecraft.world.level.block.Block;

import net.minecraft.world.level.block.Blocks;

import com.pino.cae.Cae;


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


import static com.pino.cae.Cae.REGISTRATEKJ;
import static com.simibubi.create.Create.REGISTRATE;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlockInit{
    static {
        REGISTRATEKJ.creativeModeTab(() -> AllCreativeModeTabs.BASE_CREATIVE_TAB);
    }

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

    public static final RegistryObject<Block> SINGULARITY_BLOCK = BLOCKS.register("singularity_core",
            () -> new SingularityB(Block.Properties.copy(Blocks.BEDROCK).noOcclusion()));

    public static final RegistryObject<Block> SINGULARITY_VEIN = BLOCKS.register("singularity_vein",
            () -> new SingularityVein(Block.Properties.copy(Blocks.BEDROCK).noOcclusion()));

    public static final BlockEntry<CasingBlock> GOLD_CASING = REGISTRATEKJ.block("gold_casing", CasingBlock::new)
            .transform(BuilderTransformers.casing(() -> TextureShifts.GOLD_CASING))
            .properties(p -> p.color(MaterialColor.GOLD))
            .properties(p -> p.sound(SoundType.WOOD))
            .lang("Gold Casing")
            .register();

    public static final BlockEntry<CasingBlock> ZINC_CASING = REGISTRATEKJ.block("zinc_casing", CasingBlock::new)
            .transform(BuilderTransformers.casing(() -> TextureShifts.ZINC_CASING))
            .properties(p -> p.color(MaterialColor.GLOW_LICHEN))
            .properties(p -> p.sound(SoundType.LANTERN))
            .lang("Zinc Casing")
            .register();

    public static final BlockEntry<CasingBlock> ENDERIUM_CASING = REGISTRATEKJ.block("enderium_casing", CasingBlock::new)
            .transform(BuilderTransformers.casing(() -> TextureShifts.ENDERIUM_CASING))
            .properties(p -> p.color(MaterialColor.WARPED_STEM))
            .properties(p -> p.sound(SoundType.LANTERN))
            .lang("Enderium Casing")
            .register();

    public static final BlockEntry<CasingBlock> SUPERCRITICAL_CASING = REGISTRATEKJ.block("supercritical_casing", CasingBlock::new)
            .transform(BuilderTransformers.casing(() -> TextureShifts.SUPERCRITICAL_CASING))
            .properties(p -> p.color(MaterialColor.COLOR_BLACK))
            .properties(p -> p.sound(SoundType.DEEPSLATE))
            .lang("Supercritical Casing")
            .register();

}   
