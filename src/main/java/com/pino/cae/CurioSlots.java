package com.pino.cae;


import net.minecraft.resources.ResourceLocation;
import top.theillusivec4.curios.api.SlotTypeMessage.Builder;

import java.util.Optional;

public enum CurioSlots {//Code from SonaMorningstar
    GEMS("gem", 4, new ResourceLocation(Cae.MOD_ID, "slots/gem"));

    final String id;
    final int priority;
    final ResourceLocation icon;

    CurioSlots(String id, int priority, ResourceLocation icon) {
        this.id = id;
        this.priority = priority;
        this.icon = icon;
    }

    public static Optional<CurioSlots> findPreset(String id) {
        try {
            return Optional.of(CurioSlots.valueOf(id.toUpperCase()));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    public String getIdentifier() {return this.id; }

    public String getLangIdentifier() { return "curios.identifier." + getIdentifier(); }

    public ResourceLocation getIcon() { return this.icon; }

    public Builder getMessageBuilder() {
        return new Builder(this.id).priority(this.priority).icon(this.icon);
    }
}