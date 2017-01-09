package fr.dude.isen.model;

import fr.dude.isen.model.pawns.ColorPawn;
import fr.dude.isen.model.pawns.Direction;
import fr.dude.isen.model.pawns.Pawn;
import fr.dude.isen.model.pawns.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by pierredfc on 09/01/2017.
 */
public class Board {

    private List<List<Cell>> cells;

    private User user1;
    private User user2;


    public Board(int nbRows, int nbColumns, int nbPawnsPerUser) {
        this.initCells(nbRows, nbColumns);
        this.initUsers(nbPawnsPerUser);
        this.initPawns(nbRows, nbColumns);
    }

    private void initCells(int nbRows, int nbColumns) {
        this.cells = new ArrayList<>(nbRows);

        for (int row = 0; row < nbRows; row++) {
            this.cells.add(new ArrayList<>(nbColumns));

            for (int column = 0; column < nbColumns; column++) {
                this.cells.get(row).add(new Cell(row, column));
            }
        }
    }

    private void initUsers(int nbPawns) {
        this.user1 = new User(nbPawns, ColorPawn.WHITE, Direction.UP);
        this.user2 = new User(nbPawns, ColorPawn.BLACK, Direction.DOWN);
    }

    private void initPawns(int nbRows, int nbColumns) {
        this.initPawnsUser1(nbRows, nbColumns);
        this.initPawnsUser2(nbRows, nbColumns);
    }

    private void initPawnsUser1(int nbRows, int nbColumns) {
        ListIterator<Pawn> it = this.user1.getPawns().listIterator();
        for (int y = 0; y < 4; y++) {
            for (int x = y % 2 == 0 ? 1 : 0; x < nbColumns; x += 2) {
                if (it.hasNext()) {
                    Pawn pawn = it.next();
                    pawn.setCell(getCell(y, x));
                } else {
                    break;
                }
            }
        }
    }

    private void initPawnsUser2(int nbRows, int nbColumns) {
        ListIterator<Pawn> it = this.user2.getPawns().listIterator();

        for(int y = 9; y > 9-4; y--) {
            for (int x = (y % 2 == 1 ? 0 : 1); x < nbColumns; x+=2) {
                if (it.hasNext()) {
                    Pawn pawn = it.next();
                    pawn.setCell(getCell(y, x));
                } else {
                    break;
                }
            }
        }
    }

    public List<List<Cell>> getCells() {
        return cells;
    }

    public Cell getCell(int x, int y) {
        return getCells().get(x).get(y);
    }

    public User getUser1() {
        return this.user1;
    }

    public User getUser2() {
        return this.user2;
    }
}
