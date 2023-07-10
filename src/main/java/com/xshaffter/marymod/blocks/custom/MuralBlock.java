package com.xshaffter.marymod.blocks.custom;

import com.google.common.collect.ImmutableMap;
import com.xshaffter.marymod.blocks.bases.RotableBlock;
import com.xshaffter.marymod.util.VoxelShapeUtil;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public class MuralBlock extends RotableBlock {
    public MuralBlock() {
        super(
            FabricBlockSettings.of(Material.CAKE)
                    .nonOpaque()
                    .sounds(BlockSoundGroup.WOOL)
                    .strength(4f)
                    .hardness(1f),
                VoxelShapes.cuboid(0,0,0.9,1,1,1)
        );
    }
}
