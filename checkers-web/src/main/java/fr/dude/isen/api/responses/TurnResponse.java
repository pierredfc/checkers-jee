package fr.dude.isen.api.responses;

import fr.dude.isen.entities.TurnEntity;
import fr.dude.isen.model.pawns.ColorPawn;
import fr.dude.isen.model.pawns.Position;

/**
 * Class that represents a turn response
 */
public class TurnResponse {

    /**
     * Position from where the turn starts
     */
    private Position origin;

    /**
     * Position where the turn ends
     */
    private Position destination;

    public TurnResponse(Position origin, Position destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public TurnResponse(TurnEntity entity) {
        this(entity.getInitPosition(), entity.getDestination());
    }

    public Position getOrigin() {
        return origin;
    }

    public Position getDestination() {
        return destination;
    }
}
