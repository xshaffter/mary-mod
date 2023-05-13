package com.xshaffter.marymod.blocks.entities;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.random.Random;

public class TradeInventory implements ImplementedInventory {

    public static final int CHEST_SIZE = 27;
    public DefaultedList<ItemStack> inventory = DefaultedList.ofSize(CHEST_SIZE, ItemStack.EMPTY);
    ;

    public int chooseNonEmptySlot(Random random) {
        int i = -1;
        int j = 1;

        for (int k = 0; k < this.inventory.size(); ++k) {
            if (!((ItemStack) this.inventory.get(k)).isEmpty() && random.nextInt(j++) == 0) {
                i = k;
            }
        }

        return i;
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }
}
