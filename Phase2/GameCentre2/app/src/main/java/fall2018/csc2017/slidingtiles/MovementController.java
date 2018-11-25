package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.widget.Toast;


public class MovementController {

    private BoardManager boardManager = null;
    private FlipManager flipManager = null;

/*
    private ArrayList<Board> boards = new ArrayList<>(10);
*/

    public MovementController() {
    }

    public void setBoardManager(BoardManager boardManager) {
        this.boardManager = boardManager;
    }
    public void setFlipManager(FlipManager flipManager) {
        this.flipManager = flipManager;
    }

    public void processTapMovement(Context context, int position) {
//        if (boardManager.isValidTap(position)) {
            flipManager.touchColor(position);
            if (flipManager.puzzleSolved()) {
                Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
            }
//        } else {
//            Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
//        }
    }
}

