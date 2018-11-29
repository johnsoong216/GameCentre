package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class BlackJackStartingActivity extends AppCompatActivity {


    /**
     * A temporary save file for blackjack game.
     */
    public static final String TEMP_SAVE_FILE = "save_game.ser";

    /**
     * The blackjack game manager.
     */
    private BlackJackManager blackJackManager;

    private String username;

    private Session user;

    private Button loadButton;

    private Button saveButton;

    private Button resumeButton;

    private Button newGameButton;

    private Loadsave loadsaveManager;

    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_jack_starting);
        context = this;
        loadsaveManager = new Loadsave(context);
        loadButton = findViewById(R.id.btBJLoad);
        resumeButton = findViewById(R.id.btBJResume);
        saveButton = findViewById(R.id.btBJSave);
        newGameButton = findViewById(R.id.btBJNewGame);
        user = Session.getCurrentUser();
        username = user.getUsername();
        addLoadButtonListener();
        addResumeButtonListener();
        addSaveButtonListener();
        addStartButtonListener();
    }

    /**
     * Performs a signout action.
     */
//    private void addSignOutButtonListener() {
//        signOutButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                user.logout();
//                Intent logout = new Intent(BlackJackStartingActivity.this, SignUpSignInActivity.class);
//                BlackJackStartingActivity.this.startActivity(logout);
//            }
//        });
//    }

    /**
     * Activate the start button.
     */
    private void addStartButtonListener() {
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] winDrawLoss = {0,0,0};
                blackJackManager = new BlackJackManager(new BlackJackGame(), 1000, winDrawLoss);
                loadsaveManager.saveToFile(TEMP_SAVE_FILE, username, "black_jack", blackJackManager);
                switchToGame();
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
                blackJackManager = (BlackJackManager) loadsaveManager.loadFromFile(TEMP_SAVE_FILE, username, "black_jack");
                Toast.makeText(context, "Loaded Game", Toast.LENGTH_SHORT).show();
                switchToGame();
                resumeButton.setEnabled(true);
            }
        });
    }

//
//    private void addScoreBoardListener() {
//        scoreBoardButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                switchToScoreBoard();
//            }
//        });
//    }

    /**
     * Activate the resume button
     */
    private void addResumeButtonListener() {
        resumeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blackJackManager = (BlackJackManager) loadsaveManager.loadFromFile(TEMP_SAVE_FILE, username, "black_jack");
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
                loadsaveManager.saveToFile(TEMP_SAVE_FILE, username, "black_jack", blackJackManager);
                Toast.makeText(context, "Saved Game", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        user.logout();
        Intent backtologin = new Intent(this, ChooseGameActivity.class);
        startActivity(backtologin);
    }

    /**
     * Read the temporary board from disk.
     */
    @Override
    protected void onResume() {
        super.onResume();
        blackJackManager = (BlackJackManager) loadsaveManager.loadFromFile(TEMP_SAVE_FILE, username, "black_jack");
        if (blackJackManager == null) {
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
     * Switch to the BlackJackGameActivity view to play the game.
     */
    private void switchToGame() {
        Intent tmp = new Intent(this, BlackJackComplexityActivity.class);
        loadsaveManager.saveToFile(TEMP_SAVE_FILE, username, "black_jack", blackJackManager);
        startActivity(tmp);
    }

//
//    private void switchToScoreBoard() {
//        Intent complexity = new Intent(this, ScoreBoardActivity.class);
//        startActivity(complexity);
//    }

}
