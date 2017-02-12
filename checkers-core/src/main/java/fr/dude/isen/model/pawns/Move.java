package fr.dude.isen.model.pawns;

import fr.dude.isen.model.cells.Cell;
import fr.dude.isen.model.serializable.SerializableMove;

/**
 * Class that represents a pawn's move to a specific destination cell.
 */
public class Move implements SerializableMove {

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

    @Override
    public Cell getDestination() {
        return destination;
    }

    @Override
    public Cell getPawnCellToDelete() {
        return pawnCellToDelete;
    }

    /**
     * A move is mandatory if it involves to delete a pawn.
     * @return Whether the move is mandatory or not
     */
    @Override
    public boolean isMandatory() {
        return hasPawnToDelete();
    }

    public boolean hasPawnToDelete() {
        return this.pawnCellToDelete != null && this.pawnCellToDelete.getPawn() != null;
    }
}
