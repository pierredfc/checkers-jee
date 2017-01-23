package fr.dude.isen;

import fr.dude.isen.model.pawns.ColorPawn;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pierredfc on 23/01/2017.
 */
@Entity(name="Game")
public class Game {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String token;

    @OneToMany(mappedBy="game", cascade= CascadeType.ALL, fetch=FetchType.EAGER)
    @OrderColumn(name="index")
    private List<Turn> turns = new ArrayList<>();

    private String currentTurn = ColorPawn.WHITE.toString();

    public Game() {

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;

    }

    public List<Turn> getTurns() {
        return turns;
    }

    public ColorPawn getCurrentTurn() {
        return  ColorPawn.valueOf(currentTurn);
    }

    public void setCurrentTurn(ColorPawn colour) {
        currentTurn = colour.toString();
    }
}
