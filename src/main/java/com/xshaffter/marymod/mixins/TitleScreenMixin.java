package com.xshaffter.marymod.mixins;


import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(TitleScreen.class)
public interface TitleScreenMixin {
    @Accessor("splashText")
    void setSplashText(String value);
}
