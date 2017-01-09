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
    private Integer nbRows;

    @Inject
    @Named("checkers.board.column")
    private Integer nbColumns;

    @Inject
    @Named("checkers.board.pawns_per_user")
    private Integer nbPawnsPerUser;

    private Board board;

    @Override
    public void init() {
        this.board = new Board(this.getNbRows(), this.getNbColumns(), this.getNbPawnsPerUser());
    }

    @Override
    public void run() {
        this.init();
    }

    @Override
    public Integer getNbRows() {
        return nbRows;
    }

    @Override
    public Integer getNbColumns() {
        return nbColumns;
    }

    public Integer getNbPawnsPerUser() { return this.nbPawnsPerUser; }
}
