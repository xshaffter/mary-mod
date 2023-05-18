package com.xshaffter.marymod.items.custom;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.util.Rarity;
import com.xshaffter.marymod.items.custom.materials.MaterialManager;

import static com.xshaffter.marymod.items.ItemGroups.MARY_MOD_GROUP;

public class Crown extends ArmorItem {
    public Crown() {
        super(MaterialManager.MARY_MATERIAL, EquipmentSlot.HEAD,
                new FabricItemSettings().rarity(Rarity.EPIC).group(MARY_MOD_GROUP)
                        .fireproof().maxCount(1)
        );
    }
}
