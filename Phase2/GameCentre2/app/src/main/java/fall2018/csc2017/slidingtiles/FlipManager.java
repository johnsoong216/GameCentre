package fall2018.csc2017.slidingtiles;

public class FlipManager extends BoardManager {
    FlipManager(Board board) {
        super(board);
    }

    FlipManager() {
        super();
    }

    @Override
    int getStepcounter() {
        return super.getStepcounter();
    }

    @Override
    void setStepcounter(int stepcounter) {
        super.setStepcounter(stepcounter);
    }

    @Override
    void setTimer(int timer) {
        super.setTimer(timer);
    }

    @Override
    int getTimer() {
        return super.getTimer();
    }

    @Override
    Board getBoard() {
        return super.getBoard();
    }

    @Override
    Stack getMovements() {
        return super.getMovements();
    }

    FlipManager(int complexity) {
        super(complexity);
    }

    @Override
    boolean puzzleSolved() {
        return super.puzzleSolved();
    }

    @Override
    void undo() {
        super.undo();
    }

    @Override
    boolean isValidTap(int position) {
        return super.isValidTap(position);
    }

    @Override
    void touchMove(int position) {
        super.touchMove(position);
    }

    @Override
    int getScore() {
        return super.getScore();
    }

    @Override
    void setUndo(int moves) {
        super.setUndo(moves);
    }
}
