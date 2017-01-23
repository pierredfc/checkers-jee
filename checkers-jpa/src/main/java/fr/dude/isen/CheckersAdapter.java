package fr.dude.isen;

import fr.dude.isen.model.pawns.Position;

/**
 * Created by pierredfc on 23/01/2017.
 */
public class CheckersAdapter implements CheckersGame  {

    private Game game;

    private CheckersGame coreGame;

    private CheckersGameDAO dao;

    public CheckersAdapter(CheckersGameDAO dao, Game game)
    {
        this.dao = dao;
        this.game = game;
        this.coreGame = new CheckersGameImpl();

        for (Turn turn : game.getTurns()) {
            this.coreGame.play(turn.getInitPosition(), turn.getDestination());
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void run() {

    }

    @Override
    public void play(Position init, Position destination) {

    }

    @Override
    public Integer getNbRows() {
        return coreGame.getNbRows();
    }

    @Override
    public Integer getNbColumns() {
        return coreGame.getNbColumns();
    }

    public String getToken()
    {
        return this.game.getToken();
    }
}
