package fr.dude.isen;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Created by Clement on 09/01/2017.
 */
public class CheckersApplication {

    public static void main(String args[]) {
        CheckersGame game = launch();
        game.init();
        game.run();
    }

    public static CheckersGame launch() {
        Injector injector = Guice.createInjector(new CheckersModule());
        return injector.getInstance(CheckersGameImpl.class);


    }
}
