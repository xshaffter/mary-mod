package com.xshaffter.marymod.networking.packets;

import com.xshaffter.marymod.MaryMod;
import com.xshaffter.marymod.MaryModClient;
import com.xshaffter.marymod.util.ActionHandler;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public enum ActionCommand {
    TURN_DAY((MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
              PacketByteBuf buf, PacketSender responseSender) -> {
        server.getCommandManager().executeWithPrefix(player.getCommandSource(), "time set day");
    }, Text.literal("set day")),
    TURN_NIGHT((MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                PacketByteBuf buf, PacketSender responseSender) -> {
        server.getCommandManager().executeWithPrefix(player.getCommandSource(), "time set midnight");
    }, Text.literal("set night")),
    CLEAR_INVENTORY((MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                     PacketByteBuf buf, PacketSender responseSender) -> {
        server.getPlayerManager().getPlayerList().forEach((p) -> {
            if (Objects.requireNonNull(p.getScoreboardTeam()).getName().equalsIgnoreCase(MaryModClient.PLAYER_TEAM)) {
                for (int slot = 0; slot < p.getInventory().size(); slot++) {
                    var stack = p.getInventory().getStack(slot);
                    if (!stack.isEmpty()) {
                        if (!stack.getItem().getRegistryEntry().getKey().get().getValue().getNamespace().equalsIgnoreCase(MaryMod.MOD_ID)) {
                            p.getInventory().setStack(slot, ItemStack.EMPTY);
                        }
                    }
                }
            }
        });
    }, Text.literal("clear inventory")),
    REMOVE_DARK_ROOM((MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                      PacketByteBuf buf, PacketSender responseSender) -> {
        server.getCommandManager().executeWithPrefix(player.getCommandSource(), "fill -218 -39 341 -216 -39 338 air");
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                server.getCommandManager().executeWithPrefix(player.getCommandSource(), "fill -218 -39 341 -216 -39 338 black_concrete");
            }
        }, 5000);
    }, Text.literal("remove floor in dark room")),
    REMOVE_POST_PARKOUR((MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                         PacketByteBuf buf, PacketSender responseSender) -> {
        server.getCommandManager().executeWithPrefix(player.getCommandSource(), "fill -168 -50 294 -176 -50 302 air");
        server.getCommandManager().executeWithPrefix(player.getCommandSource(), "fill -168 -42 294 -176 -42 302 air");
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                server.getCommandManager().executeWithPrefix(player.getCommandSource(), "fill -168 -50 294 -176 -50 302 stripped_dark_oak_log[axis=x]");
                server.getCommandManager().executeWithPrefix(player.getCommandSource(), "fill -168 -42 294 -176 -42 302 smooth_quartz");
            }
        }, 5000);
    }, Text.literal("remove floor post parkour"), true),
    GIVE_KEY((MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
              PacketByteBuf buf, PacketSender responseSender) -> {
        server.getCommandManager().executeWithPrefix(player.getCommandSource(), "give @p[gamemode=survival,team=%s] lockable_doors:key".formatted(MaryModClient.PLAYER_TEAM));
    }, Text.literal("give item key")),
    GIVE_DANGER_GLOW((MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                      PacketByteBuf buf, PacketSender responseSender) -> {
        server.getCommandManager().executeWithPrefix(player.getCommandSource(), "effect give @p[gamemode=creative,team=%s] glowing 20".formatted(MaryModClient.DANGER_TEAM));
    }, Text.literal("set glowing to danger"))
    ;

    public final ActionHandler handle;
    public final Text tooltip;
    private final boolean isAdvancement;

    ActionCommand(ActionHandler handler, Text tooltip) {
        this.handle = handler;
        this.tooltip = tooltip;
        this.isAdvancement = false;
    }

    ActionCommand(ActionHandler handler, Text tooltip, boolean isAdvancement) {
        this.handle = handler;
        this.tooltip = tooltip;
        this.isAdvancement = isAdvancement;
    }

    public boolean isAdvancement() {
        return isAdvancement;
    }
}
