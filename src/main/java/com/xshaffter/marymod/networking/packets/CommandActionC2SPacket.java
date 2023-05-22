package com.xshaffter.marymod.networking.packets;

import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.context.CommandContextBuilder;
import com.xshaffter.marymod.screens.handlers.ActionsScreenHandler;
import com.xshaffter.marymod.util.PlayerEntityBridge;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.message.SentMessage;
import net.minecraft.network.message.SignedMessage;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.Timer;
import java.util.TimerTask;

public class CommandActionC2SPacket {
    public static final int REMOVE_1 = 1;

    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender) {
        var command_code = buf.readByte();

        switch (command_code) {
            case REMOVE_1:
                server.getCommandManager().executeWithPrefix(player.getCommandSource(), "fill -10 0 -10 10 0 10 stone");
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        server.getCommandManager().executeWithPrefix(player.getCommandSource(), "fill -10 0 -10 10 0 10 air");
                    }
                }, 5000);
                break;
        }
    }
}
