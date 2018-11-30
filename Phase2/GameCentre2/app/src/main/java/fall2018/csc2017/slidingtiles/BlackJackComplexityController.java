package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;


public class BlackJackComplexityController extends ComplexityController {


    private BlackJackManager blackJackManager;

    BlackJackComplexityController(Context context, String saveFile){
        super(context, saveFile);
        this.blackJackManager = (BlackJackManager) loadSaveManager.loadFromFile(saveFile, username, "black_jack");

    }

    void addEasyButtonListener(Button easyButton) {
        easyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blackJackManager.setComplexity(0.8);
                switchToGame();
            }
        });
    }

    void addNormalButtonListener(Button normalButton){
        normalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blackJackManager.setComplexity(1);
                switchToGame();
            }
        });
    }

    void addHardButtonListener(Button hardButton){
        hardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blackJackManager.setComplexity(1.2);
                switchToGame();
            }
        });
    }

    void switchToGame() {
        loadSaveManager.saveToFile(saveFile, username, "black_jack", blackJackManager);
        Intent tmp = new Intent(context, BlackJackGameActivity.class);
        context.startActivity(tmp);
    }



}
