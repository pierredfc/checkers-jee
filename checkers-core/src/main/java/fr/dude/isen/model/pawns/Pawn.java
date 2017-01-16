package fr.dude.isen.model.pawns;

import fr.dude.isen.model.Cell;

/**
 * Created by pierredfc on 09/01/2017.
 */
public class Pawn {

    private ColorPawn color;

    private Direction direction;

    public Pawn(ColorPawn color, Direction direction) {
        this.color = color;
        this.direction = direction;
    }

    public ColorPawn getColor() {
        return color;
    }

    public void setColor(ColorPawn color) {
        this.color = color;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }


    public void toQueen() {
        this.direction = Direction.QUEEN;
    }
}
