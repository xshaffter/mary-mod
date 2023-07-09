package com.xshaffter.marymod.items.custom;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Rarity;

import static com.xshaffter.marymod.items.ItemGroups.MARY_MOD_GROUP;

public class ModMusicDiscItem extends MusicDiscItem {
    /**
     * Access widened by fabric-transitive-access-wideners-v1 to accessible
     *
     * @param comparatorOutput
     * @param sound
     * @param lengthInSeconds
     */
    public ModMusicDiscItem(int comparatorOutput, SoundEvent sound, final int lengthInSeconds) {
        super(comparatorOutput, sound, new FabricItemSettings()
                .rarity(Rarity.COMMON)
                .maxCount(1)
                .group(MARY_MOD_GROUP)
                .fireproof(), lengthInSeconds);
    }
}
