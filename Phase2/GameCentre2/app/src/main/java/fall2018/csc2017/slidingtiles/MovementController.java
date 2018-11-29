package fall2018.csc2017.slidingtiles;

import android.content.Context;

abstract class MovementController {
    GameManager gameManager;

    abstract void setManager(GameManager manager);

    abstract GameManager getManager();

    abstract void processTapMovement(Context context, int position);

}
