package fall2018.csc2017.Games;

import java.io.Serializable;
import java.util.Observable;

/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
abstract class GameManager extends Observable implements Serializable {

    Stack<Integer> movements;

    int complexity;

    int stepCounter = 0;

    int getStepCounter() {
        return stepCounter;
    }

    void setStepCounter(int stepCounter) {
        this.stepCounter = stepCounter;
    }

    int timer = 0;

    void setTimer(int timer) {
        this.timer = timer;
    }

    int getTimer() {
        return timer;
    }

    int defaultUndo;

    Stack getMovements() {
        return movements;
    }

    void touchMove(int position){
    };

    abstract int getScore();

    abstract boolean isGameOver();

    void undo() {};

    void setUndo(int moves) {
        defaultUndo = moves * 2;
    }
}
