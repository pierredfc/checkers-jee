package fr.dude.isen.api.responses;

import fr.dude.isen.model.Player;
import fr.dude.isen.model.pawns.ColorPawn;

/**
 * Created by Clement on 30/01/2017.
 */
public class LightUser {

    private String name;
    private ColorPawn color;
    private int nbPawns;

    public LightUser(Player player) {
        this(player.getName(), player.getColorPawn(), player.getNbPawns());
    }

    public LightUser(String name, ColorPawn colorPawn, int nbPawns) {
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
