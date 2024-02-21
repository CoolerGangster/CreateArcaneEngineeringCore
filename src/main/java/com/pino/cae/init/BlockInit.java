package com.pino.cae.init;


import com.pino.cae.kineticBEs.CustomEncasedShaftBE;
import com.pino.cae.kineticBEs.dependencies.PublicEncasedCogwheelBlockCAE;
import com.pino.cae.kineticBEs.dependencies.PublicEncasedShaftBlockCAE;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllCreativeModeTabs;
import com.simibubi.create.AllSpriteShifts;
import com.simibubi.create.content.decoration.encasing.CasingBlock;
import com.simibubi.create.content.decoration.encasing.EncasingRegistry;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogCTBehaviour;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogwheelBlock;
import com.simibubi.create.foundation.block.IBE;
import com.simibubi.create.foundation.data.BuilderTransformers;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.utility.Couple;
import com.tterrag.registrate.util.entry.BlockEntry;
import fr.iglee42.createcasing.blocks.customs.CustomEncasedShaft;
import fr.iglee42.createcasing.blocks.publics.PublicEncasedShaftBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

import net.minecraft.world.level.block.Block;

import net.minecraft.world.level.block.Blocks;

import com.pino.cae.Cae;


import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
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
import static com.simibubi.create.foundation.data.TagGen.axeOrPickaxe;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlockInit {


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

    public static final BlockEntry<PublicEncasedShaftBlockCAE> GOLD_ENCASED_SHAFT =
            REGISTRATEKJ.block("gold_encased_shaft", p -> new PublicEncasedShaftBlockCAE(p, BlockInit.GOLD_CASING::get))
                    .properties(p -> p.color(MaterialColor.GOLD))
                    .transform(BadIdeaTransformers.encasedShaft("gold", () -> TextureShifts.GOLD_CASING))
                    .transform(EncasingRegistry.addVariantTo(AllBlocks.SHAFT))
                    .transform(axeOrPickaxe())
                    .register();

    public static final BlockEntry<PublicEncasedCogwheelBlockCAE> GOLD_ENCASED_COGWHEEL = REGISTRATEKJ
            .block("gold_encased_cogwheel", p -> new PublicEncasedCogwheelBlockCAE(p, false, BlockInit.GOLD_CASING::get))
            .properties(p -> p.color(MaterialColor.GOLD))
            .transform(BadIdeaTransformers.encasedCogwheel("gold", () -> TextureShifts.GOLD_CASING))
            .transform(EncasingRegistry.addVariantTo(AllBlocks.COGWHEEL))
            .onRegister(CreateRegistrate.connectedTextures(() -> new EncasedCogCTBehaviour(TextureShifts.GOLD_CASING,
                    Couple.create(TextureShifts.GOLD_ENCASED_COGWHEEL_SIDE,
                            TextureShifts.GOLD_ENCASED_COGWHEEL_OTHERSIDE))))
            .transform(axeOrPickaxe())
            .register();

    public static final BlockEntry<PublicEncasedCogwheelBlockCAE> GOLD_ENCASED_LARGE_COGWHEEL = REGISTRATEKJ
            .block("gold_encased_large_cogwheel",
                    p -> new PublicEncasedCogwheelBlockCAE(p, true, BlockInit.GOLD_CASING::get))
            .properties(p -> p.color(MaterialColor.GOLD))
            .transform(BadIdeaTransformers.encasedLargeCogwheel("gold", () -> TextureShifts.GOLD_CASING))
            .transform(EncasingRegistry.addVariantTo(AllBlocks.LARGE_COGWHEEL))
            .transform(axeOrPickaxe())
            .register();


    public static final BlockEntry<PublicEncasedShaftBlockCAE> ZINC_ENCASED_SHAFT =
            REGISTRATEKJ.block("zinc_encased_shaft", p -> new PublicEncasedShaftBlockCAE(p, BlockInit.ZINC_CASING::get))
                    .properties(p -> p.color(MaterialColor.GLOW_LICHEN))
                    .transform(BadIdeaTransformers.encasedShaft("zinc", () -> TextureShifts.ZINC_CASING))
                    .transform(EncasingRegistry.addVariantTo(AllBlocks.SHAFT))
                    .transform(axeOrPickaxe())
                    .register();

    public static final BlockEntry<PublicEncasedCogwheelBlockCAE> ZINC_ENCASED_COGWHEEL = REGISTRATEKJ
            .block("zinc_encased_cogwheel", p -> new PublicEncasedCogwheelBlockCAE(p, false, BlockInit.ZINC_CASING::get))
            .properties(p -> p.color(MaterialColor.GLOW_LICHEN))
            .transform(BadIdeaTransformers.encasedCogwheel("zinc", () -> TextureShifts.ZINC_CASING))
            .transform(EncasingRegistry.addVariantTo(AllBlocks.COGWHEEL))
            .onRegister(CreateRegistrate.connectedTextures(() -> new EncasedCogCTBehaviour(TextureShifts.ZINC_CASING,
                    Couple.create(TextureShifts.ZINC_ENCASED_COGWHEEL_SIDE,
                            TextureShifts.ZINC_ENCASED_COGWHEEL_OTHERSIDE))))
            .transform(axeOrPickaxe())
            .register();

    public static final BlockEntry<PublicEncasedCogwheelBlockCAE> ZINC_ENCASED_LARGE_COGWHEEL = REGISTRATEKJ
            .block("zinc_encased_large_cogwheel",
                    p -> new PublicEncasedCogwheelBlockCAE(p, true, BlockInit.ZINC_CASING::get))
            .properties(p -> p.color(MaterialColor.GLOW_LICHEN))
            .transform(BadIdeaTransformers.encasedLargeCogwheel("zinc", () -> TextureShifts.ZINC_CASING))
            .transform(EncasingRegistry.addVariantTo(AllBlocks.LARGE_COGWHEEL))
            .transform(axeOrPickaxe())
            .register();



    public static final BlockEntry<PublicEncasedShaftBlockCAE> ENDERIUM_ENCASED_SHAFT =
            REGISTRATEKJ.block("enderium_encased_shaft", p -> new PublicEncasedShaftBlockCAE(p, BlockInit.ENDERIUM_CASING::get))
                    .properties(p -> p.color(MaterialColor.WARPED_STEM))
                    .transform(BadIdeaTransformers.encasedShaft("enderium", () -> TextureShifts.ENDERIUM_CASING))
                    .transform(EncasingRegistry.addVariantTo(AllBlocks.SHAFT))
                    .transform(axeOrPickaxe())
                    .register();

    public static final BlockEntry<PublicEncasedCogwheelBlockCAE> ENDERIUM_ENCASED_COGWHEEL = REGISTRATEKJ
            .block("enderium_encased_cogwheel", p -> new PublicEncasedCogwheelBlockCAE(p, false, BlockInit.ENDERIUM_CASING::get))
            .properties(p -> p.color(MaterialColor.WARPED_STEM))
            .transform(BadIdeaTransformers.encasedCogwheel("enderium", () -> TextureShifts.ENDERIUM_CASING))
            .transform(EncasingRegistry.addVariantTo(AllBlocks.COGWHEEL))
            .onRegister(CreateRegistrate.connectedTextures(() -> new EncasedCogCTBehaviour(TextureShifts.ENDERIUM_CASING,
                    Couple.create(TextureShifts.ENDERIUM_ENCASED_COGWHEEL_SIDE,
                            TextureShifts.ENDERIUM_ENCASED_COGWHEEL_OTHERSIDE))))
            .transform(axeOrPickaxe())
            .register();

    public static final BlockEntry<PublicEncasedCogwheelBlockCAE> ENDERIUM_ENCASED_LARGE_COGWHEEL = REGISTRATEKJ
            .block("enderium_encased_large_cogwheel",
                    p -> new PublicEncasedCogwheelBlockCAE(p, true, BlockInit.ENDERIUM_CASING::get))
            .properties(p -> p.color(MaterialColor.WARPED_STEM))
            .transform(BadIdeaTransformers.encasedLargeCogwheel("enderium", () -> TextureShifts.ENDERIUM_CASING))
            .transform(EncasingRegistry.addVariantTo(AllBlocks.LARGE_COGWHEEL))
            .transform(axeOrPickaxe())
            .register();


    public static final BlockEntry<PublicEncasedShaftBlockCAE> SUPERCRITICAL_ENCASED_SHAFT =
            REGISTRATEKJ.block("supercritical_encased_shaft", p -> new PublicEncasedShaftBlockCAE(p, BlockInit.SUPERCRITICAL_CASING::get))
                    .properties(p -> p.color(MaterialColor.COLOR_BLACK))
                    .transform(BadIdeaTransformers.encasedShaft("supercritical", () -> TextureShifts.SUPERCRITICAL_CASING))
                    .transform(EncasingRegistry.addVariantTo(AllBlocks.SHAFT))
                    .transform(axeOrPickaxe())
                    .register();

    public static final BlockEntry<PublicEncasedCogwheelBlockCAE> SUPERCRITICAL_ENCASED_COGWHEEL = REGISTRATEKJ
            .block("supercritical_encased_cogwheel", p -> new PublicEncasedCogwheelBlockCAE(p, false, BlockInit.SUPERCRITICAL_CASING::get))
            .properties(p -> p.color(MaterialColor.COLOR_BLACK))
            .transform(BadIdeaTransformers.encasedCogwheel("supercritical", () -> TextureShifts.SUPERCRITICAL_CASING))
            .transform(EncasingRegistry.addVariantTo(AllBlocks.COGWHEEL))
            .onRegister(CreateRegistrate.connectedTextures(() -> new EncasedCogCTBehaviour(TextureShifts.SUPERCRITICAL_CASING,
                    Couple.create(TextureShifts.SUPERCRITICAL_ENCASED_COGWHEEL_SIDE,
                            TextureShifts.SUPERCRITICAL_ENCASED_COGWHEEL_OTHERSIDE))))
            .transform(axeOrPickaxe())
            .register();

    public static final BlockEntry<PublicEncasedCogwheelBlockCAE> SUPERCRITICAL_ENCASED_LARGE_COGWHEEL = REGISTRATEKJ
            .block("supercritical_encased_large_cogwheel",
                    p -> new PublicEncasedCogwheelBlockCAE(p, true, BlockInit.SUPERCRITICAL_CASING::get))
            .properties(p -> p.color(MaterialColor.COLOR_BLACK))
            .transform(BadIdeaTransformers.encasedLargeCogwheel("supercritical", () -> TextureShifts.SUPERCRITICAL_CASING))
            .transform(EncasingRegistry.addVariantTo(AllBlocks.LARGE_COGWHEEL))
            .transform(axeOrPickaxe())
            .register();

}
