package com.pino.cae.init;

import com.pino.cae.Cae;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class FluidInit {
    public static final ResourceLocation WATER_STILL_RL = new ResourceLocation("cae:block/lst_still");
    public static final ResourceLocation WATER_FLOWING_RL = new ResourceLocation("cae:block/lst_flow");
    public static final ResourceLocation WATER_OVERLAY_RL = new ResourceLocation("cae:block/lst_flow");

    public static final DeferredRegister<Fluid> FLUIDS
            = DeferredRegister.create(ForgeRegistries.FLUIDS, Cae.MOD_ID);

    /*
    public static final RegistryObject<FlowingFluid> LST_FLUID
            = FLUIDS.register("lst_fluid", () -> new ForgeFlowingFluid.Source(FluidInit.LST_PROPERTIES));

    public static final RegistryObject<FlowingFluid> LST_FLOWING
            = FLUIDS.register("lst_flowing", () -> new ForgeFlowingFluid.Flowing(FluidInit.LST_PROPERTIES));


    public static final ForgeFlowingFluid.Properties LST_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> LST_FLUID.get(), () -> LST_FLOWING.get(), FluidAttributes.builder(WATER_STILL_RL, WATER_FLOWING_RL)
            .density(15).luminosity(2).viscosity(5).sound(SoundEvents.MUSIC_END).overlay(WATER_OVERLAY_RL)
            .color(0xbffcba03)).slopeFindDistance(2).levelDecreasePerBlock(2)
            .block(() -> FluidInit.LST_BLOCK.get()).bucket(() -> ItemInit.LST_BUCKET.get());

    public static final RegistryObject<LiquidBlock> LST_BLOCK = BlockInit.BLOCKS.register("lst",
            () -> new LiquidBlock(() -> FluidInit.LST_FLUID.get(), BlockBehaviour.Properties.of(Material.WATER)
                    .noCollission().strength(-2f).noDrops()));


*/
    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
