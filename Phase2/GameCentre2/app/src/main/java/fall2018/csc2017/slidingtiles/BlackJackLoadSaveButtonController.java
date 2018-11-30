package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

/**
 * Subclass of loadSaveButtonController to setup blackjack game's load save buttons
 */
class BlackJackLoadSaveButtonController extends LoadSaveButtonController {
    /**
     * create a loadSaveButton controller
     * @param context
     * @param saveFile
     */
    BlackJackLoadSaveButtonController(Context context, String saveFile){
        super(context, saveFile);
        this.gameType = "black_jack";
    }
    @Override
    void addNewGameButtonListener(Button newGameButton) {
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameManager = new BlackJackManager(new BlackJackGame());
                switchToComplexity();
            }
        });
    }

    @Override
    void switchToGame() {
        {
            Intent tmp = new Intent(context, BlackJackGameActivity.class);
            loadSaveManager.saveToFile(saveFile, username, gameType, gameManager);
            context.startActivity(tmp);
        }
    }

    @Override
    void switchToComplexity() {
        {
            Intent tmp = new Intent(context, BlackJackComplexityActivity.class);
            loadSaveManager.saveToFile(saveFile, username, gameType, gameManager);
            context.startActivity(tmp);
        }
    }



}
