package com.xshaffter.marymod.blocks.blockstates;

import net.minecraft.util.StringIdentifiable;

public enum TextState implements StringIdentifiable {
    TEXT_1,
    TEXT_2,
    TEXT_3,
    TEXT_4,
    TEXT_5,
    TEXT_6,
    TEXT_7,
    TEXT_8,
    TEXT_9,
    TEXT_10,
    TEXT_11,
    TEXT_12,
    TEXT_13,
    TEXT_14,
    TEXT_15,
    TEXT_16,
    TEXT_17;

    public String toString() {
        return this.asString();
    }

    @Override
    public String asString() {
        return switch (this) {

            case TEXT_1 -> "alice_madness_returns";
            case TEXT_2 -> "outlast_1_y_2";
            case TEXT_3 -> "hogwarts_legacy";
            case TEXT_4 -> "heavy_rain";
            case TEXT_5 -> "life_is_strange";
            case TEXT_6 -> "ori";
            case TEXT_7 -> "martha_is_dead";
            case TEXT_8 -> "little_nightmares_1";
            case TEXT_9 -> "one_piece";
            case TEXT_10 -> "cult_of_the_lamb";
            case TEXT_11 -> "ring_fit_adventures";
            case TEXT_12 -> "fortnite";
            case TEXT_13 -> "valorant";
            case TEXT_14 -> "dota";
            case TEXT_15 -> "subnautica";
            case TEXT_16 -> "lista_de_pendientes";
            case TEXT_17 -> "fnaf_security_breach";
        };
    }
    public String getText() {
        return switch (this) {

            case TEXT_1 -> "Alice Madness Returns";
            case TEXT_2 -> "Outlast (1 y 2)";
            case TEXT_3 -> "Hogwarts Legacy";
            case TEXT_4 -> "Heavy Rain";
            case TEXT_5 -> "Life Is Strange";
            case TEXT_6 -> "Ori";
            case TEXT_7 -> "Martha is Dead";
            case TEXT_8 -> "Little Nightmares 1";
            case TEXT_9 -> "One Piece";
            case TEXT_10 -> "Cult of the lamb";
            case TEXT_11 -> "Ring Fit Adventures";
            case TEXT_12 -> "Fortnite";
            case TEXT_13 -> "Valorant";
            case TEXT_14 -> "Dota";
            case TEXT_15 -> "Subnautica";
            case TEXT_16 -> "Lista de pendientes";
            case TEXT_17 -> "FNAF: Security Breach";
        };
    }
}
