package com.pino.cae.events;

import com.mojang.datafixers.DataFix;
import com.mojang.logging.LogUtils;
import com.pino.cae.Cae;
import com.pino.cae.init.BlockInit;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import slimeknights.mantle.registration.RegistrationHelper;

import static com.pino.cae.Cae.MOD_ID;

@Mod.EventBusSubscriber
public class Datafix {
    private static final Logger LOGGER = LogUtils.getLogger();

    @Nullable
    public static Block missingBlock(String name) {
        LOGGER.debug(name);
        return switch (name) {
            case "gold_casing" -> BlockInit.GOLD_CASING.get();
            case "zinc_casing" -> BlockInit.ZINC_CASING.get();
            case "enderium_casing" -> BlockInit.ENDERIUM_CASING.get();
            case "supercritical_casing" -> BlockInit.SUPERCRITICAL_CASING.get();
            default -> null;
        };
    }


    @SubscribeEvent
    public static void missingItems(final RegistryEvent.MissingMappings<Item> event) {
        LOGGER.debug(event.toString());
        LOGGER.debug(String.valueOf(event.getName()));
        RegistrationHelper.handleMissingMappings(event, "kubejs", name -> {
            LOGGER.debug(name);
            switch(name) {
                case "gold_casing" -> BlockInit.GOLD_CASING.get().asItem();
                case "zinc_casing" -> BlockInit.ZINC_CASING.get().asItem();
                case "enderium_casing" -> BlockInit.ENDERIUM_CASING.get().asItem();
                case "supercritical_casing" -> BlockInit.SUPERCRITICAL_CASING.get().asItem();
            }
            ItemLike block = missingBlock(name);
            return block == null ? null : block.asItem();
        });
        RegistrationHelper.handleMissingMappings(event, MOD_ID, name -> {
            LOGGER.debug(name);
            switch(name) {
                case "gold_casing" -> BlockInit.GOLD_CASING.get().asItem();
                case "zinc_casing" -> BlockInit.ZINC_CASING.get().asItem();
                case "enderium_casing" -> BlockInit.ENDERIUM_CASING.get().asItem();
                case "supercritical_casing" -> BlockInit.SUPERCRITICAL_CASING.get().asItem();
            }
            ItemLike block = missingBlock(name);
            return block == null ? null : block.asItem();
        });
    }


    @SubscribeEvent
    public static void missingBlocks(final RegistryEvent.MissingMappings<Block> event) {
        LOGGER.debug("ÄAAAAAAAAAAAAAAA");
        RegistrationHelper.handleMissingMappings(event, MOD_ID, Datafix::missingBlock);
        RegistrationHelper.handleMissingMappings(event, "kubejs", Datafix::missingBlock);
    }

}
