package com.xshaffter.marymod.items.custom;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.Rarity;

import static com.xshaffter.marymod.items.ItemGroups.MARY_MOD_ADVANCEMENTS_GROUP;

public class AdvancementItem extends Item {
    public AdvancementItem(Settings settings) {
        super(settings.group(MARY_MOD_ADVANCEMENTS_GROUP));
    }

    public AdvancementItem() {
        this(
                new FabricItemSettings()
                        .rarity(Rarity.COMMON)
                        .maxCount(16)
                        .fireproof()
        );
    }
}