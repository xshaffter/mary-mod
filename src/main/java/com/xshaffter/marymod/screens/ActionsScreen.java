package com.xshaffter.marymod.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.xshaffter.marymod.MaryMod;
import com.xshaffter.marymod.mixins.ScreenMixin;
import com.xshaffter.marymod.networking.NetworkManager;
import com.xshaffter.marymod.networking.packets.ActionCommand;
import com.xshaffter.marymod.screens.components.AdvancementButton;
import com.xshaffter.marymod.screens.components.CustomButtonWidget;
import com.xshaffter.marymod.screens.handlers.ActionsScreenHandler;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.item.BundleTooltipData;
import net.minecraft.client.item.TooltipData;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.awt.*;
import java.util.Collections;
import java.util.function.Consumer;

public class ActionsScreen extends HandledScreen<ActionsScreenHandler> {
    private static final Identifier INVENTORY_TEXTURE =
            new Identifier(MaryMod.MOD_ID, "textures/gui/actions/panel.png");
    private static final Identifier ACTION_BUTTON_TEXTURE =
            new Identifier(MaryMod.MOD_ID, "textures/gui/actions/panel_button.png");
    private static final Identifier BACK_BUTTON_TEXTURE =
            new Identifier(MaryMod.MOD_ID, "textures/gui/actions/back_panel_button.png");

    public ActionsScreen(ActionsScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    protected void drawForeground(final MatrixStack matrices, final int mouseX, final int mouseY) {
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, INVENTORY_TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);
    }

    @Override
    protected void init() {
        super.init();

        int startX = 13;
        int startY = 26;
        int buttonSize = 16;
        int maxColumnsPerRow = 8;
        int buttonGap = 2;

        var elements = ActionCommand.values();

        int currentRow = 0;
        int currentColumn = 0;

        for (var element : elements) {

            int finalCurrentRow = currentRow;
            int finalCurrentColumn = currentColumn;
            var tooltipSupplier = new ButtonWidget.TooltipSupplier() {
                @Override
                public void onTooltip(ButtonWidget button, MatrixStack matrices1, int mouseX1, int mouseY1) {
                    assert ActionsScreen.this.client != null;
                    ActionsScreen.this.renderTooltip(matrices1, element.tooltip, mouseX1, mouseY1);
                }

                @Override
                public void supply(Consumer<Text> consumer) {
                    consumer.accept(element.tooltip);
                }
            };
            ButtonWidget button;
            if (element.isAdvancement()) {
                button = new AdvancementButton(x + startX + (buttonSize + buttonGap) * currentColumn, y + startY + (buttonSize + buttonGap) * currentRow, buttonSize, buttonSize, (unused) -> {
                    var buffer = PacketByteBufs.create();
                    var actionInt = finalCurrentColumn + (finalCurrentRow * maxColumnsPerRow);
                    buffer.writeByte(actionInt);
                    ClientPlayNetworking.send(NetworkManager.COMMAND_ACTION_ID, buffer);
                }, tooltipSupplier);
            } else {
                button = new CustomButtonWidget(x + startX + (buttonSize + buttonGap) * currentColumn, y + startY + (buttonSize + buttonGap) * currentRow, buttonSize, buttonSize, ACTION_BUTTON_TEXTURE, (unused) -> {
                    var buffer = PacketByteBufs.create();
                    var actionInt = finalCurrentColumn + (finalCurrentRow * maxColumnsPerRow);
                    buffer.writeByte(actionInt);
                    ClientPlayNetworking.send(NetworkManager.COMMAND_ACTION_ID, buffer);
                }, tooltipSupplier);
            }

            this.addDrawableChild(button);

            if (++currentColumn == maxColumnsPerRow) {
                currentColumn = 0;
                currentRow++;
            }
        }
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);

        drawButtons(matrices, mouseX, mouseY, delta);
    }

    private void drawButtons(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        for (Drawable widget : ((ScreenMixin) this).getDrawables()) {
            widget.render(matrixStack, mouseX, mouseY, partialTicks);
        }
    }
}
