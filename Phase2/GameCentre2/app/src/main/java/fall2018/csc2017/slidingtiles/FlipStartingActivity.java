package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FlipStartingActivity extends AppCompatActivity {

    /**
     * The main save file.
     */
    public static final String SAVE_FLIP = "save_flip.ser";
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
    private Button signOutButton;
    private Button scoreBoardButton;
    private Loadsave loadsaveManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flip_starting);
        context = this;
        loadsaveManager = new Loadsave(context);
        loadButton = findViewById(R.id.LoadButton);
        resumeButton = findViewById(R.id.ResumeButton);
        saveButton = findViewById(R.id.SaveButton);
        startButton = findViewById(R.id.StartButton);
        signOutButton = findViewById(R.id.SignOutButton);
        scoreBoardButton = findViewById(R.id.scoreBoardButton);

        user = Session.getCurrentUser();
        username = user.getUsername();

        addStartButtonListener();
        addLoadButtonListener();
        addResumeButtonListener();
        addSaveButtonListener();
        addSignOutButtonListener();
        addScoreBoardListener();
    }

    /**
     * Performs a signout action.
     */
    private void addSignOutButtonListener() {
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.logout();
                Intent logout = new Intent(FlipStartingActivity.this, SignUpSignInActivity.class);
                FlipStartingActivity.this.startActivity(logout);
            }
        });
    }

    /**
     * Activate the start button.
     */
    private void addStartButtonListener() {
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToComplexity();
            }
        });
    }

    /**
     * Activate the load button.
     */
    private void addLoadButtonListener() {
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                flipManager = (FlipManager) loadsaveManager.loadFromFile(SAVE_FLIP, username);
                loadsaveManager.saveToFile(SAVE_FLIP, username, flipManager);

                Toast.makeText(context, "Loaded Game", Toast.LENGTH_SHORT).show();
                switchToGame();
                resumeButton.setEnabled(true);
            }
        });
    }


    private void addScoreBoardListener() {
        scoreBoardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToScoreBoard();
            }
        });
    }

    /**
     * Activate the resume button
     */
    private void addResumeButtonListener() {
        resumeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadsaveManager.loadFromFile(SAVE_FLIP, username);

                Toast.makeText(context, "Loaded Game", Toast.LENGTH_SHORT).show();
                switchToGame();
            }
        });
    }


    /**
     * Activate the save button.
     */
    private void addSaveButtonListener() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadsaveManager.saveToFile(SAVE_FLIP, username, flipManager);
                Toast.makeText(context, "Loaded Game", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        user.logout();
        Intent backtologin = new Intent(this, SignUpSignInActivity.class);
        startActivity(backtologin);
    }

    /**
     * Read the temporary board from disk.
     */
    @Override
    protected void onResume() {
        super.onResume();
        flipManager = (FlipManager) loadsaveManager.loadFromFile(SAVE_FLIP, username);
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
        loadsaveManager.saveToFile(SAVE_FLIP, username, flipManager);
        startActivity(tmp);
    }

    /**
     * Switch to the Complexity view to choose game complexity.
     */
    private void switchToComplexity() {
        Intent complexity = new Intent(this, FlipComplexity.class);
        complexity.putExtra("game", "flip");
        startActivity(complexity);
    }


    private void switchToScoreBoard() {
        Intent complexity = new Intent(this, ScoreBoardActivity.class);
        startActivity(complexity);
    }
}
