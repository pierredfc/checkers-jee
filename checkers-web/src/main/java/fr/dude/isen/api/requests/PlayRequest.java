package fr.dude.isen.api.requests;

import fr.dude.isen.model.pawns.Position;

/**
 * Created by Clement on 23/01/2017.
 */
public class PlayRequest {

    private Position origin;
    private Position destination;

    public PlayRequest() {

    }

    public Position getOrigin() {
        return origin;
    }

    public void setOrigin(Position origin) {
        this.origin = origin;
    }

    public Position getDestination() {
        return destination;
    }

    public void setDestination(Position destination) {
        this.destination = destination;
    }
}
