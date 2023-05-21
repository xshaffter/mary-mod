package com.xshaffter.marymod.networking.packets;

import com.xshaffter.marymod.events.SoundManager;
import com.xshaffter.marymod.util.PlayerEntityBridge;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;

public class BarkC2SPacket {
    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender) {
        // Everything here happens ONLY on the Server!
        ServerWorld world = player.getWorld();
        if (PlayerEntityBridge.getTeam(player).equalsIgnoreCase("administrator")) {
            world.playSound(null, player.getBlockPos(), SoundManager.BEAGLE_BARK, SoundCategory.PLAYERS, 1f, 1f);
        }
    }
}
