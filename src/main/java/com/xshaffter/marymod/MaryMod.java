package com.xshaffter.marymod;

import com.xshaffter.marymod.blocks.BlockManager;
import com.xshaffter.marymod.blocks.entities.BlockEntityManager;
import com.xshaffter.marymod.events.AdvancementManager;
import com.xshaffter.marymod.events.SoundManager;
import com.xshaffter.marymod.items.ItemManager;
import com.xshaffter.marymod.networking.NetworkManager;
import com.xshaffter.marymod.screens.handlers.ModScreenHandlers;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class MaryMod implements ModInitializer {

    public static final String MOD_ID = "mary-mod";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static final boolean DEBUG = FabricLoader.getInstance().isDevelopmentEnvironment();

    @Override
    public void onInitialize() {
        ItemManager.registerModItems();
        BlockEntityManager.registerEntities();
        BlockManager.registerModBlocks();
        ModScreenHandlers.registerScreenHandlersForServer();
        AdvancementManager.registerCriterions();
        SoundManager.registerSounds();
        NetworkManager.registerC2SPackets();
    }
}
