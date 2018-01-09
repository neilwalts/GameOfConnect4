package neil.kata.connect4;

public class Connect4Board {
    private static final int YELLOW_TOKEN = 1;
    private static final int RED_TOKEN = 2;
    private static final int CONNECT4 = 4;
    private static final String UP = "UP";
    private static final String DOWN = "DOWN";
    private static final int EMPTY_SLOT = 0;
    private static final int NO_ROWS_LEFT = -1;
    private final int COLUMNS = 7;
    private final int ROWS = 6;
    private int[][] board = new int[ROWS][COLUMNS];
    private int currentColumn = -1, currentRow = -1;
    private int redConnects = 0, yellowConnects = 0;
    private String winner = "";
    private static final String RED_WINNER = "RED (" + RED_TOKEN + ") wins";
    private static final String YELLOW_WINNER = "YELLOW (" + YELLOW_TOKEN + ") wins";

    public Connect4Board() {
    }

    public int getCurrentColumn() {
        return currentColumn;
    }

    public int getCurrentRow() {
        return currentRow;
    }

    public String getWinner() {
        return winner;
    }

    public void showBoardOnStdOut() {
        for (int row = ROWS - 1; row >= 0; row--) {
            for (int column = 0; column < COLUMNS; column++) {
                System.out.print(board[row][column] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean playersMove(int color, int column) {
        currentColumn = column;
        for (int row = 0; row < ROWS; row++) {
            if (board[row][currentColumn] == EMPTY_SLOT) {
                setPositionForToken(row, color);
                return checkTheBoardForWinner();
            }
        }
        return false;
    }

    private boolean checkTheBoardForWinner() {
        if (!checkForWinnerOnHorizontal()) {
            if (!checkForWinnerOnVertical()) {
                if (!checkForWinnerOnDiagonal(UP)) {
                    return checkForWinnerOnDiagonal(DOWN);
                }
            }
        }
        return true;
    }

    private void setPositionForToken(int row, int color) {
        board[row][currentColumn] = color;
        currentRow = row;
    }

    private boolean checkForWinnerOnDiagonal(String direction) {
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                if (checkDiagonal(row, column, direction)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkDiagonal(int row, int column, String direction) {
        for (; column < COLUMNS; column++) {
            if (tallyConnects(row, column)) {
                if (checkForWinningMove()) {
                    return true;
                }
            }
            row = setNextRowForSearch(row, direction);
            if (row == NO_ROWS_LEFT) {
                break;
            }
        }
        return resetBothPlayersTally();
    }

    private int setNextRowForSearch(int row, String direction) {
        if (direction.equals(DOWN)) {
            if (--row < 0) {
                return NO_ROWS_LEFT;
            }
        }
        if (direction.equals(UP)) {
            if (++row == ROWS) {
                return NO_ROWS_LEFT;
            }
        }
        return row;
    }

    private boolean checkForWinnerOnVertical() {
        for (int row = currentRow; row >= 0; row--) {
            if (tallyConnects(row, currentColumn)) {
                if (checkForWinningMove()) {
                    return true;
                }
            }
        }
        return resetBothPlayersTally();
    }

    private boolean checkForWinnerOnHorizontal() {
        for (int column = 0; column < COLUMNS; column++) {
            if (tallyConnects(currentRow, column)) {
                if (checkForWinningMove()) {
                    return true;
                }
            }
        }
        return resetBothPlayersTally();
    }

    private boolean tallyConnects(int row, int column) {
        if (board[row][column] == YELLOW_TOKEN) {
            return addPointToPlayer(YELLOW_TOKEN);
        } else if (board[row][column] == RED_TOKEN) {
            return addPointToPlayer(RED_TOKEN);
        }
        return resetBothPlayersTally();

    }

    private boolean addPointToPlayer(int player) {
        if (player == YELLOW_TOKEN) {
            yellowConnects++;
            redConnects = 0;
        } else if (player == RED_TOKEN) {
            redConnects++;
            yellowConnects = 0;
        }
        return true;
    }

    private boolean resetBothPlayersTally() {
        yellowConnects = 0;
        redConnects = 0;
        return false;
    }

    private boolean checkForWinningMove() {
        if (yellowConnects == CONNECT4) {
            return setWinner(YELLOW_WINNER);
        } else if (redConnects == CONNECT4) {
            return setWinner(RED_WINNER);
        }
        return false;
    }

    private boolean setWinner(String winningPlayer) {
        winner = winningPlayer;
        System.out.println(winner);
        return true;
    }
}
