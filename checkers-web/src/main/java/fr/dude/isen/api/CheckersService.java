package fr.dude.isen.api;

import fr.dude.isen.*;
import fr.dude.isen.api.requests.PlayRequest;
import fr.dude.isen.model.MoveResult;
import fr.dude.isen.model.pawns.Move;
import fr.dude.isen.model.pawns.Position;
import org.jvnet.hk2.annotations.Service;

import javax.annotation.ManagedBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.interceptor.ExcludeClassInterceptors;
import javax.ws.rs.*;
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


    private CheckersGame GAME = init();

    private CheckersGame init() {
        CheckersGame game = CheckersApplication.launch();
        game.init();
        return game;
    }

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getList2() {
        List<String> list = new ArrayList<String>() {{
            add("Hello");
            add("World");
        }};
        return list;
    }

    @GET
    @Path("/new")
    @Produces(MediaType.APPLICATION_JSON)
    public CheckersGame createGame() {
        GAME = init();
        return GAME;
    }

    @GET
    @Path("/game")
    @Produces(MediaType.APPLICATION_JSON)
    public CheckersGame getGame() {
        return GAME;
    }

    @GET
    @Path("/game/{value}/test")
    @Produces(MediaType.APPLICATION_JSON)
    public String test(@PathParam("value") String test, @QueryParam("id") String id) {
        return test + id;
    }

    @POST
    @Path("/play")
    @Produces(MediaType.APPLICATION_JSON)
    public MoveResult play(PlayRequest request) {
        return GAME.play(request.getOrigin(), request.getDestination());
    }

    @POST
    @Path("/moves")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Move> getPossibleMoves(Position position) {
        return GAME.getPossibleMoves(position);
    }

}
