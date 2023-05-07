package com.xshaffter.marymod.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;

public class LivingEntityBridge {
    public static NbtCompound getPersistentData(Entity entity) {
        var living = (IEntityDataSaver) entity;
        return living.getPersistentData();
    }
}