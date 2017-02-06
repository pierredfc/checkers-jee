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

    private MoveResult play(int initRow, int initCol, int destRow, int destCol) {
        return play(new Position(initRow, initCol), new Position(destRow, destCol));
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

    public boolean skip() {
        if (this.gameEntity.getTurnEntities() == null || this.gameEntity.getTurnEntities().isEmpty()) {
            play(6,0,5,1); play(3,1,4,2); play(6,6,5,7); play(3,5,4,4); play(6,4,5,5); play(4,4,6,6); play(4,2,6,0); play(3,3,4,2); play(6,2,5,3); play(4,2,6,4); play(2,2,3,1); play(7,5,5,3); play(5,3,4,4); play(3,1,4,2); play(4,4,3,3); play(4,2,5,3); play(7,3,6,4); play(5,3,7,5); play(1,1,2,2); play(3,3,1,1); play(5,7,4,8); play(0,0,2,2); play(3,9,5,7); play(2,0,3,1); play(8,6,6,4); play(6,4,5,3); play(6,6,7,5); play(9,7,8,6); play(7,5,9,7); play(9,7,4,2); play(5,7,6,6); play(7,7,5,5); play(5,5,4,4); play(2,4,3,5); play(7,1,6,2); play(3,5,5,3); play(5,3,7,1); play(4,2,5,3); play(8,4,7,5); play(5,3,8,6); play(8,6,5,9); play(5,9,4,8); play(9,5,8,4); play(3,1,4,2); play(8,4,7,5); play(4,2,5,3); play(7,5,6,6); play(5,3,6,4); play(9,3,8,4); play(4,8,7,5); play(7,5,9,3); play(9,3,6,6); play(8,8,7,7); play(6,6,8,8); play(3,7,4,6); play(7,9,6,8); play(8,8,3,3); play(9,9,8,8); play(3,3,9,9); play(2,2,3,3); play(6,8,5,7); play(4,6,6,8); play(6,4,7,5); play(8,2,7,3); play(9,9,5,5); play(7,3,6,4); play(6,8,7,9); play(9,1,8,2); play(5,5,7,3); play(7,3,9,1); play(9,1,4,6); play(8,0,6,2); play(6,2,5,3); play(4,6,6,4); play(5,3,4,2);
            //Win : play(6,4,3,1);
            return true;
        }
        return false;
    }
}
