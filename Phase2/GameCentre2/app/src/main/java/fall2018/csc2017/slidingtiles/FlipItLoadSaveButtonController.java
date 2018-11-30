package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

/**
 * Subclass of LoadSaveButtonController to setup blackjack game's load save buttons
 */
class FlipItLoadSaveButtonController extends LoadSaveButtonController {
    /**
     * create a LoadSaveButton controller
     * @param context
     * @param saveFile
     */
    FlipItLoadSaveButtonController(Context context, String saveFile){
        super(context, saveFile);
        this.gameType = "flip_it";
    }
    @Override
    void addNewGameButtonListener(Button newGameButton) {
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadSaveManager.saveToFile(saveFile, username, gameType, gameManager);
                switchToGame();
            }
        });
    }
    @Override
    void switchToGame() {
        {
            Intent tmp = new Intent(context, FlipGameActivity.class);
            LoadSaveManager.saveToFile(saveFile, username, gameType, gameManager);
            context.startActivity(tmp);
        }
    }

    @Override
    void switchToComplexity() {
        {
            Intent tmp = new Intent(context, FlipComplexityActivity.class);
            LoadSaveManager.saveToFile(saveFile, username, gameType, gameManager);
            context.startActivity(tmp);
        }
    }
}
