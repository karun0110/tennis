import com.game.tennis.Match;
import com.game.tennis.Player;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;

public class MatchTest {
    @Test(expected = NullPointerException.class)
    public void illegalFirstPlayer() {
        new Match(null, new Player("test"), new com.game.tennis.Set[2]);
    }


    @Test(expected = NullPointerException.class)
    public void illegalSecondPlayer() {
        new Match(new Player("test"), null, new com.game.tennis.Set[2]);
    }

    @Test(expected = NullPointerException.class)
    public void illegalSetInit() {
        new Match(new Player("test"), new Player("test"), null);
    }

    @Test
    public void isLastSet() throws Exception {
        Match match = new Match(new Player("test"), new Player("test"), new com.game.tennis.Set[2]);
        assertFalse(match.isLastSet());
    }
}
