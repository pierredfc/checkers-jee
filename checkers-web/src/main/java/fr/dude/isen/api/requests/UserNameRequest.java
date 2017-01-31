package fr.dude.isen.api.requests;

import fr.dude.isen.model.pawns.ColorPawn;

/**
 * Created by Clement on 31/01/2017.
 */
public class UserNameRequest {

    private ColorPawn color;
    private String name;

    public UserNameRequest() {

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
