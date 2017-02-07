package fr.dude.isen;

import fr.dude.isen.entities.GameEntity;
import fr.dude.isen.entities.TurnEntity;
import fr.dude.isen.entities.UserEntity;
import org.apache.commons.lang.RandomStringUtils;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.transaction.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO model of the game
 */
public class CheckersGameDAO {

    @Inject
    EntityManager em;

    @Inject
    UserTransaction ut;

    /**
     * @return a new game represented by a CheckersAdapter
     */
    public CheckersAdapter createGame()
    {
        GameEntity gameEntity = new GameEntity();
        gameEntity.setToken(RandomStringUtils.randomAlphanumeric(10).toLowerCase());
        gameEntity.setUserBlack(new UserEntity("Black", 20));
        gameEntity.setUserWhite(new UserEntity("White", 20));

        try {
            ut.begin();
            em.persist(gameEntity);
            ut.commit();

        } catch (NotSupportedException | SystemException | SecurityException
                | IllegalStateException | RollbackException
                | HeuristicMixedException | HeuristicRollbackException e) {
            return null;
        }
        return new CheckersAdapter(this, gameEntity);
    }

    /**
     * Retrieve all turns from a game
     * @param token Identifier of the game
     * @return the list of turns realised for the given game on success. Null otherwise.
     */
    public List<TurnEntity> getHistoryFromToken(String token) {
        try
        {
            GameEntity gameEntity = (GameEntity) em
                    .createNamedQuery("LOAD_FROM_TOKEN")
                    .setParameter("token", token).getSingleResult();

            return gameEntity.getTurnEntities();
        }
        catch (Exception e)
        {
            return null;
        }
    }

    /**
     * Load a game from its token
     * @param token Identifier of the game
     * @return the game represented by a CheckerAdapter on success. Null otherwise.
     */
    public CheckersAdapter loadFromToken(String token) {
        try
        {
            GameEntity gameEntity = (GameEntity) em
                    .createNamedQuery("LOAD_FROM_TOKEN")
                    .setParameter("token", token).getSingleResult();

            return new CheckersAdapter(this, gameEntity);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    /**
     * @return all saved games if there are any. Null otherwise.
     */
    public List<CheckersAdapter> loadSavedGames()
    {
        List<GameEntity> gameEntity =  em.createNamedQuery("GET_ALL_GAME").getResultList();

        if (gameEntity != null && gameEntity.size() > 0)
        {
            List<CheckersAdapter> results = new ArrayList<>(gameEntity.size());

            for (GameEntity entity : gameEntity)
            {
                results.add(new CheckersAdapter(this, entity));
            }

            return results;
        }

        return null;
    }

    /**
     * Delete the given game represented by its token.
     * @param token Identifier of the game
     */
    public void delete(String token)
    {
        GameEntity gameEntity = (GameEntity) em
                .createNamedQuery("LOAD_FROM_TOKEN")
                .setParameter("token", token).getSingleResult();

        try {
            ut.begin();
            if (!em.contains(gameEntity)) {
                gameEntity = em.merge(gameEntity);
            }
            em.remove(gameEntity);
            ut.commit();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Save the state of the game
     * @param gameEntity
     */
    protected void save(GameEntity gameEntity) {
        try {
            ut.begin();
            em.merge(gameEntity);
            ut.commit();
        } catch (SecurityException | IllegalStateException | RollbackException
                | HeuristicMixedException | HeuristicRollbackException
                | SystemException | NotSupportedException e) {
            e.printStackTrace();
        }
    }
}
