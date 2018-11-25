package fall2018.csc2017.slidingtiles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class BlackJackGameActivity extends AppCompatActivity {
    private Hand playerHand;
    private Hand dealerHand;
    private BlackJackManager blackJackManager;
    private Session user;
    private Button hitButton;
    private int hitcounter = 2;
    private Button doubleButton;
    private Button standButton;
    private ImageView[] playerCards;
    private ImageView[] dealerCards;
    private ImageView deckImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_jack_game);
        playerCards = new ImageView[]{findViewById(R.id.playerCard1), findViewById(R.id.playerCard2)
                ,findViewById(R.id.playerCard3),findViewById(R.id.playerCard4),findViewById(R.id.playerCard5)};
        dealerCards = new ImageView[]{findViewById(R.id.dealerCard1), findViewById(R.id.dealerCard2)
                ,findViewById(R.id.dealerCard3),findViewById(R.id.dealerCard4),findViewById(R.id.playerCard5)};        playerCard2 = findViewById(R.id.playerCard2);
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
                playerCard3.setImageResource(blackJackManager.getBlackJackGame().getPlayerHand().getCardBackGround(hitcounter++));
                if (hitcounter == 4 | blackJackManager.getBlackJackGame().getPlayerHand().goBusted()){
                    hitButton.setEnabled(false);
                }
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
