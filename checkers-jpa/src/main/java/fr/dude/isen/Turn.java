package fr.dude.isen;

import fr.dude.isen.model.pawns.ColorPawn;
import fr.dude.isen.model.pawns.Position;

import javax.persistence.*;

/**
 * Created by pierredfc on 23/01/2017.
 */
@Entity
public class Turn {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;

    @ManyToOne
    Game game;

    @Column(name="initRow")
    private int initRow;

    @Column(name="initColumn")
    private int initColumn;

    @Column(name="destRow")
    private int destRow;

    @Column(name="destColumn")
    private int destColumn;

    public Turn() {

    }

    public Turn(Game game, Position init, Position dest) {
        this.game = game;
        this.initRow = init.getRow();
        this.initColumn = init.getColumn();
        this.destRow = dest.getRow();
        this.destColumn = dest.getColumn();
    }


    public Position getInitPosition() {
        return new Position(initRow, initColumn);
    }

    public Position getDestination() {
        return new Position(destRow, destColumn);
    }
}
