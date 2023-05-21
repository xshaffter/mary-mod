package com.xshaffter.marymod.blocks.blockstates;

import net.minecraft.util.StringIdentifiable;

public enum KingCape implements StringIdentifiable {
    TOP_CENTER,
    NORTH_TOP, //nearest to you
    SOUTH_TOP, //further to you
    EAST_TOP, // to the right
    WEST_TOP, // to the left
    NORTH_EAST_TOP,
    NORTH_WEST_TOP,
    SOUTH_EAST_TOP,
    SOUTH_WEST_TOP,
    CENTER,
    NORTH_MID,
    SOUTH_MID,
    EAST_MID,
    WEST_MID,
    NORTH_EAST_MID,
    NORTH_WEST_MID,
    SOUTH_EAST_MID,
    SOUTH_WEST_MID,
    BOTTOM_CENTER,
    NORTH_BOTTOM,
    SOUTH_BOTTOM,
    EAST_BOTTOM,
    WEST_BOTTOM,
    NORTH_EAST_BOTTOM,
    NORTH_WEST_BOTTOM,
    SOUTH_EAST_BOTTOM,
    SOUTH_WEST_BOTTOM;

    public String toString() {
        return this.asString();
    }

    @Override
    public String asString() {
        return switch (this) {
            case TOP_CENTER -> "top_center";
            case NORTH_TOP -> "north_top";
            case SOUTH_TOP -> "south_top";
            case EAST_TOP -> "east_top";
            case WEST_TOP -> "west_top";
            case NORTH_EAST_TOP -> "north_east_top";
            case NORTH_WEST_TOP -> "north_west_top";
            case SOUTH_EAST_TOP -> "south_east_top";
            case SOUTH_WEST_TOP -> "south_west_top";
            case CENTER -> "center";
            case NORTH_MID -> "north_mid";
            case SOUTH_MID -> "south_mid";
            case EAST_MID -> "east_mid";
            case WEST_MID -> "west_mid";
            case NORTH_EAST_MID -> "north_east_mid";
            case NORTH_WEST_MID -> "north_west_mid";
            case SOUTH_EAST_MID -> "south_east_mid";
            case SOUTH_WEST_MID -> "south_west_mid";
            case BOTTOM_CENTER -> "bottom_center";
            case NORTH_BOTTOM -> "north_bottom";
            case SOUTH_BOTTOM -> "south_bottom";
            case EAST_BOTTOM -> "east_bottom";
            case WEST_BOTTOM -> "west_bottom";
            case NORTH_EAST_BOTTOM -> "north_east_bottom";
            case NORTH_WEST_BOTTOM -> "north_west_bottom";
            case SOUTH_EAST_BOTTOM -> "south_east_bottom";
            case SOUTH_WEST_BOTTOM -> "south_west_bottom";
        };
    }
}
