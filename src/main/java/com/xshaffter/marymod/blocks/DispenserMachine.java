package com.xshaffter.marymod.blocks;

import com.xshaffter.marymod.blocks.entities.CandyMachineEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.DispenserBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.Timer;
import java.util.TimerTask;

import static com.xshaffter.marymod.items.ItemManager.MARY_COIN_ITEM;


@SuppressWarnings("deprecation")
public class DispenserMachine extends DropperBlock implements BlockEntityProvider {
    private boolean canUse = true;

    protected DispenserMachine() {
        super(FabricBlockSettings.of(Material.METAL)
                .nonOpaque()
                .collidable(true)
                .hardness(10f)
                .strength(40f)
        );
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack item = player.getStackInHand(hand);
        if (world.isClient()) {
            return ActionResult.PASS;
        }
        if (canUse) {
            if (item.isOf(MARY_COIN_ITEM)) {
                var server = world.getServer();
                DispenserBlockEntity entity = (DispenserBlockEntity) world.getBlockEntity(pos);

                assert entity != null;
                assert server != null;

                int i = entity.chooseNonEmptySlot(world.random);
                if (i < 0) {
                    player.sendMessage(Text.literal("Creo que ya no queda nada más..."));
                } else {
                    dispense(server.getOverworld(), pos);
                    item.decrement(1);
                }
                canUse = false;
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        canUse = true;
                    }
                }, 150);
            } else if (player.isCreative()){
                NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);
                if (screenHandlerFactory != null) {
                    player.openHandledScreen(screenHandlerFactory);
                }
            }
        }
        return ActionResult.SUCCESS;

    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CandyMachineEntity(pos, state);
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        // super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(0f, 0f, 0f, 1f, 2f, 1f);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(0f, 0f, 0f, 1f, 2f, 1f);

    }
}
