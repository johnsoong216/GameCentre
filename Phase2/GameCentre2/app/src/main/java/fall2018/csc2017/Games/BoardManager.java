package fall2018.csc2017.Games;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
public class BoardManager extends GameManager{
    /**
     * The board being managed.
     */
    private Board board;

    /**
     * Manage a board that has been pre-populated.
     *
     * @param board the board
     */
    BoardManager(Board board) {
        this.board = board;
    }

    /**
     * Return the current board.
     */
    Board getBoard() {
        return board;
    }

    /**
     * Get the score of the game.
     *
     * @return the score of the game.
     */
    @Override
    int getScore() {
        int result = (int) Math.round(500 + 7.5 * Math.pow(stepCounter, 1 / complexity) -
                150 * Math.log(timer + 1) * Math.pow(complexity, -2) * Math.pow(defaultUndo + 1, 0.5)
                        * Math.pow(stepCounter + 1, 1 / complexity));
        if (result < 0) {
            result = 0;
        }
        return isGameOver() ? (int) (result + Math.pow(complexity, 2) * 20) : result;
    }

    /**
     * Made a new shuffled board
     *
     * @param complexity the complexity of the game.
     */
    BoardManager(int complexity) {
        List<Tile> tiles = new ArrayList<>();
        final int numTiles = complexity * complexity;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            Tile newtile = new Tile(tileNum);
            if (tileNum + 1 == complexity * complexity) {
                newtile.setBackground(R.drawable.tile_25);
            }
            tiles.add(newtile);
        }
        this.complexity = complexity;
        Collections.shuffle(tiles);
        int inv = getInv(tiles);
        int index = tiles.size() - getBlank(tiles) - 1;
        if(complexity % 2 == 1) {
            if(inv % 2 != 0) {
                swap_15_14(tiles);
            }
        } else {
            if((index / complexity) % 2 == 1 && inv % 2 != 1) {
                swap_15_14(tiles);
            }

            while((index / complexity) % 2 == 0 && inv % 2 != 0){
                swap_15_14(tiles);
            }
        }
        this.board = new Board(tiles, complexity);
    }

    private void swap_15_14(List<Tile> tiles) {
        int index1 = 0;
        int index2 = 0;
        for(int i = 0; i < tiles.size(); i ++) {
            if(tiles.get(i).getId() == 15) {
                index1 = i;
            }
            else if(tiles.get(i).getId() == 14) {
                index2 = i;
            }
        }
        Collections.swap(tiles, index1, index2);
    }

    /**
     * Manage a shuffled image board.
     *
     * @param complexity the complexity of the game.
     * @param image      the image being arranged.
     */
    BoardManager(int complexity, String image) {
        List<Tile> tiles = new ArrayList<>();
        final int numTiles = complexity * complexity;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new Tile(tileNum, image));
        }
        this.complexity = complexity;
        Collections.shuffle(tiles);
        this.board = new Board(tiles, complexity);
    }

    int getInv(List<Tile> tiles) {
        int inv = 0;
        for(int i = 0; i < tiles.size()-1; i ++) {
            for(int j = i+1; j < tiles.size(); j ++) {
                if(tiles.get(j).getId() < tiles.get(i).getId()) {
                    inv += 1;
                }
            }
        }
        int index = getBlank(tiles);
        inv -= (tiles.size()-index-1);
        return inv;
    }

    int getBlank(List<Tile> tiles) {
        int index = 0;
        for(int i = 0; i < tiles.size(); i ++) {
            if(tiles.get(i).getId() == tiles.size()) {
                index = i;
            }
        }
        return index;
    }

    /**
     * Return whether the tiles are in row-major order.
     *
     * @return whether the tiles are in row-major order
     */
    boolean isGameOver() {
        boolean solved = true;
        int num = 1;
        for (Tile tile : board) {
            if (tile.getId() != num) {
                solved = false;
            }
            num++;
        }
        return solved;
    }

    /**
     * Return whether any of the four surrounding tiles is the blank tile.
     *
     * @param position the tile to check
     * @return whether the tile at position is surrounded by a blank tile
     */
    boolean isValidTap(int position) {

        int row = position / board.getNUM_ROWS();
        int col = position % board.getNUM_COLS();
        int blankId = board.numTiles();
        // Are any of the 4 the blank tile?
        Tile above = row == 0 ? null : board.getTile(row - 1, col);
        Tile below = row == board.getNUM_ROWS() - 1 ? null : board.getTile(row + 1, col);
        Tile left = col == 0 ? null : board.getTile(row, col - 1);
        Tile right = col == board.getNUM_COLS() - 1 ? null : board.getTile(row, col + 1);
        return (below != null && below.getId() == blankId)
                || (above != null && above.getId() == blankId)
                || (left != null && left.getId() == blankId)
                || (right != null && right.getId() == blankId);
    }

    /**
     * Process a touch at position in the board, swapping tiles as appropriate.
     *
     * @param position the position
     */
    void touchMove(int position) {

        int row = position / board.getNUM_ROWS();
        int col = position % board.getNUM_COLS();
        int blankId = board.numTiles();
        int blankposition = -1;

        for (Tile tile : board) {
            blankposition++;
            if (tile.getId() == blankId) {
                break;
            }
        }
        stepCounter++;
        movements.push(position);
        movements.push(blankposition);
        movements.popLastTwo(defaultUndo);
        board.swapTiles(row, col, blankposition / board.getNUM_COLS(), blankposition % board.getNUM_ROWS());

        // tiles is the blank tile, swap by calling Board's swap method.
    }

    /**
     * Undo one step the user has made.
     */
    @Override
    void undo() {
        if (!movements.isEmpty()) {
            stepCounter++;
            int blank = movements.pop();
            int position = movements.pop();
            board.swapTiles(position / board.getNUM_ROWS(), position % board.getNUM_COLS(), blank / board.getNUM_ROWS(), blank % board.getNUM_COLS());
        }
    }
}
