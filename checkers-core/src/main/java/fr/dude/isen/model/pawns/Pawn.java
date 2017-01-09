package fr.dude.isen.model.pawns;

/**
 * Created by pierredfc on 09/01/2017.
 */
public abstract class Pawn {

    private Position currentPosition;

    private ColorPawn color;

    private Direction direction;

    public abstract void move(Position position);

    public ColorPawn getColor() {
        return color;
    }

    public void setColor(ColorPawn color) {
        this.color = color;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Position currentPosition) {
        this.currentPosition = currentPosition;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
