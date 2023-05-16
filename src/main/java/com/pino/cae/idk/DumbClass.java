package com.pino.cae.idk;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DumbClass implements ICapabilityProvider, ICapabilitySerializable<CompoundTag> {
    private DumbClass thirst = null;

    public void saveNBTData(CompoundTag tag){

    }

    private final DumbClassInterface backend = new MyCapImplemt();
    public final LazyOptional<DumbClassInterface> optionalData = LazyOptional.of(() -> backend);
    public static final ResourceLocation IDENTIFIER = new ResourceLocation("cae", "properties");

    private DumbClass createPlayerThirst() {
        if (this.thirst == null){
            this.thirst = new DumbClass();
        }
        return this.thirst;
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return myCapa.INSTANCE.orEmpty(cap, this.optionalData);
    }


    @Override
    public CompoundTag serializeNBT() {
        return this.backend.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.backend.deserializeNBT(nbt);
    }
}
