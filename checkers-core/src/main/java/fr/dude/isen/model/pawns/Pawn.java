package fr.dude.isen.model.pawns;

import fr.dude.isen.model.Cell;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pierredfc on 09/01/2017.
 */
public abstract class Pawn {

    private ColorPawn color;

    private Direction direction;

    private Cell cell;

    public Pawn(ColorPawn color, Direction direction) {
        this.color = color;
        this.direction = direction;
    }

    public abstract void move(Cell cell);

    public ColorPawn getColor() {
        return color;
    }

    public void setColor(ColorPawn color) {
        this.color = color;
    }

    public void setCell(Cell cell) {
        if (this.cell != null) {
            this.cell.setCurrentPawn(null);
        }
        cell.setCurrentPawn(this);
        this.cell = cell;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Cell getCell() {
        return cell;
    }

    public List<Cell> getPossibleMoves(List<List<Cell>> cells) {
        Cell current = this.getCell();
        Position position = current.getPosition();
        int col = position.getColumnIndex();
        int row = position.getRowIndex();

        List<Cell> result = new ArrayList<>(2);

        Direction direction = getDirection();
        boolean up = direction == Direction.UP;
        boolean down = direction == Direction.DOWN;
        boolean both = direction == Direction.BOTH;

        if (up || both) {
            tryAddCell(col-1, row+1, cells, result);
            tryAddCell(col+1, row+1, cells, result);
        }

        if (down || both) {
            tryAddCell(col-1, row-1, cells, result);
            tryAddCell(col+1, row-1, cells, result);
        }
        return result;
    }

    private void tryAddCell(int col, int row, List<List<Cell>> cells, List<Cell> result) {
        try {
            result.add(cells.get(col).get(row));
        }
        catch(IndexOutOfBoundsException ex) {
            //
        }
    }
}
