package fr.dude.isen.model;

import fr.dude.isen.model.pawns.Position;

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

        cells = new ArrayList<>(nbRows);

        for (int row = 0; row < nbRows; row++) {
            List<Cell> rowArray = new ArrayList<>(nbColumns);
            for(int column = 0; column < nbColumns; column++) {
                rowArray.add(new Cell(column, this.getLastRowIndex() - row));
            }
            this.cells.add(rowArray);
        }
    }



    public List<Cell> getRow(int row) {
        return this.cells.get(row);
    }

    public Cell get(Position position) {
        return get(position.getRow(), position.getColumn());
    }

    public Cell get(int row, int col) {
        try  {
            return this.getRow(this.getLastRowIndex() - row).get(col);
        } catch(IndexOutOfBoundsException ex) {
            return null;
        }
    }

    public List<List<Cell>> getCells() {
        return cells;
    }

    public void setCells(List<List<Cell>> cells) {
        this.cells = cells;
    }

    public int getNbColumns() {
        return this.nbColumns;
    }

    public int getNbRows() {
        return this.nbRows;
    }

    public int getLastRowIndex() {
        return this.nbRows - 1;
    }

    public Cell translate(Cell cell, Position direction, int step) {
        return this.get(cell.getPosition().translate(direction, step));
    }
}
