package fall2018.csc2017.slidingtiles;

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
    public static final String SCORE_SAVE_FILENAME = "score_save_file.ser";

    public Loadsave loadsaveManager;

    /*
     * Display the Top Players
     */
    TextView score_board;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);
        Context context = this;
        loadsaveManager = new Loadsave(context);
        scoreBoardManager = (ScoreBoardManager) loadsaveManager.loadFromFile(SCORE_SAVE_FILENAME, "admin");
        if (scoreBoardManager == null) {
            scoreBoardManager = new ScoreBoardManager();
        }
        score_board = findViewById(R.id.score_board);
        addReturnButtonListener();
        score_board.setText(displayScore(10));
    }


    /**
     * Display the Score of Top Players
     */
    private StringBuilder displayScore(int num) {
        List<Pair<String, Integer>> listOfScore = scoreBoardManager.maxGameScores(num);
        StringBuilder result = new StringBuilder("         " + "Username" + "            " + "Score" + "             " + "Rank" + '\n');
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
                Intent start = new Intent(ScoreBoardActivity.this, StartingActivity.class);
                ScoreBoardActivity.this.startActivity(start);
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
