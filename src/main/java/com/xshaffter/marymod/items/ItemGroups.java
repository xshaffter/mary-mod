package com.xshaffter.marymod.items;

import com.xshaffter.marymod.MaryMod;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import static com.xshaffter.marymod.items.ItemManager.MARY_COIN_ITEM;

public class ItemGroups {
    public static final ItemGroup MARY_MOD_GROUP = FabricItemGroupBuilder.create(
            new Identifier(MaryMod.MOD_ID, "mary_mod")
    ).icon(() -> new ItemStack(MARY_COIN_ITEM)).build();
}
