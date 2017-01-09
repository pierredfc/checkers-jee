package fr.dude.isen.model.pawns;

import fr.dude.isen.model.Cell;

/**
 * Created by pierredfc on 09/01/2017.
 */
public class QueenPawn extends Pawn {


    public QueenPawn(ColorPawn color) {
        super(color, Direction.BOTH);
    }

    @Override
    public void move(Cell cell) {

    }
}
