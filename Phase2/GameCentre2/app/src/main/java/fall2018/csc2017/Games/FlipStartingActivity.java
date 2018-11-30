package fall2018.csc2017.Games;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class FlipStartingActivity extends AppCompatActivity {

    /**
     * The main save file.
     */
    public static final String TEMP_SAVE_FILE = "save_game.ser";
    /**
     * The board manager.
     */

    private FlipManager flipManager;

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
    private Button scoreBoardButton;
    private LoadSave LoadSaveManager;
    private FlipItLoadSaveButtonController controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flip_starting);
        context = this;
        controller = new FlipItLoadSaveButtonController(context, TEMP_SAVE_FILE);
        user = Session.getCurrentUser();
        username = user.getUsername();
        LoadSaveManager = new LoadSave(context);
        loadButton = findViewById(R.id.LoadButton);
        resumeButton = findViewById(R.id.ResumeButton);
        saveButton = findViewById(R.id.SaveButton);
        startButton = findViewById(R.id.StartButton);
        scoreBoardButton = findViewById(R.id.scoreBoardButton);
        controller.addLoadButtonListener(loadButton);
        controller.addResumeButtonListener(resumeButton);
        controller.addSaveButtonListener(saveButton);
        controller.addNewGameButtonListener(startButton);

    }

    /**
     * Read the temporary board from disk.
     */
    @Override
    protected void onResume() {
        super.onResume();
        flipManager = (FlipManager) LoadSaveManager.loadFromFile(TEMP_SAVE_FILE, username, "flip_it");
        if (flipManager == null) {
            loadButton.setEnabled(false);
            saveButton.setEnabled(false);
            resumeButton.setEnabled(false);
        } else {
            loadButton.setEnabled(true);
            saveButton.setEnabled(true);
            resumeButton.setEnabled(true);
        }
    }


    /**
     * Switch to the GameActivity view to play the game.
     */
    private void switchToGame() {
        Intent tmp = new Intent(this, FlipGameActivity.class);
        LoadSaveManager.saveToFile(TEMP_SAVE_FILE, username, "flip_it", flipManager);
        startActivity(tmp);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

    }

    /**
     * Switch to the Complexity view to choose game complexity.
     */
    private void switchToComplexity() {
        Intent complexity = new Intent(this, FlipComplexityActivity.class);
        complexity.putExtra("game", "flip");
        startActivity(complexity);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

    }


    /**
     * Switch the current activity to black jack game activity
     */
    private void switchToScoreBoard() {
        Intent complexity = new Intent(this, ScoreBoardActivity.class);
        startActivity(complexity);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

    }
}