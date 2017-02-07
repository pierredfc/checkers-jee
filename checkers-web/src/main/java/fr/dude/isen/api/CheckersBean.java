package fr.dude.isen.api;

import fr.dude.isen.CheckersAdapter;
import fr.dude.isen.CheckersGameDAO;
import fr.dude.isen.api.requests.UserNameRequest;
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
 * Created by pierredfc on 30/01/2017.
 */
@Named("checkersgame")
@RequestScoped
public class CheckersBean implements Serializable {

    private CheckersAdapter game;

    @Inject
    CheckersGameDAO dao;

    public CheckersBean() {

    }

    public CheckersAdapter getGame() {
        return game;
    }

    public MoveResult play(Position init, Position destination) {
        return game.play(init, destination);
    }

    public List<Move> getPossibleMoves(Position position)
    {
        return this.game.getPossibleMoves(position);
    }

    public void createNewGame()
    {
        this.game = dao.createGame();
    }

    public void loadFromToken(String token)
    {
        this.game = dao.loadFromToken(token);
    }

    public void deleteFromToken(String token)
    {
        dao.delete(token);
        this.game = null;
    }

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

    public String setUsername(UserNameRequest request)
    {
        return this.game.setUsername(request.getName(), request.getColor());
    }

    public List<TurnEntity> getHistory(String token) {
        return dao.getHistoryFromToken(token);
    }
}
