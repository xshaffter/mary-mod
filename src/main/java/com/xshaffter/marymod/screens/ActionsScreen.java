package com.xshaffter.marymod.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.xshaffter.marymod.MaryMod;
import com.xshaffter.marymod.mixins.ScreenMixin;
import com.xshaffter.marymod.networking.NetworkManager;
import com.xshaffter.marymod.screens.components.CustomButtonWidget;
import com.xshaffter.marymod.screens.handlers.ActionsScreenHandler;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ActionsScreen extends HandledScreen<ActionsScreenHandler> {
    private static final Identifier INVENTORY_TEXTURE =
            new Identifier(MaryMod.MOD_ID, "textures/gui/actions/panel.png");
    private final PlayerInventory inventory;

    public ActionsScreen(ActionsScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.inventory = inventory;
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
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);

        int startX = 4;
        int startY = 16;
        int buttonSize = 16;
        int columns = 6;
        int rows = 2;
        int buttonGap = 2;

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                int constRow = row;
                int constCol = column;
                this.addDrawableChild(new CustomButtonWidget(x + startX + (buttonSize + buttonGap) * column, y + startY + (buttonSize + buttonGap) * row, buttonSize, buttonSize, new Identifier(MaryMod.MOD_ID, "textures/gui/actions/1_button.png"), (unused) -> {
                    var buffer = PacketByteBufs.create();
                    buffer.writeByte(constCol + (constRow * columns));
                    ClientPlayNetworking.send(NetworkManager.COMMAND_ACTION_ID, buffer);
                }));
            }
        }

        drawButtons(matrices, mouseX, mouseY, delta);
    }
    private void drawButtons(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        for (Drawable widget : ((ScreenMixin) this).getDrawables()) {
            widget.render(matrixStack, mouseX, mouseY, partialTicks);
        }
    }
}
