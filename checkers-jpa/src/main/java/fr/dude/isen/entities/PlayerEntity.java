package fr.dude.isen.entities;

import javax.persistence.*;

/**
 * Representation of a player in the database
 */
@Entity
public class PlayerEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;

    private String username;

    private Integer nbPawns;

    public PlayerEntity()
    {

    }

    public PlayerEntity(String username, Integer nbPawns)
    {
        this.username = username;
        this.nbPawns = nbPawns;
    }

    public Integer getNbPawns() {
        return nbPawns;
    }

    public void setNbPawns(Integer nbPawns) {
        this.nbPawns = nbPawns;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
