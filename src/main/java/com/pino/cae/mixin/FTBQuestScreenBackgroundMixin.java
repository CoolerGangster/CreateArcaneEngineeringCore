package com.pino.cae.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.pino.cae.mixinInterfaces.INowHaveABackgroundImage;
import dev.ftb.mods.ftblibrary.ui.BaseScreen;
import dev.ftb.mods.ftblibrary.ui.Theme;
import dev.ftb.mods.ftbquests.gui.quests.QuestScreen;
import dev.ftb.mods.ftbquests.quest.Chapter;
import dev.ftb.mods.ftbquests.quest.theme.property.ThemeProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(QuestScreen.class)
public abstract class FTBQuestScreenBackgroundMixin {

    @Shadow
    public Chapter selectedChapter;

    @Redirect(
            remap = false,
            method = "drawBackground",
            at = @At(
                    value = "INVOKE",
                    target = "Ldev/ftb/mods/ftblibrary/ui/BaseScreen;drawBackground("
                            + "Lcom/mojang/blaze3d/vertex/PoseStack;"
                            + "Ldev/ftb/mods/ftblibrary/ui/Theme;"
                            + "IIII"
                            + ")V"
            )
    )
    private void doNotDrawDefaultSquareBackground(BaseScreen instance, PoseStack matrixStack, Theme theme, int x, int y, int w, int h) {
        if (this.selectedChapter == null) {
            ThemeProperties.BACKGROUND.get().draw(matrixStack, x, y, w, h);
            return;
        }
        // is this cursed?
        ((INowHaveABackgroundImage) (Object) this.selectedChapter).cae_getBackgroundImage().draw(matrixStack, x, y, w, h);
    }
}
