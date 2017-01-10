package fr.dude.isen.model.pawns;

import fr.dude.isen.model.Cell;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by pierredfc on 09/01/2017.
 */
public abstract class Pawn {

    private ColorPawn color;

    private Direction direction;

    private Cell cell;

    public Pawn(ColorPawn color, Direction direction) {
        this.color = color;
        this.direction = direction;
    }

    public abstract Move move(Cell cell, List<List<Cell>> cells);

    public ColorPawn getColor() {
        return color;
    }

    public void setColor(ColorPawn color) {
        this.color = color;
    }

    public void setCell(Cell cell) {
        if (this.cell != null) {
            this.cell.setCurrentPawn(null);
        }
        if (cell != null) {
            cell.setCurrentPawn(this);
        } // else : Pawn removed from board
        this.cell = cell;
    }

    public Direction getDirection() {
        return direction;
    }

    private Position getPosition() {
        return this.cell != null ? this.cell.getPosition() : null;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Cell getCell() {
        return cell;
    }

    public List<Move> getPossibleMoves(List<List<Cell>> cells) {
        Cell current = this.getCell();
        Position position = current.getPosition();
        int col = position.getColumnIndex();
        int row = position.getRowIndex();

        List<Move> result = new ArrayList<>(2);

        Direction direction = getDirection();
        boolean up = direction == Direction.UP;
        boolean down = direction == Direction.DOWN;
        boolean both = direction == Direction.BOTH;

        boolean hasMandatoryMoves = false;

        if (up || both) {
            hasMandatoryMoves = tryAddMove(col-1, row+1, cells, result)  || hasMandatoryMoves;
            hasMandatoryMoves = tryAddMove(col+1, row+1, cells, result) || hasMandatoryMoves;
        }

        if (down || both) {
            hasMandatoryMoves = tryAddMove(col-1, row-1, cells, result)  || hasMandatoryMoves;
            hasMandatoryMoves = tryAddMove(col+1, row-1, cells, result) || hasMandatoryMoves;
        }

        if (hasMandatoryMoves) {
            List<Move> mandatoryMoves = new ArrayList<>(2);
            for(Move move : result) {
                if (move.isMandatory()) {
                    mandatoryMoves.add(move);
                }
            }
            result = mandatoryMoves;
        }

        return result;
    }

    /**
     *
     * @param col
     * @param row
     * @param cells
     * @param result true if this move is mandatory
     * @return
     */
    private boolean tryAddMove(int col, int row, List<List<Cell>> cells, List<Move> result) {
        boolean isMandatory = false;
        try {
            Cell cell = cells.get(col).get(row);
            if (!cell.hasPawn()) {
                result.add(new Move(cell));
            }
            else if (cell.hasOpponentPawn(this)) {
                //Check for 2-step cell
                Position current = this.getPosition();
                int currentCol = current.getColumnIndex();
                int currentRow = current.getRowIndex();

                //col+2 && row+2
                int col2 = currentCol + (col - currentCol)*2;
                int row2 = currentRow + (row - currentRow)*2;
                Cell cell2 = cells.get(col2).get(row2);

                if (!cell2.hasPawn()) {
                    result.add(new Move(cell2, cell.getCurrentPawn()));
                    isMandatory = true;
                }
            }
        }
        catch(IndexOutOfBoundsException ex) {
            //
        }
        return isMandatory;
    }
}
