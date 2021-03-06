package fr.dude.isen.entities;

import fr.dude.isen.model.pawns.Position;

import javax.persistence.*;

/**
 * Representation of a turn in the database
 */
@Entity
public class TurnEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;

    /**
     * Row index of the initial position from where the pawn has been moved.
     */
    @Column(name="initRow")
    private int initRow;

    /**
     * Column index of the initial position from where the pawn has been moved.
     */
    @Column(name="initColumn")
    private int initColumn;

    /**
     * Row index of the destination.
     */
    @Column(name="destRow")
    private int destRow;

    /**
     * Column index of the destination.
     */
    @Column(name="destColumn")
    private int destColumn;

    public TurnEntity() {

    }

    public TurnEntity(Position init, Position dest) {
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
