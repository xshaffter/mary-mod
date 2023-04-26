package com.xshaffter.marymod.blocks.entities;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.DispenserBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

public class CandyMachineEntity extends DispenserBlockEntity {

    public CandyMachineEntity(BlockPos pos, BlockState state) {
        super(pos, state);
        this.setInvStackList(DefaultedList.ofSize(20, ItemStack.EMPTY));
    }

    @Override
    public int size() {
        return 20;
    }
}
