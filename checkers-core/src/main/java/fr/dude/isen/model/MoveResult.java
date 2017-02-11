package fr.dude.isen.model;

import fr.dude.isen.model.cells.Cell;
import fr.dude.isen.model.pawns.ColorPawn;
import fr.dude.isen.model.pawns.Move;
import fr.dude.isen.model.pawns.Position;

/**
 * Class that represents the result of a move.
 */
public class MoveResult {

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
    private final int nbPawnsUserWhite;

    /**
     * Number of black pawns left.
     */
    private final int nbPawnsUserBlack;

    /**
     * True if the game is finished. False otherwise.
     */
    private final boolean isWinningMove;

    public MoveResult(Cell origin, Move move, ColorPawn nextUser, boolean becomesQueen, int nbPawnsUserWhite, int nbPawnsUserBlack, boolean isWinningMove) {
        this(origin, move.getDestination(), move.getPawnCellToDelete(), nextUser, becomesQueen, nbPawnsUserWhite, nbPawnsUserBlack, isWinningMove);
    }

    public MoveResult(Cell origin, Cell destination, Cell kill, ColorPawn nextUser, boolean becomesQueen, int nbPawnsUserWhite, int nbPawnsUserBlack, boolean isWinningMove) {
        this.origin = origin.getPosition();
        this.destination = destination.getPosition();
        this.kill = kill != null ? kill.getPosition() : null;
        this.becomesQueen = becomesQueen;
        this.nextUser = nextUser;
        this.nbPawnsUserBlack = nbPawnsUserBlack;
        this.nbPawnsUserWhite = nbPawnsUserWhite;
        this.isWinningMove = isWinningMove;
    }

    public Position getOrigin() {
        return origin;
    }

    public Position getDestination() {
        return destination;
    }

    public Position getKill() {
        return kill;
    }

    public boolean isBecomesQueen() {
        return becomesQueen;
    }

    public ColorPawn getNextUser() {
        return nextUser;
    }

    public int getNbPawnsUserWhite() { return nbPawnsUserWhite; }

    public int getNbPawnsUserBlack() { return nbPawnsUserBlack; }

    public boolean isWinningMove() {
        return isWinningMove;
    }
}
