package com.xshaffter.marymod.networking.packets;

import com.xshaffter.marymod.events.SoundManager;
import com.xshaffter.marymod.screens.ActionsScreen;
import com.xshaffter.marymod.screens.handlers.ActionsScreenHandler;
import com.xshaffter.marymod.util.PlayerEntityBridge;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerFactory;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;

import static com.xshaffter.marymod.screens.handlers.ModScreenHandlers.ACTIONS_SCREEN_HANDLER;

public class ActionsC2SPacket {
    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender) {
        // Everything here happens ONLY on the Server!
        if (PlayerEntityBridge.getTeam(player).equalsIgnoreCase("administrator")) {
            NamedScreenHandlerFactory screenHandlerFactory = new NamedScreenHandlerFactory() {
                @Override
                public Text getDisplayName() {
                    return Text.literal("MOD ACTIONS");
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
