package com.xshaffter.marymod.events;
import com.xshaffter.marymod.networking.NetworkManager;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeyboardHandler {
    public static final String KEY_CATEGORY_MARY_MOD = "key.category.mary-mod";
    public static final String KEY_BEAGLE_BARK = "key.mary-mod.bark";

    public static KeyBinding barkKey;

    private static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if(barkKey.wasPressed()) {
                ClientPlayNetworking.send(NetworkManager.BARK_ID, PacketByteBufs.create());
            }
        });
    }

    public static void register() {
        barkKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_BEAGLE_BARK,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_B,
                KEY_CATEGORY_MARY_MOD
        ));

        registerKeyInputs();
    }
}