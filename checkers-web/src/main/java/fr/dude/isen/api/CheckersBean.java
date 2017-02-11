package fr.dude.isen.api;

import fr.dude.isen.CheckersAdapter;
import fr.dude.isen.CheckersGameDAO;
import fr.dude.isen.api.requests.PlayerNameRequest;
import fr.dude.isen.api.responses.GameResponse;
import fr.dude.isen.api.responses.LightGame;
import fr.dude.isen.entities.TurnEntity;
import fr.dude.isen.model.MoveResult;
import fr.dude.isen.model.pawns.Move;
import fr.dude.isen.model.pawns.Position;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Checkers bean
 */
@Named("checkersgame")
@RequestScoped
public class CheckersBean implements Serializable {

    /**
     * Checkers adapter
     */
    private CheckersAdapter game;

    /**
     * Checker's DAO
     */
    @Inject
    CheckersGameDAO dao;

    public CheckersBean() {

    }

    public CheckersAdapter getGame() {
        return game;
    }

    /**
     * Make a move by playing the game
     * @param init Initial position from where the pawn has to be moved
     * @param destination Final position of the pawn
     * @return a MoveResult on success. Null otherwise.
     */
    public MoveResult play(Position init, Position destination) {
        return game.play(init, destination);
    }

    /**
     * @param position
     * @return a list of possible moves for a specific position. Empty list if no possible moves. Null on error.
     */
    public List<Move> getPossibleMoves(Position position)
    {
        return this.game.getPossibleMoves(position);
    }

    /**
     * Create a new game by setting the adapter attribute.
     */
    public void createNewGame()
    {
        this.game = dao.createGame();
    }

    /**
     * Load a game from the database
     * @param token Game's identifier
     */
    public void loadFromToken(String token)
    {
        this.game = dao.loadFromToken(token);
    }

    /**
     * Delete a game
     * @param token Game's identifier
     */
    public void deleteFromToken(String token)
    {
        dao.delete(token);
        this.game = null;
    }

    /**
     * @return a list of all saved games
     */
    public List<LightGame> getSavedGames()
    {
        List<LightGame> results = null;
        List<CheckersAdapter> savedGames = dao.loadSavedGames();

        if (savedGames != null)
        {
            results = new ArrayList<>(savedGames.size());

            for (CheckersAdapter game : savedGames)
            {
                results.add(new LightGame(new GameResponse(game.getToken(), game.getCoreGame(), game.getCreationDate())));
            }
        }

        return results;
    }

    /**
     * Modify the username of a player
     * @param request
     * @return the new username on success.
     */
    public String setUsername(PlayerNameRequest request)
    {
        return this.game.setUsername(request.getName(), request.getColor());
    }

    /**
     * @param token Game's identifier
     * @return the list of turns made in a specific game
     */
    public List<TurnEntity> getHistory(String token) {
        return dao.getHistoryFromToken(token);
    }
}
