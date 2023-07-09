package com.xshaffter.marymod.networking.packets;

import com.xshaffter.marymod.MaryModClient;
import com.xshaffter.marymod.screens.handlers.ActionsScreenHandler;
import com.xshaffter.marymod.util.PlayerEntityBridge;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class ActionsC2SPacket {
    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender) {
        if (PlayerEntityBridge.getTeam(player).equalsIgnoreCase(MaryModClient.ADMINISTRATOR_TEAM) ||
                PlayerEntityBridge.getTeam(player).equalsIgnoreCase(MaryModClient.DANGER_TEAM))
        {
            NamedScreenHandlerFactory screenHandlerFactory = new NamedScreenHandlerFactory() {
                @Override
                public Text getDisplayName() {
                    return Text.literal("");
                }

                @Override
                public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
                    return new ActionsScreenHandler(syncId, inv);
                }
            };
            player.openHandledScreen(screenHandlerFactory);
        }
    }
}
