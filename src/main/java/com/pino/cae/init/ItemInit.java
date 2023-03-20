package com.pino.cae.init;

import com.pino.cae.Cae;
import com.pino.cae.entity.ModEntityTypes;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.minecraft.world.item.Items.AIR;
import static net.minecraft.world.item.Items.APPLE;

public class ItemInit {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Cae.MOD_ID);
    public static class ModCreativeTab extends CreativeModeTab {
        private ModCreativeTab(int index, String label) {
            super(index, label);
        }

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(AIR);
        }
        public static final ModCreativeTab instance = new ModCreativeTab(CreativeModeTab.TABS.length, "Create Arcane Engineering");
        public static final RegistryObject<ForgeSpawnEggItem> ESSENTIALORB_SPAWN_EGG = ITEMS.register("orb_spawn_egg",
                () -> new ForgeSpawnEggItem(ModEntityTypes.ESSENTIALORB,0x948e8d,0x3b3635,
                        new Item.Properties().tab(ModCreativeTab.TAB_MISC)));
    }
}
