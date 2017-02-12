package fr.dude.isen.model;

import fr.dude.isen.model.cells.Cell;
import fr.dude.isen.model.pawns.ColorPawn;
import fr.dude.isen.model.pawns.Move;
import fr.dude.isen.model.pawns.Position;
import fr.dude.isen.model.serializable.SerializableMoveResult;

/**
 * Class that represents the result of a move.
 */
public class MoveResult implements SerializableMoveResult {

    /**
     * From which position the move has started.
     */
    private final Position origin;

    /**
     * Destination position of the move
     */
    private final Position destination;

    /**
     * Position of the pawn which has been deleted during the move. Null if no pawn has been deleted.
     */
    private final Position kill;

    /**
     * True if the pawn becomes a queen. False otherwise.
     */
    private final boolean becomesQueen;

    /**
     * The pawn's color of the next player to play
     */
    private final ColorPawn nextUser;

    /**
     * Number of white pawns left.
     */
    private final int nbPawnsPlayerWhite;

    /**
     * Number of black pawns left.
     */
    private final int nbPawnsPlayerBlack;

    /**
     * True if the game is finished. False otherwise.
     */
    private final boolean isWinningMove;

    public MoveResult(Cell origin, Move move, ColorPawn nextUser, boolean becomesQueen, int nbPawnsPlayerWhite, int nbPawnsPlayerBlack, boolean isWinningMove) {
        this(origin, move.getDestination(), move.getPawnCellToDelete(), nextUser, becomesQueen, nbPawnsPlayerWhite, nbPawnsPlayerBlack, isWinningMove);
    }

    public MoveResult(Cell origin, Cell destination, Cell kill, ColorPawn nextUser, boolean becomesQueen, int nbPawnsPlayerWhite, int nbPawnsPlayerBlack, boolean isWinningMove) {
        this.origin = origin.getPosition();
        this.destination = destination.getPosition();
        this.kill = kill != null ? kill.getPosition() : null;
        this.becomesQueen = becomesQueen;
        this.nextUser = nextUser;
        this.nbPawnsPlayerBlack = nbPawnsPlayerBlack;
        this.nbPawnsPlayerWhite = nbPawnsPlayerWhite;
        this.isWinningMove = isWinningMove;
    }

    @Override
    public Position getOrigin() {
        return origin;
    }

    @Override
    public Position getDestination() {
        return destination;
    }

    @Override
    public Position getKill() {
        return kill;
    }

    @Override
    public boolean isBecomesQueen() {
        return becomesQueen;
    }

    @Override
    public ColorPawn getNextUser() {
        return nextUser;
    }

    @Override
    public int getNbPawnsPlayerWhite() { return nbPawnsPlayerWhite; }

    @Override
    public int getNbPawnsPlayerBlack() { return nbPawnsPlayerBlack; }

    @Override
    public boolean isWinningMove() {
        return isWinningMove;
    }
}
