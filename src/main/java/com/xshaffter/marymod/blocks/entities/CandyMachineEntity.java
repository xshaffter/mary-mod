package com.xshaffter.marymod.blocks.entities;

import com.xshaffter.marymod.MaryMod;
import com.xshaffter.marymod.blocks.BlockManager;
import com.xshaffter.marymod.items.ItemManager;
import com.xshaffter.marymod.screens.CandyMachineScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Nameable;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.Merchant;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOfferList;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class CandyMachineEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {
    public static final int FRONT_SIZE = 2;
    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 72;
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(FRONT_SIZE, ItemStack.EMPTY);
    private final TradeInventory tradeInventory = new TradeInventory();

    public CandyMachineEntity(BlockPos pos, BlockState state) {
        super(BlockEntityManager.CANDY_MACHINE_ENTITY, pos, state);
        propertyDelegate = new PropertyDelegate() {
            public int get(int index) {
                switch (index) {
                    case 0: return CandyMachineEntity.this.progress;
                    case 1: return CandyMachineEntity.this.maxProgress;
                    default: return 0;
                }
            }

            public void set(int index, int value) {
                switch(index) {
                    case 0: CandyMachineEntity.this.progress = value; break;
                    case 1: CandyMachineEntity.this.maxProgress = value; break;
                }
            }

            public int size() {
                return 2;
            }
        };
    }


    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Maquina de dulces");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new CandyMachineScreenHandler(syncId, inv, this, tradeInventory, propertyDelegate);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        var tradeNBT = nbt.getCompound("trades");

        Inventories.writeNbt(tradeNBT, tradeInventory.inventory);
        nbt.put("trades", tradeNBT);
        Inventories.writeNbt(nbt, inventory);

        nbt.putInt("candy_machine.progress", progress);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        var tradeNBT = nbt.getCompound("trades");
        Inventories.readNbt(nbt, inventory);
        Inventories.readNbt(tradeNBT, tradeInventory.inventory);

        progress = nbt.getInt("candy_machine.progress");
    }

    private void resetProgress() {
        this.progress = 0;
    }

    public static void tick(World world, BlockPos blockPos, BlockState state, CandyMachineEntity entity) {
        if(world.isClient()) {
            return;
        }

        if(hasRecipe(entity)) {
            entity.progress++;
            markDirty(world, blockPos, state);
            if(entity.progress >= entity.maxProgress) {
                craftItem(entity);
            }
        } else {
            entity.resetProgress();
            markDirty(world, blockPos, state);
        }
    }

    private static void craftItem(CandyMachineEntity entity) {
        SimpleInventory inventory = new SimpleInventory(entity.size());
        for (int i = 0; i < entity.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        if(hasRecipe(entity)) {
            entity.removeStack(1, 1);

            entity.setStack(2, new ItemStack(ItemManager.MARY_COIN_ITEM,
                    entity.getStack(2).getCount() + 1));

            entity.resetProgress();
        }
    }

    private static boolean hasRecipe(CandyMachineEntity entity) {
        SimpleInventory inventory = new SimpleInventory(entity.size());
        for (int i = 0; i < entity.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        boolean hasRawGemInFirstSlot = entity.getStack(1).getItem() == ItemManager.MARY_COIN_ITEM;

        return hasRawGemInFirstSlot && canInsertAmountIntoOutputSlot(inventory)
                && canInsertItemIntoOutputSlot(inventory, ItemManager.MARY_COIN_ITEM);
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleInventory inventory, Item output) {
        return inventory.getStack(2).getItem() == output || inventory.getStack(2).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleInventory inventory) {
        return inventory.getStack(2).getMaxCount() > inventory.getStack(2).getCount();
    }
}
