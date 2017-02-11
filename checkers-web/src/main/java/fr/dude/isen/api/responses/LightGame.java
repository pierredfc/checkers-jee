package fr.dude.isen.api.responses;

import fr.dude.isen.model.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Lighter version of a checkers game
 */
public class LightGame {

    /**
     * Game's creation date
     */
    private long date;

    /**
     * Game's token
     */
    private String token;

    /**
     * Game's players
     */
    private List<LightPlayer> players;

    public LightGame(GameResponse gameResponse) {
        this(gameResponse.getToken(), gameResponse.getCreationDate().getTime(), gameResponse.getGame().getPlayerWhite(), gameResponse.getGame().getPlayerBlack());
    }

    public LightGame(String token, long date, Player... players) {
        this.token = token;
        this.date = date;
        this.players = new ArrayList<>(players.length);

        for(Player player : players) {
            this.players.add(new LightPlayer(player));
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

    public List<LightPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(List<LightPlayer> players) {
        this.players = players;
    }
}
