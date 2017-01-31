package fr.dude.isen.api;

import fr.dude.isen.CheckersAdapter;
import fr.dude.isen.CheckersGameDAO;
import fr.dude.isen.api.requests.UserNameRequest;
import fr.dude.isen.model.MoveResult;
import fr.dude.isen.model.pawns.Move;
import fr.dude.isen.model.pawns.Position;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
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

    public String setUsername(UserNameRequest request)
    {
        return this.game.setUsername(request.getName(), request.getColor());
    }

}
