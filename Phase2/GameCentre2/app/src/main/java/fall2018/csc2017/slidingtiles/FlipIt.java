package fall2018.csc2017.slidingtiles;

import android.support.annotation.NonNull;

import java.util.Iterator;
import java.util.List;

public class FlipIt extends Game {
    @Override
    int getNUM_ROWS() {
        return super.getNUM_ROWS();
    }

    @Override
    void setNUM_ROWS(int NUM_ROWS) {
        super.setNUM_ROWS(NUM_ROWS);
    }

    @Override
    int getNUM_COLS() {
        return super.getNUM_COLS();
    }

    @Override
    void setNUM_COLS(int NUM_COLS) {
        super.setNUM_COLS(NUM_COLS);
    }

    FlipIt(List<Tile> tile, int complexity) {
        super(tile, complexity);
    }

    @Override
    int numTiles() {
        return super.numTiles();
    }

    @Override
    Tile getTile(int row, int col) {
        return super.getTile(row, col);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @NonNull
    @Override
    public Iterator<Tile> iterator() {
        return super.iterator();
    }

}
