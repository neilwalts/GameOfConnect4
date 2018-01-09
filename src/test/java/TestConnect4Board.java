import neil.kata.connect4.Connect4Board;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class TestConnect4Board {

    private static final int YELLOW_TOKEN = 1;
    private static final int RED_TOKEN = 2;

    Connect4Board connect4Board = new Connect4Board();

    @Before
    public void beforeBoardGame() {
        connect4Board = new Connect4Board();
        assertThat(connect4Board, hasProperty("winner", is("")));
        assertThat(connect4Board, hasProperty("currentRow", is(-1)));
        assertThat(connect4Board, hasProperty("currentColumn", is(-1)));
    }

    @Test
    public void checkBoardRegistersTheRightColumnsAndRowsAfterMoves() {
        connect4Board.playersMove(YELLOW_TOKEN, 0);
        assertThat(connect4Board, hasProperty("currentColumn", is(0)));
        assertThat(connect4Board, hasProperty("currentRow", is(0)));
        connect4Board.playersMove(RED_TOKEN, 0);
        assertThat(connect4Board, hasProperty("currentColumn", is(0)));
        assertThat(connect4Board, hasProperty("currentRow", is(1)));
        connect4Board.playersMove(YELLOW_TOKEN, 1);
        assertThat(connect4Board, hasProperty("currentColumn", is(1)));
        assertThat(connect4Board, hasProperty("currentRow", is(0)));
        connect4Board.playersMove(RED_TOKEN, 6);
        assertThat(connect4Board, hasProperty("currentColumn", is(6)));
        assertThat(connect4Board, hasProperty("currentRow", is(0)));
    }

    @Test
    public void testBasicHorizontalWinForRed() {
        connect4Board.playersMove(RED_TOKEN, 0);
        connect4Board.playersMove(RED_TOKEN, 1);
        connect4Board.playersMove(RED_TOKEN, 2);
        connect4Board.playersMove(RED_TOKEN, 3);

        assertThat(connect4Board, hasProperty("winner", is("RED (" + RED_TOKEN + ") wins")));
    }

    @Test
    public void testBasicHorizontalWinForYellowInset() {
        connect4Board.playersMove(YELLOW_TOKEN, 1);
        connect4Board.playersMove(YELLOW_TOKEN, 2);
        connect4Board.playersMove(YELLOW_TOKEN, 3);
        connect4Board.playersMove(YELLOW_TOKEN, 4);

        assertThat(connect4Board, hasProperty("winner", is("YELLOW (" + YELLOW_TOKEN + ") wins")));
    }

    @Test
    public void testBasicHorizontalWinForRedNotWinning() {
        connect4Board.playersMove(RED_TOKEN, 1);
        connect4Board.playersMove(RED_TOKEN, 2);
        connect4Board.playersMove(RED_TOKEN, 3);
        connect4Board.playersMove(RED_TOKEN, 5);

        assertThat(connect4Board, hasProperty("winner", is("")));
    }

    @Test
    public void testVerticalWinForYellow() {
        connect4Board.playersMove(YELLOW_TOKEN, 0);
        connect4Board.playersMove(YELLOW_TOKEN, 0);
        connect4Board.playersMove(YELLOW_TOKEN, 0);
        connect4Board.playersMove(YELLOW_TOKEN, 0);

        assertThat(connect4Board, hasProperty("currentColumn", is(0)));
        assertThat(connect4Board, hasProperty("currentRow", is(3)));
        assertThat(connect4Board, hasProperty("winner", is("YELLOW (" + YELLOW_TOKEN + ") wins")));
    }


    @Test
    public void testCheckForWinnerDiagonalDownRightFromLeftBorder() {
        connect4Board.playersMove(RED_TOKEN, 0);
        connect4Board.playersMove(RED_TOKEN, 0);
        connect4Board.playersMove(RED_TOKEN, 0);
        connect4Board.playersMove(YELLOW_TOKEN, 0);

        connect4Board.playersMove(YELLOW_TOKEN, 1);
        connect4Board.playersMove(RED_TOKEN, 1);
        connect4Board.playersMove(YELLOW_TOKEN, 1);
        connect4Board.playersMove(YELLOW_TOKEN, 1);

        connect4Board.playersMove(RED_TOKEN, 2);
        connect4Board.playersMove(YELLOW_TOKEN, 2);
        connect4Board.playersMove(RED_TOKEN, 2);
        connect4Board.playersMove(RED_TOKEN, 2);

        connect4Board.playersMove(YELLOW_TOKEN, 3);

        assertThat(connect4Board, hasProperty("winner", is("YELLOW (" + YELLOW_TOKEN + ") wins")));
    }

    @Test
    public void testCheckForWinnerDiagonalDownRightToRightBorder() {

        connect4Board.playersMove(YELLOW_TOKEN, 6);
        connect4Board.playersMove(YELLOW_TOKEN, 6);
        connect4Board.playersMove(RED_TOKEN, 6);
        connect4Board.playersMove(YELLOW_TOKEN, 6);

        connect4Board.playersMove(RED_TOKEN, 5);
        connect4Board.playersMove(YELLOW_TOKEN, 5);
        connect4Board.playersMove(RED_TOKEN, 5);
        connect4Board.playersMove(RED_TOKEN, 5);

        connect4Board.playersMove(YELLOW_TOKEN, 4);
        connect4Board.playersMove(RED_TOKEN, 4);
        connect4Board.playersMove(YELLOW_TOKEN, 4);
        connect4Board.playersMove(YELLOW_TOKEN, 4);

        connect4Board.playersMove(RED_TOKEN, 3);
        connect4Board.playersMove(RED_TOKEN, 3);
        connect4Board.playersMove(RED_TOKEN, 3);
        connect4Board.playersMove(YELLOW_TOKEN, 3);

        assertThat(connect4Board, hasProperty("winner", is("YELLOW (" + YELLOW_TOKEN + ") wins")));
    }


    @Test
    public void testCheckForWinnerDiagonalUpRightFromLeftBorder() {
        connect4Board.playersMove(RED_TOKEN, 0);
        connect4Board.playersMove(RED_TOKEN, 0);
        connect4Board.playersMove(RED_TOKEN, 0);
        connect4Board.playersMove(YELLOW_TOKEN, 0);

        connect4Board.playersMove(YELLOW_TOKEN, 1);
        connect4Board.playersMove(RED_TOKEN, 1);
        connect4Board.playersMove(YELLOW_TOKEN, 1);
        connect4Board.playersMove(YELLOW_TOKEN, 1);

        connect4Board.playersMove(RED_TOKEN, 2);
        connect4Board.playersMove(YELLOW_TOKEN, 2);
        connect4Board.playersMove(RED_TOKEN, 2);
        connect4Board.playersMove(RED_TOKEN, 2);

        connect4Board.playersMove(YELLOW_TOKEN, 3);

        assertThat(connect4Board, hasProperty("winner", is("YELLOW (" + YELLOW_TOKEN + ") wins")));
    }

    @Test
    public void testCheckForWinnerDiagonalUpRightToRightBorder() {
        connect4Board.playersMove(YELLOW_TOKEN, 6);

        connect4Board.playersMove(RED_TOKEN, 5);
        connect4Board.playersMove(YELLOW_TOKEN, 5);
        connect4Board.playersMove(RED_TOKEN, 5);
        connect4Board.playersMove(RED_TOKEN, 5);

        connect4Board.playersMove(YELLOW_TOKEN, 4);
        connect4Board.playersMove(RED_TOKEN, 4);
        connect4Board.playersMove(YELLOW_TOKEN, 4);
        connect4Board.playersMove(YELLOW_TOKEN, 4);

        connect4Board.playersMove(RED_TOKEN, 3);
        connect4Board.playersMove(RED_TOKEN, 3);
        connect4Board.playersMove(RED_TOKEN, 3);
        connect4Board.playersMove(YELLOW_TOKEN, 3);

        assertThat(connect4Board, hasProperty("winner", is("YELLOW (" + YELLOW_TOKEN + ") wins")));
    }

    @Test
    public void testCheckForWinnerDiagonalDownFromCol3Row5() {
        connect4Board.playersMove(RED_TOKEN, 0);
        connect4Board.playersMove(RED_TOKEN, 0);
        connect4Board.playersMove(RED_TOKEN, 0);

        connect4Board.playersMove(YELLOW_TOKEN, 1);
        connect4Board.playersMove(YELLOW_TOKEN, 1);
        connect4Board.playersMove(YELLOW_TOKEN, 1);
        connect4Board.playersMove(RED_TOKEN, 1);

        connect4Board.playersMove(YELLOW_TOKEN, 2);
        connect4Board.playersMove(RED_TOKEN, 2);
        connect4Board.playersMove(YELLOW_TOKEN, 2);
        connect4Board.playersMove(YELLOW_TOKEN, 2);
        connect4Board.playersMove(RED_TOKEN, 2);

        connect4Board.playersMove(YELLOW_TOKEN, 3);
        connect4Board.playersMove(RED_TOKEN, 3);
        connect4Board.playersMove(YELLOW_TOKEN, 3);
        connect4Board.playersMove(RED_TOKEN, 3);
        connect4Board.playersMove(YELLOW_TOKEN, 3);
        connect4Board.playersMove(RED_TOKEN, 3);

        assertThat(connect4Board, hasProperty("winner", is("RED (" + RED_TOKEN + ") wins")));


    }
}