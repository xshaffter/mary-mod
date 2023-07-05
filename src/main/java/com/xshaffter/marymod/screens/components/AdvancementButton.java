package com.xshaffter.marymod.screens.components;

import com.mojang.blaze3d.systems.RenderSystem;
import com.xshaffter.marymod.MaryMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class AdvancementButton extends CustomButtonWidget {
    public static final Identifier BUTTON_TEXTURE = new Identifier(MaryMod.MOD_ID, "textures/gui/actions/panel_button_advancement.png");

    public AdvancementButton(int x, int y, int width, int height, PressAction onPress) {
        this(x, y, width, height, onPress, EMPTY);
    }

    public AdvancementButton(int x, int y, int width, int height, PressAction onPress, TooltipSupplier tooltipSupplier) {
        super(x, y, width, height, BUTTON_TEXTURE, onPress, tooltipSupplier);
    }
}
