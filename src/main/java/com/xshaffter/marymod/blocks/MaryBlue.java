package com.xshaffter.marymod.blocks;

import com.xshaffter.marymod.blocks.bases.RotableBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.ShapeContext;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class MaryBlue extends RotableBlock {

    public MaryBlue() {
        super(FabricBlockSettings.of(Material.WOOL)
                .nonOpaque()
                .sounds(BlockSoundGroup.WOOL)
                .strength(4f)
                .hardness(1f)
                .collidable(false));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(0.3f, 0f, 0.3f, 0.7f, 0.5f, 0.7f);

    }

}
