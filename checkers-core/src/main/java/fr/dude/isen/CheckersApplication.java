package fr.dude.isen;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Created by Clement on 09/01/2017.
 */
public class CheckersApplication {

    public static void main(String args[]) {

        Injector injector = Guice.createInjector(new CheckersModule());
        CheckersGame game = injector.getInstance(CheckersGameImpl.class);

        game.run();
    }
}
