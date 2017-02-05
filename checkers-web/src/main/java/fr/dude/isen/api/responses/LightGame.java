package fr.dude.isen.api.responses;

import fr.dude.isen.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Clement on 30/01/2017.
 */
public class LightGame {

    private long date;
    private String token;
    private List<LightUser> users;

    public LightGame(GameResponse gameResponse) {
        this(gameResponse.getToken(), gameResponse.getCreationDate().getTime(), gameResponse.getGame().getUserWhite(), gameResponse.getGame().getUserBlack());
    }

    public LightGame(String token, long date, User... users) {
        this.token = token;
        this.date = date;
        this.users = new ArrayList<>(users.length);
        for(User user : users) {
            this.users.add(new LightUser(user));
        }
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
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
