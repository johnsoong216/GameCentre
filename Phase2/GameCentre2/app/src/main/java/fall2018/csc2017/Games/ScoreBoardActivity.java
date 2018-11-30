package fall2018.csc2017.Games;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;


/**
 * Password activity.
 */
public class ScoreBoardActivity extends AppCompatActivity {

    /**
     * A scoreBoardManager object.
     */
    private ScoreBoardManager scoreBoardManager;

    /**
     * the file to store scoreboard
     */
    public static final String SCORE_SAVE_FILENAME = "save_score.ser";


    public LoadSave loadSaveManager;
    public String gameType;
    public String gameName;
    public TextView gameNameDisplay;


    /*
     * Display the Top Players
     */
    TextView score_board;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);
        Context context = this;
        loadSaveManager = new LoadSave(context);
        score_board = findViewById(R.id.score_board);
        gameNameDisplay= findViewById(R.id.gameType);
        addReturnButtonListener();
    }

    protected void onResume(){
        super.onResume();
        gameType = getIntent().getStringExtra("game");
        gameName = getIntent().getStringExtra("gameName");
        gameNameDisplay.setText(gameName);
        scoreBoardManager = (ScoreBoardManager) loadSaveManager.loadFromFile(SCORE_SAVE_FILENAME, "admin", gameType);
        if (scoreBoardManager == null) {
            scoreBoardManager = new ScoreBoardManager();
        }
        score_board.setText(displayScore(10));
    }


    /**
     * Display the Score of Top Players
     */
    public StringBuilder displayScore(int num) {
        List<Pair<String, Integer>> listOfScore = scoreBoardManager.maxGameScores(num);
        String spaces = "       ";
        String stringToBuild = String.format("%1$s Username %1$s Score %1$s Rank" + '\n', spaces);
        StringBuilder result = new StringBuilder(stringToBuild);
        if (listOfScore.size() != 0) {
            for (int i = 1; i <= listOfScore.size(); i++) {
                Pair<String, Integer> user = listOfScore.get(i - 1);
                result = fixLength(result, 12 - user.first.length());
                result.append(user.first);
                result = fixLength(result, 25 - user.first.length());
                result.append(user.second).append("                  ").append(i).append('\n');
            }
        }
        return result;
    }

    /**
     * Allows the Return to Main Page
     */
    public void addReturnButtonListener() {
        Button nextButton = findViewById(R.id.MainPageButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start = new Intent(ScoreBoardActivity.this, ChooseGameActivity.class);
                startActivity(start);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

            }
        });
    }

    /**
     * Set String Length for Display Aesthetics
     */
    public StringBuilder fixLength(StringBuilder str, int length) {
        if (length < 0) {
            length = 0;
        }
        for (int i = 0; i <= length; i++) {
            str.append(" ");
        }
        return str;
    }
}
