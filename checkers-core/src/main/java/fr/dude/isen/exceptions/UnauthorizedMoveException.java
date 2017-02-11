package fr.dude.isen.exceptions;

import fr.dude.isen.model.pawns.Move;

import java.util.List;

/**
 * Throw when a move is unauthorized.
 */
public class UnauthorizedMoveException extends Exception {

    /**
     * List of mandatory destinations
     */
    private List<Move> mandatoryDestination;

    public UnauthorizedMoveException(List<Move> mandatoryDestination)
    {
        this.mandatoryDestination = mandatoryDestination;
    }

    public List<Move> getMandatoryDestination() {
        return mandatoryDestination;
    }

    public void setMandatoryDestination(List<Move> mandatoryDestination) {
        this.mandatoryDestination = mandatoryDestination;
    }
}
