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

import java.util.UUID;


public class MaryMod implements ModInitializer {

    public static final String MOD_ID = "mary-mod";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static final boolean DEBUG = FabricLoader.getInstance().isDevelopmentEnvironment();
    public static final UUID PARAMADA_UUID = UUID.fromString("a2d5d015-3f63-4831-8441-a96174639d82");

    @Override
    public void onInitialize() {
        ItemManager.registerModItems();
        BlockEntityManager.registerEntities();
        BlockManager.registerModBlocks();
        ModScreenHandlers.registerScreenHandlersForServer();
        AdvancementManager.registerCriterions();
        NetworkManager.registerC2SPackets();
    }
}
