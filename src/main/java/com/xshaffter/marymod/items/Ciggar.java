package com.xshaffter.marymod.items;

import net.minecraft.item.Item;
import net.minecraft.util.Rarity;

import static com.xshaffter.marymod.items.ItemGroups.MARY_MOD_GROUP;

public class Ciggar extends Item {
    public Ciggar() {
        super(new Settings().fireproof().rarity(Rarity.COMMON).maxCount(1).group(MARY_MOD_GROUP));
    }

}
