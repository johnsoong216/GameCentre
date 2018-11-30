package fall2018.csc2017.Games;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

/**
 * Starting activity for blackjack game, can start new game, load, save and resume
 */
public class BlackJackStartingActivity extends AppCompatActivity {


    /**
     * A temporary save file for blackjack game.
     */
    public static final String TEMP_SAVE_FILE = "save_game.ser";

    /**
     * buttons to be setup
     */
    private Button loadButton;

    private Button saveButton;

    private Button resumeButton;

    private Button newGameButton;

    /**
     * the controller for loading and saving actions
     */
    private BlackJackLoadSaveButtonController controller;

    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_jack_starting);
        context = this;
        controller = new BlackJackLoadSaveButtonController(context, TEMP_SAVE_FILE);
        loadButton = findViewById(R.id.btBJLoad);
        resumeButton = findViewById(R.id.btBJResume);
        saveButton = findViewById(R.id.btBJSave);
        newGameButton = findViewById(R.id.btBJNewGame);
        controller.addLoadButtonListener(loadButton);
        controller.addResumeButtonListener(resumeButton);
        controller.addSaveButtonListener(saveButton);
        controller.addNewGameButtonListener(newGameButton);
    }

    /**
     * Read the temporary board from disk.
     */
    @Override
    protected void onResume() {
        super.onResume();
        controller.resume(saveButton, resumeButton, loadButton);
    }

}
