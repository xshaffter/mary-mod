package com.xshaffter.marymod.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.xshaffter.marymod.MaryMod;
import com.xshaffter.marymod.screens.CandyMachineScreenHandler;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class CandyMachineScreen extends HandledScreen<CandyMachineScreenHandler> {
    private static final Identifier INVENTORY_TEXTURE =
            new Identifier(MaryMod.MOD_ID, "textures/gui/candy_machine/inventory.png");
    private static final Identifier FRONT_TEXTURE =
            new Identifier(MaryMod.MOD_ID, "textures/gui/candy_machine/front.png");
    private final PlayerInventory inventory;

    public CandyMachineScreen(CandyMachineScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.inventory = inventory;
    }

    @Override
    protected void init() {
        super.init();
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        if (this.inventory.player.isCreative()) {
            RenderSystem.setShaderTexture(0, INVENTORY_TEXTURE);
        } else {
            RenderSystem.setShaderTexture(0, FRONT_TEXTURE);
        }
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);
    }


    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
    }
}