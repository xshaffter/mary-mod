package com.xshaffter.marymod.items;

import com.xshaffter.marymod.MaryMod;
import com.xshaffter.marymod.events.SoundManager;
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
    public static final Item BROKEN_BOAT = new AdvancementItem();
    public static final Item CIGGAR = new AdvancementItem();
    public static final Item CONFETTI = new AdvancementItem();
    public static final Item DANGER = new AdvancementItem();
    public static final Item DISK_BALL = new AdvancementItem();
    public static final Item INNER_PEACE = new AdvancementItem();
    public static final Item LOADING_BAR = new AdvancementItem();
    public static final Item MARYLAND = new AdvancementItem();
    public static final Item PAINT = new AdvancementItem();
    public static final Item PHD = new AdvancementItem();
    public static final Item POLAROID = new AdvancementItem();
    public static final Item PORO_GALLETA_ADVANCE = new AdvancementItem();

    public static final Item CROWN = new Crown();

    public static final Item FANSA_MUSIC_DISC = new ModMusicDiscItem(9, SoundManager.FANSA_MUSIC, 247);
    public static final Item RASPUTIN_DISC = new ModMusicDiscItem(9, SoundManager.RASPUTIN, 269);
    public static final Item DARK_CAVE_DISC = new ModMusicDiscItem(9, SoundManager.DARK_CAVE, 144);


    private static void registerItem(String name, Item item) {
        Registry.register(Registry.ITEM, new Identifier(MaryMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        registerItem("mary_coin_item", MARY_COIN_ITEM);
        registerItem("sword", SWORD);
        registerItem("hammer", HAMMER);
        registerItem("chocoramo", CHOCORAMO);
        registerItem("crown", CROWN);

        registerItem("broken_boat", BROKEN_BOAT);
        registerItem("ciggar", CIGGAR);
        registerItem("confetti", CONFETTI);
        registerItem("danger", DANGER);
        registerItem("disk_ball", DISK_BALL);
        registerItem("inner_peace", INNER_PEACE);
        registerItem("loading_bar", LOADING_BAR);
        registerItem("maryland", MARYLAND);
        registerItem("paint", PAINT);
        registerItem("phd", PHD);
        registerItem("polaroid", POLAROID);
        registerItem("poro_galleta_advance", PORO_GALLETA_ADVANCE);

        registerItem("fansa_music_disc", FANSA_MUSIC_DISC);
        registerItem("rasputin_disc", RASPUTIN_DISC);
        registerItem("dark_cave_disc", DARK_CAVE_DISC);
    }
}