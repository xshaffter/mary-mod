package com.xshaffter.marymod.items.custom;

import net.minecraft.item.Item;

import java.lang.reflect.InvocationTargetException;

import static com.xshaffter.marymod.items.ItemGroups.MARY_MOD_GROUP;

public class MaryItem extends Item {
    public MaryItem(Settings settings) {
        super(settings.group(MARY_MOD_GROUP));
    }
}