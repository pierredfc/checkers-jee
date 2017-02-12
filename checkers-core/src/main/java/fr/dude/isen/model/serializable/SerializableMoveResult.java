package fr.dude.isen.model.serializable;

import fr.dude.isen.model.pawns.ColorPawn;
import fr.dude.isen.model.pawns.Position;

import java.io.Serializable;

/**
 * Interface providing the properties used by the API for a MoveResult
 */
public interface SerializableMoveResult extends Serializable {

    Position getOrigin();
    Position getDestination();
    Position getKill();
    boolean isBecomesQueen();
    ColorPawn getNextUser();
    int getNbPawnsPlayerWhite();
    int getNbPawnsPlayerBlack();
    boolean isWinningMove();

}
