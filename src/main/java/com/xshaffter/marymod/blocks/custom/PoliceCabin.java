package com.xshaffter.marymod.blocks.custom;

import com.xshaffter.marymod.blocks.bases.UsableTextBlock;
import com.xshaffter.marymod.blocks.blockstates.KingCape;
import com.xshaffter.marymod.blocks.blockstates.PropertyManager;
import com.xshaffter.marymod.util.VoxelShapeUtil;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class PoliceCabin extends UsableTextBlock {
    public static final EnumProperty<KingCape> POSITION = PropertyManager.KING_CAPE;
    private static final VoxelShape northArist = VoxelShapes.cuboid(0,0,0.5,1,1,1);
    private static final VoxelShape northUpperArist = VoxelShapes.cuboid(0,0,0.5,1,0.5,1);
    private static final VoxelShape northVertex = VoxelShapes.cuboid(0.5,0,0.5,1,1,1);
    private static final VoxelShape northUpperVertex = VoxelShapes.cuboid(0.5,0,0.5,1,0.5,1);

    public PoliceCabin() {
        super(FabricBlockSettings.of(Material.METAL)
                        .nonOpaque()
                        .sounds(BlockSoundGroup.METAL)
                        .strength(4f)
                        .hardness(1f),
                Text.literal("Una cabina de policia")
        );
    }

    @Override
    public void onPlaced(World world, BlockPos bottom, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        var center = bottom.up();
        var top = center.up();
        expandCapeBottom(world, state, bottom);
        expandCapeCenter(world, state, center);
        expandCapeTop(world, state, top);
    }

    private void expandCapeTop(World world, BlockState state, BlockPos pos) {
        world.setBlockState(pos, state.with(POSITION, KingCape.TOP_CENTER));
        world.setBlockState(pos.north(), state.with(POSITION, KingCape.NORTH_TOP));
        world.setBlockState(pos.south(), state.with(POSITION, KingCape.SOUTH_TOP));
        world.setBlockState(pos.east(), state.with(POSITION, KingCape.EAST_TOP));
        world.setBlockState(pos.west(), state.with(POSITION, KingCape.WEST_TOP));
        world.setBlockState(pos.north().east(), state.with(POSITION, KingCape.NORTH_EAST_TOP));
        world.setBlockState(pos.north().west(), state.with(POSITION, KingCape.NORTH_WEST_TOP));
        world.setBlockState(pos.south().east(), state.with(POSITION, KingCape.SOUTH_EAST_TOP));
        world.setBlockState(pos.south().west(), state.with(POSITION, KingCape.SOUTH_WEST_TOP));
    }

    private void expandCapeCenter(World world, BlockState state, BlockPos pos) {
        world.setBlockState(pos, state.with(POSITION, KingCape.CENTER));
        world.setBlockState(pos.north(), state.with(POSITION, KingCape.NORTH_MID));
        world.setBlockState(pos.south(), state.with(POSITION, KingCape.SOUTH_MID));
        world.setBlockState(pos.east(), state.with(POSITION, KingCape.EAST_MID));
        world.setBlockState(pos.west(), state.with(POSITION, KingCape.WEST_MID));
        world.setBlockState(pos.north().east(), state.with(POSITION, KingCape.NORTH_EAST_MID));
        world.setBlockState(pos.north().west(), state.with(POSITION, KingCape.NORTH_WEST_MID));
        world.setBlockState(pos.south().east(), state.with(POSITION, KingCape.SOUTH_EAST_MID));
        world.setBlockState(pos.south().west(), state.with(POSITION, KingCape.SOUTH_WEST_MID));
    }

    private void expandCapeBottom(World world, BlockState state, BlockPos pos) {
        world.setBlockState(pos, state.with(POSITION, KingCape.BOTTOM_CENTER));
        world.setBlockState(pos.north(), state.with(POSITION, KingCape.NORTH_BOTTOM));
        world.setBlockState(pos.south(), state.with(POSITION, KingCape.SOUTH_BOTTOM));
        world.setBlockState(pos.east(), state.with(POSITION, KingCape.EAST_BOTTOM));
        world.setBlockState(pos.west(), state.with(POSITION, KingCape.WEST_BOTTOM));
        world.setBlockState(pos.north().east(), state.with(POSITION, KingCape.NORTH_EAST_BOTTOM));
        world.setBlockState(pos.north().west(), state.with(POSITION, KingCape.NORTH_WEST_BOTTOM));
        world.setBlockState(pos.south().east(), state.with(POSITION, KingCape.SOUTH_EAST_BOTTOM));
        world.setBlockState(pos.south().west(), state.with(POSITION, KingCape.SOUTH_WEST_BOTTOM));
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        this.tryBreakBlock(world, pos.north(), player);
        this.tryBreakBlock(world, pos.east(), player);
        this.tryBreakBlock(world, pos.south(), player);
        this.tryBreakBlock(world, pos.west(), player);
        this.tryBreakBlock(world, pos.up(), player);
        this.tryBreakBlock(world, pos.down(), player);
    }

    private void tryBreakBlock(World world, BlockPos pos, PlayerEntity player) {
        var newState = world.getBlockState(pos);
        if (newState.isOf(this)) {
            world.breakBlock(pos, false);
            this.onBreak(world, pos, newState, player);
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder.add(POSITION));
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        return super.getPlacementState(ctx).with(POSITION, KingCape.BOTTOM_CENTER);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(POSITION)) {
            case NORTH_MID, NORTH_BOTTOM -> northArist;
            case NORTH_TOP -> northUpperArist;
            case SOUTH_BOTTOM, SOUTH_MID -> VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.SOUTH, northArist);
            case SOUTH_TOP -> VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.SOUTH, northUpperArist);
            case EAST_BOTTOM, EAST_MID -> VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.EAST, northArist);
            case EAST_TOP -> VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.EAST, northUpperArist);
            case WEST_BOTTOM, WEST_MID -> VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.WEST, northArist);
            case WEST_TOP -> VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.WEST, northUpperArist);
            case NORTH_EAST_BOTTOM, NORTH_EAST_MID -> VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.EAST, northVertex);
            case NORTH_EAST_TOP -> VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.EAST, northUpperVertex);
            case NORTH_WEST_BOTTOM, NORTH_WEST_MID -> northVertex;
            case NORTH_WEST_TOP -> northUpperVertex;
            case SOUTH_EAST_BOTTOM, SOUTH_EAST_MID -> VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.SOUTH, northVertex);
            case SOUTH_EAST_TOP -> VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.SOUTH, northUpperVertex);
            case SOUTH_WEST_BOTTOM, SOUTH_WEST_MID -> VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.WEST, northVertex);
            case SOUTH_WEST_TOP -> VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.WEST, northUpperVertex);
            case CENTER, BOTTOM_CENTER,TOP_CENTER -> VoxelShapes.fullCube();
        };
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(POSITION)) {
            case NORTH_MID, NORTH_BOTTOM -> northArist;
            case NORTH_TOP -> northUpperArist;
            case SOUTH_BOTTOM, SOUTH_MID -> VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.SOUTH, northArist);
            case SOUTH_TOP -> VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.SOUTH, northUpperArist);
            case EAST_BOTTOM, EAST_MID -> VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.EAST, northArist);
            case EAST_TOP -> VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.EAST, northUpperArist);
            case WEST_BOTTOM, WEST_MID -> VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.WEST, northArist);
            case WEST_TOP -> VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.WEST, northUpperArist);
            case NORTH_EAST_BOTTOM, NORTH_EAST_MID -> VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.EAST, northVertex);
            case NORTH_EAST_TOP -> VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.EAST, northUpperVertex);
            case NORTH_WEST_BOTTOM, NORTH_WEST_MID -> northVertex;
            case NORTH_WEST_TOP -> northUpperVertex;
            case SOUTH_EAST_BOTTOM, SOUTH_EAST_MID -> VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.SOUTH, northVertex);
            case SOUTH_EAST_TOP -> VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.SOUTH, northUpperVertex);
            case SOUTH_WEST_BOTTOM, SOUTH_WEST_MID -> VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.WEST, northVertex);
            case SOUTH_WEST_TOP -> VoxelShapeUtil.rotateShape(Direction.NORTH, Direction.WEST, northUpperVertex);
            case CENTER, BOTTOM_CENTER,TOP_CENTER -> VoxelShapes.fullCube();
        };
    }
}
