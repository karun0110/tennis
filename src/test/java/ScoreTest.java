import com.game.tennis.Score;
import org.junit.Test;

import static junit.framework.TestCase.*;
import static junit.framework.TestCase.assertEquals;

public class ScoreTest {


    @Test
    public void scoreInitialization() {
        assertEquals(0, new Score().currentScore());
    }

    @Test
    public void incrementAndGet() {
        Score score = new Score();
        assertTrue(score.increment());
        //assertEquals(1, score.currentScore());
    }

    @Test
    public void maxScore() {
        Score score = new Score();
        assertTrue(score.increment());
        assertEquals(1, score.currentScore());
        assertTrue(score.increment());
        assertEquals(2, score.currentScore());
        assertTrue(score.increment());
        assertEquals(3, score.currentScore());
        assertTrue(score.increment());
        assertEquals(4, score.currentScore());
        assertTrue(score.increment());
        assertEquals(5, score.currentScore());
        assertTrue(score.increment());
        assertEquals(6, score.currentScore());
        assertTrue(score.increment());
        assertEquals(7, score.currentScore()); // max set score
        assertFalse(score.increment());
        assertEquals(7, score.currentScore());
    }

    @Test(expected = IllegalStateException.class)
    public void reset() {
        Score score = new Score();
        score.reset();
    }

}
