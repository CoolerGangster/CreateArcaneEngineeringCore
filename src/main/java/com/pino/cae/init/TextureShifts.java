package com.pino.cae.init;


import com.pino.cae.Cae;
import com.simibubi.create.foundation.block.connected.AllCTTypes;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.foundation.block.connected.CTSpriteShifter;
import com.simibubi.create.foundation.block.connected.CTType;
import org.jetbrains.annotations.NotNull;


public class TextureShifts{
    static CTSpriteShiftEntry omni(String name) {
        return getCT(AllCTTypes.OMNIDIRECTIONAL, name);
    }
    private static CTSpriteShiftEntry horizontal(String name) {
        return getCT(AllCTTypes.HORIZONTAL, name);
    }
    private static CTSpriteShiftEntry vertical(String name) {return getCT(AllCTTypes.VERTICAL, name);}
    private static CTSpriteShiftEntry getCT(CTType type, String blockTextureName, String connectedTextureName) {
        return CTSpriteShifter.getCT(type, Cae.asResource("block/" + blockTextureName), Cae.asResource("block/" + connectedTextureName + "_connected"));
    }

    private static CTSpriteShiftEntry getCT(CTType type, String blockTextureName) {
        return getCT(type, blockTextureName, blockTextureName);
    }

    public static final CTSpriteShiftEntry GOLD_CASING = omni("gold_casing");
    public static final CTSpriteShiftEntry ZINC_CASING = omni("zinc_casing");
    public static final CTSpriteShiftEntry SUPERCRITICAL_CASING = omni("supercritical_casing");
    public static final CTSpriteShiftEntry ENDERIUM_CASING = omni("enderium_casing");

    public static final CTSpriteShiftEntry
            GOLD_ENCASED_COGWHEEL_SIDE = vertical("gold_encased_cogwheel_side"),
            GOLD_ENCASED_COGWHEEL_OTHERSIDE = horizontal("gold_encased_cogwheel_side"),

            ZINC_ENCASED_COGWHEEL_SIDE = vertical("zinc_encased_cogwheel_side"),
            ZINC_ENCASED_COGWHEEL_OTHERSIDE = horizontal("zinc_encased_cogwheel_side"),

            ENDERIUM_ENCASED_COGWHEEL_SIDE = vertical("enderium_encased_cogwheel_side"),
            ENDERIUM_ENCASED_COGWHEEL_OTHERSIDE = horizontal("enderium_encased_cogwheel_side"),

            SUPERCRITICAL_ENCASED_COGWHEEL_SIDE = vertical("supercritical_encased_cogwheel_side"),
            SUPERCRITICAL_ENCASED_COGWHEEL_OTHERSIDE = horizontal("supercritical_encased_cogwheel_side");

}
