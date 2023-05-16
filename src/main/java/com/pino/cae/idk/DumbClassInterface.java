package com.pino.cae.idk;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public interface DumbClassInterface extends ICapabilitySerializable<CompoundTag> {
    String getValue();

    int getMyInt();
    void setMyValue(String myValue);
    void setMyInt(int myInt);
}
