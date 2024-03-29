package com.xshaffter.marymod.screens.components;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class CustomButtonWidget extends ButtonWidget {
    public final Identifier BUTTON_TEXTURE;

    public CustomButtonWidget(int x, int y, int width, int height, Identifier id, PressAction onPress) {
        this(x, y, width, height, id, onPress, EMPTY);
    }

    public CustomButtonWidget(int x, int y, int width, int height, Identifier id, PressAction onPress, TooltipSupplier tooltipSupplier) {
        super(x, y, width, height, Text.literal("Jugar"), onPress, tooltipSupplier);
        this.BUTTON_TEXTURE = id;
    }

    @Override
    public void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, BUTTON_TEXTURE);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, this.alpha);

        int i = this.getYImage(this.isHovered());
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();

        drawTexture(matrices, this.x, this.y, this.width, this.height, 0, i * 18, 18, 18, 18, 54);
        this.renderBackground(matrices, minecraftClient, mouseX, mouseY);
        if (this.isHovered()) {
            this.renderTooltip(matrices, mouseX, mouseY);
        }
    }
}
