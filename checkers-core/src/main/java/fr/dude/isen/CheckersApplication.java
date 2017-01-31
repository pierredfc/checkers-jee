package fr.dude.isen;

import com.google.inject.Guice;
import com.google.inject.Injector;

import java.io.IOException;

/**
 * Created by Clement on 09/01/2017.
 */
public class CheckersApplication {

    public static void main(String args[]) {
        CheckersGame game = launch();
        game.init();
    }

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
