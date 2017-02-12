package fr.dude.isen.model.serializable;

import java.io.Serializable;

/**
 * Interface providing the properties used by the API for a Position
 */
public interface SerializablePosition extends Serializable {

    Integer getColumn();
    Integer getRow();

}
