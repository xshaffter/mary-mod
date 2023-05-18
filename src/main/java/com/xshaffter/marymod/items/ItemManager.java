package com.xshaffter.marymod.items;

import com.xshaffter.marymod.MaryMod;
import com.xshaffter.marymod.items.custom.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

import static com.xshaffter.marymod.items.ItemGroups.MARY_MOD_GROUP;

public class ItemManager {
    public static final Item MARY_COIN_ITEM = new MaryCoinItem();
    public static final Item SWORD = new Sword();
    public static final Item HAMMER = new Hammer();

    public static final Item CHOCORAMO = new MaryItem(
            new FabricItemSettings()
                    .rarity(Rarity.COMMON)
                    .maxCount(16)
                    .food(new FoodComponent.Builder()
                            .hunger(6)
                            .snack()
                            .alwaysEdible()
                            .saturationModifier(11)
                            .build())
    );
    public static final Item CIGGAR = new MaryItem(
            new FabricItemSettings()
                    .rarity(Rarity.COMMON)
                    .maxCount(16)
                    .fireproof()
    );

    public static final Item CROWN = new Crown();


    private static void registerItem(String name, Item item) {
        Registry.register(Registry.ITEM, new Identifier(MaryMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        registerItem("ciggar", CIGGAR);
        registerItem("mary_coin_item", MARY_COIN_ITEM);
        registerItem("sword", SWORD);
        registerItem("hammer", HAMMER);
        registerItem("chocoramo", CHOCORAMO);
        registerItem("crown", CROWN);
    }
}