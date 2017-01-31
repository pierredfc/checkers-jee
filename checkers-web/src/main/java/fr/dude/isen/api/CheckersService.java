package fr.dude.isen.api;


import fr.dude.isen.CheckersGame;
import fr.dude.isen.CheckersGameImpl;
import fr.dude.isen.api.requests.PlayRequest;
import fr.dude.isen.model.MoveResult;
import fr.dude.isen.model.pawns.Move;
import fr.dude.isen.model.pawns.Position;


import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

/**
 * Created by Clement on 16/01/2017.
 */
@Path("checkers")
@Produces({"application/json","text/xml"})
public class CheckersService {


    @Inject
    CheckersBean checkersgame;

    @GET
    @Path("/new")
    public CheckersGameImpl createGame() {
        this.checkersgame.createNewGame();
        System.out.println(this.checkersgame.getGame().getCoreGame());
        return (CheckersGameImpl) this.checkersgame.getGame().getCoreGame();
    }

    @GET
    @Path("/game/{value}")
    public CheckersGame loadFromToken(@PathParam("value") String token)
    {
        checkersgame.loadFromToken(token);
        return checkersgame.getGame().getCoreGame();
    }

    @GET
    @Path("/game/{value}/test")
    public String test(@PathParam("value") String test, @QueryParam("id") String id) {
        return test + id;
    }

    @POST
    @Path("/play")
    public MoveResult play(PlayRequest request) {
        return checkersgame.play(request.getOrigin(), request.getDestination());
    }

    @POST
    @Path("/moves")
    public List<Move> getPossibleMoves(Position position) {
        return checkersgame.getPossibleMoves(position);
    }

}
