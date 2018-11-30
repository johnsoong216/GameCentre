package fall2018.csc2017.Games;

import android.content.Context;
import android.widget.Toast;

/**
 * A movement controller class that control the movement of two different board game
 */
public class MovementController {
    GameManager gameManager;

    MovementController() {
    }

    /**
     * setting the current manager to the input manager
     * @param manager
     */
    public void setManager(GameManager manager) {
        this.gameManager = manager;
    }

    ;

    /**
     *
     * @return game manager
     */
    public GameManager getManager() {
        return gameManager;
    }

    ;

    /**
     * Preform swapping actions in sliding tiles and flip action in flip it game
     * @param context
     * @param position
     */
    public void processTapMovement(Context context, int position) {
        if (gameManager instanceof SlidingTileBoardManager) {
            if (((SlidingTileBoardManager) gameManager).isValidTap(position)) {
                gameManager.touchMove(position);
                if (gameManager.isGameOver()) {
                    Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
            }
        } else if (gameManager instanceof FlipManager) {
            (gameManager).touchMove(position);
            if (gameManager.isGameOver()) {
                Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
