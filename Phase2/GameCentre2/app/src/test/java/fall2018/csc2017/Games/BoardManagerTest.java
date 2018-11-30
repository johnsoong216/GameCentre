package fall2018.csc2017.Games;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BoardManagerTest {
    BoardManager boardManager;
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
     * Make a solved Board.
     */
    private void setUp() {
        List<Tile> tiles = makeTiles();
        Collections.shuffle(tiles);
        Board board = new Board(tiles, 4);
        boardManager = new BoardManager(board);
    }

    private void setUp3() {
        boardManager = new BoardManager(3);
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
        setUp();
        boardManager.setStepCounter(10);
        assertEquals(10, boardManager.getStepCounter());
    }
    /**
     * test whether the timer counts correctly
     */
    @Test
    public void testTimer() {
        setUp();
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
}
