package com.pino.cae.idk;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MyCapImplemt implements DumbClassInterface{
    private String myValue = "";
    private int myInt = 5;
    @Override
    public String getValue() {
        return this.myValue;
    }
    @Override
    public int getMyInt() {
        return this.myInt;
    }

    @Override
    public void setMyValue(String myValue) {
        this.myValue = myValue;
    }
    @Override
    public void setMyInt(int myInt) {
        this.myInt = myInt;
    }
    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return null;
    }

    @Override
    public CompoundTag serializeNBT() {
        final CompoundTag tag = new CompoundTag();
        tag.putString("kubejsdoshit", this.myValue);
        tag.putInt("robert", this.myInt);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.myValue = nbt.getString("kubejsdoshit");
        this.myInt = nbt.getInt("robert");
    }
}
