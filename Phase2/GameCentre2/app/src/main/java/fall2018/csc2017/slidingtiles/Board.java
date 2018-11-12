
package fall2018.csc2017.slidingtiles;

import android.support.annotation.NonNull;

import java.util.NoSuchElementException;
import java.util.Observable;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * The sliding tiles board.
 */
public class Board extends Observable implements Serializable, Iterable<Tile> {

    /**
     * The number of rows of the board.
     */
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
    Board(List<Tile> tile, int complexity) {
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

    /**
     * Return the number of tiles on the board.
     *
     * @return the number of tiles on the board
     */
    int numTiles() {
        return getNUM_COLS() * getNUM_ROWS();
    }

    /**
     * Return the tile at (row, col)
     *
     * @param row the tile row
     * @param col the tile column
     * @return the tile at (row, col)
     */
    Tile getTile(int row, int col) {
        return tiles[row][col];
    }

    /**
     * Swap the tiles at (row1, col1) and (row2, col2)
     *
     * @param row1 the first tile row
     * @param col1 the first tile col
     * @param row2 the second tile row
     * @param col2 the second tile col
     */
    void swapTiles(int row1, int col1, int row2, int col2) {
        Tile item1 = tiles[row1][col1];
        Tile item2 = tiles[row2][col2];
        tiles[row2][col2] = item1;
        tiles[row1][col1] = item2;
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
    @Override
    public Iterator<Tile> iterator() {
        return new TileIterator();
    }

    private class TileIterator implements Iterator<Tile> {
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
