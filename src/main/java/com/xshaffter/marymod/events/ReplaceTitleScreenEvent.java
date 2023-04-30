package com.xshaffter.marymod.events;

import com.xshaffter.marymod.util.NewTitleScreen;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;

public class ReplaceTitleScreenEvent implements ScreenEvents.BeforeInit{
    @Override
    public void beforeInit(MinecraftClient client, Screen screen, int scaledWidth, int scaledHeight) {
        if (screen instanceof TitleScreen && !(screen instanceof NewTitleScreen)) {
            client.setScreen(new NewTitleScreen());
        }
    }
}
