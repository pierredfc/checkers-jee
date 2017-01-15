package fr.dude.isen;

import fr.dude.isen.model.*;
import fr.dude.isen.model.pawns.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
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
        logger.info("[BoardTest][isBoardInitialized] BEGIN");
        assertThat(board.getCells()).isNotNull();
        assertThat(board.getCells().getNbRows()).isEqualTo(nbRows);
        for (int i = 0; i < nbRows; i++) {
            assertThat(board.getCells().getColumn(i).size()).isEqualTo(nbColumns);
        }

        logger.info("[BoardTest][isBoardInitialized] Board size =  " + nbRows + "x" + nbColumns);
        logger.info("[BoardTest][isBoardInitialized] END");
    }

    @Test
    public void isBoardColorsOk() throws Exception {
        logger.info("[BoardTest][isBoardColorsOk] BEGIN");
        Cells cells = board.getCells();

        for (int x = 0; x < nbRows; x++) {
            List<Cell> row = cells.getColumn(x);
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
        logger.info("[BoardTest][isBoardColorsOk] END");
    }

    @Test
    public void isUsersCellsOk() {
        logger.info("[BoardTest][isUsersCellsOk] BEGIN");
        this.isUserCellsOk(this.board.getUserWhite());
        this.isUserCellsOk(this.board.getUserBlack());
        logger.info("[BoardTest][isUsersCellsOk] END");
    }

    private void isUserCellsOk(User user) {
        assertThat(user.getPawns()).isNotNull();
        assertThat(user.getPawns().size()).isEqualTo(this.nbPawnRows * this.nbColumns/2);
    }

    @Test
    public void isPawnsWellPlaced() {
        logger.info("[BoardTest][isPawnsWellPlaced] BEGIN");
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
        logger.info("[BoardTest][isPawnsWellPlaced] END");
    }

    @Test
    public void canPawnsMoveWell() {
        logger.info("[BoardTest][canPawnsMoveWell] BEGIN");
        Cells cells = this.board.getCells();

        //Centered
        Pawn centeredPawn = cells.get(5, 6).getCurrentPawn();
        testPossibleMoves(centeredPawn, 2, new Position(4,5), new Position(6,5));

        //Bordered - Only 1 move
        Pawn borderedPawn = this.board.getCells().get(9,6).getCurrentPawn();
        testPossibleMoves(borderedPawn, 1, new Position(8,5));

        //Blocked - 0 moves
        Pawn blockedPawn = this.board.getCells().get(0,9).getCurrentPawn();
        testPossibleMoves(blockedPawn, 0);

        logger.info("[BoardTest][canPawnsMoveWell] END");
    }

    private void testPossibleMoves(Pawn pawn, int nbMoves, Position... positions) {
        List<Move> moves = pawn.getPossibleMoves(this.board.getCells());
        assertThat(moves).isNotNull();
        logger.info("[BoardTest][canPawnsMoveWell][testPossibleMoves] Pawn at " + pawn.getCell().getPosition().toString() + " has " + moves.size() + " possible move(s).");
        assertThat(moves.size()).isEqualTo(nbMoves);

        for(Move move : moves) {
            logger.info("[BoardTest][canPawnsMoveWell][testPossibleMoves] " + move.getDestination().getPosition());
            Position position = move.getDestination().getPosition();
            assertThat(position).isIn(positions);
        }
    }

    @Test
    public void areKilledPawnsRemoved() {
        logger.info("[BoardTest][areKilledPawnsRemoved] BEGIN");
        Pawn pawn1 = this.board.getCells().get(1, 6).getCurrentPawn();
        Pawn pawn2 = this.board.getCells().get(4, 3).getCurrentPawn();

        this.draw();

        move(1,6,2,5, 2, false);
        move(4,3,3,4, 2, false);
        move(0,3,1,4,1, false);
        move(2,5,4,3, 2, true);

        assertThat(pawn2.getCell()).isNull();
        assertThat(pawn1.getCell().getPosition()).isEqualTo(new Position(4,3));
        logger.info("[BoardTest][areKilledPawnsRemoved] END");
    }

    @Test
    public void makeQueen()
    {
        logger.info("[BoardTest][makeQueen] BEGIN");
        move(1,6,0,5,2, false);
        move(0,3,1,4,1, false);
        move(2,3,3,4,1, false);
        move(1,2,0,3,2,false);
        move(0,1,1,2,1, false);
        move(0,5,2,3, 1, true);
        move(2,3,0,1,1,true);
        move(2,1,1,2,1,false);
        move(1,0,2,1,1, false);
        move(0,1,1,0, 1, false);

        Pawn queen = this.board.getCell(1,0).getCurrentPawn();

        assertThat(queen).isNotNull();
        assertThat(queen.getDirection()).isEqualTo(Direction.BOTH);

        logger.info("[BoardTest][makeQueen] END");
    }

    private void move(int col, int row, int destCol, int destRow, int nbMoves, boolean hasMandatory) {
        Pawn pawn = this.board.getCell(col, row).getCurrentPawn();
        Move move = getMove(pawn, destCol, destRow, nbMoves, hasMandatory);
        if (move == null)
        {
            logger.info("[BoardTest][areKilledPawnsRemoved][move] No move possible.");
            return;
        }
        logger.info("[BoardTest][areKilledPawnsRemoved][move] Pawn at " + pawn.getCell().getPosition() + " can go at " + move.getDestination().getPosition());

        this.board.movePawn(pawn, move.getDestination());
        this.draw();
    }

    private Move getMove(Pawn pawn, int destCol, int destRow, int nbMoves, boolean hasMandatory) {
        List<Move> moves = pawn.getPossibleMoves(this.board.getCells());
        assertThat(moves.size()).isEqualTo(nbMoves);

        for(Move move : moves) {
            assertThat(move.isMandatory()).isEqualTo(hasMandatory);

            Position position = move.getDestination().getPosition();
            if (position.getRowIndex().equals(destRow) && position.getColumnIndex().equals(destCol)) {
                return move;
            }
        }
        return null;
    }

    private void draw()
    {
        int size = this.board.getCells().getNbRows();

        for (int row = 0; row < size; row++)
        {
            String rowPawns = "";
            for (int column = 0; column < size; column++)
            {
                Cell cell = this.board.getCell(column, row);
                Pawn currentPawn = cell.getCurrentPawn();

                if (cell.hasPawn())
                {
                    if (currentPawn.getDirection().equals(Direction.BOTH))
                    {
                        // Queen
                        // Not Queen
                        if (currentPawn.getColor().equals(ColorPawn.BLACK))
                        {
                            rowPawns += "X";
                        }
                        else
                        {
                            rowPawns += "O";
                        }
                    }
                    else
                    {
                        // Not Queen
                        if (currentPawn.getColor().equals(ColorPawn.BLACK))
                        {
                            rowPawns += "x";
                        }
                        else
                        {
                            rowPawns += "o";
                        }
                    }
                }
                else
                {
                    rowPawns += ".";
                }
            }

            logger.info(rowPawns);
        }
    }
}
