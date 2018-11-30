package fall2018.csc2017.Games;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see < a href=" ">Testing documentation</ a>
 */
public class SlidingTileBoardAndTileTest {

    /**
     * The board manager for testing.
     */
    SlidingTileBoardManager boardManager3;
    SlidingTileBoardManager boardManager4;
    SlidingTileBoardManager boardManager5;

    List<Tile> tiles3;
    List<Tile> tiles4;
    List<Tile> tiles5;

    /**
     * Make a set of tiles that are in order.
     *
     * @return a set of tiles that are in order
     */
    private List<Tile> makeTiles3() {
        List<Tile> tiles3 = new ArrayList<>();
        final int numTiles = 9;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles3.add(new Tile(tileNum + 1, tileNum));
        }
        return tiles3;
    }
    /**
     * Make a set of tiles that are in order.
     *
     * @return a set of tiles that are in order
     */
    private List<Tile> makeTiles4() {
        List<Tile> tiles4 = new ArrayList<>();
        final int numTiles = 16;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles4.add(new Tile(tileNum + 1, tileNum));
        }
        return tiles4;
    }
    /**
     * Make a set of tiles that are in order.
     *
     * @return a set of tiles that are in order
     */
    private List<Tile> makeTiles5() {
        List<Tile> tiles5 = new ArrayList<>();
        final int numTiles = 25;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles5.add(new Tile(tileNum + 1, tileNum));
        }
        return tiles5;
    }
    /**
     * Make a solved SlidingTileBoard.
     */
    private void setUpCorrect() {
        tiles3 = makeTiles3();
        tiles4 = makeTiles4();
        tiles5 = makeTiles5();

        SlidingTileBoard board5 = new SlidingTileBoard(tiles5, 5);
        SlidingTileBoard board4 = new SlidingTileBoard(tiles4, 4);
        SlidingTileBoard board3 = new SlidingTileBoard(tiles3, 3);

        boardManager3 = new SlidingTileBoardManager(board3);
        boardManager4 = new SlidingTileBoardManager(board4);
        boardManager5 = new SlidingTileBoardManager(board5);

    }

    /**
     * Shuffle a few tiles.
     */
    private void swapFirstTwoTiles3() {
        boardManager3.getBoard().swapTiles(0, 0, 0, 1);
    }
    private void swapFirstTwoTiles4() {
        boardManager4.getBoard().swapTiles(0, 0, 0, 1);
    }
    private void swapFirstTwoTiles5() {
        boardManager5.getBoard().swapTiles(0, 0, 0, 1);
    }

    /**
     * Test whether swapping two tiles makes a solved board unsolved.
     */
    @Test
    public void testIsSolved4() {
        setUpCorrect();
        assertEquals(true, boardManager4.isGameOver());
        swapFirstTwoTiles4();
        assertEquals(false, boardManager4.isGameOver());
    }
    /**
     * Test whether swapping two tiles makes a solved board unsolved.
     */
    @Test
    public void testIsSolved3() {
        setUpCorrect();
        assertEquals(true, boardManager3.isGameOver());
        swapFirstTwoTiles3();
        assertEquals(false, boardManager3.isGameOver());
    }
    /**
     * Test whether swapping two tiles makes a solved board unsolved.
     */
    @Test
    public void testIsSolved5() {
        setUpCorrect();
        assertEquals(true, boardManager5.isGameOver());
        swapFirstTwoTiles5();
        assertEquals(false, boardManager5.isGameOver());
    }

    /**
     * Test whether swapping the first two tiles works.
     */
    @Test
    public void testSwapFirstTwo4() {
        setUpCorrect();
        assertEquals(1, boardManager4.getBoard().getTile(0, 0).getId());
        assertEquals(2, boardManager4.getBoard().getTile(0, 1).getId());
        boardManager4.getBoard().swapTiles(0, 0, 0, 1);
        assertEquals(2, boardManager4.getBoard().getTile(0, 0).getId());
        assertEquals(1, boardManager4.getBoard().getTile(0, 1).getId());
    }
    /**
     * Test whether swapping the first two tiles works.
     */
    @Test
    public void testSwapFirstTwo3() {
        setUpCorrect();
        assertEquals(1, boardManager3.getBoard().getTile(0, 0).getId());
        assertEquals(2, boardManager3.getBoard().getTile(0, 1).getId());
        boardManager3.getBoard().swapTiles(0, 0, 0, 1);
        assertEquals(2, boardManager3.getBoard().getTile(0, 0).getId());
        assertEquals(1, boardManager3.getBoard().getTile(0, 1).getId());
    }
    /**
     * Test whether swapping the first two tiles works.
     */
    @Test
    public void testSwapFirstTwo5() {
        setUpCorrect();
        assertEquals(1, boardManager5.getBoard().getTile(0, 0).getId());
        assertEquals(2, boardManager5.getBoard().getTile(0, 1).getId());
        boardManager5.getBoard().swapTiles(0, 0, 0, 1);
        assertEquals(2, boardManager5.getBoard().getTile(0, 0).getId());
        assertEquals(1, boardManager5.getBoard().getTile(0, 1).getId());
    }

    /**
     * Test whether swapping the last two tiles works for complexity 3.
     */
    @Test
    public void testSwapLastTwo3() {
        setUpCorrect();
        assertEquals(8, boardManager3.getBoard().getTile(2, 1).getId());
        assertEquals(9, boardManager3.getBoard().getTile(2, 2).getId());
        boardManager3.getBoard().swapTiles(2, 1, 2, 2);
        assertEquals(9, boardManager3.getBoard().getTile(2, 1).getId());
        assertEquals(8, boardManager3.getBoard().getTile(2, 2).getId());
    }

    /**
     * Test whether swapping the last two tiles works for complexity 4.
     */
    @Test
    public void testSwapLastTwo4() {
        setUpCorrect();
        assertEquals(15, boardManager4.getBoard().getTile(3, 2).getId());
        assertEquals(16, boardManager4.getBoard().getTile(3, 3).getId());
        boardManager4.getBoard().swapTiles(3, 2, 3, 3);
        assertEquals(16, boardManager4.getBoard().getTile(3, 2).getId());
        assertEquals(15, boardManager4.getBoard().getTile(3, 3).getId());
    }
    /**
     * Test whether swapping the last two tiles works for complexity 5.
     */
    @Test
    public void testSwapLastTwo5() {
        setUpCorrect();
        assertEquals(24, boardManager5.getBoard().getTile(4, 3).getId());
        assertEquals(25, boardManager5.getBoard().getTile(4, 4).getId());
        boardManager5.getBoard().swapTiles(4, 3, 4, 4);
        assertEquals(25, boardManager5.getBoard().getTile(4, 3).getId());
        assertEquals(24, boardManager5.getBoard().getTile(4, 4).getId());
    }

    /**
     * Test whether isValidHelp works.
     */
    @Test
    public void testIsValidTap4() {
        setUpCorrect();
        assertEquals(true, boardManager4.isValidTap(11));
        assertEquals(true, boardManager4.isValidTap(14));
        assertEquals(false, boardManager4.isValidTap(10));
    }
    /**
     * Test whether isValidHelp works.
     */
    @Test
    public void testIsValidTap3() {
        setUpCorrect();
        assertEquals(true, boardManager3.isValidTap(7));
        assertEquals(true, boardManager3.isValidTap(5));
        assertEquals(false, boardManager3.isValidTap(4));
    }
    /**
     * Test whether isValidHelp works.
     */
    @Test
    public void testIsValidTap5() {
        setUpCorrect();
        assertEquals(true, boardManager5.isValidTap(23));
        assertEquals(true, boardManager5.isValidTap(19));
        assertEquals(false, boardManager5.isValidTap(18));
    }
    /**
     * Test whether get movements works.
     */
    @Test
    public void testGetMovements3(){
        setUpCorrect();
        swapFirstTwoTiles3();
        boardManager3.getMovements().push(8);
        boardManager3.getMovements().push(2);

        assertEquals(2, boardManager3.getMovements().pop());
        assertEquals(8, boardManager3.getMovements().pop());
    }
    /**
     * Test whether get movements works.
     */
    @Test
    public void testGetMovements4(){
        setUpCorrect();
        swapFirstTwoTiles3();
        boardManager4.getMovements().push(8);
        boardManager4.getMovements().push(2);

        assertEquals(2, boardManager4.getMovements().pop());
        assertEquals(8, boardManager4.getMovements().pop());
    }
    /**
     * Test whether get movements works.
     */
    @Test
    public void testGetMovements5(){
        setUpCorrect();
        swapFirstTwoTiles3();
        boardManager5.getMovements().push(8);
        boardManager5.getMovements().push(2);

        assertEquals(2, boardManager5.getMovements().pop());
        assertEquals(8, boardManager5.getMovements().pop());
        }
    /**
     * Test whether get movements works.
     */
    @Test
    public void testGetBlank3(){
        setUpCorrect();
        assertEquals(8,boardManager3.getBlank(tiles3));
    }
    /**
     * Test whether get movements works.
     */
    @Test
    public void testGetBlank4(){
        setUpCorrect();
        assertEquals(15,boardManager4.getBlank(tiles4));
    }
    /**
     * Test whether get movements works.
     */
    @Test
    public void testGetBlank5(){
        setUpCorrect();
        assertEquals(24,boardManager5.getBlank(tiles5));
    }

}


