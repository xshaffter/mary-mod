package com.xshaffter.marymod.blocks.custom;

import com.xshaffter.marymod.blocks.bases.UsableTextBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.ShapeContext;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class PoroGalleta extends UsableTextBlock {
    public PoroGalleta() {
        super(FabricBlockSettings.of(Material.METAL)
                        .strength(4)
                        .hardness(1)
                        .collidable(false)
                        .sounds(BlockSoundGroup.GLASS)
                        .nonOpaque(),
                Text.literal("Unas poro galletas... se ven deliciosas")
        );
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(0f, 0f, 0f, 1f, 0.2f, 1f);
    }
}
