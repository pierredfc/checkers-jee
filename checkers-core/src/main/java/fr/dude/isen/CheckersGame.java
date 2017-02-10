package fr.dude.isen;

import fr.dude.isen.model.Cell;
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
     *
     */
    void init();

    /**
     *
     * @param init
     * @param destination
     * @return
     */
    MoveResult play(Position init, Position destination);

    /**
     *
     * @param row
     * @param column
     * @return
     */
    Cell getCell(int row, int column);

    /**
     *
     * @param position
     * @return
     */
    List<Move> getPossibleMoves(Position position);

    /**
     *
     * @return
     */
    Integer getNbRows();

    /**
     *
     * @return
     */
    Integer getNbColumns();

    /**
     *
     * @return
     */
    Player getUserWhite();

    /**
     *
     * @return
     */
    Player getUserBlack();
}
