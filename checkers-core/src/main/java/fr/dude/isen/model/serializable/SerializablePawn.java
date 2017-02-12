package fr.dude.isen.model.serializable;

import fr.dude.isen.model.pawns.ColorPawn;
import fr.dude.isen.model.pawns.Direction;

import java.io.Serializable;

/**
 * Interface providing the properties used by the API for a Pawn
 */
public interface SerializablePawn extends Serializable {
    ColorPawn getColor();
    Direction getDirection();
}
