package fall2018.csc2017.slidingtiles;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

public class FlipItTest {
    /**
     * The board manager for testing.
     */
    FlipManager flipManager3;
    FlipManager flipManager4;
    FlipManager flipManager5;

    /**
     * Make a set of tiles that are in order.
     *
     * @return a set of tiles that are in order
     */
    private List<Tile> makeTiles3() {
        List<Tile> tiles3 = new ArrayList<>();
        for (int tileNum = 0; tileNum != 9; tileNum++) {
            Tile newtile = new Tile(1);
            newtile.setBackground(R.drawable.login_background);
            if (tileNum == 2 || tileNum == 4 || tileNum == 6) {
                newtile.setBackground(R.drawable.back);
            }
            tiles3.add(newtile);
        }
        return tiles3;

    }


    private List<Tile> makeTiles4() {
        List<Tile> tiles4 = new ArrayList<>();
        for (int tileNum = 0; tileNum != 16; tileNum++) {
            Tile newtile = new Tile(1);
            newtile.setBackground(R.drawable.login_background);
            if (tileNum == 0 || tileNum == 2 || tileNum == 9 || tileNum == 14) {
                newtile.setBackground(R.drawable.back);
            }
            tiles4.add(newtile);
        }
        return tiles4;
    }

    private List<Tile> makeTiles5() {
        List<Tile> tiles5 = new ArrayList<>();
        for (int tileNum = 0; tileNum != 25; tileNum++) {
            Tile newtile = new Tile(1);
            newtile.setBackground(R.drawable.login_background);
            if (tileNum == 0 || tileNum == 2 || tileNum == 4 || tileNum == 12 || tileNum == 15 || tileNum == 18 || tileNum == 19) {
                newtile.setBackground(R.drawable.back);
            }
            tiles5.add(newtile);
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

        FlipIt flipIt3 = new FlipIt(tiles3, 3);
        FlipIt flipIt4 = new FlipIt(tiles4, 4);
        FlipIt flipIt5 = new FlipIt(tiles5, 5);

        flipManager3 = new FlipManager(flipIt3);
        flipManager4 = new FlipManager(flipIt4);
        flipManager5 = new FlipManager(flipIt5);

    }

    /**
     * Shuffle a few tiles.
     */
    private void clickFirstTile3() {
        //flipManager3.touchColor(0);
        flipManager3.getFlip().changeColor(0, 0);
        flipManager3.getFlip().changeColor(1, 0);
        flipManager3.getFlip().changeColor(0, 1);
    }

    private void clickLastTile3() {
        //flipManager3.touchColor(1);
        flipManager3.getFlip().changeColor(2, 2);
        flipManager3.getFlip().changeColor(2, 1);
        flipManager3.getFlip().changeColor(1, 2);
    }

    private void clickFirstTile4() {
        //flipManager4.touchColor(1);
        flipManager4.getFlip().changeColor(0, 0);
        flipManager4.getFlip().changeColor(1, 0);
        flipManager4.getFlip().changeColor(0, 1);
    }

    private void clickFirstTile5() {
        //flipManager5.touchColor(0);
        flipManager5.getFlip().changeColor(0, 0);
        flipManager5.getFlip().changeColor(1, 0);
        flipManager5.getFlip().changeColor(0, 1);
    }

    /**
     * Test whether swapping two tiles makes a solved board unsolved.
     */
    @Test
    public void testIsSolved4() {
        setUpCorrect();
        assertEquals(false, flipManager4.puzzleSolved());
        clickFirstTile4();
        assertEquals(false, flipManager4.puzzleSolved());
    }

    @Test
    public void testIsSolved3() {
        setUpCorrect();
        assertEquals(false, flipManager3.puzzleSolved());
        clickFirstTile3();
        clickLastTile3();
        assertEquals(true, flipManager3.puzzleSolved());
    }

    @Test
    public void testIsSolved5() {
        setUpCorrect();
        assertEquals(false, flipManager5.puzzleSolved());
        clickFirstTile5();
        assertEquals(false, flipManager5.puzzleSolved());
    }

    /**
     * Test whether swapping the first two tiles works.
     */

    @Test
    public void testClickFirst4() {
        setUpCorrect();
        clickFirstTile4();
        assertEquals(R.drawable.login_background, flipManager4.getFlip().getTile(0, 0).getBackground());
        assertEquals(R.drawable.back, flipManager4.getFlip().getTile(0, 1).getBackground());
        assertEquals(R.drawable.back, flipManager4.getFlip().getTile(1, 0).getBackground());
    }

    @Test
    public void testClickFirst3() {
        setUpCorrect();
        clickFirstTile3();
        assertEquals(R.drawable.back, flipManager3.getFlip().getTile(0, 0).getBackground());
        assertEquals(R.drawable.back, flipManager3.getFlip().getTile(0, 1).getBackground());
        assertEquals(R.drawable.back, flipManager3.getFlip().getTile(1, 0).getBackground());

    }

    @Test
    public void testClickFirst5() {
        setUpCorrect();
        clickFirstTile5();
        assertEquals(R.drawable.login_background, flipManager5.getFlip().getTile(0, 0).getBackground());
        assertEquals(R.drawable.back, flipManager5.getFlip().getTile(0, 1).getBackground());
        assertEquals(R.drawable.back, flipManager5.getFlip().getTile(1, 0).getBackground());
    }

    @Test
    public void testGetMovements3() {
        setUpCorrect();
        flipManager3.getMovements().push(0);
        flipManager3.getMovements().push(3);
        flipManager3.getMovements().push(1);

        assertEquals(1, flipManager3.getMovements().pop());
        assertEquals(3, flipManager3.getMovements().pop());
        assertEquals(0, flipManager3.getMovements().pop());
    }
    @Test
    public void testGetMovements4(){
        setUpCorrect();
        flipManager4.getMovements().push(0);
        flipManager4.getMovements().push(3);
        flipManager4.getMovements().push(1);

        assertEquals(1, flipManager4.getMovements().pop());
        assertEquals(3, flipManager4.getMovements().pop());
        assertEquals(0, flipManager4.getMovements().pop());

    }
    @Test
    public void testGetMovements5(){
        setUpCorrect();
        flipManager5.getMovements().push(0);
        flipManager5.getMovements().push(3);
        flipManager5.getMovements().push(1);

        assertEquals(1, flipManager5.getMovements().pop());
        assertEquals(3, flipManager5.getMovements().pop());
        assertEquals(0, flipManager5.getMovements().pop());
    }

}