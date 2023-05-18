package com.xshaffter.marymod.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.xshaffter.marymod.MaryMod;
import com.xshaffter.marymod.MaryModClient;
import com.xshaffter.marymod.mixins.ScreenMixin;
import com.xshaffter.marymod.mixins.TitleScreenMixin;
import com.xshaffter.marymod.screens.components.PlayButtonWidget;
import com.xshaffter.marymod.updater.ResourceDownloader;
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

import java.io.IOException;

public class NewTitleScreen extends TitleScreen {
    private static final Identifier SPLASH =
            new Identifier(MaryMod.MOD_ID, "textures/gui/background/main_menu.png");
    public static final Identifier MINECRAFT_TITLE_TEXTURE = new Identifier(MaryMod.MOD_ID, "textures/gui/title/marycraft_logo.png");

    private final ServerInfo serverEntry = new ServerInfo(I18n.translate("selectServer.defaultName"), "54.39.26.111:25583", false);
    private final ServerInfo localEntry = new ServerInfo(I18n.translate("selectServer.defaultName"), "localhost", false);

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        ((TitleScreenMixin) this).setSplashText("");
        int width = this.width;
        int height = this.height;
        int y = height / 4 + 48;
        int buttonHeight = 20;
        int Vgap = 4;
        int spacingY = buttonHeight + Vgap;
        if (!MaryMod.DEBUG) {
            var playBtn = new PlayButtonWidget(width / 2 - 100, y + spacingY, 200, 20, button -> {
                assert NewTitleScreen.this.client != null;
                ConnectScreen.connect(NewTitleScreen.this, NewTitleScreen.this.client, ServerAddress.parse(serverEntry.address), serverEntry);
            });

            var updateBtn = new ButtonWidget(width / 2 - 100, y + spacingY * 2, 200, 20, Text.literal("Actualizar"), button -> {
                var downloader = new ResourceDownloader();
                try {
                    downloader.CheckModpackVersion();
                    assert this.client != null;
                    this.client.setScreen(new RestartGameScreen());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }, ButtonWidget.EMPTY);


            this.addDrawableChild(playBtn);
            this.addDrawableChild(updateBtn);
        } else {
            var playBtn = new PlayButtonWidget(width / 2 - 100, y + spacingY, 200, 20, button -> {
                assert NewTitleScreen.this.client != null;
                ConnectScreen.connect(NewTitleScreen.this, NewTitleScreen.this.client, ServerAddress.parse(localEntry.address), localEntry);
            });
            this.addDrawableChild(playBtn);
        }

        drawCustomTitleScreen(matrixStack, width, height);
        drawMinecraftLogo(matrixStack);
        drawCopyrightNotice(matrixStack);
        drawTitleScreenButtons(matrixStack, mouseX, mouseY, partialTicks);
    }

    private void drawCopyrightNotice(MatrixStack matrixStack) {
        assert this.client != null;
        var text = "MaryMod %s".formatted(MaryModClient.getModVersion());
        drawStringWithShadow(matrixStack, this.client.textRenderer, text, 2, 2, 0xFFFFFFFF);
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
            drawTexture(matrixStack, x - 15, y, this.getZOffset(), 0, 0, 310, 44, 310, 44);
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
        return button.getMessage().toString().equals("translation{key='menu.multiplayer', args=[]}") ||
                button.getMessage().toString().equals("translation{key='menu.online', args=[]}") ||
                (!MaryMod.DEBUG && button.getMessage().toString().equals("translation{key='menu.singleplayer', args=[]}"));
    }
}