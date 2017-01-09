package fr.dude.isen.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pierredfc on 09/01/2017.
 */
public class Board {

    private List<List<Cell>> cells;

    public Board(int nbRows, int nbColumns)
    {
        this.cells = new ArrayList<>(nbRows);

        for (int row = 0; row < nbRows; row++)
        {
            this.cells.add(new ArrayList<>(nbColumns));

            for(int column = 0; column < nbColumns; column++)
            {
                this.cells.get(row).add(new Cell(row, column));
            }
        }
    }

    public List<List<Cell>> getCells() {
        return cells;
    }
}
