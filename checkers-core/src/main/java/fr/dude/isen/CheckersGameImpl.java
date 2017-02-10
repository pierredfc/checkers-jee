package fr.dude.isen;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import fr.dude.isen.model.Board;
import fr.dude.isen.model.Cell;
import fr.dude.isen.model.MoveResult;
import fr.dude.isen.model.Player;
import fr.dude.isen.model.pawns.*;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pierredfc on 09/01/2017.
 */
public class CheckersGameImpl implements CheckersGame, Serializable {

    @Inject
    @Named("checkers.board.size")
    private Integer size;

    @Inject
    @Named("checkers.board.pawn_rows")
    private Integer nbPawnRows;

    @Inject
    @Named("checkers.board.player_white.name")
    private String usernamePlayerWhite;

    @Inject
    @Named("checkers.board.player_black.name")
    private String usernamePlayerBlack;

    private Board board;

    @Override
    public void init() {
        this.board = new Board(this.getNbRows(), this.getNbColumns(), this.getNbPawnRows());
        this.board.getPlayerWhite().setName(usernamePlayerWhite);
        this.board.getPlayerBlack().setName(usernamePlayerBlack);
    }

    @Override
    public MoveResult play(Position init, Position destination) {
        return this.board.play(init, destination);
    }

    @Override
    public Cell getCell(int row, int column) {
        return this.board.getCell(row, column);
    }

    @Override
    public List<Move> getPossibleMoves(Position position) {
        return this.board.getBoardManager().getPossibleMoves(this.board.getCell(position));
    }

    @Override
    public Integer getNbRows() {
        return size;
    }

    @Override
    public Integer getNbColumns() {
        return size;
    }

    @JsonIgnore
    @Override
    public Player getUserWhite() {
        return this.board.getPlayerWhite();
    }

    @JsonIgnore
    @Override
    public Player getUserBlack() {
        return this.board.getPlayerBlack();
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
