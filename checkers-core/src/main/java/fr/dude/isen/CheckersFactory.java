package fr.dude.isen;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.function.Function;

/**
 * Created by Clement on 09/01/2017.
 */
public class CheckersFactory {

    public Properties getProperties() throws IOException {
        Properties props = new Properties();

        URL url = getClass().getClassLoader().getResource("checkers.properties");
        props.load(url.openStream());

        return props;
    }

    public Class<CheckersGame> getCheckersGameClass() throws IOException, ClassNotFoundException {
        return (Class<CheckersGame>) Class.forName(getCheckersGameType());
    }

    public String getCheckersGameType() throws IOException {
        return getCheckersGameType(getProperties());
    }

    public String getCheckersGameType(Properties properties) {
        return properties.getProperty("checkers.application.instance");
    }

    @SuppressWarnings("unchecked")
    public CheckersGame getCheckersGame() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Properties props = getProperties();

        Class<?> klass = Class.forName(getCheckersGameType(props));
        return (CheckersGame) klass.newInstance();
    }

}
