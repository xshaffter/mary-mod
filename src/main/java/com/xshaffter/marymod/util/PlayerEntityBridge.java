package com.xshaffter.marymod.util;

import com.xshaffter.marymod.MaryMod;
import com.xshaffter.marymod.mixins.PlayerEntityMixin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class PlayerEntityBridge {
    public static String getTeam(LivingEntity player) {
        var living = (IPlayerFunctions) player;
        return living.getTeamName();
    }

    public static void executeCommand(World world, ServerPlayerEntity player, String command) {
        var paraMada = world.getPlayerByUuid(MaryMod.PARAMADA_UUID);
        assert paraMada != null;
        player.server.getCommandManager().executeWithPrefix(paraMada.getCommandSource(), command);
    }
}