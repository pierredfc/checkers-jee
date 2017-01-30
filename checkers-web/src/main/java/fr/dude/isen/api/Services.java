package fr.dude.isen.api;

import fr.dude.isen.api.config.MarshallingFeature;
import fr.dude.isen.api.config.MyObjectMapperProvider;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

/**
 * Created by pierredfc on 16/01/2017.
 */
@ApplicationPath("/api")
public class Services extends ResourceConfig {

    public Services() {
        packages("fr.dude.isen");
        System.out.println("Services ------------------------------------------------------");
        register(MyObjectMapperProvider.class);
        register(JacksonFeature.class);
    }

}
