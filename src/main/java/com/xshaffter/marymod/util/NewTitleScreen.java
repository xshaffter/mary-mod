package com.xshaffter.marymod.util;

import com.mojang.blaze3d.systems.RenderSystem;
import com.xshaffter.marymod.MaryMod;
import com.xshaffter.marymod.mixins.ScreenMixin;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.screen.ConnectScreen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.network.ServerAddress;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class NewTitleScreen extends TitleScreen {
    private static final Identifier SPLASH =
            new Identifier(MaryMod.MOD_ID, "textures/gui/background/main_menu.jpg");
    private static final Identifier MINECRAFT_TITLE_TEXTURE = new Identifier("textures/gui/title/minecraft.png");

    private final ServerInfo serverEntry = new ServerInfo(I18n.translate("selectServer.defaultName"), "54.39.26.111:25583", false);

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);

        int width = this.width;
        int height = this.height;

        int y = height / 4 + 48;
        int spacingY = 24;
        this.addDrawableChild(new ButtonWidget(width / 2 - 100, y + spacingY, 200, 20, Text.literal("Jugar"), button -> {
            assert NewTitleScreen.this.client != null;
            ConnectScreen.connect(NewTitleScreen.this, NewTitleScreen.this.client, ServerAddress.parse(serverEntry.address), serverEntry);
        }, ButtonWidget.EMPTY));

        drawCustomTitleScreen(matrixStack, width, height);
        drawMinecraftLogo(matrixStack);
        drawCopyrightNotice(matrixStack, width, height);
        drawTitleScreenButtons(matrixStack, mouseX, mouseY, partialTicks);
    }

    private void drawCopyrightNotice(MatrixStack matrixStack, int width, int height) {
        assert this.client != null;
        drawStringWithShadow(matrixStack, this.client.textRenderer, "Copyright Mojang AB. Do not distribute!",
                width - this.textRenderer.getWidth("Copyright Mojang AB. Do not distribute!") - 2,
                height - 10, 0xFFFFFFFF);
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
            this.drawTexture(matrixStack, x, (int) y, 0, 0, 99, 44);
            this.drawTexture(matrixStack, x + 99, (int) y, 129, 0, 27, 44);
            this.drawTexture(matrixStack, x + 99 + 26, (int) y, 126, 0, 3, 44);
            this.drawTexture(matrixStack, x + 99 + 26 + 3, (int) y, 99, 0, 26, 44);
            this.drawTexture(matrixStack, x + 155, (int) y, 0, 45, 155, 44);
        });
    }

    private void drawTitleScreenButtons(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        for (Drawable widget : ((ScreenMixin) this).getDrawables()) {
            if (widget instanceof ButtonWidget btn && isPlayBtn(btn)) {
                btn.active = false;
                btn.visible = false;
                continue;
            }
            widget.render(matrixStack, mouseX, mouseY, partialTicks);
        }
    }

    private boolean isPlayBtn(ButtonWidget button) {
        return button.getMessage().toString().equals("translation{key='menu.singleplayer', args=[]}") ||
                button.getMessage().toString().equals("translation{key='menu.multiplayer', args=[]}") ||
                button.getMessage().toString().equals("translation{key='menu.online', args=[]}");
    }
}