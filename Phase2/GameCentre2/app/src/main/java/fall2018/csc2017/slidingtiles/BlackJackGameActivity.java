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
    private Button doubleButton;
    private Button standButton;
    private Button startButton;
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
                ,findViewById(R.id.dealerCard3),findViewById(R.id.dealerCard4),findViewById(R.id.dealerCard5)};
        deckImage = findViewById(R.id.deck);
        doubleButton = findViewById(R.id.btDouble);
        startButton = findViewById(R.id.startButton);
        hitButton = findViewById(R.id.btHit);
        standButton = findViewById(R.id.btStand);



    }


    private void addStartButtonListener() {
        hitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
    private void addHitButtonListener() {
        hitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blackJackManager.hit();
                int index = blackJackManager.getBlackJackGame().getPlayerHand().getHandSize() - 1;
                playerCards[index].setImageResource(blackJackManager.getBlackJackGame().getPlayerHand().getCardBackGround(index));
            }
        });
    }


    private void addStandButtonListener(){
        standButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                dealerCards[1].setImageResource(blackJackManager.getBlackJackGame().getPlayerHand().getCardBackGround(1));
                blackJackManager.stand();
            }
        });
    }


    private void addDoubleButtonListener(){
        doubleButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                blackJackManager.douBle();
                playerCards[2].setImageResource(blackJackManager.getBlackJackGame().getPlayerHand().getCardBackGround(2));
                blackJackManager.stand();
            }
        });
    }

}
