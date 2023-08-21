package com.pino.cae.init;

import com.pino.cae.Cae;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.minecraft.world.item.Items.AIR;

public class ItemInit {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Cae.MOD_ID);
    public static final RegistryObject<ForgeSpawnEggItem> ESSENTIALORB_SPAWN_EGG = ITEMS.register("orb_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.ESSENTIALORB,0x948e8d,0x3b3635,
                    new Item.Properties().tab(ModCreativeTab.TAB_MISC)));
    /*
    public static final RegistryObject<Item> LST_BUCKET = ITEMS.register("lst_bucket",
            () -> new BucketItem(FluidInit.LST_FLUID,
                    new Item.Properties().tab(ModCreativeTab.instance).stacksTo(1)));

     */
    public static class ModCreativeTab extends CreativeModeTab {
        private ModCreativeTab(int index, String label) {
            super(index, label);
        }

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(AIR);
        }
        public static final ModCreativeTab instance = new ModCreativeTab(CreativeModeTab.TABS.length, "Create Arcane Engineering");



    }
}
