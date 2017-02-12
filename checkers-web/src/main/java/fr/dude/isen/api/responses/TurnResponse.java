package fr.dude.isen.api.responses;

import fr.dude.isen.entities.TurnEntity;
import fr.dude.isen.model.pawns.ColorPawn;
import fr.dude.isen.model.pawns.Position;
import fr.dude.isen.model.serializable.SerializablePosition;

/**
 * Class that represents a turn response
 */
public class TurnResponse {

    /**
     * Position from where the turn starts
     */
    private SerializablePosition origin;

    /**
     * Position where the turn ends
     */
    private SerializablePosition destination;

    public TurnResponse(SerializablePosition origin, SerializablePosition destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public TurnResponse(TurnEntity entity) {
        this(entity.getInitPosition(), entity.getDestination());
    }

    public SerializablePosition getOrigin() {
        return origin;
    }

    public SerializablePosition getDestination() {
        return destination;
    }
}
