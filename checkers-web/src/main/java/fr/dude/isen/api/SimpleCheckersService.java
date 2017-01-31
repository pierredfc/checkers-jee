package fr.dude.isen.api;

import fr.dude.isen.CheckersApplication;
import fr.dude.isen.CheckersGame;
import fr.dude.isen.api.requests.PlayRequest;
import fr.dude.isen.api.requests.UserNameRequest;
import fr.dude.isen.api.responses.GameResponse;
import fr.dude.isen.api.responses.LightGame;
import fr.dude.isen.model.MoveResult;
import fr.dude.isen.model.pawns.ColorPawn;
import fr.dude.isen.model.pawns.Move;
import fr.dude.isen.model.pawns.Position;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Clement on 31/01/2017.
 */

@Path("simple")
@Produces(MediaType.APPLICATION_JSON)
public class SimpleCheckersService implements CheckersApi {

    private static List<GameResponse> GAMES_RESPONSES = init();

    private static List<GameResponse> init() {
        final CheckersGame game1 = CheckersApplication.launch();
        final CheckersGame game2 = CheckersApplication.launch();
        final CheckersGame game3 = CheckersApplication.launch();
        game1.init();
        game2.init();
        game3.init();

        return new ArrayList<GameResponse>(3) {{
            add(new GameResponse("game1", game1, null));
            add(new GameResponse("game2", game2, null));
            add(new GameResponse("game3", game3, null));
        }};
    }

    @GET
    @Path("/games")
    @Override
    public List<LightGame> getGames() {
        List<LightGame> lightGames = new ArrayList<>(GAMES_RESPONSES.size());
        for(GameResponse game : GAMES_RESPONSES) {
            lightGames.add(new LightGame(game));
        }
        return lightGames;
    }

    @PUT
    @Path("/{token}/name")
    @Override
    public String setName(@PathParam("token") String token, UserNameRequest request) {
        String newName = request.getName();
        for(GameResponse game : GAMES_RESPONSES) {
            if (token.equals(game.getToken())) {
                if (request.getColor().equals(ColorPawn.BLACK)) {
                    game.getGame().getUserBlack().setName(newName);
                }
                else {
                    game.getGame().getUserWhite().setName(newName);
                }
                return newName;
            }
        }
        return null;
    }

    @GET
    @Path("/new")
    @Override
    public GameResponse createGame() {
        final CheckersGame game = CheckersApplication.launch();
        game.init();
        final GameResponse gameResponse = new GameResponse("game"+(GAMES_RESPONSES.size()+1), game, null);
        GAMES_RESPONSES.add(gameResponse);
        return gameResponse;
    }

    @GET
    @Path("/{token}/game")
    @Override
    public GameResponse getGame(@PathParam("token") String token) {
        for(GameResponse gameResponse : GAMES_RESPONSES) {
            if (token.equals(gameResponse.getToken())) {
                return gameResponse;
            }
        }
        return null;
    }

    @POST
    @Path("/{token}/play")
    @Override
    public MoveResult play(@PathParam("token") String token, PlayRequest request) {
        GameResponse game = getGame(token);
        return game.getGame().play(request.getOrigin(), request.getDestination());
    }

    @POST
    @Path("/{token}/moves")
    @Override
    public List<Move> getPossibleMoves(@PathParam("token") String token, Position position) {
        GameResponse game = getGame(token);
        return game.getGame().getPossibleMoves(position);
    }
}
