package fr.dude.isen;

import fr.dude.isen.entities.GameEntity;
import fr.dude.isen.entities.TurnEntity;
import fr.dude.isen.entities.PlayerEntity;
import fr.dude.isen.model.Cell;
import fr.dude.isen.model.MoveResult;
import fr.dude.isen.model.Player;
import fr.dude.isen.model.pawns.ColorPawn;
import fr.dude.isen.model.pawns.Move;
import fr.dude.isen.model.pawns.Position;

import java.util.Date;
import java.util.List;

/**
 * Checkers adapter
 */
public class CheckersAdapter {

    /**
     * Entity of the game
     */
    private GameEntity gameEntity;

    /**
     * Model of the game
     */
    private CheckersGame coreGame;

    /**
     * DAO model of the game
     */
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

        Player playerBlack = this.coreGame.getUserBlack();
        Player playerWhite = this.coreGame.getUserWhite();

        // If it's a new game, we create the two user entities from the core game.
        if ((this.gameEntity.getPlayerWhite() == null) && (this.gameEntity.getPlayerBlack() == null))
        {
            this.gameEntity.setPlayerWhite(this.convertPlayerToPlayerEntity(playerWhite));
            this.gameEntity.setPlayerBlack(this.convertPlayerToPlayerEntity(playerBlack));
            dao.save(this.gameEntity);
        }
        else
        {
            // It's a game load from its token, we retrieve users information from their entities.
            if (playerBlack != null && playerWhite != null)
            {
                playerBlack.setName(gameEntity.getPlayerBlack().getUsername());
                playerWhite.setName(gameEntity.getPlayerWhite().getUsername());
                playerWhite.setOpponent(playerBlack);
                playerBlack.setOpponent(playerWhite);
            }
        }
    }

    public CheckersGame getCoreGame() {
        return coreGame;
    }

    /**
     * Play a move
     * @param init Initial position of the pawn to be moved
     * @param destination Destination positif of the pawn to be moved
     * @return a MoveResult on success. Null otherwise.
     */
    public MoveResult play(Position init, Position destination) {
        MoveResult result = this.coreGame.play(init, destination);
        this.gameEntity.getTurnEntities().add(new TurnEntity(init, destination));
        this.switchTurn();
        dao.save(gameEntity);
        return result;
    }

    private MoveResult play(int initRow, int initCol, int destRow, int destCol) {
        return play(new Position(initRow, initCol), new Position(destRow, destCol));
    }

    /**
     * Set the uername of the player who has the colorPawn
     * @param username New username of the player
     * @param colorPawn Color of the user's pawn
     * @return the new username
     */
    public String setUsername(String username, ColorPawn colorPawn)
    {
        PlayerEntity user;

        if (ColorPawn.WHITE.equals(colorPawn))
        {
            user = this.getPlayerWhite();
            this.coreGame.getUserWhite().setName(username);
        }
        else
        {
            user = this.getPlayerBlack();
            this.coreGame.getUserBlack().setName(username);
        }

        user.setUsername(username);
        dao.save(gameEntity);
        return user.getUsername();
    }

    /**
     * Retrieve a Cell from its coordinates
     * @param row Row index of the cell
     * @param column Column index of the cell
     * @return the matching cell
     */
    public Cell getCell(int row, int column) {
        return this.coreGame.getCell(row, column);
    }

    /**
     * Return the possible move for a current position
     * @param position Position of the pawn
     * @return the possible moves in a list
     */
    public List<Move> getPossibleMoves(Position position) {
        return coreGame.getPossibleMoves(position);
    }

    /**
     * Switch player's turn
     */
    private void switchTurn()
    {
        gameEntity.setCurrentTurn(gameEntity.getCurrentTurn() == ColorPawn.WHITE ? ColorPawn.BLACK : ColorPawn.WHITE);
    }

    public String getToken()
    {
        return this.gameEntity.getToken();
    }

    public PlayerEntity getPlayerWhite()
    {
        return this.gameEntity.getPlayerWhite();
    }
    public PlayerEntity getPlayerBlack()
    {
        return this.gameEntity.getPlayerBlack();
    }

    public Date getCreationDate()
    {
        return this.gameEntity.getCreatedAt();
    }

    /**
     * Test function for testing a winning game !
     * @return true on success. False otherwise.
     */
    public boolean skip() {
        if (this.gameEntity.getTurnEntities() == null || this.gameEntity.getTurnEntities().isEmpty()) {
            play(6,0,5,1); play(3,1,4,2); play(6,6,5,7); play(3,5,4,4); play(6,4,5,5); play(4,4,6,6); play(4,2,6,0); play(3,3,4,2); play(6,2,5,3); play(4,2,6,4); play(2,2,3,1); play(7,5,5,3); play(5,3,4,4); play(3,1,4,2); play(4,4,3,3); play(4,2,5,3); play(7,3,6,4); play(5,3,7,5); play(1,1,2,2); play(3,3,1,1); play(5,7,4,8); play(0,0,2,2); play(3,9,5,7); play(2,0,3,1); play(8,6,6,4); play(6,4,5,3); play(6,6,7,5); play(9,7,8,6); play(7,5,9,7); play(9,7,4,2); play(5,7,6,6); play(7,7,5,5); play(5,5,4,4); play(2,4,3,5); play(7,1,6,2); play(3,5,5,3); play(5,3,7,1); play(4,2,5,3); play(8,4,7,5); play(5,3,8,6); play(8,6,5,9); play(5,9,4,8); play(9,5,8,4); play(3,1,4,2); play(8,4,7,5); play(4,2,5,3); play(7,5,6,6); play(5,3,6,4); play(9,3,8,4); play(4,8,7,5); play(7,5,9,3); play(9,3,6,6); play(8,8,7,7); play(6,6,8,8); play(3,7,4,6); play(7,9,6,8); play(8,8,3,3); play(9,9,8,8); play(3,3,9,9); play(2,2,3,3); play(6,8,5,7); play(4,6,6,8); play(6,4,7,5); play(8,2,7,3); play(9,9,5,5); play(7,3,6,4); play(6,8,7,9); play(9,1,8,2); play(5,5,7,3); play(7,3,9,1); play(9,1,4,6); play(8,0,6,2); play(6,2,5,3); play(4,6,6,4); play(5,3,4,2);
            //Win : play(6,4,3,1);
            return true;
        }
        return false;
    }


    /**
     * Convert a player model to a PlayerEntity.
     * @param player The player to be converted
     * @return a PlayerEntity corresponding to a Player
     */
    private PlayerEntity convertPlayerToPlayerEntity(Player player)
    {
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setNbPawns(player.getNbPawns());
        playerEntity.setUsername(player.getName());
        return playerEntity;
    }
}
