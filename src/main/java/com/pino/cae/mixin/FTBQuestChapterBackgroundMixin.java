package com.pino.cae.mixin;

import com.pino.cae.mixinInterfaces.INowHaveABackgroundImage;
import dev.ftb.mods.ftblibrary.config.ConfigGroup;
import dev.ftb.mods.ftblibrary.icon.Icon;
import dev.ftb.mods.ftbquests.quest.Chapter;
import dev.ftb.mods.ftbquests.quest.theme.property.ThemeProperties;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(Chapter.class)
public abstract class FTBQuestChapterBackgroundMixin implements INowHaveABackgroundImage {
    @Unique
    private Icon cae_background = null;

    @Unique
    private String cae_backgroundId = "unset";

    @Unique
    @OnlyIn(Dist.CLIENT)
    public Icon cae_getBackgroundImage() {
        if (cae_background == null) return ThemeProperties.BACKGROUND.get();
        return cae_background;
    }

    @Unique
    private void cae_updateBackgroundId(String id) {
        cae_backgroundId = id;
        if (!Objects.equals(cae_backgroundId, "unset")) {
            cae_background = Icon.getIcon(cae_backgroundId);
        }
    }

    @Inject(method = "getConfig", at = @At("TAIL"), remap = false)
    public void configExtension(ConfigGroup config, CallbackInfo ci) {
        // prolly unintented way of getting a resourcelocation...
        config.addString("cae_background", cae_backgroundId, this::cae_updateBackgroundId, "unset");
    }

    @Inject(method = "writeData", at = @At("TAIL"), remap = false)
    public void writeDataExtension(CompoundTag nbt, CallbackInfo ci) {
        nbt.putString("cae_background", cae_backgroundId);
    }

    @Inject(method = "readData", at = @At("TAIL"), remap = false)
    public void readDataExtension(CompoundTag nbt, CallbackInfo ci) {
        cae_updateBackgroundId(nbt.getString("cae_background"));
    }

    @Inject(method = "writeNetData", at = @At("TAIL"), remap = false)
    public void writeNetDataExtension(FriendlyByteBuf buffer, CallbackInfo ci) {
        buffer.writeUtf(cae_backgroundId, 32767);
    }
    @Inject(method = "readNetData", at = @At("TAIL"), remap = false)
    public void readNetDataExtension(FriendlyByteBuf buffer, CallbackInfo ci) {
        cae_updateBackgroundId(buffer.readUtf(32767));
    }
}
