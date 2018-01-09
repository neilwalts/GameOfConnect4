import neil.kata.connect4.Player;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class TestPlayer {
    Player player;

    @Before
    public void setUpPlayer() {
        player = new Player();
    }

    @Test
    public void testFirstMoveIsPlayerOne() {
        player.setPlayer();
        assertThat(player, hasProperty("player", is(1)));
    }

    @Test
    public void testSecondMoveIsPlayerTwo() {
        player.setPlayer();
        player.setPlayer();
        assertThat(player, hasProperty("player", is(2)));
    }

    @Test
    public void testFifthMoveIsPlayerOne() {
        player.setPlayer();
        player.setPlayer();
        player.setPlayer();
        player.setPlayer();
        player.setPlayer();
        assertThat(player, hasProperty("player", is(1)));
    }

}
