package com.xshaffter.marymod.screens;

import com.xshaffter.marymod.blocks.entities.CandyMachineEntity;
import com.xshaffter.marymod.blocks.entities.TradeInventory;
import com.xshaffter.marymod.screens.subhandlers.CandyMachineSubHandler;
import com.xshaffter.marymod.util.MaryCoinSlot;
import com.xshaffter.marymod.util.OutputSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class CandyMachineScreenHandler extends ScreenHandler {
    public static final int INPUT_SLOT = 0;
    public static final int OUTPUT_SLOT = 1;
    private final PropertyDelegate propertyDelegate;
    private final CandyMachineSubHandler subHandler;

    public CandyMachineScreenHandler(int syncId, PlayerInventory inventory) {
        this(syncId, inventory, new SimpleInventory(CandyMachineEntity.FRONT_SIZE), new SimpleInventory(TradeInventory.CHEST_SIZE), new ArrayPropertyDelegate(2));
    }

    public CandyMachineScreenHandler(int syncId, PlayerInventory playerInventory, Inventory frontInventory, Inventory chestInventory, PropertyDelegate delegate) {
        super(ModScreenHandlers.CANDY_MACHINE_SCREEN_HANDLER, syncId);
        checkSize(frontInventory, CandyMachineEntity.FRONT_SIZE);
        checkSize(chestInventory, TradeInventory.CHEST_SIZE);
        if (playerInventory.player.isCreative()) {
            subHandler = new CandyMachineSubHandler(this, chestInventory, playerInventory.player);
            addChestSlots();
        } else {
            subHandler = new CandyMachineSubHandler(this, frontInventory, playerInventory.player);
            addFrontSlots();
        }

        this.propertyDelegate = delegate;

        addProperties(delegate);
    }

    public void addFrontSlots() {
        this.addSlot(new MaryCoinSlot(subHandler.inventory, INPUT_SLOT, 80, 29));
        this.addSlot(new OutputSlot(subHandler.inventory, OUTPUT_SLOT, 80, 61));
    }

    public void addChestSlots() {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(subHandler.inventory, l + i * 9, 8 + l * 18, 18 + i * 18));
            }
        }
    }

    public Slot addSlot(Slot slot) {
        return super.addSlot(slot);
    }

    @Override
    public boolean insertItem(ItemStack stack, int startIndex, int endIndex, boolean fromLast) {
        return super.insertItem(stack, startIndex, endIndex, fromLast);
    }

    @Override
    public ItemStack transferSlot(PlayerEntity player, int invSlot) {
        return subHandler.transferSlot(player, invSlot);
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.subHandler.inventory.canPlayerUse(player);
    }
}
