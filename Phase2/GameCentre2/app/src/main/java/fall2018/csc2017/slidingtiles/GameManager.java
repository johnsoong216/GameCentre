package fall2018.csc2017.slidingtiles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
abstract class GameManager extends Observable implements Serializable {

    Stack<Integer> movements = new Stack<>();

    int complexity;

    int stepCounter = 0;

    int getStepCounter() {
        return stepCounter;
    }

    void setStepCounter(int stepCounter) {
        this.stepCounter = stepCounter;
    }

    private int timer = 0;

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

    void touchMove(int position) {
    }

    int getScore() {
        return 1;
    }

    boolean puzzleSolved() {
        return true;
    }

    void undo() {}

    void setUndo(int moves) {
        defaultUndo = moves * 2;
    }
}
