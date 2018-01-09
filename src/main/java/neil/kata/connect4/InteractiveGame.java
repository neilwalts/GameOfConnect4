package neil.kata.connect4;

import java.util.Scanner;

public class InteractiveGame {

    Player player = new Player();
    Connect4Board connect4Board = new Connect4Board();
    Scanner reader = new Scanner(System.in);

    public static void main(String args[]) {
        InteractiveGame interactiveGame = new InteractiveGame();
        interactiveGame.playGame();
    }

    public int getColumnOfMove() {
        System.out.println("Player: " + player.getPlayer() + ", enter a column to place a token: ");
        int column = reader.nextInt();
        column--;
        return column;
    }

    public void playGame() {
        connect4Board.showBoardOnStdOut();
        do {
            player.setPlayer();
            connect4Board.playersMove(player.getPlayer(), getColumnOfMove());
            connect4Board.showBoardOnStdOut();
        } while (connect4Board.getWinner() == "");
        reader.close();
    }
}
