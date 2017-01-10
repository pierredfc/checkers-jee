package fr.dude.isen.model.pawns;

/**
 * Created by pierredfc on 09/01/2017.
 */
public class Position {

    /**
     * Indicates column index
     */
    private Integer columnIndex;

    /**
     * Indicates row index
     */
    private Integer rowIndex;

    public Position(int columnIndex, int rowIndex)
    {
        this.columnIndex = columnIndex;
        this.rowIndex = rowIndex;
    }

    public Integer getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(Integer columnIndex) {
        this.columnIndex = columnIndex;
    }

    public Integer getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(Integer rowIndex) {
        this.rowIndex = rowIndex;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Position) {
            Position other = (Position) obj;
            return other.getColumnIndex().equals(this.getColumnIndex()) && other.getRowIndex().equals(this.getRowIndex());
        }
        return super.equals(obj);
    }
}
