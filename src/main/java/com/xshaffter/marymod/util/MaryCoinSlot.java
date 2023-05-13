package com.xshaffter.marymod.util;

import com.xshaffter.marymod.items.ItemManager;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class MaryCoinSlot extends Slot {

    public MaryCoinSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return stack.isOf(ItemManager.MARY_COIN_ITEM);
    }
}
