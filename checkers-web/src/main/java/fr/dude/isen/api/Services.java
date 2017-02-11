package fr.dude.isen.api;

import fr.dude.isen.api.config.MyObjectMapperProvider;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

/**
 * API services
 */
@ApplicationPath("/api")
public class Services extends ResourceConfig {

    public Services() {
        packages("fr.dude.isen");
        register(MyObjectMapperProvider.class);
        register(JacksonFeature.class);
    }

}
