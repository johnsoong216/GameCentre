package fall2018.csc2017.slidingtiles;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Observable;

public class FlipIt extends Observable implements Serializable, Iterable<Tile> {
    private int NUM_ROWS;

    /**
     * The number of columns of the board
     */
    private int NUM_COLS;

    /**
     * Get the number of rows of the board.
     *
     * @return the number of rows of the board.
     */
    int getNUM_ROWS() {
        return NUM_ROWS;
    }

    /**
     * Set the number of rows of the board.
     *
     * @param NUM_ROWS number of rows of the board.
     */
    void setNUM_ROWS(int NUM_ROWS) {
        this.NUM_ROWS = NUM_ROWS;
    }

    /**
     * Get the number of columns of the board.
     *
     * @return the number of the columns of the board.
     */
    int getNUM_COLS() {
        return NUM_COLS;
    }
    int numTiles() {
        return getNUM_COLS() * getNUM_ROWS();
    }

    Tile getTile(int row, int col) {
        return tiles[row][col];
    }
    /**
     * Set the number of columns of the board.
     *
     * @param NUM_COLS number of columns of the board.
     */
    void setNUM_COLS(int NUM_COLS) {
        this.NUM_COLS = NUM_COLS;
    }

    /**
     * The tiles on the board in row-major order.
     */
    private Tile[][] tiles;


    /**
     * Initialize a board of 2d array tiles.
     *
     * @param tile       a tile object.
     * @param complexity the complexity level of a game.
     */
    FlipIt(List<Tile> tile, int complexity) {

        Iterator<Tile> iter = tile.iterator();

        setNUM_COLS(complexity);
        setNUM_ROWS(complexity);
        tiles = new Tile[getNUM_ROWS()][getNUM_COLS()];
        for (int row = 0; row != getNUM_ROWS(); row++) {
            for (int col = 0; col != getNUM_ROWS(); col++) {
                this.tiles[row][col] = iter.next();
            }
        }
    }

    void changeColor(int row, int col) {
        Tile item1 = tiles[row][col];

        if (item1.getBackground() == R.drawable.back) {

            item1.setBackground(R.drawable.flip_it);
        } else if (item1.getBackground() == R.drawable.flip_it) {

            item1.setBackground(R.drawable.back);
        }
        setChanged();
        notifyObservers();
    }

    @Override
    public String toString() {
        return "Board{" +
                "tiles=" + Arrays.toString(tiles) +
                '}';
    }

    @NonNull

    public Iterator<Tile> iterator() {
        return new flipIterator();
    }

    private class flipIterator implements Iterator<Tile> {
        /**
         * The row index in the board.
         */
        int i = 0;

        /**
         * The column index in the board.
         */
        int j = 0;

        /**
         * Reset column index j to 0 and increment row index i by 1 after each row.
         */
        private void reset() {
            if (j == tiles[i].length) {
                j = 0;
                i++;
            }
        }

        @Override
        public boolean hasNext() {

            return i < tiles.length && j < tiles[i].length;
        }

        @Override
        public Tile next() {

            if (!hasNext()) {
                throw new NoSuchElementException();
            } else {
                Tile result = tiles[i][j];
                j++;
                reset();

                return result;
            }


        }
    }
}
