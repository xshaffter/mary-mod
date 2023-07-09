package com.xshaffter.marymod.events;

import com.xshaffter.marymod.MaryMod;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;



public class SoundManager {
    public static final SoundEvent BEAGLE_BARK = registerSound("beagle_bark");
    public static final SoundEvent FANSA_MUSIC = registerSound("fansa_music");
    public static final SoundEvent RASPUTIN = registerSound("rasputin");
    public static final SoundEvent DARK_CAVE = registerSound("dark_cave");

    private static SoundEvent registerSound(final String name) {
        Identifier id = new Identifier(MaryMod.MOD_ID, name);
        return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
    }
}
