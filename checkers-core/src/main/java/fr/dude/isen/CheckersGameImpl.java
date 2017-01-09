package fr.dude.isen;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import fr.dude.isen.model.Board;

/**
 * Created by pierredfc on 09/01/2017.
 */
public class CheckersGameImpl implements CheckersGame {

    @Inject
    @Named("checkers.board.row")
    public Integer NbRows;


    @Inject
    @Named("checkers.board.column")
    public Integer NbColumns;

    public CheckersGameImpl()
    {
        this.init();
    }

    @Override
    public void init() {
        Board board = new Board();
    }
}
