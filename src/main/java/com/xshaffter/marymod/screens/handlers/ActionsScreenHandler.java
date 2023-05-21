package com.xshaffter.marymod.screens.handlers;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;

public class ActionsScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final PlayerEntity player;
    public final int SYNC_ID;

    public ActionsScreenHandler(int syncId, PlayerInventory inventory) {
        super(ModScreenHandlers.ACTIONS_SCREEN_HANDLER, syncId);
        this.inventory = inventory;
        this.player = inventory.player;
        this.SYNC_ID = syncId;
    }

    @Override
    public ItemStack transferSlot(PlayerEntity player, int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }
}
