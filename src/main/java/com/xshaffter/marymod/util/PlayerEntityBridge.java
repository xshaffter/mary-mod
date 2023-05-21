package com.xshaffter.marymod.util;

import com.xshaffter.marymod.mixins.PlayerEntityMixin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;

public class PlayerEntityBridge {
    public static String getTeam(LivingEntity player) {
        var living = (IPlayerFunctions) player;
        return living.getTeamName();
    }
}