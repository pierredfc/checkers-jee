package fr.dude.isen.model.pawns;

/**
 * Created by pierredfc on 09/01/2017.
 */
public class Position {

    /**
     * Indicates column index
     */
    private Integer column;

    /**
     * Indicates row index
     */
    private Integer row;

    public Position(int rowIndex, int columnIndex)
    {
        this.column = columnIndex;
        this.row = rowIndex;
    }

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

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
