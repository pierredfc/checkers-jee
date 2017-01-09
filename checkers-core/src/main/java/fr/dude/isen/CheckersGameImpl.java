package fr.dude.isen;

import com.google.inject.Inject;
import com.google.inject.name.Named;

/**
 * Created by Pierre Defache on 09/01/2017.
 */
public class CheckersGameImpl implements CheckersGame {

    @Inject
    @Named("checkers.board.row")
    public Integer NbRows;


    @Inject
    @Named("checkers.board.column")
    public Integer NbColumns;

    public CheckersGameImpl() {

    }

    @Override
    public void run() {
        System.out.println("Running checkers game with size : "+NbRows+":"+NbColumns);
    }
}
