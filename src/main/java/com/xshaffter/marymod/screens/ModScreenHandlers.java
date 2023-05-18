package com.xshaffter.marymod.screens;

import com.xshaffter.marymod.MaryMod;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModScreenHandlers {
    public static final ScreenHandlerType<CandyMachineScreenHandler> CANDY_MACHINE_SCREEN_HANDLER = new ScreenHandlerType<>(CandyMachineScreenHandler::new);

    public static void registerScreenHandlersForServer() {
        Registry.register(Registry.SCREEN_HANDLER, new Identifier(MaryMod.MOD_ID, "candy_machine_screen_handler"), CANDY_MACHINE_SCREEN_HANDLER);
    }
}
