package com.xshaffter.marymod.blocks.custom;

import com.xshaffter.marymod.blocks.bases.UseBreakBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class MaryCoin extends UseBreakBlock {
    public MaryCoin() {
        super(FabricBlockSettings.of(Material.METAL)
                        .nonOpaque()
                        .hardness(50f)
                        .strength(50f)
                        .collidable(false),
                VoxelShapes.cuboid(0.2f, 0f, 0.2f, 0.8f, 0.1f, 0.8f)
        );
    }


}
