package fr.dude.isen.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pierredfc on 09/01/2017.
 */
public class Board {

    private List<List<Cell>> cells;

    public Board()
    {
        this.cells = new ArrayList<>(10);

        for (int i = 0; i < 10; i++)
        {
            this.cells.add(new ArrayList<>(10));

            for(int j = 0; j < 10; j++)
            {
                this.cells.get(i).add(new Cell(i, j));
            }
        }
    }

    public List<List<Cell>> getCells() {
        return cells;
    }
}
