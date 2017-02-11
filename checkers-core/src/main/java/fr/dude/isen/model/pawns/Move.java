package fr.dude.isen.model.pawns;

import fr.dude.isen.model.cells.Cell;

/**
 * Class that represents a pawn's move to a specific destination cell.
 */
public class Move {

    /**
     * Destination of the pawn
     */
    private Cell destination;

    /**
     * Set if a pawn was on the path of the move
     */
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

    /**
     * A move is mandatory if it's include to delete a pawn.
     * @return
     */
    public boolean isMandatory() {
        return hasPawnToDelete();
    }
}
