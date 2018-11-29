package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.widget.Toast;


public class BoardMovementController extends MovementController {

    private BoardManager boardManager = null;

    public BoardMovementController() {}

    @Override
    public void setManager(GameManager boardManager) {
        this.boardManager = (BoardManager) boardManager;
    }

    @Override
    public BoardManager getManager() {return boardManager;}

    @Override
    public void processTapMovement(Context context, int position) {
        if (boardManager.isValidTap(position)) {
            boardManager.touchMove(position);
            if (boardManager.puzzleSolved()) {
                Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
        }
    }
}

