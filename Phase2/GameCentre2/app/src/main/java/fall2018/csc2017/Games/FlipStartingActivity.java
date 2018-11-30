//excluded from tests because it's a (model / view) class
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
    private LoadSave loadSaveManager;
    private FlipItLoadSaveButtonController controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flip_starting);
        context = this;
        controller = new FlipItLoadSaveButtonController(context, TEMP_SAVE_FILE);
        user = Session.getCurrentUser();
        username = user.getUsername();
        loadSaveManager = new LoadSave(context);
        loadButton = findViewById(R.id.LoadButton);
        resumeButton = findViewById(R.id.ResumeButton);
        saveButton = findViewById(R.id.SaveButton);
        startButton = findViewById(R.id.StartButton);
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
        flipManager = (FlipManager) loadSaveManager.loadFromFile(TEMP_SAVE_FILE, username, "flip_it");
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
    @Override
    public void onBackPressed() {
        Intent backToLogin = new Intent(this, ChooseGameActivity.class);
        startActivity(backToLogin);
    }
}