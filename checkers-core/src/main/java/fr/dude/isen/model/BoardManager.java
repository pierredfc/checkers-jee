package fr.dude.isen.model;

import fr.dude.isen.exceptions.UnauthorizedMoveException;
import fr.dude.isen.model.cells.Cell;
import fr.dude.isen.model.cells.Cells;
import fr.dude.isen.model.pawns.Direction;
import fr.dude.isen.model.pawns.Move;
import fr.dude.isen.model.pawns.Pawn;
import fr.dude.isen.model.pawns.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * Board manager
 */
public class BoardManager {

    /**
     * Cells of the board
     */
    private final Cells cells;

    public BoardManager(Cells cells) {
        this.cells = cells;
    }

    private void movePawn(Cell origin, Cell destination) {
        Pawn pawn = origin.getPawn();
        origin.setPawn(null);
        destination.setPawn(pawn);
    }

    /**
     * Move a pawn from an initial position to a final position.
     * @param origin Initial position of the pawn
     * @param destination Final position of the move
     * @return the Move on success. Null otherwise.
     * @throws UnauthorizedMoveException
     */
    public Move move(Position origin, Position destination) throws UnauthorizedMoveException {
        return this.move(cells.get(origin), cells.get(destination));
    }

    private Move checkMove(Cell origin, Cell destination) throws UnauthorizedMoveException {
        if (origin == null || destination == null) return null;
        Pawn pawn = origin.getPawn();
        if (pawn == null) return null;

        List<Move> possibleMoves = this.getPossibleMoves(origin);
        for(Move move : possibleMoves) {
            Cell destinationCell = move.getDestination();
            if (destinationCell.equals(destination)) {
                return move;
            }
        }
        throw new UnauthorizedMoveException(possibleMoves.isEmpty() ? null : possibleMoves);
    }

    /**
     * Move a pawn from an initial cell to a final cell.
     * @param origin Initial cell of the pawn.
     * @param destination Final cell of the move
     * @return the Move on success. Null otherwise.
     * @throws UnauthorizedMoveException
     */
    public Move move(Cell origin, Cell destination) throws UnauthorizedMoveException {

        Move move = checkMove(origin, destination);
        this.movePawn(origin, destination);
        return move;
    }

    /**
     * @param originCell
     * @return a list of possible moves from an origin cell.
     */
    public List<Move> getPossibleMoves(Cell originCell) {
        if (originCell == null) return null;

        Pawn pawn = originCell.getPawn();
        if (pawn == null) return null;

        List<Move> moves = new ArrayList<>(2);

        Direction direction = pawn.getDirection();
        boolean up = direction == Direction.UP;
        boolean down = direction == Direction.DOWN;
        boolean queen = direction == Direction.QUEEN;
        int step = queen ? -1 : 1;

        boolean hasMandatoryMoves = false;

        if (up || queen) {
            hasMandatoryMoves = tryAddMoves(originCell, step, moves, 1) || hasMandatoryMoves;
        }

        if (down || queen) {
            hasMandatoryMoves = tryAddMoves(originCell, step, moves, -1) || hasMandatoryMoves;
        }

        if (hasMandatoryMoves) {
            moves = toMandatoryMoves(moves);
        }

        return moves;
    }

    private boolean tryAddMoves(Cell originCell, int nbSteps, List<Move> result, int row) {
        return tryAddMoves(originCell, nbSteps, result, new Position(row, -1), new Position(row, 1));
    }

    private boolean tryAddMoves(Cell originCell, int nbSteps, List<Move> result, Position... directions) {
        boolean hasMandatoryMoves = false;
        for (Position direction : directions) {
            hasMandatoryMoves = tryAddMove(originCell, direction, nbSteps, result) || hasMandatoryMoves;
        }
        return hasMandatoryMoves;
    }

    /**
     * @param originCell From where the pawn has to been moved
     * @param direction the direction to go to
     * @param nbSteps   the maximum number of steps a pawn can move
     * @param result    true if this move is mandatory
     * @return true if the move is possible. False otherwise.
     */
    private boolean tryAddMove(Cell originCell, Position direction, int nbSteps, List<Move> result) {
        Pawn pawn = originCell.getPawn();
        boolean isMandatory = false;
        int step = 1;
        int isPreviousPawn = 0;
        int isPawn = 0;
        Cell cellPawnToDelete = null;

        Cell cell;

        do {
            cell = cells.translate(originCell, direction, step + isPawn);
            if (cell != null) {
                if (!cell.hasPawn()) {
                    result.add(new Move(cell, cellPawnToDelete));
                    isMandatory = cellPawnToDelete != null && cellPawnToDelete.getPawn() != null;
                    if (step != -1) step++; //Limited steps
                    isPreviousPawn = 0;
                } else if (cell.hasOpponentPawn(pawn)) {
                    if (isPreviousPawn == 1) {
                        break;
                    }
                    isPreviousPawn = 1;
                    isPawn = 1;
                    cellPawnToDelete = cell;
                } else { //Player pawn
                    cell = null; //Stop
                }
            }

        } while (cell != null && (step <= nbSteps || nbSteps == -1));

        return isMandatory;
    }

    /**
     * @param moves
     * @return the list of the mandatory moves from a list of moves.
     */
    private static List<Move> toMandatoryMoves(List<Move> moves) {
        List<Move> mandatoryMoves = new ArrayList<>(2);
        for (Move move : moves) {
            if (move.isMandatory()) {
                mandatoryMoves.add(move);
            }
        }
        return mandatoryMoves;
    }

}
