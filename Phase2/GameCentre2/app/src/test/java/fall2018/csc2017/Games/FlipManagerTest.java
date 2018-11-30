package fall2018.csc2017.Games;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class FlipManagerTest {
    FlipManager flipManager1;
    FlipManager flipManager2;
    FlipManager flipManager3;
    FlipManager flipManager4;
    FlipManager flipManager5;
    FlipManager flipManager6;
    FlipManager flipManager7;
    FlipManager flipManager8;
    FlipManager flipManager9;

    private void setUpAll() {
        flipManager1 = new FlipManager(3, 1);
        flipManager2 = new FlipManager(3, 2);
        flipManager3 = new FlipManager(3, 3);
        flipManager4 = new FlipManager(4, 1);
        flipManager5 = new FlipManager(4, 2);
        flipManager6 = new FlipManager(4, 3);
        flipManager7 = new FlipManager(5, 1);
        flipManager8 = new FlipManager(5, 2);
        flipManager9 = new FlipManager(5, 3);
    }


    @Test
    public void testGetScore() {
        setUpAll();
        flipManager1.setUndo(3);
        flipManager1.setStepCounter(100);
        flipManager1.setTimer(100);
        assertEquals(700, flipManager1.getScore());
    }
}
