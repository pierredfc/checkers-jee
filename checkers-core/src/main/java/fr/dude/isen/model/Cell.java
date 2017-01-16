package fr.dude.isen.model;

import fr.dude.isen.model.pawns.Pawn;
import fr.dude.isen.model.pawns.Position;

/**
 * Created by pierredfc on 09/01/2017.
 */
public class Cell {

    private ColorCell color;

    private Position position;

    private Pawn pawn;

    public Cell(int column, int row) {
        this.position = new Position(row, column);
        this.color = (((row + (column % 2 == 0 ? 0 : 1)) % 2) == 0) ? ColorCell.DARK : ColorCell.LIGHT;
    }

    public boolean hasPawn() {
        return this.pawn != null;
    }

    public boolean hasOpponentPawn(Pawn other) {
        return hasPawn() && getPawn().getColor() != other.getColor();
    }

    public Pawn getPawn() {
        return pawn;
    }

    public void setPawn(Pawn pawn) {
        this.pawn = pawn;
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
