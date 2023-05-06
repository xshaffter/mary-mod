package com.xshaffter.marymod.items.custom;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Rarity;

import static com.xshaffter.marymod.items.ItemGroups.MARY_MOD_GROUP;

public class Sword extends SwordItem {
    public Sword() {
        super(
                ToolMaterials.IRON,
                9,
                0.8f,
                new FabricItemSettings().fireproof().rarity(Rarity.EPIC).group(MARY_MOD_GROUP)
        );
    }
}
