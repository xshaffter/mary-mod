package com.xshaffter.marymod;

import com.xshaffter.marymod.blocks.BlockManager;
import com.xshaffter.marymod.blocks.entities.BlockEntityManager;
import com.xshaffter.marymod.items.ItemManager;
import com.xshaffter.marymod.screens.ModScreenHandlers;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class MaryMod implements ModInitializer {

    public static final String MOD_ID = "mary-mod";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ItemManager.registerModItems();
        BlockEntityManager.registerEntities();
        BlockManager.registerModBlocks();
        ModScreenHandlers.registerAllScreenHandlers();
    }
}
