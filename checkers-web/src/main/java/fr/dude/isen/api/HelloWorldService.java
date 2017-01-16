package fr.dude.isen.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Clement on 16/01/2017.
 */
@Path("hello")
public class HelloWorldService {

    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    public String hello() {
        return "Hello world";
    }

}
