package fall2018.csc2017.Games;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MovementControllerTest {
    MovementController mController = new MovementController();
    SlidingTileBoardManager boardManager = new SlidingTileBoardManager(4);

    @Test
    public void testGetManager() {
        mController.setManager(boardManager);
        assertEquals(boardManager, mController.getManager());
    }
}
