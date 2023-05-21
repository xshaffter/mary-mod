package com.xshaffter.marymod.blocks.blockstates;


import net.minecraft.util.StringIdentifiable;

public enum VerticalCape implements StringIdentifiable {
    TOP_CENTER,
    NORTH_TOP,
    SOUTH_TOP,
    EAST_TOP,
    WEST_TOP,
    CENTER,
    NORTH_MID,
    SOUTH_MID,
    EAST_MID,
    WEST_MID,
    BOTTOM_CENTER,
    NORTH_BOTTOM,
    SOUTH_BOTTOM,
    EAST_BOTTOM,
    WEST_BOTTOM;

    public String toString() {
        return this.asString();
    }

    @Override
    public String asString() {
        return switch (this) {
            case TOP_CENTER -> "TOP_CENTER";
            case NORTH_TOP -> "NORTH_TOP";
            case SOUTH_TOP -> "SOUTH_TOP";
            case EAST_TOP -> "EAST_TOP";
            case WEST_TOP -> "WEST_TOP";
            case CENTER -> "CENTER";
            case NORTH_MID -> "NORTH_MID";
            case SOUTH_MID -> "SOUTH_MID";
            case EAST_MID -> "EAST_MID";
            case WEST_MID -> "WEST_MID";
            case BOTTOM_CENTER -> "BOTTOM_CENTER";
            case NORTH_BOTTOM -> "NORTH_BOTTOM";
            case SOUTH_BOTTOM -> "SOUTH_BOTTOM";
            case EAST_BOTTOM -> "EAST_BOTTOM";
            case WEST_BOTTOM -> "WEST_BOTTOM";
        };
    }
}
