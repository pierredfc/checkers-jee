package fr.dude.isen.model;

import fr.dude.isen.model.pawns.ColorPawn;
import fr.dude.isen.model.pawns.Move;
import fr.dude.isen.model.pawns.Position;

/**
 * Created by Clement on 23/01/2017.
 */
public class MoveResult {

    private final Position origin;
    private final Position destination;
    private final Position kill;
    private final boolean becomesQueen;
    private final ColorPawn nextUser;
    private final int nbPawnsUserWhite;
    private final int nbPawnsUserBlack;
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
