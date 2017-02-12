package fr.dude.isen.api;

import fr.dude.isen.api.requests.PlayRequest;
import fr.dude.isen.api.requests.PlayerNameRequest;
import fr.dude.isen.api.responses.GameResponse;
import fr.dude.isen.api.responses.LightGame;
import fr.dude.isen.api.responses.TurnResponse;
import fr.dude.isen.model.MoveResult;
import fr.dude.isen.model.pawns.Move;
import fr.dude.isen.model.pawns.Position;
import fr.dude.isen.model.serializable.SerializableMove;
import fr.dude.isen.model.serializable.SerializableMoveResult;

import java.util.List;

/**
 * Checkers API interface
 */
public interface CheckersApi {

    /**
     * @return the list of saved games.
     */
    List<LightGame> getGames();

    /**
     * Modify the player's username
     * @param token Game's token
     * @param request Affected player and his new name
     * @return the new username
     */
    String setName(String token, PlayerNameRequest request);

    /**
     * Create a new game
     * @return the new game
     */
    GameResponse createGame();

    /**
     * Retrieve a game from its identifier
     * @param token Game's identifier
     * @return the matching game
     */
    GameResponse getGame(String token);

    /**
     * Move a pawn on a specific game
     * @param token Game's identifier
     * @param request Origin cell and destination cell
     * @return a MoveResult on success. Null otherwise.
     */
    SerializableMoveResult play(String token, PlayRequest request);

    /**
     * Retrieve all possible moves for a specific position
     * @param token Game's identifier
     * @param position Pawn's position
     * @return a list of possible moves on success. Empty list if no moves. Null on error (no pawn at the position).
     */
    List<? extends SerializableMove> getPossibleMoves(String token, Position position);

    /**
     * Delete a game
     * @param token Game's identifier
     * @return the deleted game's token
     */
    String deleteGame(String token);

    /**
     * @param token Game's identifier
     * @return the list of turns made in a specific game
     */
    List<TurnResponse> getHistory(String token);

    /**
     * DEBUG function in order to have a specific game configuration to have one less move to win
     * @param token Game's identifier
     * @return the game
     */
    GameResponse skip(String token);
}
