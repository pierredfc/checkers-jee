package fr.dude.isen.model.pawns;

import fr.dude.isen.model.Cell;

/**
 * Created by Clement on 10/01/2017.
 */
public class Move {

    private Cell destination;
    private Cell pawnCellToDelete;

    public Move(Cell destination) {
        this.destination = destination;
    }

    public Move(Cell destination, Cell pawnCellToDelete) {
        this(destination);
        this.pawnCellToDelete = pawnCellToDelete;
    }

    public Cell getDestination() {
        return destination;
    }

    public Cell getPawnCellToDelete() {
        return pawnCellToDelete;
    }

    public boolean hasPawnToDelete() {
        return this.pawnCellToDelete != null && this.pawnCellToDelete.getPawn() != null;
    }

    public boolean isMandatory() {
        return hasPawnToDelete();
    }
}
