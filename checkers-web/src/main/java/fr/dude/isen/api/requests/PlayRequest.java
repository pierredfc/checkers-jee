package fr.dude.isen.api.requests;

import fr.dude.isen.model.pawns.Position;

/**
 * Class that represents a play request
 */
public class PlayRequest {

    /**
     * Position from where the pawn has to been moved.
     */
    private Position origin;

    /**
     * Position where the pawn has to go.
     */
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
