package fr.dude.isen;

import com.google.inject.Guice;
import com.google.inject.Injector;

import java.io.IOException;

/**
 * Checkers application
 */
public class CheckersApplication {

    public static void main(String args[]) {
        CheckersGame game = launch();
        game.init();
    }

    /**
     * Launch a new game
     * @return an implementation of a CheckersGame from properties. A CheckersGameImpl otherwise.
     */
    public static CheckersGame launch() {
        CheckersModule module = new CheckersModule();
        Injector injector = Guice.createInjector(module);

        try {
            return injector.getInstance(module.getCheckersGameClass());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new CheckersGameImpl();
    }
}
