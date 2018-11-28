package fall2018.csc2017.slidingtiles;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see < a href=" ">Testing documentation</ a>
 */
public class BoardAndTileTest {

    /**
     * The board manager for testing.
     */
    BoardManager boardManager3;
    BoardManager boardManager4;
    BoardManager boardManager5;

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
    private List<Tile> makeTiles4() {
        List<Tile> tiles4 = new ArrayList<>();
        final int numTiles = 16;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles4.add(new Tile(tileNum + 1, tileNum));
        }
        return tiles4;
    }
    private List<Tile> makeTiles5() {
        List<Tile> tiles5 = new ArrayList<>();
        final int numTiles = 25;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles5.add(new Tile(tileNum + 1, tileNum));
        }
        return tiles5;
    }
    /**
     * Make a solved Board.
     */
    private void setUpCorrect() {
        List<Tile> tiles3 = makeTiles3();
        List<Tile> tiles4 = makeTiles4();
        List<Tile> tiles5 = makeTiles5();

        Board board5 = new Board(tiles5, 5);
        Board board4 = new Board(tiles4, 4);
        Board board3 = new Board(tiles3, 3);

        boardManager3 = new BoardManager(board3);
        boardManager4 = new BoardManager(board4);
        boardManager5 = new BoardManager(board5);

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
        assertEquals(true, boardManager4.puzzleSolved());
        swapFirstTwoTiles4();
        assertEquals(false, boardManager4.puzzleSolved());
    }
    @Test
    public void testIsSolved3() {
        setUpCorrect();
        assertEquals(true, boardManager3.puzzleSolved());
        swapFirstTwoTiles3();
        assertEquals(false, boardManager3.puzzleSolved());
    }
    @Test
    public void testIsSolved5() {
        setUpCorrect();
        assertEquals(true, boardManager5.puzzleSolved());
        swapFirstTwoTiles5();
        assertEquals(false, boardManager5.puzzleSolved());
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
    @Test
    public void testSwapFirstTwo3() {
        setUpCorrect();
        assertEquals(1, boardManager3.getBoard().getTile(0, 0).getId());
        assertEquals(2, boardManager3.getBoard().getTile(0, 1).getId());
        boardManager3.getBoard().swapTiles(0, 0, 0, 1);
        assertEquals(2, boardManager3.getBoard().getTile(0, 0).getId());
        assertEquals(1, boardManager3.getBoard().getTile(0, 1).getId());
    }
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
     * Test whether swapping the last two tiles works.
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
    @Test
    public void testSwapLastTwo4() {
        setUpCorrect();
        assertEquals(15, boardManager4.getBoard().getTile(3, 2).getId());
        assertEquals(16, boardManager4.getBoard().getTile(3, 3).getId());
        boardManager4.getBoard().swapTiles(3, 2, 3, 3);
        assertEquals(16, boardManager4.getBoard().getTile(3, 2).getId());
        assertEquals(15, boardManager4.getBoard().getTile(3, 3).getId());
    }
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
    @Test
    public void testIsValidTap3() {
        setUpCorrect();
        assertEquals(true, boardManager3.isValidTap(7));
        assertEquals(true, boardManager3.isValidTap(5));
        assertEquals(false, boardManager3.isValidTap(4));
    }
    @Test
    public void testIsValidTap5() {
        setUpCorrect();
        assertEquals(true, boardManager5.isValidTap(23));
        assertEquals(true, boardManager5.isValidTap(19));
        assertEquals(false, boardManager5.isValidTap(18));
    }
    @Test
    public void testGetMovements3(){
        setUpCorrect();
        swapFirstTwoTiles3();
        assertEquals(2, boardManager3.getMovements().pop());


    }
    @Test
    public void testGetMovements4(){
        setUpCorrect();
        swapFirstTwoTiles5();
        assertEquals(2, boardManager5.getMovements().pop());


    }
    @Test
    public void testGetMovements5(){
        setUpCorrect();
        swapFirstTwoTiles5();
        assertEquals(2, boardManager5.getMovements().pop());


    }

    @Test
    public void testGetBlank3(){
        setUpCorret();
        assertEquals(9,boardManager3.getBlank());


    }
    @Test
    public void testGetBlank4(){
        setUpCorret();
        assertEquals(16,boardManager4.getBlank());


    }
    @Test
    public void testGetBlank5(){
        setUpCorret();
        assertEquals(25,boardManager5.getBlank());


    }

}


