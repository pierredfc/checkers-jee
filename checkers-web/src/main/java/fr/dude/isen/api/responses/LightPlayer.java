package fr.dude.isen.api.responses;

import fr.dude.isen.model.Player;
import fr.dude.isen.model.pawns.ColorPawn;

/**
 * Lighter version of a player
 */
public class LightPlayer {

    /**
     * Player's name
     */
    private String name;

    /**
     * Pawn's color of the player
     */
    private ColorPawn color;

    /**
     * Number of pawns that the player owns.
     */
    private int nbPawns;

    public LightPlayer(Player player) {
        this(player.getName(), player.getColorPawn(), player.getNbPawns());
    }

    public LightPlayer(String name, ColorPawn colorPawn, int nbPawns) {
        this.name = name;
        this.color = colorPawn;
        this.nbPawns = nbPawns;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ColorPawn getColor() {
        return color;
    }

    public void setColor(ColorPawn color) {
        this.color = color;
    }

    public int getNbPawns() {
        return nbPawns;
    }

    public void setNbPawns(int nbPawns) {
        this.nbPawns = nbPawns;
    }
}
