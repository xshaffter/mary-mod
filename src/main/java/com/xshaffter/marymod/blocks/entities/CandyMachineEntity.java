package com.xshaffter.marymod.blocks.entities;

import com.xshaffter.marymod.blocks.BlockManager;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.DispenserBlockEntity;
import net.minecraft.block.entity.DropperBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

public class CandyMachineEntity extends DropperBlockEntity {

    public CandyMachineEntity(BlockPos pos, BlockState state) {
        super(pos, state);
        var inventory = DefaultedList.ofSize(20, new ItemStack(BlockManager.MARY_BLUE));
        //inventory.set(0, new ItemStack(BlockManager.MARY_BLUE) );
        this.setInvStackList(inventory);
    }

    @Override
    public int size() {
        return 20;
    }
}
