package fr.dude.isen.model;

import fr.dude.isen.exceptions.UnauthorizedMoveException;
import fr.dude.isen.model.pawns.*;

import java.util.Scanner;

/**
 * Created by pierredfc on 09/01/2017.
 */
public class Board {

    private Cells cells;
    private BoardManager boardManager;

    private User userWhite;
    private User userBlack;

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
        this.userWhite = new User(nbPawns, ColorPawn.WHITE, Direction.DOWN, 0);
        this.userBlack = new User(nbPawns, ColorPawn.BLACK, Direction.UP, this.cells.getLastRowIndex());
    }

    private void initPawns(int nbPawnRows) {
        this.initPawnsUserWhite(nbPawnRows);
        this.initPawnsUserBlack(nbPawnRows);
    }

    private void initPawnsUserWhite(int nbPawnRows) {
        for (int row = 0; row < nbPawnRows; row++) {
            /*for (int column = row % 2 == 0 ? 1 : 0; column < nbColumns; column += 2) {
                this.cells.get(column, row).setPawn(this.userBlack.newPawn());
            }*/
            fillColumnWithPawns(this.userBlack, row);
        }
    }

    private void initPawnsUserBlack(int nbPawnRows) {
        for (int row = this.cells.getLastRowIndex(); row > this.cells.getLastRowIndex() - nbPawnRows; row--) {
            /*for(int column = (row % 2 == 1 ? 0:1); column < nbColumns; column+=2) {
            }*/
            fillColumnWithPawns(this.userWhite, row);
        }
    }

    private void fillColumnWithPawns(User user, int row) {
        for (int column = 0; column < this.cells.getNbColumns(); column++) {
            Cell cell = cells.get(row, column);
            if (cell.getColor() == ColorCell.DARK) {
                cell.setPawn(user.newPawn());
            }
        }
    }

    public Cells getCells() {
        return this.cells;
    }

    public User getUserWhite() {
        return this.userWhite;
    }

    public User getUserBlack() {
        return this.userBlack;
    }


    public void play(Position origin, Position destination)
    {
        final Cell originCell = getCell(origin.getRow(), origin.getColumn());
        final Cell destinationCell = getCell(destination.getRow(), destination.getColumn());

        if (originCell.hasPawn())
        {
            this.movePawn(originCell, destinationCell);
        }
    }

    public void movePawn(Cell origin, Cell destination) {
        try {
            Move move = this.boardManager.move(origin, destination);
            checkPawnToDelete(move);
            checkTransformToQueen(move);
        } catch (UnauthorizedMoveException e)
        {
            e.printStackTrace();// TODO
        }
    }

    private void checkPawnToDelete(Move move) {
        if (move.hasPawnToDelete()) {
            Cell pawnCellToDelete = move.getPawnCellToDelete();
            removeUserPawn(pawnCellToDelete.getPawn());
            pawnCellToDelete.setPawn(null);
        }
    }

    public void checkTransformToQueen(Move move) {
        Pawn pawn = move.getDestination().getPawn();
        int row = move.getDestination().getPosition().getRow();
        if ((pawn.getColor() == ColorPawn.BLACK && row == this.userBlack.getQueenRow()) || (pawn.getColor() == ColorPawn.WHITE && row == this.userWhite.getQueenRow())) {
            pawn.toQueen();
        }
    }

    private void removeUserPawn(Pawn pawnToDelete) {
        if (pawnToDelete.getColor() == ColorPawn.BLACK) {
            userBlack.removePawn(pawnToDelete);
        } else {
            userWhite.removePawn(pawnToDelete);
        }
    }

    public Cell getCell(int row, int col) {
        return this.cells.get(row, col);
    }

    public BoardManager getBoardManager() {
        return boardManager;
    }
}
