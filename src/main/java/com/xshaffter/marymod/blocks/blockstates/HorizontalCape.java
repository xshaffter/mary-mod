package com.xshaffter.marymod.blocks.blockstates;

import net.minecraft.util.StringIdentifiable;

public enum HorizontalCape implements StringIdentifiable {
    CENTER,
    NORTH_MID,
    SOUTH_MID,
    EAST_MID,
    WEST_MID,
    NORTH_EAST_MID,
    NORTH_WEST_MID,
    SOUTH_EAST_MiD,
    SOUTH_WEST_MID;

    public String toString() {
        return this.asString();
    }

    @Override
    public String asString() {
        return switch (this) {
            case CENTER -> "CENTER";
            case NORTH_MID -> "NORTH_MID";
            case SOUTH_MID -> "SOUTH_MID";
            case EAST_MID -> "EAST_MID";
            case WEST_MID -> "WEST_MID";
            case NORTH_EAST_MID -> "NORTH_EAST_MID";
            case NORTH_WEST_MID -> "NORTH_WEST_MID";
            case SOUTH_EAST_MiD -> "SOUTH_EAST_MiD";
            case SOUTH_WEST_MID -> "SOUTH_WEST_MID";
        };
    }
}
