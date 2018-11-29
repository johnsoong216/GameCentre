package fall2018.csc2017.slidingtiles;

import android.content.Context;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class FlipMovementControllerTest {
    FlipMovementController mController = new FlipMovementController();
    FlipManager flipManager;
    List<Tile> tiles;
    private Context context;

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

    private void setUp() {
        tiles = makeTiles();
        FlipIt flip = new FlipIt(tiles, 4);
        flipManager = new FlipManager(flip);
        flipManager.puzzleSolved();
    }

    @Test
    public void testSetBoardmanager() {
        setUp();
        mController.setManager(flipManager);
        assertEquals(flipManager, mController.getManager());
    }

    

}