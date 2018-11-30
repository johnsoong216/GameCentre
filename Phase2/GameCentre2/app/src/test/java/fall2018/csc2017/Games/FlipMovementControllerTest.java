package fall2018.csc2017.Games;

import android.content.Context;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FlipMovementControllerTest {
    /**
     * the flip movement controller for testing
     */
    FlipMovementController mController = new FlipMovementController();
    FlipManager flipManager;
    List<Tile> tiles;
    private Context context;
    /**
     * creating a list of tiles
     */
    private List<Tile> makeTiles() {
        List<Tile> tiles = new ArrayList<>();
        final int numTiles = 14;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new Tile(tileNum + 1, tileNum));
        }
        tiles.add(new Tile(15));
        tiles.add(new Tile(14));
        return tiles;
    }
    /**
     * set up the flip and flip manager
     */
    private void setUp() {
        tiles = makeTiles();
        FlipIt flip = new FlipIt(tiles, 4);
        flipManager = new FlipManager(flip);
        flipManager.isGameOver();
    }
    /**
     * test whether set the board manager correctly
     */
    @Test
    public void testSetBoardmanager() {
        setUp();
        mController.setManager(flipManager);
        assertEquals(flipManager, mController.getManager());
    }

    

}