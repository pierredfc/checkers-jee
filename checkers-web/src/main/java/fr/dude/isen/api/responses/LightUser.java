package fr.dude.isen.api.responses;

import fr.dude.isen.model.User;
import fr.dude.isen.model.pawns.ColorPawn;

/**
 * Created by Clement on 30/01/2017.
 */
public class LightUser {

    private String name;
    private ColorPawn color;
    private int nbPawns;

    public LightUser(User user) {
        this(user.getName(), user.getColorPawn(), user.getNbPawns());
    }

    public LightUser(String name, ColorPawn colorPawn, int nbPawns) {
        this.name = name;
        this.color = colorPawn;
        this.nbPawns = nbPawns;
    }

}
