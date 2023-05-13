package com.xshaffter.marymod;

import com.xshaffter.marymod.blocks.BlockManager;
import com.xshaffter.marymod.events.ReplaceScreens;
import com.xshaffter.marymod.screens.CandyMachineScreen;
import com.xshaffter.marymod.screens.ModScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;

import java.nio.file.Path;

@Environment(EnvType.CLIENT)
public class MaryModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(BlockManager.MARY_COIN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockManager.MARY_BLUE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockManager.CANDY_MACHINE, RenderLayer.getCutout());

        ScreenEvents.BEFORE_INIT.register(new ReplaceScreens());
        HandledScreens.register(ModScreenHandlers.CANDY_MACHINE_SCREEN_HANDLER, CandyMachineScreen::new);
    }

    public static Path getClientPath() {
        return FabricLoader.getInstance().getGameDir();
    }
}
