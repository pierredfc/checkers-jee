package fr.dude.isen.model.pawns;

/**
 * Class that represents a Pawn.
 */
public class Pawn {

    /**
     * Pawn's color
     */
    private ColorPawn color;

    /**
     * Direction's pawn
     */
    private Direction direction;

    public Pawn(ColorPawn color, Direction direction) {
        this.color = color;
        this.direction = direction;
    }

    public ColorPawn getColor() {
        return color;
    }

    public Direction getDirection() {
        return direction;
    }

    /**
     * Call when a pawn becomes a queen.
     */
    public void toQueen() {
        this.direction = Direction.QUEEN;
    }

}
