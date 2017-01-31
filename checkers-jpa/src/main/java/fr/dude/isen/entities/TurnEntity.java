package fr.dude.isen.entities;

import fr.dude.isen.model.pawns.Position;

import javax.persistence.*;

/**
 * Created by pierredfc on 23/01/2017.
 */
@Entity
public class TurnEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;

    @ManyToOne
    GameEntity gameEntity;

    @Column(name="initRow")
    private int initRow;

    @Column(name="initColumn")
    private int initColumn;

    @Column(name="destRow")
    private int destRow;

    @Column(name="destColumn")
    private int destColumn;

    public TurnEntity() {

    }

    public TurnEntity(GameEntity gameEntity, Position init, Position dest) {
        this.gameEntity = gameEntity;
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
