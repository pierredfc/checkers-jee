package fr.dude.isen;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/**
 * Created by Clement on 09/01/2017.
 */
public class CheckersModule extends AbstractModule {

    @SuppressWarnings("unchecked")
    @Provides
    @Singleton
    public CheckersGame getInstance(@Named("checkers.application.instance") String className) {
        try {
            Class<?> clazz = Class.forName(className);
            return (CheckersGame) clazz.newInstance();
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException e) {
            return null;
        }
    }

    @Override
    protected void configure() {
        try {
            Names.bindProperties(binder(), getProperties());
        } catch (IOException e) {
            System.out.println("Unable to bind properties");
        }

    }

    private Properties getProperties() throws IOException {
        Properties props = new Properties();
        URL url = getClass().getClassLoader()
                .getResource("checkers.properties");
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

}
