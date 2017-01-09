package fr.dude.isen;

import fr.dude.isen.model.Board;
import fr.dude.isen.model.Cell;
import fr.dude.isen.model.ColorCell;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import fr.dude.isen.model.User;
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
    private int nbPawns = 20;
    private Board board;

    @Before
    public void doBefore() throws Exception {
        this.board = new Board(nbRows, nbColumns, nbPawns);
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
        this.isUserCellsOk(this.board.getUser1());
        this.isUserCellsOk(this.board.getUser2());
    }

    private void isUserCellsOk(User user) {
        assertThat(user.getPawns()).isNotNull();
        assertThat(user.getPawns().size()).isEqualTo(this.nbPawns);
    }

    @Test
    public void isPawnsWellPlaced() {
        for(List<Cell> row : this.board.getCells()) {
            for(Cell cell : row) {
                System.out.print(cell.getCurrentPawn() != null ? "o" : ".");
                if (cell.getCurrentPawn() != null) {
                    assertThat(cell).isEqualTo(cell.getCurrentPawn().getCell());
                    assertThat(cell.getColor()).isEqualTo(ColorCell.DARK);
                }
            }
            System.out.println();
        }
    }


}
