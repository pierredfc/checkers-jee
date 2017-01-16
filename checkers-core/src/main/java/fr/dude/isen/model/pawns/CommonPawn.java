package fr.dude.isen.model.pawns;

import fr.dude.isen.exceptions.UnauthorizedMoveException;
import fr.dude.isen.model.Cell;
import fr.dude.isen.model.Cells;

import java.util.List;

/**
 * Created by pierredfc on 09/01/2017.
 */
public class CommonPawn extends Pawn {

    public CommonPawn(ColorPawn color, Direction direction) {
        super(color, direction);
    }

    @Override
    public Move move(Cell cell, Cells cells) throws UnauthorizedMoveException {
        List<Move> possibleMoves = this.getPossibleMoves(cells);
        for(Move move : possibleMoves) {
            Cell destinationCell = move.getDestination();
            if (destinationCell.equals(cell)) {
                this.setCell(destinationCell);
                if (move.hasPawnToDelete()) {
                    move.getPawnToDelete().setCell(null);
                }

                Integer destinationRowIndex = destinationCell.getPosition().getRowIndex();
                if (this.getDirection().equals(Direction.UP) && destinationRowIndex.equals(cells.getNbRows()-1))
                {
                    this.setDirection(Direction.QUEEN);
                }
                else if (this.getDirection().equals(Direction.DOWN) && destinationRowIndex.equals(0)){
                    this.setDirection(Direction.QUEEN);
                }

                return move;
            }
        }
        throw new UnauthorizedMoveException(possibleMoves.isEmpty() ? null : possibleMoves);
    }
}
