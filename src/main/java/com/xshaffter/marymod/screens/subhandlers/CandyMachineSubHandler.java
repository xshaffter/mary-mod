package com.xshaffter.marymod.screens.subhandlers;

import com.xshaffter.marymod.screens.CandyMachineScreenHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class CandyMachineSubHandler {
    private final CandyMachineScreenHandler screen;
    public final Inventory inventory;

    public CandyMachineSubHandler(CandyMachineScreenHandler screen, Inventory inventory, PlayerEntity player) {
        this.screen = screen;
        this.inventory = inventory;
        inventory.onOpen(player);
        addPlayerInventory(player.getInventory());
        addPlayerHotbar(player.getInventory());
    }


    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                screen.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 86 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            screen.addSlot(new Slot(playerInventory, i, 8 + i * 18, 144));
        }
    }

    public ItemStack transferSlot(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = screen.slots.get(invSlot);
        if (slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!screen.insertItem(originalStack, this.inventory.size(), screen.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!screen.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }
}
