package com.xshaffter.marymod.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.xshaffter.marymod.MaryMod;
import com.xshaffter.marymod.mixins.ScreenMixin;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class RestartGameScreen extends Screen {
    private static final Identifier SPLASH =
            new Identifier(MaryMod.MOD_ID, "textures/gui/background/main_menu.png");
    private static final Identifier MINECRAFT_TITLE_TEXTURE = new Identifier(MaryMod.MOD_ID, "textures/gui/title/marycraft.png");

    protected RestartGameScreen() {
        super(Text.literal("Reiniia el juego"));
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {

        int width = this.width;
        int height = this.height;

        int y = height / 4 + 48;
        int spacingY = 24;

        var updateBtn = new ButtonWidget(width / 2 - 100, y + spacingY * 2, 200, 20, Text.literal("Cerrar"), button -> {
            assert this.client != null;
            this.client.scheduleStop();
        }, ButtonWidget.EMPTY);

        this.addDrawableChild(updateBtn);
        drawCustomTitleScreen(matrixStack, width, height);
        drawMinecraftLogo(matrixStack);
        drawRestartNotice(matrixStack, width, height);
        drawTitleScreenButtons(matrixStack, mouseX, mouseY, partialTicks);
    }

    private void drawRestartNotice(MatrixStack matrixStack, int width, int height) {
        assert this.client != null;
        final String text = "Porfavor reinicia el juego";
        int y = height / 4 + 48;
        int spacingY = 24;
        int textWidth = this.textRenderer.getWidth(text);


        drawStringWithShadow(matrixStack, this.client.textRenderer, text,
                width / 2 - textWidth / 2 - 2,
                y + spacingY, 0xFFFFFFFF);
    }

    private void drawCustomTitleScreen(MatrixStack matrixStack, int width, int height) {
        RenderSystem.enableTexture();
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        RenderSystem.setShaderTexture(0, SPLASH);
        drawTexture(matrixStack, 0, 0, 0, 0, this.width, this.height, width, height);
    }

    private void drawMinecraftLogo(MatrixStack matrixStack) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, MINECRAFT_TITLE_TEXTURE);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);

        this.drawWithOutline(this.width / 2 - 137, 30, (x, y) -> {
            this.drawTexture(matrixStack, x, y, 0, 0, 155, 44);
            this.drawTexture(matrixStack, x + 155, y, 0, 45, 155, 44);
        });
    }

    private void drawTitleScreenButtons(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        for (Drawable widget : ((ScreenMixin) this).getDrawables()) {
            widget.render(matrixStack, mouseX, mouseY, partialTicks);
        }
    }
}
