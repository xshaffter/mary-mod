package com.xshaffter.marymod.blocks.custom;

import com.xshaffter.marymod.blocks.bases.BlockActionHandler;
import com.xshaffter.marymod.blocks.bases.OnActionBlock;
import com.xshaffter.marymod.blocks.blockstates.KingCape;
import com.xshaffter.marymod.blocks.blockstates.PropertyManager;
import com.xshaffter.marymod.blocks.blockstates.TextState;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TombBlock extends OnActionBlock {

    public static final EnumProperty<TextState> TEXT = PropertyManager.TEXT_STATE;

    public TombBlock() {
        super(
                FabricBlockSettings.of(Material.STONE)
                        .nonOpaque()
                        .sounds(BlockSoundGroup.STONE)
                        .strength(4f)
                        .hardness(1f), (BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) -> {
                    if (!world.isClient) {
                        return ActionResult.PASS;
                    }
                    var text = state.get(TEXT);
                    System.out.println(text);
                    player.sendMessage(Text.literal(text.getText()));
                    return ActionResult.SUCCESS;
                });
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder.add(TEXT));
    }
}
