package com.xshaffter.marymod;

import com.xshaffter.marymod.blocks.BlockManager;
import com.xshaffter.marymod.events.ReplaceTitleScreenEvent;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.minecraft.client.render.RenderLayer;

@Environment(EnvType.CLIENT)
public class MaryModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(BlockManager.MARY_COIN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockManager.MARY_BLUE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockManager.CANDY_MACHINE, RenderLayer.getCutout());

        ScreenEvents.BEFORE_INIT.register(new ReplaceTitleScreenEvent());
    }
}
