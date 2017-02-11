package fr.dude.isen.api;


import fr.dude.isen.CheckersAdapter;
import fr.dude.isen.api.requests.PlayRequest;
import fr.dude.isen.api.requests.PlayerNameRequest;
import fr.dude.isen.api.responses.GameResponse;
import fr.dude.isen.api.responses.LightGame;
import fr.dude.isen.api.responses.TurnResponse;
import fr.dude.isen.entities.TurnEntity;
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
@Produces({"application/json", "text/xml"})
public class CheckersService implements CheckersApi {

    @Inject
    CheckersBean checkersgame;

    @GET
    @Path("/games")
    @Override
    public List<LightGame> getGames() {
        return this.checkersgame.getSavedGames();
    }

    @PUT
    @Path("/game/{token}/name")
    @Override
    public String setName(@PathParam("token") String token, PlayerNameRequest request) {
        this.checkersgame.loadFromToken(token);
        return this.checkersgame.setUsername(request);
    }

    @GET
    @Path("/new")
    @Override
    public GameResponse createGame() {
        this.checkersgame.createNewGame();
        final CheckersAdapter checkersAdapter = this.checkersgame.getGame();
        return new GameResponse(checkersAdapter.getToken(), checkersAdapter.getCoreGame(), checkersAdapter.getCreationDate());
    }

    @GET
    @Path("/game/{token}")
    @Override
    public GameResponse getGame(@PathParam("token") String token) {
        this.checkersgame.loadFromToken(token);
        final CheckersAdapter checkersAdapter = this.checkersgame.getGame();
        return new GameResponse(checkersAdapter.getToken(), checkersAdapter.getCoreGame(), checkersAdapter.getCreationDate());
    }

    @POST
    @Path("/game/{token}/play")
    @Override
    public MoveResult play(@PathParam("token") String token, PlayRequest request) {
        this.checkersgame.loadFromToken(token);
        return this.checkersgame.play(request.getOrigin(), request.getDestination());
    }

    @POST
    @Path("/game/{token}/moves")
    @Override
    public List<Move> getPossibleMoves(@PathParam("token") String token, Position position) {
        this.checkersgame.loadFromToken(token);
        return this.checkersgame.getPossibleMoves(position);
    }

    @DELETE
    @Path("/game/{token}")
    @Override
    public String deleteGame(@PathParam("token") String token) {
        this.checkersgame.deleteFromToken(token);
        return token;
    }

    @GET
    @Path("/game/{token}/history")
    @Override
    public List<TurnResponse> getHistory(@PathParam("token") String token) {
        List<TurnEntity> entities = this.checkersgame.getHistory(token);
        if (entities != null && entities.size() > 0) {
            List<TurnResponse> turnResponses = new ArrayList<>(entities.size());
            for (TurnEntity entity : entities) {
                turnResponses.add(new TurnResponse(entity));
            }
            return turnResponses;
        }
        return new ArrayList<>(0);
    }

    @GET
    @Path("/game/{token}/skip")
    public GameResponse skip(@PathParam("token") String token) {
        this.checkersgame.loadFromToken(token);
        final CheckersAdapter checkersAdapter = this.checkersgame.getGame();
        if (checkersAdapter.skip()) {
            return new GameResponse(checkersAdapter.getToken(), checkersAdapter.getCoreGame(), checkersAdapter.getCreationDate());
        }
        return null;
    }
}
