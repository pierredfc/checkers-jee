package fr.dude.isen;

import fr.dude.isen.entities.GameEntity;
import fr.dude.isen.entities.UserEntity;
import org.apache.commons.lang.RandomStringUtils;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.*;

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
