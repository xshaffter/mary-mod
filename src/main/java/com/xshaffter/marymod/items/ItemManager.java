package com.xshaffter.marymod.items;

import com.xshaffter.marymod.MaryMod;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemManager {
    public static final Item MARY_COIN_ITEM = new MaryCoinItem();
    public static final Item CIGGAR = new Ciggar();


    private static void registerItem(String name, Item item) {
        Registry.register(Registry.ITEM, new Identifier(MaryMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        registerItem("ciggar", CIGGAR);
        registerItem("mary_coin_item", MARY_COIN_ITEM);
    }
}