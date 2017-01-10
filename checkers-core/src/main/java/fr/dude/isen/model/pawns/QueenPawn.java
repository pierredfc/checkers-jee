package fr.dude.isen.model.pawns;

import fr.dude.isen.exceptions.UnauthorizedMoveException;
import fr.dude.isen.model.Cell;

import java.util.List;

/**
 * Created by pierredfc on 09/01/2017.
 */
public class QueenPawn extends Pawn {


    public QueenPawn(ColorPawn color) {
        super(color, Direction.BOTH);
    }


    @Override
    public Move move(Cell cell, List<List<Cell>> cells) throws UnauthorizedMoveException {
        return null;
    }
}
