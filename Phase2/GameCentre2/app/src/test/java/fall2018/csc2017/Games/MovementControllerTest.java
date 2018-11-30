package fall2018.csc2017.Games;

import android.content.Context;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MovementControllerTest {
    MovementController mController = new MovementController();
    SlidingTileBoardManager boardManager = new SlidingTileBoardManager(4);

    private List<Tile> makeTiles() {
        List<Tile> tiles = new ArrayList<>();
        final int numTiles = 16;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new Tile(tileNum + 1, tileNum));
        }

        return tiles;
    }

    private void setUp() {
        List<Tile> tiles = makeTiles();
        SlidingTileBoard board = new SlidingTileBoard(tiles, 4);
        boardManager = new SlidingTileBoardManager(board);

    }

    @Test
    public void testGetManager() {
        mController.setManager(boardManager);
        assertEquals(boardManager, mController.getManager());
    }

    @Test
    public void testProcessTap() {
        FlipManager flipManager = mock(FlipManager.class);
        Context context = mock(Context.class);
        mController.setManager(flipManager);
        mController.processTapMovement(context, 13);
        verify(flipManager).touchMove(13);
    }
}
