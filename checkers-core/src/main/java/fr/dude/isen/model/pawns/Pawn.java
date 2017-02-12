package fr.dude.isen.model.pawns;

import fr.dude.isen.model.serializable.SerializablePawn;

/**
 * Class that represents a Pawn.
 */
public class Pawn implements SerializablePawn {

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

    @Override
    public ColorPawn getColor() {
        return color;
    }

    @Override
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
