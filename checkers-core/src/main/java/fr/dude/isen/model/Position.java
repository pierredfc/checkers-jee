package fr.dude.isen.model;

/**
 * Created by pierredfc on 09/01/2017.
 */
public class Position {

    private Integer x;

    private Integer y;

    public Position(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }
}
