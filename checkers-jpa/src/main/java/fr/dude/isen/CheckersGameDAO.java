package fr.dude.isen;

import fr.dude.isen.entities.GameEntity;
import fr.dude.isen.entities.UserEntity;
import org.apache.commons.lang.RandomStringUtils;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pierredfc on 23/01/2017.
 */
public class CheckersGameDAO {

    @Inject
    EntityManager em;

    @Inject
    UserTransaction ut;

    public CheckersAdapter createGame()
    {
        GameEntity gameEntity = new GameEntity();
        gameEntity.setToken(RandomStringUtils.randomAlphanumeric(10).toLowerCase());
        gameEntity.setUserBlack(new UserEntity(gameEntity, "Black", 20));
        gameEntity.setUserWhite(new UserEntity(gameEntity, "White", 20));

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

    public CheckersAdapter loadFromToken(String token) {
        GameEntity gameEntity = (GameEntity) em
                .createQuery("SELECT g FROM Game g WHERE g.token = :token")
                .setParameter("token", token).getSingleResult();

        return new CheckersAdapter(this, gameEntity);
    }

    public List<CheckersAdapter> loadSavedGames()
    {
        List<GameEntity> gameEntity =  em
                .createQuery("SELECT g FROM Game g").getResultList();

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

    public void save(GameEntity gameEntity) {
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
