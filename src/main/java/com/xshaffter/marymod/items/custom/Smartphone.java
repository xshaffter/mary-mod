package com.xshaffter.marymod.items.custom;

import com.xshaffter.marymod.MaryMod;
import com.xshaffter.marymod.MaryModClient;
import com.xshaffter.marymod.blocks.BlockManager;
import com.xshaffter.marymod.events.AdvancementManager;
import com.xshaffter.marymod.util.Boundaries;
import com.xshaffter.marymod.util.Functions;
import com.xshaffter.marymod.util.PlayerEntityBridge;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class Smartphone extends MaryItem {
    public Smartphone() {
        super(new Settings().fireproof().rarity(Rarity.EPIC).maxCount(1));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            if (user instanceof ServerPlayerEntity player)
                if (!AdvancementManager.hasAdvancement(player, "home")) {
                    // 4 5 148 -1 2 154
                    var hallBounds = new Boundaries(new Vec3d(-1, 2, 148), new Vec3d(4, 5, 154));
                    if (Functions.playerInBounds(player, hallBounds)) {
                        user.teleport(-62, 4, 191);
                        AdvancementManager.grantAdvancement(player, "home");
                    } else {
                        player.sendMessage(Text.literal("Primero deberia salir del departamento"));
                    }
                } else if (!AdvancementManager.hasAdvancement(player, "death")) {
                    if (world.getBlockState(new BlockPos(-92, 5, 178)).isOf(BlockManager.MARY_COIN) ||
                            world.getBlockState(new BlockPos(-75, 12, 207)).isOf(BlockManager.MARY_COIN)) {
                        player.sendMessage(Text.literal("Primero quiero pasearme por todas las atracciones"));
                    } else {
                        player.sendMessage(Text.literal("Es momento de probar esa gran atraccion!!"));
                        PlayerEntityBridge.executeCommand(world, player, "fill -111 12 185 -111 7 200 air replace minecraft:barrier");
                    }
                } else {
                    player.sendMessage(Text.literal("Mi celular ya no tiene señal..."));
                }
        }
        return super.use(world, user, hand);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("Mi celular"));
    }
}
