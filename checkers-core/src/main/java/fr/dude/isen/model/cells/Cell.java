package fr.dude.isen.model.cells;

import fr.dude.isen.model.pawns.Pawn;
import fr.dude.isen.model.pawns.Position;
import fr.dude.isen.model.serializable.SerializableCell;

/**
 * Class that represents a cell.
 */
public class Cell implements SerializableCell {

    /**
     * Cell's color
     */
    private ColorCell color;

    /**
     * Cell's position
     */
    private Position position;

    /**
     * Set if a pawn is on the cell. Null otherwise.
     */
    private Pawn pawn;

    public Cell(int column, int row) {
        this.position = new Position(row, column);
        // According to the row and column indexes, we can infer the cell's color.
        this.color = (((row + (column % 2 == 0 ? 0 : 1)) % 2) == 0) ? ColorCell.DARK : ColorCell.LIGHT;
    }

    /**
     * @return true if there is a pawn on the cell. False otherwise.
     */
    public boolean hasPawn() {
        return this.pawn != null;
    }

    /**
     * @param other An opponent pawn
     * @return true if there is an opponent pawn on the cell according the pawn given in parameter. False otherwise.
     */
    public boolean hasOpponentPawn(Pawn other) {
        return hasPawn() && getPawn().getColor() != other.getColor();
    }

    @Override
    public Pawn getPawn() {
        return pawn;
    }

    public void setPawn(Pawn pawn) {
        this.pawn = pawn;
    }

    @Override
    public ColorCell getColor() {
        return color;
    }

    public void setColor(ColorCell color) {
        this.color = color;
    }

    @Override
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
