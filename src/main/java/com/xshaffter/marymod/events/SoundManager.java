package com.xshaffter.marymod.events;

import com.xshaffter.marymod.MaryMod;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;



public class SoundManager {
    private static final Identifier BEAGLE_BARK_ID = new Identifier(MaryMod.MOD_ID, "beagle_bark");
    public static final SoundEvent BEAGLE_BARK = new SoundEvent(BEAGLE_BARK_ID);

    public static void registerSounds() {
        Registry.register(Registry.SOUND_EVENT, BEAGLE_BARK_ID, BEAGLE_BARK);
    }
}
