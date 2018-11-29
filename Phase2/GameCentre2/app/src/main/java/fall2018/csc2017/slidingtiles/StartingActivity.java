package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * The initial activity for the sliding puzzle tile game.
 */
public class StartingActivity extends AppCompatActivity {

    /**
     * The main save file.
     */
    public static final String TEMP_SAVE_FILE = "save_game.ser";

    /**
     * The board manager.
     */
    private BoardManager boardManager;

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
    private Loadsave loadsaveManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_);
        context = this;
        loadsaveManager = new Loadsave(context);
        loadButton = findViewById(R.id.LoadButton);
        resumeButton = findViewById(R.id.ResumeButton);
        saveButton = findViewById(R.id.SaveButton);
        startButton = findViewById(R.id.LoginButton);
        signOutButton = findViewById(R.id.SignOutButton);

        user = Session.getCurrentUser();
        username = user.getUsername();

        addStartButtonListener();
        addLoadButtonListener();
        addResumeButtonListener();
        addSaveButtonListener();
        addSignOutButtonListener();
    }

    /**
     * Performs a signout action.
     */
    private void addSignOutButtonListener() {
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.logout();
                Intent logout = new Intent(StartingActivity.this, SignUpSignInActivity.class);
                StartingActivity.this.startActivity(logout);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

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

                boardManager = (BoardManager) loadsaveManager.loadFromFile(TEMP_SAVE_FILE, username, "sliding_tiles");
                loadsaveManager.saveToFile(TEMP_SAVE_FILE, username, "sliding_tiles", boardManager);

                Toast.makeText(context, "Loaded Game", Toast.LENGTH_SHORT).show();
                switchToGame();
                resumeButton.setEnabled(true);
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
                loadsaveManager.loadFromFile(TEMP_SAVE_FILE, username, "sliding_tiles");

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
                loadsaveManager.saveToFile(TEMP_SAVE_FILE, username, "sliding_tiles", boardManager);
                Toast.makeText(context, "Loaded Game", Toast.LENGTH_SHORT).show();
            }
        });
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
        boardManager = (BoardManager) loadsaveManager.loadFromFile(TEMP_SAVE_FILE, username, "sliding_tiles");
        if (boardManager == null) {
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
        Intent tmp = new Intent(this, GameActivity.class);
        loadsaveManager.saveToFile(TEMP_SAVE_FILE, username, "sliding_tiles", boardManager);
        startActivity(tmp);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

    }

    /**
     * Switch to the Complexity view to choose game complexity.
     */
    private void switchToComplexity() {
        Intent complexity = new Intent(this, ComplexityActivity.class);
        complexity.putExtra("game", "sliding");
        startActivity(complexity);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

}

