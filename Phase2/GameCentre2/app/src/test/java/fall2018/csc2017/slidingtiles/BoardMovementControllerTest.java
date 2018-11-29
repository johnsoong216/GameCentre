package fall2018.csc2017.slidingtiles;

import android.content.Context;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BoardMovementControllerTest {
    BoardMovementController mController = new BoardMovementController();
    BoardManager boardManager;
    List<Tile> tiles;
    Context context = new GameActivity();

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
        Board board = new Board(tiles, 4);
        boardManager = new BoardManager(board);
    }

    @Test
    public void testSetBoardmanager() {
        setUp();
        mController.setBoardManager(boardManager);
        assertEquals(boardManager, mController.getBoardManager());
    }
}
