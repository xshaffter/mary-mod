package com.xshaffter.marymod.blocks.blockstates;

import net.minecraft.util.StringIdentifiable;

public enum CubySkin implements StringIdentifiable {
    AMILCAR,
    ANSI,
    CRAFT,
    DAARICK,
    EMET;

    public String toString() {
        return this.asString();
    }

    @Override
    public String asString() {
        return switch (this) {
            case AMILCAR -> "amilcar";
            case ANSI -> "ansi";
            case CRAFT -> "craft";
            case DAARICK -> "daarick";
            case EMET -> "emet";
        };
    }
}
