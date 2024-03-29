package com.xshaffter.marymod.blocks.custom;

import com.xshaffter.marymod.blocks.bases.UsableTextBlock;
import com.xshaffter.marymod.blocks.bases.UseBreakBlock;
import com.xshaffter.marymod.blocks.blockstates.CubySkin;
import com.xshaffter.marymod.blocks.blockstates.KingCape;
import com.xshaffter.marymod.blocks.blockstates.PropertyManager;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.text.Text;

public class CubyFriend extends UseBreakBlock {
    public CubyFriend() {
        super(
                FabricBlockSettings.of(Material.CAKE)
                        .nonOpaque()
                        .sounds(BlockSoundGroup.WOOL)
                        .strength(4f)
                        .hardness(1f)
        );
    }
}
