package fr.dude.isen.model;

import fr.dude.isen.model.pawns.ColorPawn;
import fr.dude.isen.model.pawns.Direction;
import fr.dude.isen.model.pawns.Pawn;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by pierredfc on 09/01/2017.
 */
public class Board {

    private List<List<Cell>> cells;

    private User userWhite;
    private User userBlack;

    public Board(int nbRows, int nbColumns, int nbPawnsPerUser) {
        this.initCells(nbRows, nbColumns);
        this.initUsers(nbPawnsPerUser);
        this.initPawns(nbRows, nbColumns);
    }

    private void initCells(int nbRows, int nbColumns) {
        this.cells = new ArrayList<>(nbColumns);

        for (int column = 0; column < nbColumns; column++) {
            this.cells.add(new ArrayList<>(nbRows));

            for (int row = 0; row < nbRows; row++) {
                this.cells.get(column).add(new Cell(column, row));
            }
        }
    }

    private void initUsers(int nbPawns) {
        this.userWhite = new User(nbPawns, ColorPawn.WHITE, Direction.UP);
        this.userBlack = new User(nbPawns, ColorPawn.BLACK, Direction.DOWN);
    }

    private void initPawns(int nbRows, int nbColumns) {
        this.initPawnsUserWhite(nbColumns);
        this.initPawnsUserBlack(nbColumns);
    }

    private void initPawnsUserWhite(int nbColumns) {
        ListIterator<Pawn> it = this.userWhite.getPawns().listIterator();

        for (int row = 0; row < 4; row++) {
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

    private void initPawnsUserBlack(int nbColumns) {
        ListIterator<Pawn> it = this.userBlack.getPawns().listIterator();

        for(int row = 9; row > 9-4; row--) {
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

    public List<List<Cell>> getCells() {
        return this.cells;
    }

    public Cell getCell(int columnIndex, int rowIndex) {
        return this.getCells().get(columnIndex).get(rowIndex);
    }

    public User getUserWhite() {
        return this.userWhite;
    }

    public User getUserBlack() {
        return this.userBlack;
    }
}
