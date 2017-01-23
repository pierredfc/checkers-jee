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

    private String colorPawn;

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

    public Turn(Game game, ColorPawn color, Position init, Position dest) {
        this.game = game;
        this.colorPawn = color.toString();
        this.initRow = init.getRow();
        this.initColumn = init.getColumn();
        this.destRow = dest.getRow();
        this.destColumn = dest.getColumn();
    }

    public ColorPawn getColorPawn() {
        return ColorPawn.valueOf(colorPawn);
    }

    public Position getInitPosition() {
        return new Position(initRow, initColumn);
    }

    public Position getDestination() {
        return new Position(destRow, destColumn);
    }
}
