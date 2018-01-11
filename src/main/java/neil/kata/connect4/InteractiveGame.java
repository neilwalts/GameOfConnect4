package neil.kata.connect4;

import java.util.Scanner;

public class InteractiveGame {

    private Player player = new Player();
    private Connect4Board connect4Board = new Connect4Board();
    private Scanner reader = new Scanner(System.in);

    public static void main(String args[]) {
        InteractiveGame interactiveGame = new InteractiveGame();
        interactiveGame.playGame();
    }

    private int getColumnOfMove() {
        System.out.println("Player: " + player.getPlayer() + ", enter a column to place a token");
        int column = getPlayersColumnNumber();
        column--;
        return validatePlayersColumn(column);
    }

    private int getPlayersColumnNumber() {
        while (!reader.hasNextInt()) {
            System.out.println("Column number please");
            reader.next();
        }
        return reader.nextInt();
    }

    private int validatePlayersColumn(int column) {
        while (column < 0 || column >= Connect4Board.getNumberOfColumnsOnBoard() || !connect4Board.doesColumnHaveEmptySlots(column)) {
            System.out.println("Please enter a valid column number");
            column = getColumnOfMove();
        }
        return column;
    }
    private void playGame() {
        connect4Board.showBoardOnStdOut();
        do {
            player.setPlayer();
            connect4Board.playersMove(player.getPlayer(), getColumnOfMove());
            connect4Board.showBoardOnStdOut();
        } while (connect4Board.getWinner().equals(""));
        reader.close();
    }
}
