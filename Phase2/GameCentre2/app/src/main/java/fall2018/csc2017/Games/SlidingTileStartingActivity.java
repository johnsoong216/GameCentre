//excluded from tests because it's a (model / view) class
package fall2018.csc2017.Games;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

/**
 * The initial activity for the sliding puzzle tile game.
 */
public class SlidingTileStartingActivity extends AppCompatActivity {

    /**
     * The main save file.
     */
    public static final String TEMP_SAVE_FILE = "save_game.ser";

    /**
     * The board manager.
     */
    private SlidingTileBoardManager boardManager;

    /**
     * The user's username.
     */
    private String username;

    /**
     * A Session object that holds the user's information.
     */
    private Session user;

    /**
     * The context of this activity.
     */
    private Context context;
    /**
     * Buttons for the user's interactions.
     */
    private Button loadButton;
    private Button resumeButton;
    private Button saveButton;
    private Button startButton;

    private SlidingTilesLoadSaveButtonController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_);
        context = this;
        controller = new SlidingTilesLoadSaveButtonController(context, TEMP_SAVE_FILE);
        loadButton = findViewById(R.id.LoadButton);
        resumeButton = findViewById(R.id.ResumeButton);
        saveButton = findViewById(R.id.SaveButton);
        startButton = findViewById(R.id.StartButton);

        controller.addNewGameButtonListener(startButton);
        controller.addLoadButtonListener(loadButton);
        controller.addResumeButtonListener(resumeButton);
        controller.addSaveButtonListener(saveButton);
    }

    @Override
    public void onBackPressed() {
        Intent backtologin = new Intent(this, ChooseGameActivity.class);
        startActivity(backtologin);
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

