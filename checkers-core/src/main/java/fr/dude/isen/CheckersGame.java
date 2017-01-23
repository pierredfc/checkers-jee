package fr.dude.isen;

import fr.dude.isen.model.Cell;
import fr.dude.isen.model.pawns.Position;

/**
 * Created by pierredfc on 09/01/2017.
 */
public interface CheckersGame {

    void init();
    void run();

    void play(Position init, Position destination);

    Integer getNbRows();
    Integer getNbColumns();
}
