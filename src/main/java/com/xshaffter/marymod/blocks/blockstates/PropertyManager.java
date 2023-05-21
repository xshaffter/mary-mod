package com.xshaffter.marymod.blocks.blockstates;

import net.minecraft.block.enums.DoorHinge;
import net.minecraft.state.property.EnumProperty;

public class PropertyManager {
    /**
     * A property that specifies whether a door's hinge is to the right or left.
     */
    public static final EnumProperty<KingCape> KING_CAPE = EnumProperty.of("king_pos", KingCape.class);    /**
     * A property that specifies whether a door's hinge is to the right or left.
     */
    public static final EnumProperty<VerticalCape> VERTICAL_CAPE = EnumProperty.of("vertical_pos", VerticalCape.class);    /**
     * A property that specifies whether a door's hinge is to the right or left.
     */
    public static final EnumProperty<HorizontalCape> HORIZONTAL_CAPE = EnumProperty.of("horizontal_pos", HorizontalCape.class);
}
