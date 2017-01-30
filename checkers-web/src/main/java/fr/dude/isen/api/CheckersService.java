package fr.dude.isen.api;


import fr.dude.isen.*;
import fr.dude.isen.api.requests.PlayRequest;
import fr.dude.isen.model.MoveResult;
import fr.dude.isen.model.pawns.Move;
import fr.dude.isen.model.pawns.Position;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Clement on 16/01/2017.
 */
@Path("checkers")
@Produces({"application/json","text/xml"})
public class CheckersService {

    private static CheckersGame GAME = init();

    private static CheckersGame init() {
        CheckersGame game = CheckersApplication.launch();
        game.init();
        return game;
    }

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
    @Path("/newgame")
    public CheckersGame getNewGame() {
        return GAME;
    }

    @GET
    @Path("/list")
    public List<String> list()
    {
        List<String> returns = new ArrayList<>();
        returns.add("coucou");
        returns.add("ok");
        return returns;
        /*CheckersGame game = CheckersApplication.launch();
        game.init();
        return game.getCell(0,0).getPosition();*/
    }

    @GET
    @Path("/position")
    public Position getPos() {
        return new Position(0,0);
    }

    @GET
    @Path("/string")
    public String getString()
    {
        return "Coucou";
    }

    @GET
    @Path("/game")
    public CheckersGame getGame() {
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
