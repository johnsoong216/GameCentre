//excluded from tests because it's a (model / view) class
package fall2018.csc2017.Games;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * ScoreActivity.
 */
@SuppressLint("Registered")
public class ScoreActivity extends AppCompatActivity {
    /**
     * the file to store scoreboard
     */
    public static final String SCORE_SAVE_FILENAME = "save_score.ser";


    /**
     * the file to empty game progress
     */
    public static final String TEMP_SAVE_FILENAME = "save_game.ser";
    /**
     * the current scoreboard manager
     */
    private ScoreBoardManager scoreBoardManager;


    /**
     * textview that needed to be changed
     */
    TextView currentScore;
    TextView playerHighest;
    TextView gameHighest;
    TextView userHighest;
    TextView gameSecondHighest;
    TextView gameThirdHighest;
    TextView userSecondHighest;
    TextView userThirdHighest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Session user = Session.getCurrentUser();
        Context context = this;
        LoadSave loadSaveManager = new LoadSave(context);
        String gameType = getIntent().getStringExtra("game");

        scoreBoardManager = (ScoreBoardManager) loadSaveManager.loadFromFile(SCORE_SAVE_FILENAME, "admin", gameType);
        if (scoreBoardManager == null) {
            scoreBoardManager = new ScoreBoardManager();
        }
        scoreBoardManager.addScore(user.getUsername(), user.getScore());


        currentScore = findViewById(R.id.tvScorez);
        playerHighest = findViewById(R.id.tvHighest);
        gameHighest = findViewById(R.id.tvGameHIghestScore);
        userHighest = findViewById(R.id.tvUserHighest);
        gameSecondHighest = findViewById(R.id.tvSecondScore);
        gameThirdHighest = findViewById(R.id.tvThirdScore);
        userSecondHighest = findViewById(R.id.tvSecondUser);
        userThirdHighest = findViewById(R.id.tvThirdUser);

        arrangeScore(scoreBoardManager);
        setdisplay(user);
        addReturnButtonListener();

        loadSaveManager.saveToFile(SCORE_SAVE_FILENAME, "admin", gameType, scoreBoardManager);
        loadSaveManager.saveToFile(TEMP_SAVE_FILENAME, user.getUsername(), gameType, null);
    }

    /**
     * Arranging user scores.
     * @param sbm ScoreBoardManager object that contains user's score information.
     */
    private void arrangeScore(ScoreBoardManager sbm) {
        List<Pair<String, Integer>> listOfScore = sbm.maxGameScores(3);
        setTextView(listOfScore, 1, userHighest, gameHighest);
        if (listOfScore.size() == 3) {
            setTextView(listOfScore, 2, userSecondHighest, gameSecondHighest);
            setTextView(listOfScore, 3, userThirdHighest, gameThirdHighest);
        } else if (listOfScore.size() == 2) {
            setTextView(listOfScore, 2, userSecondHighest, gameSecondHighest);
        }
    }

    @SuppressLint("SetTextI18n")
    private void setdisplay(Session user) {
        currentScore.setText(Integer.toString(user.getScore()));
        playerHighest.setText(Integer.toString(scoreBoardManager.maxUserScore(user.getUsername())));
        List<Pair<String, Integer>> l = scoreBoardManager.maxGameScores(1);
        gameHighest.setText(Integer.toString(l.get(0).second));
        userHighest.setText(l.get(0).first);
    }

    /**
     * Return to the starting page.
     */
    public void addReturnButtonListener() {
        Button nextButton = findViewById(R.id.btReturn);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start = new Intent(ScoreActivity.this, ChooseGameActivity.class);
                ScoreActivity.this.startActivity(start);
            }
        });
    }

    /**
     * set textview for score and user in the list of pair
     *
     * @param l List.
     * @param n integer.
     * @param user TextView.
     * @param score TextView.
     */
    @SuppressLint("SetTextI18n")
    private void setTextView(List<Pair<String, Integer>> l, int n, TextView user, TextView score) {
        user.setText(l.get(n - 1).first);
        score.setText(Integer.toString(l.get(n - 1).second));
    }

    /*
    Go back to Starting Activity Page
     */

}
