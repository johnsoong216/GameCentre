package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.support.transition.TransitionManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ChooseGameActivity extends AppCompatActivity {
    private Button st;
    private Button fi;
    private Button blackJackButton;
    private Button scoreBoardButton;
//    final ViewGroup transitionsContainer = (ViewGroup) findViewById(R.id.transitions_container);
//    final TextView text = (TextView) transitionsContainer.findViewById(R.id.text);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_games_activiy);
        st = findViewById(R.id.bt_slidingTiles);
        fi = findViewById(R.id.bt_flipit);
        blackJackButton = findViewById(R.id.btBlackJack);
        scoreBoardButton = findViewById(R.id.scoreBoardButton);
        addSlidingListener();
        addFlipListener();
        addBlackJackListener();
        addScoreBoardListener();
    }
    private void addSlidingListener() {
        st.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toStarting = new Intent(ChooseGameActivity.this, StartingActivity.class);
                toStarting.putExtra("game", "sliding");
                startActivity(toStarting);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }
    private void addBlackJackListener() {
        blackJackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toStarting = new Intent(ChooseGameActivity.this, BlackJackStartingActivity.class);
                toStarting.putExtra("game", "blackjack");
                startActivity(toStarting);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }
    private void addFlipListener() {
        fi.setOnClickListener(new View.OnClickListener() {
            boolean visible;

            @Override
            public void onClick(View v) {

                Intent toStarting = new Intent(ChooseGameActivity.this, FlipStartingActivity.class);
                toStarting.putExtra("game", "flip");
                startActivity(toStarting);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }

    private void addScoreBoardListener() {
        scoreBoardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent complexity = new Intent(ChooseGameActivity.this, ScoreBoardSelectionActivity.class);
                startActivity(complexity);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }

}
