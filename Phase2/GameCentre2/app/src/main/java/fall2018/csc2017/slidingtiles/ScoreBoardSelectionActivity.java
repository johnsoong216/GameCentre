package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class ScoreBoardSelectionActivity extends AppCompatActivity {


    private ImageButton blackJackScore;
    private ImageButton flipItScore;
    private ImageButton slidingTilesScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board_selection);
        blackJackScore = findViewById(R.id.blackJackScore);
        flipItScore = findViewById(R.id.flipItScore);
        slidingTilesScore = findViewById(R.id.slidingTilesScore);
        addblackJackScoreListener();
        addflipItScoreListener();
        addslidingTilesScoreListener();
    }

    private void addflipItScoreListener() {
        flipItScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toScoreBoard = new Intent(ScoreBoardSelectionActivity.this, ScoreBoardActivity.class);
                toScoreBoard.putExtra("game", "flip_it");
                toScoreBoard.putExtra("gameName", "FLIP IT!");
                startActivity(toScoreBoard);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }
        });
    }

    private void addblackJackScoreListener() {
        blackJackScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toScoreBoard = new Intent(ScoreBoardSelectionActivity.this, ScoreBoardActivity.class);
                toScoreBoard.putExtra("game", "black_jack");
                toScoreBoard.putExtra("gameName", "BLACKJACK");
                startActivity(toScoreBoard);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }
        });
    }

    private void addslidingTilesScoreListener(){
        slidingTilesScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toScoreBoard = new Intent(ScoreBoardSelectionActivity.this, ScoreBoardActivity.class);
                toScoreBoard.putExtra("game", "sliding_tiles");
                toScoreBoard.putExtra("gameName", "SLIDING TILES");
                startActivity(toScoreBoard);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }
        });

    };

}
