package fr.dude.isen.model;

import fr.dude.isen.model.pawns.ColorPawn;
import fr.dude.isen.model.pawns.Direction;
import fr.dude.isen.model.pawns.Pawn;
import fr.dude.isen.model.serializable.SerializablePlayer;
import org.codehaus.jackson.annotate.JsonIgnore;


/**
 * Class that represents a player.
 */
public class Player implements SerializablePlayer {

    /**
     *
     */
    private final Direction pawnDirection;

    /**
     * Row index in which opponent pawns can become queens.
     */
    private final int queenRow;

    /**
     * Number of pawns that the player owns.
     */
    private int nbPawns;

    /**
     * Pawn's color of the player
     */
    private ColorPawn colorPawn;

    /**
     * Opponent of the player
     */
    private Player opponent;

    /**
     * Player's name
     */
    private String name;

    public Player(String name, int nbPawns, ColorPawn colorPawn, Direction pawnDirection, int queenRow) {
        this.name = name;
        this.nbPawns = nbPawns;
        this.colorPawn = colorPawn;
        this.pawnDirection = pawnDirection;
        this.queenRow = queenRow;
    }

    public Pawn newPawn() {
        return new Pawn(colorPawn, pawnDirection);
    }

    public void removePawn(Pawn pawn) {
        this.nbPawns--;
    }


    public ColorPawn findOpponentColor() {
        return this.opponent.colorPawn;
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    @JsonIgnore
    public Player getOpponent() {
        return opponent;
    }

    public void decrementOpponentPawns() {
        opponent.decrementNbPawns();
    }

    public void decrementNbPawns() {
        nbPawns--;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getNbPawns() {
        return nbPawns;
    }

    @Override
    public Direction getPawnDirection() {
        return pawnDirection;
    }

    @Override
    public int getQueenRow() {
        return queenRow;
    }

    @Override
    public ColorPawn getColorPawn() {
        return colorPawn;
    }

}
