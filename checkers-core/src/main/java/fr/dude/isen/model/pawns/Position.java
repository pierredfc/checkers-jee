package fr.dude.isen.model.pawns;

import fr.dude.isen.model.serializable.SerializablePosition;

/**
 * Class that represents a position on the board.
 */
public class Position implements SerializablePosition {

    /**
     * Indicates column index
     */
    private Integer column;

    /**
     * Indicates row index
     */
    private Integer row;

    public Position() {
        //Deserialization
    }

    public Position(int rowIndex, int columnIndex)
    {
        this.column = columnIndex;
        this.row = rowIndex;
    }

    @Override
    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    @Override
    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Position) {
            Position other = (Position) obj;
            return other.getColumn().equals(this.getColumn()) && other.getRow().equals(this.getRow());
        }
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return new StringBuilder().append("(").append(column).append(",").append(row).append(")").toString();
    }

    public Position translate(Position direction, int step) {
        return new Position(this.row + direction.row *step, this.column + direction.column *step);
    }
}
