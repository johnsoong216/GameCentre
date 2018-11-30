package fall2018.csc2017.Games;

import java.io.Serializable;
import java.util.Observable;

/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
abstract class GameManager extends Observable implements Serializable {
    /**
     * A stack of movements that contains the last set of swapping position
     */
    Stack<Integer> movements;

    /**
     * the timer for the game
     */
    int timer = 0;

    /**
     * the maximum number of default undo(maximum 3 undo in this case)
     */
    int defaultUndo = 3;

    /**
     * The complexity of the current game
     */
    int complexity;

    /**
     * counting steps taken
     */
    int stepCounter = 0;

    /**
     * @return number of steps
     */
    int getStepCounter() {
        return stepCounter;
    }

    /**
     * set the current step counter
     *
     * @param stepCounter
     */
    void setStepCounter(int stepCounter) {
        this.stepCounter = stepCounter;
    }


    /**
     * setting the current timer
     *
     * @param timer
     */
    void setTimer(int timer) {
        this.timer = timer;
    }

    /**
     * @return the current timer
     */
    int getTimer() {
        return timer;
    }

    /**
     * @return the stack of positions
     */
    Stack getMovements() {
        return movements;
    }

    /**
     * return the score for the game since different games have different scores calculations
     * it is abstract
     *
     * @return the score for the game
     */
    abstract int getScore();

    /**
     * return whether the current game is over
     *
     * @return whether the game is over
     */
    abstract boolean isGameOver();

    /**
     * setting up undo numbers
     *
     * @param moves
     */
    void setUndo(int moves) {
        defaultUndo = moves * 2;
    }
}
