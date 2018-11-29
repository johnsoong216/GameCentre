package fall2018.csc2017.slidingtiles;

import android.content.Context;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BoardMovementControllerTest {
    /**
     * crease board movement controller for testing
     */
    BoardMovementController mController = new BoardMovementController();
    BoardManager boardManager;
    List<Tile> tiles;
    Context context = new GameActivity();
    /**
     * make a list of tiles for testing
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
     * set up the board and board manager
     */
    private void setUp() {
        tiles = makeTiles();
        Board board = new Board(tiles, 4);
        boardManager = new BoardManager(board);
    }
    /**
     * test whether the boardmanager is set correctly
     */
    @Test
    public void testSetBoardmanager() {
        setUp();
        mController.setManager(boardManager);
        assertEquals(boardManager, mController.getManager());
    }
}
