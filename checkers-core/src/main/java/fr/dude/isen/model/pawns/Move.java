package fr.dude.isen.model.pawns;

import fr.dude.isen.model.Cell;

/**
 * Created by Clement on 10/01/2017.
 */
public class Move {

    private Cell destination;
    private Pawn pawnToDelete;

    public Move(Cell destination) {
        this.destination = destination;
    }

    public Move(Cell destination, Pawn pawnToDelete) {
        this(destination);
        this.pawnToDelete = pawnToDelete;
    }

    public Cell getDestination() {
        return destination;
    }

    public Pawn getPawnToDelete() {
        return pawnToDelete;
    }

    public boolean hasPawnToDelete() {
        return this.pawnToDelete != null;
    }

    public boolean isMandatory() {
        return hasPawnToDelete();
    }
}
