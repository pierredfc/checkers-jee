package fr.dude.isen.model;

import fr.dude.isen.model.pawns.ColorPawn;
import fr.dude.isen.model.pawns.CommonPawn;
import fr.dude.isen.model.pawns.Direction;
import fr.dude.isen.model.pawns.Pawn;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Clement on 09/01/2017.
 */
public class User {

    private List<Pawn> pawns;

    public User(int nbPawns, ColorPawn colorPawn, Direction direction) {
        this.pawns = new ArrayList<>(nbPawns);

        for(int i = 0; i < nbPawns; i++) {
            this.pawns.add(new CommonPawn(colorPawn, direction));
        }
    }

    public List<Pawn> getPawns() {
        return pawns;
    }
}
