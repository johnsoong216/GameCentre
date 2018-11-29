package fall2018.csc2017.slidingtiles;
/*
An activity that allow user to choose difficulties and bets for the game
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static fall2018.csc2017.slidingtiles.BlackJackStartingActivity.TEMP_SAVE_FILE;

public class BlackJackComplexityActivity extends AppCompatActivity {

    int bet;
    private Context context;
    private Loadsave loadSaveManager;
    /*
    current user and his username
     */
    private Session user;
    private String username;
    /*
    current blackjack game manager in use
     */
    private BlackJackManager blackJackManager;
    /*
    Buttons
     */
    private Button easyButton;
    private Button normalButton;
    private Button hardButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_jack_complexity);
        context = this;
        user = Session.getCurrentUser();
        username = user.getUsername();
        easyButton = findViewById(R.id.btEasyEasy);
        normalButton = findViewById(R.id.btNormal);
        hardButton = findViewById(R.id.btHard);
        loadSaveManager = new Loadsave(context);
        blackJackManager = (BlackJackManager) loadSaveManager.loadFromFile(TEMP_SAVE_FILE, username, "black_jack");
        addEasyButtonListener();
        addHardButtonListener();
        addNormalButtonListener();
        chooseBet();

    }
    /**
     * Choose the number of bets for each round
     */
    private void chooseBet() {
        SeekBar seekBar = findViewById(R.id.sbDifficulty);
        seekBar.setMin(0);
        seekBar.setMax(200);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                bet = 100 + progress;
                blackJackManager.setInitialBet(bet);
                blackJackManager.setBet();
            }


            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(BlackJackComplexityActivity.this, bet + " bet for each round Selected", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void addEasyButtonListener() {
        easyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blackJackManager.setComplexity(0.8);
                switchToGame();
            }
        });
    }

    private void addNormalButtonListener(){
        normalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blackJackManager.setComplexity(1);
                switchToGame();
            }
        });
    }

    private void addHardButtonListener(){
        hardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blackJackManager.setComplexity(1.2);
                switchToGame();
            }
        });
    }

    private void switchToGame() {
        loadSaveManager.saveToFile(TEMP_SAVE_FILE, username, "black_jack", blackJackManager);
        Intent tmp = new Intent(this, BlackJackGameActivity.class);
        tmp.putExtra("startBet", bet);
        startActivity(tmp);
    }
}
