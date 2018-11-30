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
     * The blackjack game manager.
     */
    private BlackJackManager blackJackManager;

    private String username;

    private Session user;

    private Button loadButton;

    private Button saveButton;

    private Button resumeButton;

    private Button newGameButton;

    private LoadSave LoadSaveManager;

    private BlackJackLoadSaveButtonController controller;

    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_jack_starting);
        context = this;
        controller = new BlackJackLoadSaveButtonController(context, TEMP_SAVE_FILE);
        LoadSaveManager = new LoadSave(context);
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
////    }
//
//    /**
//     * Activate the start button.
//     */
//    private void addStartButtonListener() {
//        newGameButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int[] winDrawLoss = {0,0,0};
//                blackJackManager = new BlackJackManager(new BlackJackGame(), 1000, winDrawLoss);
//                LoadSaveManager.saveToFile(TEMP_SAVE_FILE, username, "black_jack", blackJackManager);
//                switchToGame();
//            }
//        });
//    }
//
//    /**
//     * Activate the load button.
//     */
//    private void addLoadButtonListener() {
//        loadButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                blackJackManager = (BlackJackManager) LoadSaveManager.loadFromFile(TEMP_SAVE_FILE, username, "black_jack");
//                Toast.makeText(context, "Loaded Game", Toast.LENGTH_SHORT).show();
//                switchToGame();
//                resumeButton.setEnabled(true);
//            }
//        });
//    }
//
////
////    private void addScoreBoardListener() {
////        scoreBoardButton.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                switchToScoreBoard();
////            }
////        });
////    }
//
//    /**
//     * Activate the resume button
//     */
//    private void addResumeButtonListener() {
//        resumeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                blackJackManager = (BlackJackManager) LoadSaveManager.loadFromFile(TEMP_SAVE_FILE, username, "black_jack");
//                Toast.makeText(context, "Loaded Game", Toast.LENGTH_SHORT).show();
//                switchToGame();
//            }
//        });
//    }
//
//
//    /**
//     * Activate the save button.
//     */
//    private void addSaveButtonListener() {
//        saveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                LoadSaveManager.saveToFile(TEMP_SAVE_FILE, username, "black_jack", blackJackManager);
//                Toast.makeText(context, "Saved Game", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    @Override
    public void onBackPressed() {
        Intent backToLogin = new Intent(this, ChooseGameActivity.class);
        startActivity(backToLogin);
    }

    /**
     * Read the temporary board from disk.
     */
    @Override
    protected void onResume() {
        super.onResume();
        controller.resume(saveButton, resumeButton, loadButton);
    }


//    /**
//     * Switch to the BlackJackGameActivity view to play the game.
//     */
//    private void switchToGame() {
//        Intent tmp = new Intent(this, BlackJackComplexityActivity.class);
//        LoadSaveManager.saveToFile(TEMP_SAVE_FILE, username, "black_jack", blackJackManager);
//        startActivity(tmp);
//    }

//
//    private void switchToScoreBoard() {
//        Intent complexity = new Intent(this, ScoreBoardActivity.class);
//        startActivity(complexity);
//    }

}
