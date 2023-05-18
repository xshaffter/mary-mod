package com.xshaffter.marymod;

import com.xshaffter.marymod.blocks.BlockManager;
import com.xshaffter.marymod.events.KeyboardHandler;
import com.xshaffter.marymod.events.ReplaceScreens;
import com.xshaffter.marymod.networking.NetworkManager;
import com.xshaffter.marymod.screens.CandyMachineScreen;
import com.xshaffter.marymod.screens.ModScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.Version;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;

import java.io.FileInputStream;
import java.nio.file.Path;
import java.util.Properties;

@Environment(EnvType.CLIENT)
public class MaryModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(BlockManager.MARY_COIN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockManager.MARY_BLUE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockManager.CANDY_MACHINE, RenderLayer.getCutout());

        ScreenEvents.BEFORE_INIT.register(new ReplaceScreens());
        HandledScreens.register(ModScreenHandlers.CANDY_MACHINE_SCREEN_HANDLER, CandyMachineScreen::new);
        NetworkManager.registerS2CPackets();
        KeyboardHandler.register();
    }

    public static Path getClientPath() {
        return FabricLoader.getInstance().getGameDir();
    }

    public static String getModVersion() {
        return "1.3";
    }
}
