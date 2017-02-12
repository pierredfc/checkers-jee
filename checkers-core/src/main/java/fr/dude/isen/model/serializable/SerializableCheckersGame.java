package fr.dude.isen.model.serializable;

import fr.dude.isen.model.Board;
import fr.dude.isen.model.Player;

import java.io.Serializable;

/**
 * Interface providing the properties used by the API for a CheckersGame
 */
public interface SerializableCheckersGame extends Serializable {

    /**
     * Serialized field
     * @return the number of rows.
     */
    Integer getNbRows();

    /**
     * Serialized field
     * @return the number of columns.
     */
    Integer getNbColumns();

    /**
     * Serialized field
     * @return the game board.
     */
    Board getBoard();

    /**
     * @return the player who owns white pawns.
     */
    Player getPlayerWhite();

    /**
     * @return the player who owns black pawns.
     */
    Player getPlayerBlack();

}
