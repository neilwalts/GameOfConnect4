package neil.kata.connect4;

public class Connect4Board {
    private static final int YELLOW_TOKEN = 1;
    private static final int RED_TOKEN = 2;
    private static final int CONNECT4 = 4;
    private static final String UP = "UP";
    private static final String DOWN = "DOWN";
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

    public void playersMove(int color, int column) {
        currentColumn = column;
        for (int i = 0; i < ROWS; i++) {
            if (board[i][currentColumn] == 0) {
                board[i][currentColumn] = color;
                currentRow = i;
                if (!checkForWinnerOnHorizontal()) {
                    if (!checkForWinnerOnVertical()) {
                        if (!checkForWinnerOnDiagonal(UP)) {
                            checkForWinnerOnDiagonal(DOWN);
                        }
                    }
                }
                return;
            }
        }
    }

    private boolean checkForWinnerOnDiagonal(String direction) {
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                if (direction == DOWN) {
                    if (checkDiagonalDown(row, column)) {
                        return true;
                    }
                } else if (direction == UP) {
                    if (checkDiagonalUp(row, column)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkDiagonalDown(int row, int column) {
        for (; column < COLUMNS; column++) {
            if (tallyConnects(row, column)) {
                if (checkForWinningMove()) {
                    return true;
                }
                row--;
                if (row < 0) {
                    break;
                }
            }
        }
        return resetBothPlayersTally();
    }

    private boolean checkDiagonalUp(int row, int column) {
        for (; column < COLUMNS; column++) {
            if (tallyConnects(row, column)) {
                if (checkForWinningMove()) {
                    return true;
                }
                row++;
                if (row == ROWS) {
                    break;
                }
            }
        }
        return resetBothPlayersTally();
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
