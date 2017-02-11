package fr.dude.isen.api.requests;

import fr.dude.isen.model.pawns.ColorPawn;

/**
 * Class that represents an username modification request
 */
public class PlayerNameRequest {

    /**
     * Player pawn's color
     */
    private ColorPawn color;

    /**
     * New name of the player
     */
    private String name;

    public PlayerNameRequest() {

    }

    public ColorPawn getColor() {
        return color;
    }

    public void setColor(ColorPawn color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
