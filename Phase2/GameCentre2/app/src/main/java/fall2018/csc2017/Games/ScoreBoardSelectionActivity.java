//excluded from tests because it's a (model / view) class
package fall2018.csc2017.Games;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;

public class ScoreBoardSelectionActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board_selection);
        Context context = this;
        ImageButton blackJackScore = findViewById(R.id.blackJackScore);
        ImageButton flipItScore = findViewById(R.id.flipItScore);
        ImageButton slidingTilesScore = findViewById(R.id.slidingTilesScore);
        ScoreBoardSelectionController controller = new ScoreBoardSelectionController(context);
        controller.addFlipItScoreListener(flipItScore);
        controller.addSlidingTilesScoreListener(slidingTilesScore);
        controller.addBlackJackScoreListener(blackJackScore);
    }


//
//    private void addflipItScoreListener() {
//        flipItScore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent toScoreBoard = new Intent(ScoreBoardSelectionActivity.this, ScoreBoardActivity.class);
//                toScoreBoard.putExtra("game", "flip_it");
//                toScoreBoard.putExtra("gameName", "FLIP IT!");
//                startActivity(toScoreBoard);
//                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//
//            }
//        });
//    }
//
//    private void addblackJackScoreListener() {
//        blackJackScore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent toScoreBoard = new Intent(ScoreBoardSelectionActivity.this, ScoreBoardActivity.class);
//                toScoreBoard.putExtra("game", "black_jack");
//                toScoreBoard.putExtra("gameName", "BLACKJACK");
//                startActivity(toScoreBoard);
//                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//
//            }
//        });
//    }
//
//    private void addslidingTilesScoreListener(){
//        slidingTilesScore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent toScoreBoard = new Intent(ScoreBoardSelectionActivity.this, ScoreBoardActivity.class);
//                toScoreBoard.putExtra("game", "sliding_tiles");
//                toScoreBoard.putExtra("gameName", "SLIDING TILES");
//                startActivity(toScoreBoard);
//                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//
//            }
//        });
//
//    }

}
