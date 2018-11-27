package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.widget.Toast;


public class MovementController {

    private BoardManager boardManager;
    private FlipManager flipManager;
    private GameManager gameManager;


    public MovementController() {}

//    public void setBoardManager(BoardManager boardManager) {
//        this.boardManager = boardManager;
//    }
//    public void setFlipManager(FlipManager flipManager) {
//        this.flipManager = flipManager;
//    }

    public void setGameManager(GameManager gameManager) {
        if (gameManager instanceof BoardManager){
            this.boardManager = (BoardManager) gameManager;
            this.flipManager = null;
        }
        else if (gameManager instanceof FlipManager){
            this.flipManager = (FlipManager) gameManager;
            this.boardManager = null;
        }
    }

    public void processTapMovement(Context context, int position) {
        if (boardManager != null && flipManager == null){
            if (boardManager.isValidTap(position)) {
                boardManager.touchMove(position);
                if (boardManager.puzzleSolved()) {
                    Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
            }
        }
        else if (flipManager != null && boardManager == null){
            flipManager.touchColor(position);
            if (flipManager.puzzleSolved()) {
                Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

