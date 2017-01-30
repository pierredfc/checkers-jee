package fr.dude.isen.api;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by pierredfc on 16/01/2017.
 */
@ApplicationPath("/api")
public class Services extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(CheckerBoardBodyReaderWriter.class);
        return classes;

    }
}
