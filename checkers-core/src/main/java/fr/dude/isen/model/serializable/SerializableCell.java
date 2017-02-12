package fr.dude.isen.model.serializable;

import fr.dude.isen.model.cells.ColorCell;
import fr.dude.isen.model.pawns.Pawn;
import fr.dude.isen.model.pawns.Position;

/**
 * Interface providing the properties used by the API for a Cell
 */
public interface SerializableCell {

    Pawn getPawn();
    ColorCell getColor();
    Position getPosition();

}
