package fr.dude.isen;

import com.google.inject.Inject;
import com.google.inject.name.Named;

/**
 * Created by Pierre Defache on 09/01/2017.
 */
public class CheckersGameImpl implements CheckersGame {

    @Inject
    @Named("checkers.board.row")
    private Integer NbRows;


    @Inject
    @Named("checkers.board.column")
    private Integer NbColumns;

    @Override
    public void run() {
    }

    @Override
    public Integer getNbRows() {
        return NbRows;
    }

    public void setNbRows(Integer nbRows) {
        NbRows = nbRows;
    }

    @Override
    public Integer getNbColumns() {
        return NbColumns;
    }

    public void setNbColumns(Integer nbColumns) {
        NbColumns = nbColumns;
    }
}
