package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.widget.Toast;

public class FlipMovementController {

    private FlipManager flipManager;

    public FlipMovementController() { }

    public void setFlipManager(FlipManager flipManager) { this.flipManager = flipManager; }

    public void processTapMovement(Context context, int position) {
        flipManager.touchColor(position);
        if (flipManager.puzzleSolved()) {
            Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
        }
    }
}