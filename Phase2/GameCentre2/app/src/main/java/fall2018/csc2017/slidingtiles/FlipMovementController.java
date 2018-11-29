package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.widget.Toast;


public class FlipMovementController extends MovementController{
    private FlipManager flipManager = null;

    public FlipMovementController() {}

    @Override
    public void setManager(GameManager flipManager) {
        this.flipManager = (FlipManager) flipManager;
    }

    @Override
    public void processTapMovement(Context context, int position) {
        flipManager.touchColor(position);
        if (flipManager.puzzleSolved()) {
            Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    FlipManager getManager() {return flipManager;}
}

