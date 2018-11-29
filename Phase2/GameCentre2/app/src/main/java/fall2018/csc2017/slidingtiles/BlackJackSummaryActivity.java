package fall2018.csc2017.slidingtiles;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BlackJackSummaryActivity extends AppCompatActivity {

    private TextView gameOutcome;
    private TextView earnings;
    private Button checkScore;
    private Session user;
    private Loadsave loadsaveManager;
    private Context context;
    private BlackJackManager blackJackManager;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_jack_summary);
        context = this;
        loadsaveManager = new Loadsave(context);
        blackJackManager =  (BlackJackManager) loadsaveManager.loadFromFile(BlackJackStartingActivity.TEMP_SAVE_FILE, user.getUsername(), "black_jack");
        gameOutcome = findViewById(R.id.gameOutcome);
        earnings = findViewById(R.id.earnings);
        checkScore = findViewById(R.id.checkScore);
        user = Session.getCurrentUser();
        gameOutcome.setText(String.format("WIN: %d\nDRAW: %d\nLOSS: %d", blackJackManager.getWinDrawLoss()[0], blackJackManager.getWinDrawLoss()[1], blackJackManager.getWinDrawLoss()[2]));
        earnings.setText(String.format("Total Earnings: $%d", blackJackManager.getChips() - 100));
        checkScoreListener();
    }

    public void calculateScore(){
        double score = (blackJackManager.getChips() + 50 * blackJackManager.getWinDrawLoss()[0]) * blackJackManager.getComplexity();
        user.setScore((int) Math.round(score));

    }


    public void checkScoreListener() {
        checkScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateScore();
                loadsaveManager.saveToFile(BlackJackStartingActivity.TEMP_SAVE_FILE, user.getUsername(), "black_jack", null);
                Intent checkScore = new Intent(BlackJackSummaryActivity.this, ScoreActivity.class);
                checkScore.putExtra("game", "black_jack");
                startActivity(checkScore);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }

        });
    }
}
