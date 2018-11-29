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
     * Stack that holds previous user moves.
     */
    private Stack<Integer> movements = new Stack<>();

    /**
     * The game level.
     */
    private int complexity;

    /**
     * FlipManager constructor
     */
    FlipManager() {
    }

    /**
     * A step counter for the number of steps a user made.
     */
    private int stepcounter = 0;

    /**
     * Return the number of steps the user has made.
     *
     * @return the number of steps the user has made.
     */
    int getStepCounter() {
        return stepcounter;
    }

    /**
     * Set the number of steps the user has made.
     *
     * @param stepcounter number of steps taken.
     */
    void setStepCounter(int stepcounter) {
        this.stepcounter = stepcounter;
    }

    /**
     * Timer of the game.
     */
    private int timer = 0;

    /**
     * Set the timer of the game.
     *
     * @param timer timer of the game.
     */
    void setTimer(int timer) {
        this.timer = timer;
    }

    /**
     * Get the timer of the game.
     *
     * @return the timer of the game.
     */
    int getTimer() {
        return timer;
    }

    /**
     * Default undo allows the user to undo 3 steps.
     */
    private int default_undo;

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
    int getScore() {
        int result = (int) Math.round(1000 + 7.5 * Math.pow(stepcounter, 1 / complexity) -
                150 * Math.log(timer + 1) * Math.pow(complexity, -2) * Math.pow(default_undo + 1, 0.5)
                        * Math.pow(stepcounter + 1, 1 / complexity));

        if (result < 0) {
            result = 0;
        }
        return puzzleSolved() ? (int) (result + Math.pow(complexity, 2) * 20) : result;
    }

    /**
     * Return a stack containing the moves a user has made.
     *
     * @return a stack containing the moves a user has made.
     */
    Stack getMovements() {
        return movements;
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

        stepcounter++;
        movements.push(position);
        movements.push(upId);
        movements.push(downId);
        movements.push(leftId);
        movements.push(rightId);
        movements.poplastfive(default_undo);

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
    void undo() {

        if (!movements.isEmpty()) {
            stepcounter++;

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
        default_undo = moves * 5;
    }


}
