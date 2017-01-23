package fr.dude.isen.model;

import fr.dude.isen.model.pawns.ColorPawn;
import fr.dude.isen.model.pawns.Direction;
import fr.dude.isen.model.pawns.Pawn;

/**
 * Created by Clement on 09/01/2017.
 */
public class User {

    private final Direction pawnDirection;
    private final int queenRow;
    private int nbPawns;
    private ColorPawn colorPawn;
    private User opponent;

    public User(int nbPawns, ColorPawn colorPawn, Direction pawnDirection, int queenRow) {
        this.nbPawns = nbPawns;
        this.colorPawn = colorPawn;
        this.pawnDirection = pawnDirection;
        this.queenRow = queenRow;
    }

    public int getNbPawns() {
        return nbPawns;
    }

    public void removePawn(Pawn pawn) {
        this.nbPawns--;
    }

    public Direction getPawnDirection() {
        return pawnDirection;
    }

    public int getQueenRow() {
        return queenRow;
    }

    public ColorPawn getColorPawn() {
        return colorPawn;
    }

    public Pawn newPawn() {
        return new Pawn(colorPawn, pawnDirection);
    }

    public ColorPawn findOpponentColor() {
        return this.opponent.colorPawn;
    }

    public void setOpponent(User opponent) {
        this.opponent = opponent;
    }
}
