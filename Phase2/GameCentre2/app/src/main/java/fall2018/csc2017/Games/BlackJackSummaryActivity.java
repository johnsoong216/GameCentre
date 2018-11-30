package fall2018.csc2017.Games;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BlackJackSummaryActivity extends AppCompatActivity {

    private Button checkScore;
    private Session user;
    private LoadSave LoadSaveManager;
    private BlackJackManager blackJackManager;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_jack_summary);
        Context context = this;
        LoadSaveManager = new LoadSave(context);
        user = Session.getCurrentUser();
        blackJackManager =  (BlackJackManager) LoadSaveManager.loadFromFile(BlackJackStartingActivity.TEMP_SAVE_FILE, user.getUsername(), "black_jack");
        TextView gameOutcome = findViewById(R.id.gameOutcome);
        TextView earnings = findViewById(R.id.earnings);
        checkScore = findViewById(R.id.checkScore);
        gameOutcome.setText(String.format("WIN: %d\nDRAW: %d\nLOSS: %d", blackJackManager.getWinDrawLoss()[0], blackJackManager.getWinDrawLoss()[1], blackJackManager.getWinDrawLoss()[2]));
        earnings.setText(String.format("Total Earnings: $%d", blackJackManager.getChips() - 1000));
        checkScoreListener();
        calculateScore();
    }

    public void calculateScore(){
        double score = (blackJackManager.getChips() + 50 * blackJackManager.getWinDrawLoss()[0]) * blackJackManager.getComplexity();
        user.setScore((int) Math.round(score));
    }


    public void checkScoreListener() {
        checkScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadSaveManager.saveToFile(BlackJackStartingActivity.TEMP_SAVE_FILE, user.getUsername(), "black_jack", null);
                Intent checkScore = new Intent(BlackJackSummaryActivity.this, ScoreActivity.class);
                checkScore.putExtra("game", "black_jack");
                startActivity(checkScore);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }

        });
    }
}
