package fall2018.csc2017.Games;

import android.content.Context;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MovementControllerTest {
    MovementController mController = new MovementController();
    GameManager boardManager = new BoardManager(4);

    @Test
    public void testGetManager() {
        mController.setManager(boardManager);
        assertEquals(boardManager, mController.getManager());
    }

//    @Test
//    public void testProcessTap() {
//        FlipManager flipManager = mock(FlipManager.class);
//        Context context = mock(Context.class);
//        mController.processTapMovement(context, 5);
//        verify(flipManager.touchColor(5));
//    }
}
