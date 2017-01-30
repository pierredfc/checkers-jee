package fr.dude.isen;

import fr.dude.isen.model.Cell;
import fr.dude.isen.model.MoveResult;
import fr.dude.isen.model.pawns.ColorPawn;
import fr.dude.isen.model.pawns.Move;
import fr.dude.isen.model.pawns.Position;

import java.util.List;

/**
 * Created by pierredfc on 23/01/2017.
 */
public class CheckersAdapter {

    private Game game;

    private CheckersGame coreGame;

    private CheckersGameDAO dao;

    public CheckersAdapter(CheckersGameDAO dao, Game game)
    {
        this.dao = dao;
        this.game = game;
        this.coreGame = CheckersApplication.launch();
        this.coreGame.init();

        for (Turn turn : game.getTurns()) {
            this.coreGame.play(turn.getInitPosition(), turn.getDestination());
        }
    }

    public CheckersGame getCoreGame() {
        return coreGame;
    }

    public void init() {
        this.coreGame.init();
    }

    public MoveResult play(Position init, Position destination) {
        MoveResult result = this.coreGame.play(init, destination);
        this.game.getTurns().add(new Turn(this.game, init, destination));
        this.switchTurn();
        dao.save(game);
        return result;
    }

    public Cell getCell(int row, int column) {
        return this.coreGame.getCell(row, column);
    }

    public List<Move> getPossibleMoves(Position position) {
        return coreGame.getPossibleMoves(position);
    }

    private void switchTurn()
    {
        game.setCurrentTurn(game.getCurrentTurn() == ColorPawn.WHITE ? ColorPawn.BLACK : ColorPawn.WHITE);
    }

    public Integer getNbRows() {
        return coreGame.getNbRows();
    }

    public Integer getNbColumns() {
        return coreGame.getNbColumns();
    }

    public String getToken()
    {
        return this.game.getToken();
    }
}
