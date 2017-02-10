package fr.dude.isen.model;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import fr.dude.isen.exceptions.UnauthorizedMoveException;
import fr.dude.isen.model.pawns.*;

import java.io.Serializable;

/**
 * Created by pierredfc on 09/01/2017.
 */
public class Board implements Serializable {

    private Cells cells;
    private BoardManager boardManager;

    private ColorPawn nextUser = ColorPawn.WHITE;

    private Player playerWhite;
    private Player playerBlack;

    public Board(int nbRows, int nbColumns, int nbPawnRows) {
        this.initCells(nbRows, nbColumns);
        this.initUsers(nbPawnRows * nbColumns / 2);
        this.initPawns(nbPawnRows);
    }

    private void initCells(int nbRows, int nbColumns) {
        this.cells = new Cells(nbRows, nbColumns);
        this.boardManager = new BoardManager(cells);
    }

    private void initUsers(int nbPawns) {
        this.playerWhite = new Player("Default User 1", nbPawns, ColorPawn.WHITE, Direction.DOWN, 0);
        this.playerBlack = new Player("Default User 2", nbPawns, ColorPawn.BLACK, Direction.UP, this.cells.getLastRowIndex());
        this.playerWhite.setOpponent(playerBlack);
        this.playerBlack.setOpponent(playerWhite);
    }

    private void initPawns(int nbPawnRows) {
        this.initPawnsUserWhite(nbPawnRows);
        this.initPawnsUserBlack(nbPawnRows);
    }

    private void initPawnsUserWhite(int nbPawnRows) {
        for (int row = 0; row < nbPawnRows; row++) {
            /*for (int column = row % 2 == 0 ? 1 : 0; column < nbColumns; column += 2) {
                this.cells.get(column, row).setPawn(this.userrBlack.newPawn());
            }*/
            fillColumnWithPawns(this.playerBlack, row);
        }
    }

    private void initPawnsUserBlack(int nbPawnRows) {
        for (int row = this.cells.getLastRowIndex(); row > this.cells.getLastRowIndex() - nbPawnRows; row--) {
            /*for(int column = (row % 2 == 1 ? 0:1); column < nbColumns; column+=2) {
            }*/
            fillColumnWithPawns(this.playerWhite, row);
        }
    }

    private void fillColumnWithPawns(Player player, int row) {
        for (int column = 0; column < this.cells.getNbColumns(); column++) {
            Cell cell = cells.get(row, column);
            if (cell.getColor() == ColorCell.DARK) {
                cell.setPawn(player.newPawn());
            }
        }
    }

    public Cells getCells() {
        return this.cells;
    }

    public Player getPlayerWhite() {
        return this.playerWhite;
    }

    public Player getPlayerBlack() {
        return this.playerBlack;
    }

    public MoveResult play(Position origin, Position destination) {
        final Cell originCell = getCell(origin.getRow(), origin.getColumn());
        final Cell destinationCell = getCell(destination.getRow(), destination.getColumn());
        return play(originCell, destinationCell);

    }

    public MoveResult play(Cell origin, Cell destination) {
        if (origin.hasPawn() && origin.getPawn().getColor() == this.nextUser) {
            Player player = origin.getPawn().getColor() == ColorPawn.BLACK ? this.getPlayerBlack() : this.getPlayerWhite();
            return this.movePawn(player, origin, destination);
        }
        return null;
    }

    private MoveResult movePawn(Player player, Cell origin, Cell destination) {
        try {
            Move move = this.boardManager.move(origin, destination);
            checkPawnToDelete(move, player);
            boolean becomesQueen = checkTransformToQueen(move);
            this.nextUser = getNextUser(move) ? player.getColorPawn() : player.findOpponentColor();
            return new MoveResult(origin, move, nextUser, becomesQueen, this.playerWhite.getNbPawns(), this.playerBlack.getNbPawns(), this.isUserWins(player));
        } catch (UnauthorizedMoveException e) {
            return null;
        }
    }

    private boolean isUserWins(Player player)
    {
        return (player.getOpponent().getNbPawns() == 0);
    }

    private boolean getNextUser(Move move) {
        return move.getPawnCellToDelete() != null;
    }

    private void checkPawnToDelete(Move move, Player player) {
        if (move.hasPawnToDelete()) {
            Cell pawnCellToDelete = move.getPawnCellToDelete();
            player.decrementOpponentPawns();
            pawnCellToDelete.setPawn(null);
        }
    }

    public boolean checkTransformToQueen(Move move) {
        Pawn pawn = move.getDestination().getPawn();
        int row = move.getDestination().getPosition().getRow();
        if ((pawn.getColor() == ColorPawn.BLACK && row == this.playerBlack.getQueenRow()) || (pawn.getColor() == ColorPawn.WHITE && row == this.playerWhite.getQueenRow())) {
            pawn.toQueen();
            return true;
        }
        return false;
    }

    public Cell getCell(int row, int col) {
        return this.cells.get(row, col);
    }

    public Cell getCell(Position position) {
        return getCell(position.getRow(), position.getColumn());
    }

    public BoardManager getBoardManager() {
        return boardManager;
    }

}
