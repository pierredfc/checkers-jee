package fr.dude.isen.api.responses;

import fr.dude.isen.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Clement on 30/01/2017.
 */
public class LightGame {
    public static int i = 0;
    private String date = "Now "+i++;

    private List<LightUser> users;

    public LightGame(User... users) {
        this.users = new ArrayList<>(users.length);
        for(User user : users) {
            this.users.add(new LightUser(user));
        }
    }
}
