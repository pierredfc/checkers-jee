package fr.dude.isen.api.responses;

import fr.dude.isen.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Clement on 30/01/2017.
 */
public class LightGame {

    private String date;
    private String token;
    private List<LightUser> users;

    public LightGame(GameResponse gameResponse) {
        this(gameResponse.getToken(), gameResponse.getCreationDate().toString(), gameResponse.getGame().getUserWhite(), gameResponse.getGame().getUserBlack());
    }

    public LightGame(String token, String date, User... users) {
        this.token = token;
        this.date = date;
        this.users = new ArrayList<>(users.length);
        for(User user : users) {
            this.users.add(new LightUser(user));
        }
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<LightUser> getUsers() {
        return users;
    }

    public void setUsers(List<LightUser> users) {
        this.users = users;
    }
}
