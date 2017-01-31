package fr.dude.isen.api.responses;

import fr.dude.isen.CheckersGame;

/**
 * Created by Clement on 31/01/2017.
 */
public class GameResponse {

    private String token;
    private CheckersGame game;

    public GameResponse(String token, CheckersGame game) {
        this.token = token;
        this.game = game;
    }

    public String getToken() {
        return token;
    }

    public CheckersGame getGame() {
        return game;
    }
}
