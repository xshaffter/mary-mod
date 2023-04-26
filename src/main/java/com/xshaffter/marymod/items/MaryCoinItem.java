package com.xshaffter.marymod.items;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.xshaffter.marymod.items.ItemGroups.MARY_MOD_GROUP;

public class MaryCoinItem extends Item {
    private boolean canUse = true;
    public MaryCoinItem() {
        super(new Item.Settings().fireproof().rarity(Rarity.EPIC).maxCount(64).group(MARY_MOD_GROUP));
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.mary-mod.mary_coin"));
        tooltip.add(Text.translatable("tooltip.mary-mod.mary_coin.2"));
    }
}
