package fr.dude.isen.entities;

import fr.dude.isen.model.pawns.ColorPawn;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pierredfc on 23/01/2017.
 */
@Entity(name="Game")
public class GameEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String token;

    @OneToMany(mappedBy= "gameEntity", cascade= CascadeType.ALL, fetch=FetchType.EAGER)
    @OrderColumn(name="index")
    private List<TurnEntity> turnEntities = new ArrayList<>();

    @OneToOne(mappedBy= "gameEntity", cascade= CascadeType.ALL, fetch=FetchType.EAGER)
    private UserEntity userWhite;

    @OneToOne(mappedBy= "gameEntity", cascade= CascadeType.ALL, fetch=FetchType.EAGER)
    private UserEntity userBlack;

    private String currentTurn = ColorPawn.WHITE.toString();

    public GameEntity() {

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        System.out.println("TOKEN = " + token);
        this.token = token;
    }

    public List<TurnEntity> getTurnEntities() {
        return turnEntities;
    }

    public ColorPawn getCurrentTurn() {
        return  ColorPawn.valueOf(currentTurn);
    }

    public void setCurrentTurn(ColorPawn colour) {
        currentTurn = colour.toString();
    }

    public UserEntity getUserBlack() {
        return userBlack;
    }

    public UserEntity getUserWhite() {
        return userWhite;
    }

    public void setUserBlack(UserEntity userBlack) {
        this.userBlack = userBlack;
    }

    public void setUserWhite(UserEntity userWhite) {
        this.userWhite = userWhite;
    }
}
