package fr.dude.isen.api.responses;

import fr.dude.isen.CheckersGame;
import fr.dude.isen.model.serializable.SerializableCheckersGame;

import java.util.Date;

/**
 * Class that represents a game response
 */
public class GameResponse {

    /**
     * Game's token
     */
    private String token;

    /**
     * Game's creation date
     */
    private Date creationDate;

    /**
     * Game state
     */
    private SerializableCheckersGame game;

    public GameResponse(String token, SerializableCheckersGame game, Date creationDate) {
        this.token = token;
        this.game = game;
        this.creationDate = creationDate;
    }

    public String getToken() {
        return token;
    }

    public SerializableCheckersGame getGame() {
        return game;
    }

    public Date getCreationDate() {
        return creationDate;
    }
}
