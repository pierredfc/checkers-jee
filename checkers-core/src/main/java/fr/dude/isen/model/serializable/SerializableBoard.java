package fr.dude.isen.model.serializable;

import fr.dude.isen.model.Player;
import fr.dude.isen.model.cells.Cells;
import fr.dude.isen.model.pawns.ColorPawn;

/**
 * Interface providing the properties used by the API for a Board
 */
public interface SerializableBoard {

    /**
     *
     * @return The cells of the board
     */
    Cells getCells();

    /**
     *
     * @return Player who owns the white pawns
     */
    Player getPlayerWhite();

    /**
     *
     * @return Player who owns the black pawns
     */
    Player getPlayerBlack();

    /**
     *
     * @return Whose turn is next
     */
    ColorPawn getNextPlayer();
}
