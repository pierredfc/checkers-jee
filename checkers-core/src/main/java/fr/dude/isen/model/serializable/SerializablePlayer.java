package fr.dude.isen.model.serializable;

import fr.dude.isen.model.pawns.ColorPawn;
import fr.dude.isen.model.pawns.Direction;

import java.io.Serializable;

/**
 * Interface providing the properties used by the API for a Player
 */
public interface SerializablePlayer extends Serializable {

    /**
     *
     * @return The number of pawns the player owns
     */
    int getNbPawns();

    /**
     *
     * @return The color of the player pawns
     */
    ColorPawn getColorPawn();

    /**
     *
     * @return The direction of the player pawns
     */
    Direction getPawnDirection();

    /**
     *
     * @return The row on which the player pawns become queen
     */
    int getQueenRow();

    /**
     *
     * @return The name of the player
     */
    String getName();

}
