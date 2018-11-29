package fall2018.csc2017.slidingtiles;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BoardManagerTest {
    BoardManager boardManager = new BoardManager();
    private List<Tile> makeTiles() {
        List<Tile> tiles = new ArrayList<>();
        final int numTiles = 16;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new Tile(tileNum + 1, tileNum));
        }

        return tiles;
    }

    /**
     * Make a solved Board.
     */
    private void setUpCorrect() {
        List<Tile> tiles = makeTiles();
        Collections.shuffle(tiles);
        Board board = new Board(tiles, 4);
        boardManager = new BoardManager(board);
    }

    private void setUp4() {
        List<Tile> tiles = makeTiles();
        Collections.shuffle(tiles);
        boardManager = new BoardManager(4);
    }

    /**
     * Test whether isValidHelp works.
     */
    @Test
    public void testIInversion() {
        List<Tile> tiles = new ArrayList<>();
        Tile newtile1 = new Tile(0);
        Tile newtile2 = new Tile(1);
        Tile newtile3 = new Tile(2);
        Tile newtile4 = new Tile(3);
        Tile newtile5 = new Tile(4);
        Tile newtile6 = new Tile(5);
        Tile newtile7 = new Tile(6);
        Tile newtile8 = new Tile(7);
        Tile newtile9 = new Tile(8);
        Tile newtile10 = new Tile(9);
        Tile newtile11 = new Tile(10);
        Tile newtile12 = new Tile(11);
        Tile newtile13 = new Tile(12);
        Tile newtile14 = new Tile(13);
        Tile newtile15 = new Tile(14);
        Tile newtile16 = new Tile(15);
        newtile16.setBackground(R.drawable.tile_25);
        tiles.add(newtile5);
        tiles.add(newtile7);
        tiles.add(newtile9);
        tiles.add(newtile1);
        tiles.add(newtile3);
        tiles.add(newtile12);
        tiles.add(newtile4);
        tiles.add(newtile6);
        tiles.add(newtile15);
        tiles.add(newtile8);
        tiles.add(newtile2);
        tiles.add(newtile16);
        tiles.add(newtile10);
        tiles.add(newtile14);
        tiles.add(newtile11);
        tiles.add(newtile13);
        assertEquals(33, boardManager.getInv(tiles));
    }

    @Test
    public void testBlank() {
        List<Tile> tiles = new ArrayList<>();
        Tile newtile1 = new Tile(0);
        Tile newtile2 = new Tile(1);
        Tile newtile3 = new Tile(2);
        Tile newtile4 = new Tile(3);
        Tile newtile5 = new Tile(4);
        Tile newtile6 = new Tile(5);
        Tile newtile7 = new Tile(6);
        Tile newtile8 = new Tile(7);
        Tile newtile9 = new Tile(8);
        Tile newtile10 = new Tile(9);
        Tile newtile11 = new Tile(10);
        Tile newtile12 = new Tile(11);
        Tile newtile13 = new Tile(12);
        Tile newtile14 = new Tile(13);
        Tile newtile15 = new Tile(14);
        Tile newtile16 = new Tile(15);
        newtile16.setBackground(R.drawable.tile_25);
        tiles.add(newtile5);
        tiles.add(newtile7);
        tiles.add(newtile9);
        tiles.add(newtile1);
        tiles.add(newtile3);
        tiles.add(newtile12);
        tiles.add(newtile4);
        tiles.add(newtile6);
        tiles.add(newtile15);
        tiles.add(newtile8);
        tiles.add(newtile2);
        tiles.add(newtile16);
        tiles.add(newtile10);
        tiles.add(newtile14);
        tiles.add(newtile11);
        tiles.add(newtile13);
        assertEquals(11, boardManager.getBlank(tiles));
    }

    @Test
    public void testStepCounter() {
        setUpCorrect();
        boardManager.setStepCounter(10);
        assertEquals(10, boardManager.getStepCounter());
    }

    @Test
    public void testTimer() {
        setUpCorrect();
        boardManager.setTimer(20);
        assertEquals(20, boardManager.getTimer());
    }

    @Test
    public void testScore() {
        setUp4();
        boardManager.setTimer(20);
        boardManager.setStepCounter(10);
        boardManager.setUndo(3);
        assertEquals(432, boardManager.getScore());
    }

    @Test
    public void testScoreLessThan0() {
        setUp4();
        boardManager.setTimer(3000);
        boardManager.setStepCounter(1000);
        boardManager.setUndo(30);
        assertEquals(0, boardManager.getScore());
    }

    @Test
    public void testMovement() {
        setUp4();
        assertEquals(0, boardManager.getMovements().size());
    }
}
