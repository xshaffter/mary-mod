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
    CLEAR_PARK_BARRIER((MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                        PacketByteBuf buf, PacketSender responseSender) -> {
        server.getCommandManager().executeWithPrefix(player.getCommandSource(), "fill -114 7 201 -117 9 201 air");
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                server.getCommandManager().executeWithPrefix(player.getCommandSource(), "fill -114 7 201 -117 9 201 barrier");
            }
        }, 5000);
    }, Text.literal("remove park barrier")),
    TURN_DAY((MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
              PacketByteBuf buf, PacketSender responseSender) -> {
        server.getCommandManager().executeWithPrefix(player.getCommandSource(), "time set day");
    }, Text.literal("set day")),
    TURN_NIGHT((MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                PacketByteBuf buf, PacketSender responseSender) -> {
        server.getCommandManager().executeWithPrefix(player.getCommandSource(), "time set midnight");
    }, Text.literal("set night")),
    TP_PARK((MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
             PacketByteBuf buf, PacketSender responseSender) -> {
        server.getCommandManager().executeWithPrefix(player.getCommandSource(), "tp @p[gamemode=survival,team=%s] -62 4 191".formatted(MaryModClient.PLAYER_TEAM));
    }, Text.literal("tp to park")),
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
                server.getCommandManager().executeWithPrefix(player.getCommandSource(), "fill -168 -50 294 -176 -50 302 stripped_dark_oak_log");
                server.getCommandManager().executeWithPrefix(player.getCommandSource(), "fill -168 -42 294 -176 -42 302 smooth_quartz");
            }
        }, 5000);
    }, Text.literal("remove floor post parkour")),
    GIVE_KEY((MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
              PacketByteBuf buf, PacketSender responseSender) -> {
        server.getCommandManager().executeWithPrefix(player.getCommandSource(), "give @p[gamemode=survival,team=%s] lockable-doors:key".formatted(MaryModClient.PLAYER_TEAM));
    }, Text.literal("give item key")),
    GIVE_DANGER_GLOW((MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                      PacketByteBuf buf, PacketSender responseSender) -> {
        server.getCommandManager().executeWithPrefix(player.getCommandSource(), "effect give @p[gamemode=creative,team=%s] glowing 20".formatted(MaryModClient.ADMINISTRATOR_TEAM));
    }, Text.literal("set glowing to danger")),

    GIVE_ADVANCEMENT_MARYLAND((MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender) -> {
        server.getCommandManager().executeWithPrefix(player.getCommandSource(), "advancement grant @p[team=%s] only mary-mod:home".formatted(MaryModClient.PLAYER_TEAM));
    }, Text.literal("give advancement MaryLand"), true),
    GIVE_ADVANCEMENT_BROKEN_BOAT((MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                                  PacketByteBuf buf, PacketSender responseSender) -> {
        server.getCommandManager().executeWithPrefix(player.getCommandSource(), "advancement grant @p[team=%s] only mary-mod:death".formatted(MaryModClient.PLAYER_TEAM));
    }, Text.literal("give advancement muerto"), true),
    GIVE_ADVANCEMENT_DANGER((MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                             PacketByteBuf buf, PacketSender responseSender) -> {
        server.getCommandManager().executeWithPrefix(player.getCommandSource(), "advancement grant @p[team=%s] only mary-mod:danger".formatted(MaryModClient.PLAYER_TEAM));
    }, Text.literal("give advancement danger"), true),
    GIVE_ADVANCEMENT_PAINT((MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                            PacketByteBuf buf, PacketSender responseSender) -> {
        server.getCommandManager().executeWithPrefix(player.getCommandSource(), "advancement grant @p[team=%s] only mary-mod:gallery".formatted(MaryModClient.PLAYER_TEAM));
    }, Text.literal("give advancement galeria"), true),
    GIVE_ADVANCEMENT_PORO_GALLETA_ADVANCE((MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                                           PacketByteBuf buf, PacketSender responseSender) -> {
        server.getCommandManager().executeWithPrefix(player.getCommandSource(), "advancement grant @p[team=%s] only mary-mod:cook".formatted(MaryModClient.PLAYER_TEAM));
    }, Text.literal("give advancement poro galleta"), true),
    GIVE_ADVANCEMENT_PHD((MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                          PacketByteBuf buf, PacketSender responseSender) -> {
        server.getCommandManager().executeWithPrefix(player.getCommandSource(), "advancement grant @p[team=%s] only mary-mod:mary_corazon".formatted(MaryModClient.PLAYER_TEAM));
    }, Text.literal("give advancement Dr Mary Corazon"), true),
    GIVE_ADVANCEMENT_CIGGAR((MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                             PacketByteBuf buf, PacketSender responseSender) -> {
        server.getCommandManager().executeWithPrefix(player.getCommandSource(), "advancement grant @p[team=%s] only mary-mod:gastronomia".formatted(MaryModClient.PLAYER_TEAM));
    }, Text.literal("give advancement gastronomia"), true),
    GIVE_ADVANCEMENT_LOADING_BAR((MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                                  PacketByteBuf buf, PacketSender responseSender) -> {
        server.getCommandManager().executeWithPrefix(player.getCommandSource(), "advancement grant @p[team=%s] only mary-mod:piece_of_way".formatted(MaryModClient.PLAYER_TEAM));
    }, Text.literal("give advancement barra de carga"), true),
    GIVE_ADVANCEMENT_INNER_PEACE((MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                                  PacketByteBuf buf, PacketSender responseSender) -> {
        server.getCommandManager().executeWithPrefix(player.getCommandSource(), "advancement grant @p[team=%s] only mary-mod:inner_peace".formatted(MaryModClient.PLAYER_TEAM));
    }, Text.literal("give advancement paz mental"), true),
    GIVE_ADVANCEMENT_DISK_BALL((MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                                PacketByteBuf buf, PacketSender responseSender) -> {
        server.getCommandManager().executeWithPrefix(player.getCommandSource(), "advancement grant @p[team=%s] only mary-mod:big_party".formatted(MaryModClient.PLAYER_TEAM));
    }, Text.literal("give advancement bola disco"), true),
    GIVE_ADVANCEMENT_POLAROID((MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender) -> {
        server.getCommandManager().executeWithPrefix(player.getCommandSource(), "advancement grant @p[team=%s] only mary-mod:polaroid".formatted(MaryModClient.PLAYER_TEAM));
    }, Text.literal("give advancement polaroid"), true),
    GIVE_ADVANCEMENT_CONFETTI((MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender) -> {
        server.getCommandManager().executeWithPrefix(player.getCommandSource(), "advancement grant @p[team=%s] only mary-mod:end".formatted(MaryModClient.PLAYER_TEAM));
    }, Text.literal("give advancement fiesta"), true),
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
