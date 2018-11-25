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
     * The main save file for blackjack game.
     */
    public static final String SAVE_FILENAME_BLACK_JACK = "save_file_black_jack.ser";
    /**
     * A temporary save file for blackjack game.
     */
    public static final String TEMP_SAVE_FILENAME_BLACK_JACK = "save_file_tmp_black_jack.ser";

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

    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_jack_starting);
        context = this;
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
                blackJackManager = new BlackJackManager(new BlackJackGame(), 1000);
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
                blackJackManager = loadFromFile(SAVE_FILENAME_BLACK_JACK, username);
                saveToFile(TEMP_SAVE_FILENAME_BLACK_JACK, username, blackJackManager);
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
                loadFromFile(TEMP_SAVE_FILENAME_BLACK_JACK, username);
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
                saveToFile(SAVE_FILENAME_BLACK_JACK, username, blackJackManager);
                saveToFile(TEMP_SAVE_FILENAME_BLACK_JACK, username, blackJackManager);
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
        blackJackManager = loadFromFile(TEMP_SAVE_FILENAME_BLACK_JACK, username);
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
        Intent tmp = new Intent(this, BlackJackGameActivity.class);
        saveToFile(TEMP_SAVE_FILENAME_BLACK_JACK, username, blackJackManager);
        startActivity(tmp);
    }

//
//    private void switchToScoreBoard() {
//        Intent complexity = new Intent(this, ScoreBoardActivity.class);
//        startActivity(complexity);
//    }
    public BlackJackManager loadFromFile(String fileName, String username) {

        try {
            InputStream inputStream = this.openFileInput(username + fileName);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                BlackJackManager manager = (BlackJackManager) input.readObject();
                inputStream.close();
                return manager;
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
        }
        return null;
    }

    /**
     * Save the blackjack manager to fileName.
     *
     * @param fileName the name of the file
     */
    public void saveToFile(String fileName, String username, BlackJackManager manager) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(this.openFileOutput(username + fileName, MODE_PRIVATE));
            outputStream.writeObject(manager);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}
