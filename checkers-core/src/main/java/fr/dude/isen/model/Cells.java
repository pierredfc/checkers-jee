package fr.dude.isen.model;

import fr.dude.isen.exceptions.UnauthorizedMoveException;
import fr.dude.isen.model.pawns.Move;
import fr.dude.isen.model.pawns.Pawn;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Clement on 10/01/2017.
 */
public class Cells {

    private List<List<Cell>> cells;

    private int nbColumns;
    private int nbRows;

    public Cells(int nbRows, int nbColumns) {
        this.nbRows = nbRows;
        this.nbColumns = nbColumns;

        cells = new ArrayList<>(nbColumns);

        for (int column = 0; column < nbColumns; column++) {
            this.cells.add(new ArrayList<>(nbRows));

            for (int row = 0; row < nbRows; row++) {
                this.cells.get(column).add(new Cell(column, row));
            }
        }
    }



    public List<Cell> getColumn(int col) {
        return this.cells.get(col);
    }

    public Cell get(int col, int row) {
        return this.getColumn(col).get(row);
    }

    public int getNbColumns() {
        return this.nbColumns;
    }

    public int getNbRows() {
        return this.nbRows;
    }

    public Move move(Pawn pawn, Cell destinationCell) throws UnauthorizedMoveException {
        return pawn.move(destinationCell, this);
    }
}
