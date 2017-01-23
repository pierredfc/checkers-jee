package fr.dude.isen;

import fr.dude.isen.model.*;
import fr.dude.isen.model.pawns.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

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
            assertThat(board.getCells().getRow(i).size()).isEqualTo(nbColumns);
        }

        logger.info("[BoardTest][isBoardInitialized] Board size =  " + nbRows + "x" + nbColumns);
        logger.info("[BoardTest][isBoardInitialized] END");
    }

    @Test
    public void isBoardColorsOk() throws Exception {
        logger.info("[BoardTest][isBoardColorsOk] BEGIN");
        Cells cells = board.getCells();

        for (int x = 0; x < nbRows; x++) {
            List<Cell> row = cells.getRow(x);
            for (int y = 0; y < nbColumns; y++) {
                Cell cell = row.get(y);
                ColorCell color;
                if (x % 2 == 0) {
                    color = y % 2 == 0 ? ColorCell.LIGHT : ColorCell.DARK;
                } else {
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
        assertThat(user.getNbPawns()).isEqualTo(this.nbPawnRows * this.nbColumns / 2);
    }

    @Test
    public void checkBottomLeftColorCell() {
        // Must be dark
        assertThat(this.board.getCell(0, 0).getColor()).isEqualTo(ColorCell.DARK);
    }

    @Test
    public void isPawnsWellPlaced() {
        logger.info("[BoardTest][isPawnsWellPlaced] BEGIN");
        for (int row = 0; row < nbRows; row++) {
            for (int column = 0; column < nbColumns; column++) {
                Cell currentCell = this.board.getCell(column, row);
                Pawn currentPawn = currentCell.getPawn();

                if (currentPawn != null) {
                    assertThat(currentCell.getColor()).isEqualTo(ColorCell.DARK);
                    assertThat(currentCell.getPosition().getRow()).isNotEqualTo(4);
                    assertThat(currentCell.getPosition().getRow()).isNotEqualTo(5);
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
        Cell centeredPawnCell = cells.get(3, 5);
        testPossibleMoves(centeredPawnCell, 2, new Position(4, 4), new Position(4, 6));

        //Bordered - Only 1 move
        Cell borderedPawnCell = this.board.getCells().get(3, 9);
        testPossibleMoves(borderedPawnCell, 1, new Position(4, 8));

        //Blocked - 0 moves
        Cell blockedPawnCell = this.board.getCells().get(0, 0);
        testPossibleMoves(blockedPawnCell, 0);

        logger.info("[BoardTest][canPawnsMoveWell] END");
    }

    private void testPossibleMoves(Cell pawnCell, int nbMoves, Position... positions) {
        List<Move> moves = this.board.getBoardManager().getPossibleMoves(pawnCell);
        assertThat(moves).isNotNull();
        logger.info("[BoardTest][canPawnsMoveWell][testPossibleMoves] Pawn at " + pawnCell.getPosition().toString() + " has " + moves.size() + " possible move(s).");
        assertThat(moves.size()).isEqualTo(nbMoves);

        for (Move move : moves) {
            logger.info("[BoardTest][canPawnsMoveWell][testPossibleMoves] " + move.getDestination().getPosition());
            Position position = move.getDestination().getPosition();
            assertThat(position).isIn(positions);
        }
    }

    @Test
    public void areKilledPawnsRemoved() {
        logger.info("[BoardTest][areKilledPawnsRemoved] BEGIN");
        Cell cell_3_1 = this.board.getCells().get(3, 1);
        Pawn pawn1 = cell_3_1.getPawn();
        Cell cell_6_4 = this.board.getCells().get(6, 4);
        Pawn pawn2 = cell_6_4.getPawn();

        this.draw();

        move(3, 1, 4, 2, 2, false);
        move(6, 4, 5, 3, 2, false);
        move(6, 0, 5, 1, 1, false);
        move(4, 2, 6, 4, 2, true);

        assertThat(cell_6_4.getPawn()).isEqualTo(pawn1);
        assertThat(cell_3_1.getPawn()).isNull();
        logger.info("[BoardTest][areKilledPawnsRemoved] END");
    }

    @Test
    public void makeQueen() {
        logger.info("[BoardTest][makeQueen] BEGIN");
        move(3, 1, 4, 0, 2, false);
        move(6, 0, 5, 1, 1, false);
        move(6, 2, 5, 3, 1, false);
        move(7, 1, 6, 0, 2, false);
        move(8, 0, 7, 1, 1, false);
        move(4, 0, 6, 2, 1, true);
        move(6, 2, 8, 0, 1, true);
        move(8, 2, 7, 1, 1, false);
        move(9, 1, 8, 2, 1, false);
        move(8, 0, 9, 1, 1, false);

        Pawn queen = this.board.getCell(1, 9).getPawn();

        assertThat(queen).isNotNull();
        assertThat(queen.getDirection()).isEqualTo(Direction.QUEEN);

        //9:1 --> 8:0
        //8:0 --> 6:2
        move(9,1,8,0,1,false);
        move(8,0,6,2,1,true);



        logger.info("[BoardTest][makeQueen] END");
    }

    private void move(int row, int col, int destRow, int destCol, int nbMoves, boolean hasMandatory) {
        Cell pawnCell = this.board.getCell(col, row);
        Move move = getMove(pawnCell, destCol, destRow, nbMoves, hasMandatory);
        if (move == null) {
            logger.info("[BoardTest][areKilledPawnsRemoved][move] No move possible.");
            return;
        }
        logger.info("[BoardTest][areKilledPawnsRemoved][move] Pawn at " + pawnCell.getPosition() + " can go at " + move.getDestination().getPosition());

        this.board.movePawn(pawnCell, move.getDestination());
        this.draw();
    }

    private Move getMove(Cell pawnCell, int destCol, int destRow, int nbMoves, boolean hasMandatory) {
        List<Move> moves = this.board.getBoardManager().getPossibleMoves(pawnCell);
        assertThat(moves.size()).isEqualTo(nbMoves);

        for (Move move : moves) {
            assertThat(move.isMandatory()).isEqualTo(hasMandatory);

            Position position = move.getDestination().getPosition();
            if (position.getRow().equals(destRow) && position.getColumn().equals(destCol)) {
                return move;
            }
        }
        return null;
    }

    private void draw() {
        int size = this.board.getCells().getNbRows();

        for (int row = size - 1; row >= 0; row--) {
            String rowPawns = "";
            for (int column = 0; column < size; column++) {
                Cell cell = this.board.getCell(column, row);
                Pawn currentPawn = cell.getPawn();

                if (cell.hasPawn()) {
                    if (currentPawn.getDirection().equals(Direction.QUEEN)) {
                        // Queen
                        // Not Queen
                        if (currentPawn.getColor().equals(ColorPawn.BLACK)) {
                            rowPawns += "X";
                        } else {
                            rowPawns += "O";
                        }
                    } else {
                        // Not Queen
                        if (currentPawn.getColor().equals(ColorPawn.BLACK)) {
                            rowPawns += "x";
                        } else {
                            rowPawns += "o";
                        }
                    }
                } else {
                    rowPawns += ".";
                }
            }

            logger.info(rowPawns);
        }
    }
}
