package fr.dude.isen.model.pawns;

import fr.dude.isen.exceptions.UnauthorizedMoveException;
import fr.dude.isen.model.Cell;
import fr.dude.isen.model.Cells;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pierredfc on 09/01/2017.
 */
public abstract class Pawn {

    private ColorPawn color;

    private Direction direction;

    private Cell cell;

    public Pawn(ColorPawn color, Direction direction) {
        this.color = color;
        this.direction = direction;
    }

    public abstract Move move(Cell destinationCell, Cells cells) throws UnauthorizedMoveException;

    public ColorPawn getColor() {
        return color;
    }

    public void setColor(ColorPawn color) {
        this.color = color;
    }

    public void setCell(Cell cell) {
        if (this.cell != null) {
            this.cell.setCurrentPawn(null);
        }
        if (cell != null) {
            cell.setCurrentPawn(this);
        } // else : Pawn removed from board
        this.cell = cell;
    }

    public Direction getDirection() {
        return direction;
    }

    private Position getPosition() {
        return this.cell != null ? this.cell.getPosition() : null;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Cell getCell() {
        return cell;
    }

    public List<Move> getPossibleMoves(Cells cells) {
        Cell current = this.getCell();
        Position position = current.getPosition();
        int col = position.getColumnIndex();
        int row = position.getRowIndex();

        List<Move> result = new ArrayList<>(2);

        Direction direction = getDirection();
        boolean up = direction == Direction.UP;
        boolean down = direction == Direction.DOWN;
        boolean queen = direction == Direction.QUEEN;
        int step = queen ? -1 : 1;

        boolean hasMandatoryMoves = false;

        if (up || queen) {
            hasMandatoryMoves = tryAddMove(new Position(-1, 1), step, cells, result) || hasMandatoryMoves;
            hasMandatoryMoves = tryAddMove(new Position(1, 1), step, cells, result) || hasMandatoryMoves;
        }

        if (down || queen) {
            hasMandatoryMoves = tryAddMove(new Position(-1, -1), step, cells, result) || hasMandatoryMoves;
            hasMandatoryMoves = tryAddMove(new Position(1, -1), step, cells, result) || hasMandatoryMoves;
        }

        if (hasMandatoryMoves) {
            List<Move> mandatoryMoves = new ArrayList<>(2);
            for (Move move : result) {
                if (move.isMandatory()) {
                    mandatoryMoves.add(move);
                }
            }
            result = mandatoryMoves;
        }

        return result;
    }

    /**
     * @param direction the direction to go to
     * @param nbSteps the maximum number of steps a pawn can move
     * @param cells
     * @param result true if this move is mandatory
     * @return
     */
    private boolean tryAddMove(Position direction, int nbSteps, Cells cells, List<Move> result) {
        boolean isMandatory = false;
        int step = 1;
        int isPreviousPawn = 0;
        int isPawn = 0;
        Pawn pawnToDelete = null;

        Cell cell;

        do {
            cell = cells.translate(this.getCell(), direction, step + isPawn);
            if (cell != null) {
                if (!cell.hasPawn()) {
                    result.add(new Move(cell, pawnToDelete));
                    isMandatory = pawnToDelete != null;
                    if (step != -1) step++; //Limited steps
                    isPreviousPawn = 0;
                }
                else if (cell.hasOpponentPawn(this)){
                    if (isPreviousPawn == 1) {
                        break;
                    }
                    isPreviousPawn = 1;
                    isPawn = 1;
                    pawnToDelete = cell.getCurrentPawn();
                }
                else { //Player pawn
                    cell = null; //Stop
                }
            }

        } while(cell != null && (step <= nbSteps || step == -1));

        return isMandatory;
    }
}
