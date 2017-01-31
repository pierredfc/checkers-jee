package fr.dude.isen.entities;

import javax.persistence.*;

/**
 * Created by pierredfc on 31/01/2017.
 */
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;

    @OneToOne
    GameEntity gameEntity;

    private String username;

    private Integer nbPawns;

    public UserEntity()
    {

    }

    public UserEntity(GameEntity gameEntity, String username, Integer nbPawns)
    {
        this.gameEntity = gameEntity;
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
