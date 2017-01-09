package fr.dude.isen;

import fr.dude.isen.model.Board;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by pierredfc on 09/01/2017.
 */
public class BoardTest {

    private Board board;

    @Before
    public void doBefore() throws Exception {
        this.board = new Board();
    }

    @Test
    public void isBoardInitialized() throws Exception {
        assertThat(board.getCells()).isNotNull();
        assertThat(board.getCells().size()).isEqualTo(10);
    }
}
