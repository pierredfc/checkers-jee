package fr.dude.isen.model;

import fr.dude.isen.exceptions.UnauthorizedMoveException;
import fr.dude.isen.model.pawns.*;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

/**
 * Created by pierredfc on 09/01/2017.
 */
public class Board {

    private Cells cells;

    private User userWhite;
    private User userBlack;

    public Board(int nbRows, int nbColumns, int nbPawnRows) {
        this.initCells(nbRows, nbColumns);
        this.initUsers(nbPawnRows * nbColumns/2);
        this.initPawns(nbPawnRows, nbRows, nbColumns);
    }

    private void initCells(int nbRows, int nbColumns) {
        this.cells = new Cells(nbRows, nbColumns);
    }

    private void initUsers(int nbPawns) {
        this.userWhite = new User(nbPawns, ColorPawn.WHITE, Direction.UP);
        this.userBlack = new User(nbPawns, ColorPawn.BLACK, Direction.DOWN);
    }

    private void initPawns(int nbPawnRows, int nbRows, int nbColumns) {
        this.initPawnsUserWhite(nbPawnRows, nbColumns, nbRows);
        this.initPawnsUserBlack(nbPawnRows, nbColumns, nbRows);
    }

    private void initPawnsUserWhite(int nbPawnRows, int nbColumns, int nbRows) {
        ListIterator<Pawn> it = this.userWhite.getPawns().listIterator();

        for (int row = 0; row < nbPawnRows; row++) {
            for (int column = row % 2 == 0 ? 1 : 0; column < nbColumns; column += 2) {
                if (it.hasNext()) {
                    Pawn pawn = it.next();
                    pawn.setCell(getCell(column, row));
                } else {
                    break;
                }
            }
        }
    }

    private void initPawnsUserBlack(int nbPawnRows, int nbColumns, int nbRows) {
        ListIterator<Pawn> it = this.userBlack.getPawns().listIterator();

        for(int row = nbRows-1; row > (nbRows-1)-nbPawnRows; row--) {
            for (int column = (row % 2 == 1 ? 0 : 1); column < nbColumns; column+=2) {
                if (it.hasNext()) {
                    Pawn pawn = it.next();
                    pawn.setCell(getCell(column, row));
                } else {
                    break;
                }
            }
        }
    }

    public User launchGame()
    {
        // White start!
        Scanner sc = new Scanner(System.in);
        int col = sc.nextInt();
        int row = sc.nextInt();

        int destinationCol = sc.nextInt();
        int destinationRow = sc.nextInt();

        sc.close();

        Position begin = new Position(col, row);
        Position end = new Position(destinationCol, destinationRow);

        System.out.println(begin.toString() + " to " + end.toString());

        Cell beginCell = this.getCell(begin);
        Cell endCell = this.getCell(end);

        movePawn(beginCell.getCurrentPawn(), endCell);

        return null;
    }

    public Cells getCells()
    {
        return this.cells;
    }

    public Cell getCell(int columnIndex, int rowIndex) {
        return this.getCells().get(columnIndex, rowIndex);
    }

    public Cell getCell(Position position)
    {
        return this.getCells().get(position.getColumnIndex(), position.getRowIndex());
    }

    public User getUserWhite() {
        return this.userWhite;
    }

    public User getUserBlack() {
        return this.userBlack;
    }

    public void movePawn(Pawn pawn, Cell destinationCell) {
        try {
            Move move = this.cells.move(pawn, destinationCell);
            Pawn pawnToDelete = move.getPawnToDelete();
            if (pawnToDelete == null) return;

            if (pawnToDelete.getColor() == ColorPawn.BLACK) {
                userBlack.removePawn(pawnToDelete);
            } else {
                userWhite.removePawn(pawnToDelete);
            }
        } catch (UnauthorizedMoveException e) {
            e.printStackTrace();// TODO
        }
    }
}
