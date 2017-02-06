package fr.dude.isen.api;

import fr.dude.isen.api.requests.PlayRequest;
import fr.dude.isen.api.requests.UserNameRequest;
import fr.dude.isen.api.responses.GameResponse;
import fr.dude.isen.api.responses.LightGame;
import fr.dude.isen.model.MoveResult;
import fr.dude.isen.model.pawns.Move;
import fr.dude.isen.model.pawns.Position;

import java.util.List;

/**
 * Created by Clement on 31/01/2017.
 */
public interface CheckersApi {

    /**
     * Récupérer la liste des parties en cours
     * @return La liste des parties en cours
     */
    List<LightGame> getGames();

    /**
     * Modifier le nom d'un joueur dans une partie
     * @param token Le token de la partie
     * @param request Le joueur concerné (couleur des pions) et son nouveau nom
     * @return Le nouveau nom du joueur
     */
    String setName(String token, UserNameRequest request);

    /**
     * Créer une nouvelle partie
     * @return La nouvelle partie
     */
    GameResponse createGame();

    /**
     * Récupérer la plateau de jeu d'une partie
     * @param token Le token de la partie
     * @return La partie
     */
    GameResponse getGame(String token);

    /**
     * Avancer un pion (avancer d'un tour) sur le plateau de jeu
     * @param token Le token de la partie
     * @param request La cellule d'origine, la cellule de destination
     * @return Les conséquences du mouvement, ou null
     */
    MoveResult play(String token, PlayRequest request);

    /**
     * Récupérer les mouvements possibles sur une cellule donnée
     * @param position La position du pion concerné
     * @return Les mouvements possibles de ce pion
     */
    List<Move> getPossibleMoves(String token, Position position);

    /**
     * Suppression d'une partie à partir de son identifiant (token)
     * @param token Identifiant de la partie
     * @return Le token de la partie supprimée
     */
    String deleteGame(String token);
}
