package fr.dude.isen.model.serializable;

import fr.dude.isen.model.cells.Cell;

import java.io.Serializable;

/**
 * Interface providing the properties used by the API for a Move
 */
public interface SerializableMove extends Serializable {
    Cell getDestination();
    Cell getPawnCellToDelete();
    boolean isMandatory();

}
