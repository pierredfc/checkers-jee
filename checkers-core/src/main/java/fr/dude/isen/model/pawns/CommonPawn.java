package fr.dude.isen.model.pawns;

import fr.dude.isen.model.Cell;

import java.util.List;

/**
 * Created by pierredfc on 09/01/2017.
 */
public class CommonPawn extends Pawn {

    public CommonPawn(ColorPawn color, Direction direction) {
        super(color, direction);
    }

    @Override
    public Move move(Cell cell, List<List<Cell>> cells) {
        List<Move> possibleMoves = this.getPossibleMoves(cells);
        for(Move move : possibleMoves) {
            if (move.getDestination().equals(cell)) {
                this.setCell(move.getDestination());
                if (move.hasPawnToDelete()) {
                    move.getPawnToDelete().setCell(null);
                }
                return move;
            }
        }
        return null;
    }
}
