package fr.dude.isen;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.function.Function;

/**
 * Created by Clement on 09/01/2017.
 */
public class CheckersFactory {

    @SuppressWarnings("unchecked")
    public CheckersGame getCheckersGame() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Properties props = new Properties();

        URL url = getClass().getClassLoader().getResource("checkers.properties");
        props.load(url.openStream());

        return new CheckersGameImpl();
    }

}
