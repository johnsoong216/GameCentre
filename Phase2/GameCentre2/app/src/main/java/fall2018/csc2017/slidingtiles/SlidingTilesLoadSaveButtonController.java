package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class SlidingTilesLoadSaveButtonController extends LoadSaveButtonController {

    SlidingTilesLoadSaveButtonController(Context context, String saveFile){
        super(context, saveFile);
        this.gameType = "sliding_tiles";
    }
    @Override
    void addNewGameButtonListener(Button newGameButton) {
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadSaveManager.saveToFile(saveFile, username, gameType, gameManager);
                switchToComplexity();
            }
        });
    }

    @Override
    void switchToGame() {
        Intent tmp = new Intent(context, GameActivity.class);
        LoadSaveManager.saveToFile(saveFile, username, gameType, gameManager);
        context.startActivity(tmp);
    }
    @Override
    /**
     * Switch to the Complexity view to choose game complexity.
     */
    void switchToComplexity() {
        Intent complexity = new Intent(context, ComplexityActivity.class);
        context.startActivity(complexity);
    }


}
