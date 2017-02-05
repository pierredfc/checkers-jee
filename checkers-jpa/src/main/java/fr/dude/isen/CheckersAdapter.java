package fr.dude.isen;

import fr.dude.isen.entities.GameEntity;
import fr.dude.isen.entities.TurnEntity;
import fr.dude.isen.entities.UserEntity;
import fr.dude.isen.model.Cell;
import fr.dude.isen.model.MoveResult;
import fr.dude.isen.model.User;
import fr.dude.isen.model.pawns.ColorPawn;
import fr.dude.isen.model.pawns.Move;
import fr.dude.isen.model.pawns.Position;

import java.util.Date;
import java.util.List;

/**
 * Created by pierredfc on 23/01/2017.
 */
public class CheckersAdapter {

    private GameEntity gameEntity;

    private CheckersGame coreGame;

    private CheckersGameDAO dao;

    public CheckersAdapter(CheckersGameDAO dao, GameEntity gameEntity)
    {
        this.dao = dao;
        this.gameEntity = gameEntity;
        this.coreGame = CheckersApplication.launch();
        this.coreGame.init();

        for (TurnEntity turnEntity : gameEntity.getTurnEntities()) {
            this.coreGame.play(turnEntity.getInitPosition(), turnEntity.getDestination());
        }

        User userBlack = this.coreGame.getUserBlack();
        User userWhite = this.coreGame.getUserWhite();

        if (userBlack != null && userWhite != null)
        {
            userBlack.setName(gameEntity.getUserBlack().getUsername());
            userWhite.setName(gameEntity.getUserWhite().getUsername());
            userWhite.setOpponent(userBlack);
            userBlack.setOpponent(userWhite);
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
        this.gameEntity.getTurnEntities().add(new TurnEntity(this.gameEntity, init, destination));
        this.switchTurn();
        dao.save(gameEntity);
        return result;
    }

    public String setUsername(String username, ColorPawn colorPawn)
    {
        UserEntity user;

        if (ColorPawn.WHITE.equals(colorPawn))
        {
            user = this.getUserWhite();
            this.coreGame.getUserWhite().setName(username);
        }
        else
        {
            user = this.getUserBlack();
            this.coreGame.getUserBlack().setName(username);
        }

        user.setUsername(username);
        dao.save(gameEntity);
        return user.getUsername();
    }



    public Cell getCell(int row, int column) {
        return this.coreGame.getCell(row, column);
    }

    public List<Move> getPossibleMoves(Position position) {
        return coreGame.getPossibleMoves(position);
    }

    private void switchTurn()
    {
        gameEntity.setCurrentTurn(gameEntity.getCurrentTurn() == ColorPawn.WHITE ? ColorPawn.BLACK : ColorPawn.WHITE);
    }

    public Integer getNbRows() {
        return coreGame.getNbRows();
    }

    public Integer getNbColumns() {
        return coreGame.getNbColumns();
    }

    public String getToken()
    {
        return this.gameEntity.getToken();
    }

    public UserEntity getUserWhite()
    {
        return this.gameEntity.getUserWhite();
    }
    public UserEntity getUserBlack()
    {
        return this.gameEntity.getUserBlack();
    }

    public Date getCreationDate()
    {
        return this.gameEntity.getCreatedAt();
    }
}
