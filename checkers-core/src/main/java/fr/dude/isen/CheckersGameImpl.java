package fr.dude.isen;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import fr.dude.isen.model.Board;
import fr.dude.isen.model.User;

/**
 * Created by pierredfc on 09/01/2017.
 */
public class CheckersGameImpl implements CheckersGame {

    @Inject
    @Named("checkers.board.size")
    private Integer size;

    @Inject
    @Named("checkers.board.pawn_rows")
    private Integer nbPawnRows;

    private Board board;

    @Override
    public void init() {
        this.board = new Board(this.getNbRows(), this.getNbColumns(), this.getNbPawnRows());
    }

    @Override
    public void run() {
        User winner = this.board.launchGame();
        // TODO gestion vainqueur + rejouer
    }

    @Override
    public Integer getNbRows() {
        return size;
    }

    @Override
    public Integer getNbColumns() {
        return size;
    }

    public Integer getNbPawnRows() { return nbPawnRows; }

    public Integer getNbPawnsPerUser() { return getNbPawnRows() * size/2; }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
