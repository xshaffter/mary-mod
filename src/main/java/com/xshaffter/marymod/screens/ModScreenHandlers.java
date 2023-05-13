package com.xshaffter.marymod.screens;

import net.minecraft.screen.ScreenHandlerType;

public class ModScreenHandlers {
    public static ScreenHandlerType<CandyMachineScreenHandler> CANDY_MACHINE_SCREEN_HANDLER;

    public static void registerAllScreenHandlers() {
        CANDY_MACHINE_SCREEN_HANDLER = new ScreenHandlerType<>(CandyMachineScreenHandler::new);
    }
}
