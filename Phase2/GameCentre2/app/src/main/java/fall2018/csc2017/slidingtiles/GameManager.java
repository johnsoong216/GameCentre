package fall2018.csc2017.slidingtiles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
class GameManager implements Serializable {

    private Game game;


    GameManager(Board game) {
        this.game = game;
    }

    GameManager() {}

    Stack<Integer> movements = new Stack<>();

    int complexity;

    int stepcounter = 0;

    int getStepcounter() {
        return stepcounter;
    }

    void setStepcounter(int stepcounter) {
        this.stepcounter = stepcounter;
    }

    private int timer = 0;

    void setTimer(int timer) {
        this.timer = timer;
    }

    int getTimer() {
        return timer;
    }

    int default_undo;

    Game getGame() {
        return game;
    }

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
        default_undo = moves * 2;
    }
}