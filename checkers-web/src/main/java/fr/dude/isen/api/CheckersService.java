package fr.dude.isen.api;

import fr.dude.isen.CheckersApplication;
import fr.dude.isen.CheckersGame;
import fr.dude.isen.CheckersGameImpl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Created by Clement on 16/01/2017.
 */
@Path("checkers")
public class CheckersService {

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getList2() {
        List<String> list = new ArrayList<String>() {{
            add("Hello"); add("World");
        }};
        return list;
    }

    @GET
    @Path("/new")
    @Produces(MediaType.APPLICATION_JSON)
    public CheckersGameImpl createGame() {
        CheckersGame game = CheckersApplication.launch();
        game.init();
        return (CheckersGameImpl) game;
    }

}
