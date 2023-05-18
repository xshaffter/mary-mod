package com.xshaffter.marymod.networking;

import com.xshaffter.marymod.MaryMod;
import com.xshaffter.marymod.networking.packets.BarkC2SPacket;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

public class NetworkManager {
    public static final Identifier BARK_ID = new Identifier(MaryMod.MOD_ID, "bark");

    public static void registerC2SPackets() {
        ServerPlayNetworking.registerGlobalReceiver(BARK_ID, BarkC2SPacket::receive);
    }

    public static void registerS2CPackets() {

    }
}