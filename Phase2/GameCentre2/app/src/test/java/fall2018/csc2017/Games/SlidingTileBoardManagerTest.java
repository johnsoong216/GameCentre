package fall2018.csc2017.Games;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SlidingTileBoardManagerTest {
    SlidingTileBoardManager boardManager;
    private List<Tile> makeTiles() {
        List<Tile> tiles = new ArrayList<>();
        final int numTiles = 16;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new Tile(tileNum + 1, tileNum));
        }

        return tiles;
    }

    private List<Tile> makeTilesNotInOrder() {
        List<Tile> tiles = new ArrayList<>();
        final int numTiles = 11;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new Tile(tileNum + 1, tileNum));
        }
        tiles.add(new Tile(15));
        tiles.add(new Tile(14));
        tiles.add(new Tile(13));
        tiles.add(new Tile(12));
        tiles.add(new Tile(11));


        return tiles;
    }

    /**
     * Make a solved SlidingTileBoard.
     */
    private void setUp() {
        List<Tile> tiles = makeTiles();
        Collections.shuffle(tiles);
        SlidingTileBoard board = new SlidingTileBoard(tiles, 4);
        boardManager = new SlidingTileBoardManager(board);
    }

    /**
     * set up a board manager with complexity 3
     */
    private void setUp3() {
        boardManager = new SlidingTileBoardManager(3);
    }

    /**
     * set up a boardManager with picture ferries wheel
     */
    private void setUpPicBoardManager() {
        boardManager = new SlidingTileBoardManager(4, "fw");
    }

    /**
     * set up a boardManager with picture dog
     */
    private void setUpDogBoardManager() {
        boardManager = new SlidingTileBoardManager(3, "dog");
    }
    /**
     * Test whether isValidHelp works.
     */
    @Test
    public void testInversion() {
        setUp3();
        List<Tile> tiles = makeTilesNotInOrder();
        assertEquals(6, boardManager.getInv(tiles));
    }
    /**
     *  test whether return the blank tile
     */
    @Test
    public void testBlank() {
        setUp3();
        List<Tile> tiles = makeTilesNotInOrder();
        assertEquals(11, boardManager.getBlank(tiles));
    }
    /**
     * test whether return the correct step
     */
    @Test
    public void testStepCounter() {
        setUpDogBoardManager();
        boardManager.setStepCounter(10);
        assertEquals(10, boardManager.getStepCounter());
    }
    /**
     * test whether the timer setting and getting correctly
     */
    @Test
    public void testTimer() {
        setUpPicBoardManager();
        boardManager.setTimer(20);
        assertEquals(20, boardManager.getTimer());
    }
    /**
     * test whether the score is calculated correctly
     */
    @Test
    public void testScore() {
        setUp3();
        boardManager.setTimer(20);
        boardManager.setStepCounter(10);
        boardManager.setUndo(3);
        assertEquals(373, boardManager.getScore());
    }

    /**
     * Test the touch move method and undo method
     */
    @Test
    public void testUndoAndTouchMove(){
        List<Tile> tiles = makeTilesNotInOrder();
        boardManager = new SlidingTileBoardManager(new SlidingTileBoard(tiles, 4));
        assertEquals(true, boardManager.isValidTap(15));
        boardManager.touchMove(15);
        assertEquals(false, boardManager.isValidTap(15));
        boardManager.undo();
        assertEquals(true, boardManager.isValidTap(15));
    }
}
