package fr.dude.isen;

import fr.dude.isen.guice.GuiceRunner;
import fr.dude.isen.guice.H2DBModule;
import fr.dude.isen.guice.Modules;
import fr.dude.isen.model.pawns.Position;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by pierredfc on 23/01/2017.
 */
@RunWith(GuiceRunner.class)
@Modules({ H2DBModule.class, JPAModule.class })
public class CheckersGameDAOTest {

    private static Logger logger = LogManager.getLogger(CheckersGameDAOTest.class);

    @Inject
    EntityManager em;

    @Inject
    CheckersGameDAO dao;


    @Test
    public void daoIsInjected() throws Exception {
        logger.info("[CheckersGameDAOTest][daoIsInjected] BEGIN");
        assertThat(dao).isNotNull();
        logger.info("[CheckersGameDAOTest][daoIsInjected] CheckersGameDAO is well injected");
        logger.info("[CheckersGameDAOTest][daoIsInjected] END");
    }

    @Test
    public void itCanCreateAndDeleteAGame() throws Exception {
        logger.info("[CheckersGameDAOTest][itCanCreateAndDeleteAGame] BEGIN");
        CheckersAdapter game = dao.createGame();
        assertThat(game).isNotNull();

        String token = game.getToken();
        assertThat(token).isNotNull();
        logger.info("[CheckersGameDAOTest][itCanCreateAndDeleteAGame] Game " + token + " created");
        em.clear();

        game = dao.loadFromToken(token);
        assertThat(game).isNotNull();
        logger.info("[CheckersGameDAOTest][itCanCreateAndDeleteAGame] Game " + token + " successfully loaded from database");

        dao.delete(token);
        logger.info("[CheckersGameDAOTest][itCanCreateAndDeleteAGame] Game " + token + " deleted");
        game = dao.loadFromToken(token);
        assertThat(game).isNull();
        logger.info("[CheckersGameDAOTest][itCanCreateAndDeleteAGame] Game " + token + " can not be load");
        logger.info("[CheckersGameDAOTest][itCanCreateAndDeleteAGame] END");
    }

    @Test
    public void itCanPlayWithAJPAGame() throws Exception {
        logger.info("[CheckersGameDAOTest][itCanPlayWithAJPAGame] BEGIN");
        CheckersAdapter game = dao.createGame();
        String token = game.getToken();
        logger.info("[CheckersGameDAOTest][itCanPlayWithAJPAGame] Game " + token + " created");

        Position origin = new Position(6,0);
        Position destination = new Position(5,1);
        game.play(origin, destination);

        origin = new Position(3,1);
        destination = new Position(4,2);
        game.play(origin, destination);

        assertThat(game.getCell(4, 2).getPawn()).isNotNull();
        assertThat(game.getCell(5, 1).getPawn()).isNotNull();

        em.clear();
        game = dao.loadFromToken(token);
        assertThat(game).isNotNull();

        logger.info("[CheckersGameDAOTest][itCanPlayWithAJPAGame] Game " + token + " successfully loaded from database");

        assertThat(game.getCell(4, 2).getPawn()).isNotNull();
        assertThat(game.getCell(5, 1).getPawn()).isNotNull();
        logger.info("[CheckersGameDAOTest][itCanPlayWithAJPAGame] END");
    }

}
