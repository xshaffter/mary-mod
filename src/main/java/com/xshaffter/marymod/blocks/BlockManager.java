package com.xshaffter.marymod.blocks;

import com.xshaffter.marymod.MaryMod;
import com.xshaffter.marymod.blocks.custom.PoroGalleta;
import com.xshaffter.marymod.items.ItemGroups;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockManager {
    public static final Block MARY_COIN = new MaryCoin();
    public static final Block MARY_BLUE = new MaryBlue();
    public static final Block PORO_GALLETA = new PoroGalleta();
    public static final Block CANDY_MACHINE = new DispenserMachine();


    private static void registerBlock(String name, Block block, BlockItem blockItem) {
        Registry.register(Registry.BLOCK, new Identifier(MaryMod.MOD_ID, name), block);
        registerBlockItem(name, blockItem);
    }

    private static void registerBlockItem(String name, BlockItem blockItem) {
        Registry.register(Registry.ITEM, new Identifier(MaryMod.MOD_ID, name), blockItem);
    }

    public static void registerModBlocks() {
        registerBlock("mary_coin", MARY_COIN, new BlockItem(MARY_COIN, new FabricItemSettings().fireproof().maxCount(64).group(ItemGroups.MARY_MOD_GROUP)));
        registerBlock("mary_blue", MARY_BLUE, new BlockItem(MARY_BLUE, new FabricItemSettings().fireproof().maxCount(1).group(ItemGroups.MARY_MOD_GROUP)));
        registerBlock("poro_galletas", PORO_GALLETA, new BlockItem(PORO_GALLETA, new FabricItemSettings().fireproof().maxCount(64).group(ItemGroups.MARY_MOD_GROUP)));
        registerBlock("candy_machine", CANDY_MACHINE, new BlockItem(CANDY_MACHINE, new FabricItemSettings().fireproof().maxCount(64).group(ItemGroups.MARY_MOD_GROUP)));
    }
}