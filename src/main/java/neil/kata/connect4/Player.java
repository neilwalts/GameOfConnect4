package neil.kata.connect4;

public class Player {
    private int player = 0;

    public void setPlayer() {
        if (player == 1) {
            player = 2;
        } else if (player == 2){
            player = 1;
        } else {
            player = 1;
        }
    }

    public int getPlayer() {
        return player;
    }
}
