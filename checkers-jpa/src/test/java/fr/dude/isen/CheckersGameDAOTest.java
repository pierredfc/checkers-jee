package fr.dude.isen;

import fr.dude.isen.guice.GuiceRunner;
import fr.dude.isen.guice.H2DBModule;
import fr.dude.isen.guice.Modules;
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

    @Inject
    EntityManager em;

    @Inject
    CheckersGameDAO dao;


    @Test
    public void daoIsInjected() throws Exception {
        assertThat(dao).isNotNull();
    }

    @Test
    public void itCanCreateAGame() throws Exception {
        CheckersAdapter game = dao.createGame();
        assertThat(game).isNotNull();

        String token = game.getToken();
        assertThat(game.getToken()).isNotNull();
        em.clear();
        game = dao.loadFromToken(token);
        assertThat(game).isNotNull();
    }

}
