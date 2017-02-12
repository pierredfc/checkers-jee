package fr.dude.isen.model.serializable;

import fr.dude.isen.model.cells.Cell;

import java.io.Serializable;
import java.util.List;

/**
 * Interface providing the properties used by the API for Cells
 */
public interface SerializableCells extends Serializable {

    List<List<Cell>> getCells();

}
