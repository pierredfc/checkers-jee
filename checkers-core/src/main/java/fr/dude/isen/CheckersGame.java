package fr.dude.isen;

import fr.dude.isen.model.cells.Cell;
import fr.dude.isen.model.MoveResult;
import fr.dude.isen.model.Player;
import fr.dude.isen.model.pawns.Move;
import fr.dude.isen.model.pawns.Position;

import java.util.List;

/**
 * Created by pierredfc on 09/01/2017.
 */
public interface CheckersGame {

    /**
     * Initialize a checkers game
     */
    void init();

    /**
     * Play a move with Positions
     * @param init
     * @param destination
     * @return
     */
    MoveResult play(Position init, Position destination);

    /**
     * @param row Row index of the cell
     * @param column Column index of the cell
     * @return the cell at (row, column)
     */
    Cell getCell(int row, int column);

    /**
     * @param position
     * @return the list of possible moves from a position.
     */
    List<Move> getPossibleMoves(Position position);

    /**
     * @return the number of rows.
     */
    Integer getNbRows();

    /**
     * @return the number of columns.
     */
    Integer getNbColumns();

    /**
     * @return the player who owns white pawns.
     */
    Player getPlayerWhite();

    /**
     * @return the player who owns black pawns.
     */
    Player getPlayerBlack();
}
