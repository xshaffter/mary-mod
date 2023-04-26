package com.xshaffter.marymod.blocks;

import com.xshaffter.marymod.blocks.bases.RotableBlock;
import com.xshaffter.marymod.blocks.entities.CandyMachineEntity;
import com.xshaffter.marymod.items.ItemManager;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.DropperBlock;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.*;

import static com.xshaffter.marymod.items.ItemManager.MARY_COIN_ITEM;


@SuppressWarnings("deprecation")
public class DispenserMachine extends DropperBlock {
    private boolean canUse = true;
    private DefaultedList<ItemStack> inventory;

    protected DispenserMachine() {
        super(FabricBlockSettings.of(Material.METAL)
                .nonOpaque()
                .collidable(true)
                .hardness(10f)
                .strength(40f)
        );

        inventory = DefaultedList.of();
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack item = player.getStackInHand(hand);
        if (item.isOf(MARY_COIN_ITEM)) {
            if (this.inventory.isEmpty()) {
                player.sendMessage(Text.literal("Creo que ya no queda nada más..."));
                return ActionResult.PASS;
            } else if (canUse) {
                if (player instanceof ServerPlayerEntity serverPlayer) {
                    dispense(serverPlayer, pos);
                    item.decrement(1);
                    canUse = false;
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            canUse = true;
                        }
                    }, 150);

                    return ActionResult.SUCCESS;
                }
            }
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CandyMachineEntity(pos, state);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        inventory.add(new ItemStack(BlockManager.MARY_BLUE));
        super.onPlaced(world, pos, state, placer, itemStack);
    }

    public ItemStack pop() {
        return this.inventory.remove(0);
    }

    public void dispense(ServerPlayerEntity player, BlockPos pos) {
        this.dispense(player.getServer().getOverworld(), pos);
    }
}
