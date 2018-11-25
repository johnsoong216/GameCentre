package fall2018.csc2017.slidingtiles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class BlackJackGameActivity extends AppCompatActivity {
    private Hand playerHand;
    private Hand dealerHand;
    private BlackJackManager blackJackManager;
    private Session user;
    private Button hitButton;
    private int hitcounter = 0;
    private Button doubleButton;
    private Button standButton;
    private ImageView playerCard1;
    private ImageView playerCard2;
    private ImageView playerCard3;
    private ImageView playerCard4;
    private ImageView playerCard5;
    private ImageView dealerCard1;
    private ImageView dealerCard2;
    private ImageView dealerCard3;
    private ImageView dealerCard4;
    private ImageView dealerCard5;
    private ImageView deckImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_jack_game);
        playerCard1 = findViewById(R.id.playerCard1);
        playerCard2 = findViewById(R.id.playerCard2);
        playerCard3 = findViewById(R.id.playerCard3);
        playerCard4 = findViewById(R.id.playerCard4);
        playerCard5 = findViewById(R.id.playerCard5);
        dealerCard1 = findViewById(R.id.dealerCard1);
        dealerCard2 = findViewById(R.id.dealerCard2);
        dealerCard3 = findViewById(R.id.dealerCard3);
        dealerCard4 = findViewById(R.id.dealerCard4);
        dealerCard5 = findViewById(R.id.dealerCard5);
        deckImage = findViewById(R.id.deck);

    }
    private void addHitButtonListener() {
        hitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blackJackManager.hit();
                hitcounter++;
                playerCard3.setImageResource(blackJackManager.getBlackJackGame().getPlayerHand().getCardBackGround(2));
            }
        });
    }


    private void addStandButtonListener(){
        standButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                blackJackManager.stand();
            }
        });
    }


    private void addDoubleButtonListener(){
        doubleButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                blackJackManager.douBle();
            }
        });
    }

}
