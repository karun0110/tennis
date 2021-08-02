import com.game.tennis.Player;
import com.game.tennis.TennisGame;
import org.junit.Test;

import java.util.Optional;

import static junit.framework.TestCase.*;
import static junit.framework.TestCase.assertEquals;

public class TennisGameTest {


    @org.junit.Test(expected = NullPointerException.class)
    public void betweenKOFirstPlayer() {
        TennisGame.between(null, new Player("test"));
    }

    @org.junit.Test(expected = NullPointerException.class)
    public void betweenKOSecondPlayer() {
        TennisGame.between(new Player("test"), null);
    }


    @org.junit.Test(expected = NullPointerException.class)
    public void betweenKOFirstPlayerName() {
        TennisGame.between(null, "");
    }

    @org.junit.Test(expected = NullPointerException.class)
    public void betweenKOSecondPlayerName() {
        TennisGame.between("", null);
    }

    @org.junit.Test
    public void incrementFirstPlayer() {
        TennisGame game = TennisGame.between("Thierry", "Raptao");
        game.incrementFirstPlayer();

        assertEquals(0, game.getSecondPlayer().getScore());
        assertEquals(15, game.getFirstPlayer().getScore());
    }

    @org.junit.Test
    public void incrementSecondPlayer() {
        TennisGame game = TennisGame.between("Thierry", "Raptao");
        game.incrementSecondPlayer();
        assertEquals(0, game.getFirstPlayer().getScore());
        assertEquals(15, game.getSecondPlayer().getScore());
    }

    @org.junit.Test
    public void winningFirstPlayer() {
        TennisGame game = TennisGame.between("Thierry", "Raptao");
        assertFalse(game.getWinner().isPresent());
        assertTrue(game.incrementFirstPlayer()); // 15 - 0
        assertTrue(game.incrementFirstPlayer()); // 30 - 0
        assertTrue(game.incrementFirstPlayer()); // 40 - 0
        assertFalse(game.incrementFirstPlayer()); // game won
        assertFalse(game.incrementFirstPlayer());
        Optional<Player> winningPlayer = game.getWinner();
        assertTrue(winningPlayer.isPresent());
        assertEquals("Thierry", winningPlayer.get().getName());
    }

    @org.junit.Test
    public void winningSecondPlayer() {
        TennisGame game = TennisGame.between("Thierry", "Raptao");
        assertFalse(game.getWinner().isPresent());
        assertTrue(game.incrementSecondPlayer()); // 0 - 15
        assertTrue(game.incrementSecondPlayer()); // 0 - 30
        assertTrue(game.incrementSecondPlayer()); // 0 - 40
        assertFalse(game.incrementSecondPlayer()); // game won
        Optional<Player> winningPlayer = game.getWinner();
        assertTrue(winningPlayer.isPresent());
        assertEquals("Raptao", winningPlayer.get().getName());
    }

    @org.junit.Test
    public void deuceToWinFirstPlayer() {
        TennisGame game = TennisGame.between("Thierry", "Raptao");
        assertTrue(game.incrementFirstPlayer());
        assertTrue(game.incrementSecondPlayer()); // 15 - 15
        assertTrue(game.incrementFirstPlayer());
        assertTrue(game.incrementSecondPlayer()); // 30 - 30
        assertTrue(game.incrementFirstPlayer());
        assertTrue(game.incrementSecondPlayer()); // 40 -40
        assertTrue(game.isDeuce());

        assertTrue(game.incrementFirstPlayer()); // player one has advantage
        assertTrue(game.getFirstPlayer().hasAdvantage());
        assertFalse(game.getSecondPlayer().hasAdvantage());

        assertTrue(game.incrementSecondPlayer()); // return to deuce
        assertTrue(game.isDeuce());

        assertTrue(game.incrementFirstPlayer()); // player one has advantage
        assertFalse(game.incrementFirstPlayer()); // player one has won the game

        Optional<Player> winningPlayer = game.getWinner();
        assertTrue(winningPlayer.isPresent());
        assertEquals("Thierry", winningPlayer.get().getName());

        assertTrue(game.isFinished());
    }

    @org.junit.Test
    public void deuceToWinSecondPlayer() {
        TennisGame game = TennisGame.between("Thierry", "Raptao");
        assertTrue(game.incrementFirstPlayer());
        assertTrue(game.incrementSecondPlayer()); // 15 - 15
        assertTrue(game.incrementFirstPlayer());
        assertTrue(game.incrementSecondPlayer()); // 30 - 30
        assertTrue(game.incrementFirstPlayer());
        assertTrue(game.incrementSecondPlayer()); // 40 -40
        assertTrue(game.isDeuce());

        assertTrue(game.incrementSecondPlayer()); // player two has advantage
        assertTrue(game.getSecondPlayer().hasAdvantage());
        assertFalse(game.getFirstPlayer().hasAdvantage());

        assertTrue(game.incrementFirstPlayer()); // return to deuce
        assertTrue(game.isDeuce());

        assertTrue(game.incrementSecondPlayer()); // player two has advantage
        assertTrue(game.getSecondPlayer().hasAdvantage());
        assertFalse(game.incrementSecondPlayer()); // player two has won the game
        assertFalse(game.incrementSecondPlayer());
        assertTrue(game.isFinished());
        Optional<Player> winningPlayer = game.getWinner();
        assertTrue(winningPlayer.isPresent());
        assertEquals("Raptao", winningPlayer.get().getName());
        assertTrue(game.isFinished());
    }

    @org.junit.Test
    public void isDeuce() {
        TennisGame game = TennisGame.between("Thierry", "Raptao");
        assertFalse(game.isDeuce()); // 0 - 0
        game.incrementFirstPlayer();
        assertFalse(game.isDeuce()); // 15 - 0
        game.incrementSecondPlayer();
        game.incrementSecondPlayer();
        assertFalse(game.isDeuce()); // 15 - 30

        game.incrementFirstPlayer();
        game.incrementFirstPlayer();
        game.incrementSecondPlayer();
        assertTrue(game.isDeuce()); // 40 - 40
    }

    @org.junit.Test
    public void firstPlayerScore(){
        TennisGame game = TennisGame.between("thierry", "raptao");
        assertEquals(0, game.firstPlayerScore());
    }

    @Test
    public void secondPlayerScore(){
        TennisGame game = TennisGame.between("thierry", "raptao");
        assertEquals(0, game.secondPlayerScore());
    }

}
