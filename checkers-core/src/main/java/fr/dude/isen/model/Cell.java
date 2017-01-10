package fr.dude.isen.model;

import fr.dude.isen.model.pawns.Pawn;
import fr.dude.isen.model.pawns.Position;

import java.util.List;

/**
 * Created by pierredfc on 09/01/2017.
 */
public class Cell {

    private ColorCell color;

    private Position position;

    private Pawn currentPawn;

    public Cell(int column, int row) {
        this.position = new Position(column, row);
        this.color = (((row + (column % 2 == 0 ? 0 : 1)) % 2) == 0) ? ColorCell.LIGHT : ColorCell.DARK;
    }

    public boolean hasPawn() {
        return this.currentPawn != null;
    }

    public boolean hasOpponentPawn(Pawn other) {
        return hasPawn() && getCurrentPawn().getColor() != other.getColor();
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

    public Position getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Cell) {
            Cell other = (Cell) obj;
            return this.getPosition().equals(other.getPosition());
        }
        return super.equals(obj);
    }
}
