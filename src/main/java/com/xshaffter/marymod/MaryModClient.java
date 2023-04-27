package com.xshaffter.marymod;

import com.xshaffter.marymod.blocks.BlockManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

@Environment(EnvType.CLIENT)
public class MaryModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(BlockManager.MARY_COIN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockManager.MARY_BLUE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockManager.CANDY_MACHINE, RenderLayer.getCutout());

    }
}
