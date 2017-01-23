package fr.dude.isen;

import fr.dude.isen.model.Cell;
import fr.dude.isen.model.MoveResult;
import fr.dude.isen.model.pawns.Position;

/**
 * Created by pierredfc on 09/01/2017.
 */
public interface CheckersGame {

    void init();

    MoveResult play(Position init, Position destination);

    Cell getCell(int row, int column);

    Integer getNbRows();
    Integer getNbColumns();
}
