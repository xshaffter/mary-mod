package com.xshaffter.marymod.items.custom;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.Rarity;

import java.lang.reflect.InvocationTargetException;

import static com.xshaffter.marymod.items.ItemGroups.MARY_MOD_GROUP;

public class MaryItem extends Item {
    public MaryItem(Settings settings) {
        super(settings.group(MARY_MOD_GROUP));
    }

    public MaryItem() {
        this(
                new FabricItemSettings()
                        .rarity(Rarity.COMMON)
                        .maxCount(16)
                        .fireproof()
        );
    }
}