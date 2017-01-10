package fr.dude.isen.exceptions;

import fr.dude.isen.model.pawns.Move;

import java.util.List;

/**
 * Created by pierredfc on 10/01/2017.
 */
public class UnauthorizedMoveException extends Exception {

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
