package fr.dude.isen.model;

import fr.dude.isen.model.pawns.Pawn;

/**
 * Created by pierredfc on 09/01/2017.
 */
public class Cell {

    private ColorCell color;

    private Pawn currentPawn;

    public Cell(int x, int y)
    {
        this.color = (((y + (x % 2 == 0? 0:1)) % 2) == 0) ? ColorCell.LIGHT : ColorCell.DARK;
    }

    public Pawn getCurrentPawn() {
        return currentPawn;
    }

    public void setCurrentPawn(Pawn currentPawn) {
        this.currentPawn = currentPawn;
    }

    public ColorCell getColor() {
        return color;
    }

    public void setColor(ColorCell color) {
        this.color = color;
    }
}
