package com.xshaffter.marymod.blocks.blockstates;

import net.minecraft.block.enums.DoorHinge;
import net.minecraft.state.property.EnumProperty;

public class PropertyManager {
    public static final EnumProperty<KingCape> KING_CAPE = EnumProperty.of("king_pos", KingCape.class);
    public static final EnumProperty<VerticalCape> VERTICAL_CAPE = EnumProperty.of("vertical_pos", VerticalCape.class);
    public static final EnumProperty<HorizontalCape> HORIZONTAL_CAPE = EnumProperty.of("horizontal_pos", HorizontalCape.class);
    public static final EnumProperty<CubySkin> CUBY_SKIN = EnumProperty.of("cuby_skin", CubySkin.class);
}
