package fr.dude.isen.entities;

import fr.dude.isen.model.pawns.ColorPawn;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Representation of a Game in the database
 */
@NamedQueries({
        @NamedQuery(name = "LOAD_FROM_TOKEN", query = "SELECT g FROM Game g WHERE g.token = :token"),
        @NamedQuery(name = "GET_ALL_GAME", query = "SELECT g FROM Game g")
})
@Entity(name="Game")
public class GameEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    /**
     * Unique identifier of the game, used for retrieving a game.
     */
    private String token;

    @Temporal(TemporalType.DATE)
    @Column(name = "created_at")
    private Date createdAt;

    @OneToMany(cascade= CascadeType.ALL, fetch=FetchType.EAGER)
    @OrderColumn(name="index")
    private List<TurnEntity> turnEntities = new ArrayList<>();

    @OneToOne(cascade= CascadeType.ALL, fetch=FetchType.EAGER)
    private PlayerEntity playerWhite;

    @OneToOne(cascade= CascadeType.ALL, fetch=FetchType.EAGER)
    private PlayerEntity playerBlack;

    /**
     * By default, white starts!
     */
    private String currentTurn = ColorPawn.WHITE.toString();

    public GameEntity() {

    }

    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<TurnEntity> getTurnEntities() {
        return turnEntities;
    }

    public ColorPawn getCurrentTurn() {
        return ColorPawn.valueOf(currentTurn);
    }

    public void setCurrentTurn(ColorPawn colour) {
        currentTurn = colour.toString();
    }

    public PlayerEntity getPlayerBlack() {
        return playerBlack;
    }

    public PlayerEntity getPlayerWhite() {
        return playerWhite;
    }

    public void setPlayerBlack(PlayerEntity userBlack) {
        this.playerBlack = userBlack;
    }

    public void setPlayerWhite(PlayerEntity userWhite) {
        this.playerWhite = userWhite;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
