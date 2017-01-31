package fr.dude.isen.api.responses;

import fr.dude.isen.CheckersGame;

import java.util.Date;

/**
 * Created by Clement on 31/01/2017.
 */
public class GameResponse {

    private String token;
    private Date creationDate;
    private CheckersGame game;

    public GameResponse(String token, CheckersGame game, Date creationDate) {
        this.token = token;
        this.game = game;
        this.creationDate = creationDate;
    }

    public String getToken() {
        return token;
    }

    public CheckersGame getGame() {
        return game;
    }

    public Date getCreationDate() {
        return creationDate;
    }
}
