package com.xshaffter.marymod.blocks.custom;

import com.xshaffter.marymod.blocks.bases.RotableBlock;
import com.xshaffter.marymod.blocks.entities.CandyMachineEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.DispenserBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Properties;
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
public class CandyMachine extends RotableBlock implements BlockEntityProvider {

    public CandyMachine() {
        super(FabricBlockSettings.of(Material.METAL)
                .nonOpaque()
                .collidable(true)
                .hardness(10f)
                .strength(40f)
        );
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient)
        {
            if (!state.get(Properties.BOTTOM)) {
                pos = pos.down();
            }
            BlockEntity te = world.getBlockEntity(pos);
            if (te instanceof CandyMachineEntity)
                ((CandyMachineEntity) te).openGui(player);
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
        return VoxelShapes.cuboid(0f, 0f, 0f, 1f, 1.4f, 1f);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(0f, 0f, 0f, 1f, 1.4f, 1f);

    }
}
