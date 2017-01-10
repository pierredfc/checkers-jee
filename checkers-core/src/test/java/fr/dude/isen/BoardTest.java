package fr.dude.isen;

import fr.dude.isen.model.Board;
import fr.dude.isen.model.Cell;
import fr.dude.isen.model.ColorCell;
import fr.dude.isen.model.pawns.Move;
import fr.dude.isen.model.pawns.Pawn;
import fr.dude.isen.model.pawns.Position;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import fr.dude.isen.model.User;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by pierredfc on 09/01/2017.
 */
public class BoardTest {

    private static Logger logger = LogManager.getLogger(BoardTest.class);

    private int nbColumns = 10;
    private int nbRows = 10;
    private int nbPawnRows = 4;
    private Board board;

    @Before
    public void doBefore() throws Exception {
        this.board = new Board(nbRows, nbColumns, nbPawnRows);
    }

    @Test
    public void isBoardInitialized() throws Exception {
        assertThat(board.getCells()).isNotNull();
        assertThat(board.getCells().size()).isEqualTo(nbRows);
        for (int i = 0; i < nbRows; i++) {
            assertThat(board.getCells().get(i).size()).isEqualTo(nbColumns);
        }

        logger.info("[BoardTest][isBoardInitialized] Board size =  " + nbRows + "x" + nbColumns);
    }

    @Test
    public void isBoardColorsOk() throws Exception {
        List<List<Cell>> cells = board.getCells();

        for (int x = 0; x < nbRows; x++) {
            List<Cell> row = cells.get(x);
            for(int y = 0; y < nbColumns; y++) {
                Cell cell = row.get(y);
                ColorCell color;
                if (x % 2 == 0) {
                    color = y % 2 == 0 ? ColorCell.LIGHT : ColorCell.DARK;
                }
                else {
                    color = y % 2 == 0 ? ColorCell.DARK : ColorCell.LIGHT;
                }
                assertThat(cell.getColor()).isEqualTo(color);
            }
        }
    }

    @Test
    public void isUsersCellsOk() {
        this.isUserCellsOk(this.board.getUserWhite());
        this.isUserCellsOk(this.board.getUserBlack());
    }

    private void isUserCellsOk(User user) {
        assertThat(user.getPawns()).isNotNull();
        assertThat(user.getPawns().size()).isEqualTo(this.nbPawnRows * this.nbColumns/2);
    }

    @Test
    public void isPawnsWellPlaced() {
        for (int row = 0; row < nbRows; row++)
        {
            for (int column = 0; column < nbColumns; column++)
            {
                Cell currentCell = this.board.getCell(column, row);
                Pawn currentPawn = currentCell.getCurrentPawn();

                if (currentPawn != null)
                {
                    assertThat(currentCell).isEqualTo(currentPawn.getCell());
                    assertThat(currentCell.getColor()).isEqualTo(ColorCell.DARK);
                    assertThat(currentCell.getPosition().getRowIndex()).isNotEqualTo(4);
                    assertThat(currentCell.getPosition().getRowIndex()).isNotEqualTo(5);
                }
            }
            System.out.println();
        }
    }

    @Test
    public void canPawnsMoveWell() {
        List<List<Cell>> cells = this.board.getCells();

        //Centered
        Pawn centeredPawn = cells.get(5).get(6).getCurrentPawn();
        testPossibleMoves(centeredPawn, 2, new Position(4,5), new Position(6,5));

        //Bordered - Only 1 move
        Pawn borderedPawn = this.board.getCells().get(9).get(6).getCurrentPawn();
        testPossibleMoves(borderedPawn, 1, new Position(8,5));

        //Blocked - 0 moves
        Pawn blockedPawn = this.board.getCells().get(0).get(9).getCurrentPawn();
        testPossibleMoves(blockedPawn, 0);
    }

    private void testPossibleMoves(Pawn pawn, int nbMoves, Position... positions) {
        List<Move> moves = pawn.getPossibleMoves(this.board.getCells());
        assertThat(moves).isNotNull();
        assertThat(moves.size()).isEqualTo(nbMoves);

        for(Move move : moves) {
            Position position = move.getDestination().getPosition();
            assertThat(position).isIn(positions);
        }
    }

    @Test
    public void areKilledPawnsRemoved() {
        Pawn pawn1 = this.board.getCells().get(1).get(6).getCurrentPawn();
        Pawn pawn2 = this.board.getCells().get(4).get(3).getCurrentPawn();

        move(1,6,2,5);
        move(4,3,3,4);
        move(2,5,4,3);

        assertThat(pawn2.getCell()).isNull();
        assertThat(pawn1.getCell().getPosition()).isEqualTo(new Position(4,3));
    }

    private void move(int col, int row, int destCol, int destRow) {
        Pawn pawn = this.board.getCells().get(col).get(row).getCurrentPawn();
        Move move = getMove(pawn, destCol, destRow);
        if (move == null) return;
        this.board.movePawn(pawn, move.getDestination());
    }

    private Move getMove(Pawn pawn, int destCol, int destRow) {
        List<Move> moves = pawn.getPossibleMoves(this.board.getCells());
        for(Move move : moves) {
            Position position = move.getDestination().getPosition();
            if (position.getRowIndex().equals(destRow) && position.getColumnIndex().equals(destCol)) {
                return move;
            }
        }
        return null;
    }
}
