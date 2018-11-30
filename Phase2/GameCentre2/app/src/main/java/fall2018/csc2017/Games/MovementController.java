package fall2018.csc2017.Games;

import android.content.Context;
import android.widget.Toast;

public class MovementController {
    GameManager gameManager;

    MovementController(){
    }

    public void setManager(GameManager manager) {
        this.gameManager = manager;
    };

    public GameManager getManager() {
        return gameManager;
    };

    public void processTapMovement(Context context, int position){
        if(gameManager instanceof BoardManager) {
            if (((BoardManager)gameManager).isValidTap(position)) {
                gameManager.touchMove(position);
                if (gameManager.isGameOver()) {
                    Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
            }
        }
        else if(gameManager instanceof FlipManager) {
            ((FlipManager)gameManager).touchColor(position);
            if (gameManager.isGameOver()) {
                Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
