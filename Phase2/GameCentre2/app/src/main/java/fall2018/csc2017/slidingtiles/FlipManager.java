package fall2018.csc2017.slidingtiles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FlipManager extends GameManager{

    /**
     * The board being managed.
     */
    private FlipIt flip;

    FlipManager(FlipIt flip) {
        this.flip = flip;
    }

    /**
     * FlipManager constructor
     */
    FlipManager() {
    }

    /**
     * Return the current board.
     */
    FlipIt getFlip() {
        return flip;
    }

    /**
     * Get the score of the game.
     *
     * @return the score of the game.
     */
    @Override
    int getScore() {
        int result = (int) Math.round(1000 + 7.5 * Math.pow(stepCounter, 1 / complexity) -
                150 * Math.log(timer + 1) * Math.pow(complexity, -2) * Math.pow(defaultUndo + 1, 0.5)
                        * Math.pow(stepCounter + 1, 1 / complexity));

        if (result < 0) {
            result = 0;
        }
        return puzzleSolved() ? (int) (result + Math.pow(complexity, 2) * 20) : result;
    }

    /**
     * Made a new flip board
     *
     * @param complexity the complexity of the game.
     * @param level the level of the game.
     */
    FlipManager(int complexity, int level) {

        List<Tile> tiles = new ArrayList<>();
        if (complexity == 3) {
            if(level == 1){
                for (int tileNum = 0; tileNum != 9; tileNum++) {
                    Tile newtile = new Tile(1);
                    newtile.setBackground(R.drawable.flip_it);
                    if (tileNum == 2 || tileNum == 4 || tileNum == 6) {
                        newtile.setBackground(R.drawable.back);
                    }
                    tiles.add(newtile);
                }
                this.complexity = complexity;
                this.flip = new FlipIt(tiles, complexity);
            }
            else if (level == 2){
                for (int tileNum = 0; tileNum != 9; tileNum++) {
                    Tile newtile = new Tile(1);
                    newtile.setBackground(R.drawable.flip_it);
                    if (tileNum == 1 || tileNum == 2 || tileNum == 6 || tileNum == 8) {
                        newtile.setBackground(R.drawable.back);
                    }
                    tiles.add(newtile);
                }
                this.complexity = complexity;
                this.flip = new FlipIt(tiles, complexity);
            }
            else if (level == 3){
                for (int tileNum = 0; tileNum != 9; tileNum++) {
                    Tile newtile = new Tile(1);
                    newtile.setBackground(R.drawable.flip_it);
                    if (tileNum == 1 || tileNum == 2 || tileNum == 5 || tileNum == 7 || tileNum == 8) {
                        newtile.setBackground(R.drawable.back);
                    }
                    tiles.add(newtile);
                }
                this.complexity = complexity;
                this.flip = new FlipIt(tiles, complexity);
            }

        } else if (complexity == 4) {
            if (level == 1){
                for (int tileNum = 0; tileNum != 16; tileNum++) {
                    Tile newtile = new Tile(1);
                    newtile.setBackground(R.drawable.flip_it);
                    if (tileNum == 0 || tileNum == 2 || tileNum == 9 || tileNum == 14) {
                        newtile.setBackground(R.drawable.back);
                    }
                    tiles.add(newtile);
                }
                this.complexity = complexity;
                this.flip = new FlipIt(tiles, complexity);
            }
            else if (level == 2){
                for (int tileNum = 0; tileNum != 16; tileNum++) {
                    Tile newtile = new Tile(1);
                    newtile.setBackground(R.drawable.flip_it);
                    if (tileNum == 0 || tileNum == 3 || tileNum == 5 || tileNum == 6 || tileNum == 9|| tileNum == 10 || tileNum == 12 || tileNum == 15) {
                        newtile.setBackground(R.drawable.back);
                    }
                    tiles.add(newtile);
                }
                this.complexity = complexity;
                this.flip = new FlipIt(tiles, complexity);
            }
            else if (level == 3){
                for (int tileNum = 0; tileNum != 16; tileNum++) {
                    Tile newtile = new Tile(1);
                    newtile.setBackground(R.drawable.flip_it);
                    if (tileNum == 0 || tileNum == 5 || tileNum == 10 || tileNum == 15) {
                        newtile.setBackground(R.drawable.back);
                    }
                    tiles.add(newtile);
                }
                this.complexity = complexity;
                this.flip = new FlipIt(tiles, complexity);
            }


        } else if (complexity == 5) {
            if (level ==1){
                for (int tileNum = 0; tileNum != 25; tileNum++) {
                    Tile newtile = new Tile(1);
                    newtile.setBackground(R.drawable.flip_it);
                    if (tileNum == 0 || tileNum == 2 || tileNum == 4 || tileNum == 12 || tileNum == 15 || tileNum == 18 || tileNum == 19) {
                        newtile.setBackground(R.drawable.back);
                    }
                    tiles.add(newtile);
                }
                this.complexity = complexity;
                this.flip = new FlipIt(tiles, complexity);
            }
            else if (level == 2){
                for (int tileNum = 0; tileNum != 25; tileNum++) {
                    Tile newtile = new Tile(1);
                    newtile.setBackground(R.drawable.back);
                    if (tileNum == 1 || tileNum == 3 || tileNum == 5 || tileNum == 6 ||
                            tileNum == 9 || tileNum == 12 || tileNum == 15 || tileNum == 16 ||
                            tileNum == 17 || tileNum == 19 || tileNum == 21 || tileNum == 24) {
                        newtile.setBackground(R.drawable.flip_it);
                    }
                    tiles.add(newtile);
                }
                this.complexity = complexity;
                this.flip = new FlipIt(tiles, complexity);
            }
            else if (level == 3){
                for (int tileNum = 0; tileNum != 25; tileNum++) {
                    Tile newtile = new Tile(1);
                    newtile.setBackground(R.drawable.back);
                    if (tileNum == 0 || tileNum == 4 || tileNum == 15 || tileNum == 19) {
                        newtile.setBackground(R.drawable.flip_it);
                    }
                    tiles.add(newtile);
                }
                this.complexity = complexity;
                this.flip = new FlipIt(tiles, complexity);
            }
        }

    }

    /**
     *
     * @return whether the game is solved.
     */
    @Override
    boolean puzzleSolved() {
        boolean solved = true;
        for (Tile tile : flip) {
            if (tile.getBackground() != R.drawable.back) {
                solved = false;
            }
        }
        return solved;
    }

    /**
     * Touch a tile, the surrounding four will change color as well
     * @param position the position of the tile that is being touched.
     */
    void touchColor(int position) {

        int upId = position - this.complexity;
        int downId = position + this.complexity;
        int leftId = position - 1;
        int rightId = position + 1;

        stepCounter++;
        movements.push(position);
        movements.push(upId);
        movements.push(downId);
        movements.push(leftId);
        movements.push(rightId);
        movements.poplastfive(defaultUndo);

        flip.changeColor(position / flip.getNUM_ROWS(), position % flip.getNUM_COLS());

        if (upId >= 0) {
            flip.changeColor(upId / flip.getNUM_ROWS(), upId % flip.getNUM_COLS());
        }
        if (leftId / this.complexity == position / this.complexity && leftId >= 0) {
            flip.changeColor(leftId / flip.getNUM_ROWS(), leftId % flip.getNUM_COLS());
        }
        if (rightId / this.complexity == position / this.complexity && rightId <= flip.numTiles() - 1) {
            flip.changeColor(rightId / flip.getNUM_ROWS(), rightId % flip.getNUM_COLS());
        }
        if (downId <= flip.numTiles() - 1) {
            flip.changeColor(downId / flip.getNUM_ROWS(), downId % flip.getNUM_COLS());
        }
    }

    /**
     * Undo one step the user has made.
     */
    @Override
    void undo() {

        if (!movements.isEmpty()) {
            stepCounter++;

            int rightId = movements.pop();
            int leftId = movements.pop();
            int downId = movements.pop();
            int upId = movements.pop();
            int position = movements.pop();

            flip.changeColor(position / flip.getNUM_ROWS(), position % flip.getNUM_COLS());
            if (upId >= 0) {
                flip.changeColor(upId / flip.getNUM_ROWS(), upId % flip.getNUM_COLS());
            }
            if (leftId / this.complexity == position / this.complexity && leftId >= 0) {
                flip.changeColor(leftId / flip.getNUM_ROWS(), leftId % flip.getNUM_COLS());
            }
            if (rightId / this.complexity == position / this.complexity && rightId <= flip.numTiles() - 1) {
                flip.changeColor(rightId / flip.getNUM_ROWS(), rightId % flip.getNUM_COLS());
            }
            if (downId <= flip.numTiles() - 1) {
                flip.changeColor(downId / flip.getNUM_ROWS(), downId % flip.getNUM_COLS());
            }
        }
    }

    /**
     * Setting the number of undo times a user can make.
     *
     * @param moves the move taken.
     */
    void setUndo(int moves) {
        defaultUndo = moves * 5;
    }


}
