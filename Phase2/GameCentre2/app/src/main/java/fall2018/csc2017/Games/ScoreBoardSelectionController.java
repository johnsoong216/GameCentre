package fall2018.csc2017.Games;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;

public class ScoreBoardSelectionController {

    private ImageButton blackJackScore;
    private ImageButton flipItScore;
    private ImageButton slidingTilesScore;
    private Context context;



    ScoreBoardSelectionController(Context context){
        this.context = context;
    }



    void addFlipItScoreListener(ImageButton flipItScoreButton) {
        flipItScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toScoreBoard = new Intent(context, ScoreBoardActivity.class);
                toScoreBoard.putExtra("game", "flip_it");
                toScoreBoard.putExtra("gameName", "FLIP IT!");
                context.startActivity(toScoreBoard);
            }
        });
    }

    void addBlackJackScoreListener(ImageButton blackJackScoreButton) {
        blackJackScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toScoreBoard = new Intent(context, ScoreBoardActivity.class);
                toScoreBoard.putExtra("game", "black_jack");
                toScoreBoard.putExtra("gameName", "BLACKJACK");
                context.startActivity(toScoreBoard);
            }
        });
    }

    void addSlidingTilesScoreListener(ImageButton slidingTilesScoreButton){
        slidingTilesScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toScoreBoard = new Intent(context, ScoreBoardActivity.class);
                toScoreBoard.putExtra("game", "flip_it");
                toScoreBoard.putExtra("gameName", "Sliding Tiles");
                context.startActivity(toScoreBoard);
            }
        });

    }

}
