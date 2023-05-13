package com.xshaffter.marymod.blocks.entities;

import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

public class TradeInventory implements ImplementedInventory{

    public static final int CHEST_SIZE = 27;
    public DefaultedList<ItemStack> inventory = DefaultedList.ofSize(CHEST_SIZE, ItemStack.EMPTY);;

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }
}
