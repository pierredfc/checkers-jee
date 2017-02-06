package fr.dude.isen.api.responses;

import fr.dude.isen.entities.TurnEntity;
import fr.dude.isen.model.pawns.ColorPawn;
import fr.dude.isen.model.pawns.Position;

/**
 * Created by Clement on 06/02/2017.
 */
public class TurnResponse {

    private Position origin;
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
