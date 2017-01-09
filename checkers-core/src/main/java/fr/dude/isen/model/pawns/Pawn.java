package fr.dude.isen.model.pawns;

import fr.dude.isen.model.Cell;

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
}
