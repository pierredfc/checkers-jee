package fr.dude.isen;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Created by Clement on 09/01/2017.
 */
public class CheckersApplicationTest {

    private CheckersGame game;

    @Before
    public void doBefore() {
        Injector injector = Guice.createInjector(new CheckersModule());
        game = injector.getInstance(CheckersGameImpl.class);
        game.run();
    }

    @Test
    public void gameIsCheckersGameImpl() {
        assertEquals(CheckersGameImpl.class, game.getClass());
    }

    @Test
    public void gameHasCorrectProperties() {
        assertEquals(10, (int)game.getNbColumns());
        assertEquals(10, (int)game.getNbRows());
    }

}
