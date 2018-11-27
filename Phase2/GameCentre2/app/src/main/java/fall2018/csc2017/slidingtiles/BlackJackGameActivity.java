package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class BlackJackGameActivity extends AppCompatActivity {
    private BlackJackManager blackJackManager;
    private Session user;
    private Button hitButton;
    private Button doubleButton;
    private Button standButton;
    private Button newRoundButton;
    private ImageView[] playerCards;
    private ImageView[] dealerCards;
    private ImageView deckImage;
    private String username;
    private Loadsave loadsaveManager;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadsaveManager = new Loadsave(context);
        setContentView(R.layout.activity_black_jack_game);
        playerCards = new ImageView[]{findViewById(R.id.playerCard1), findViewById(R.id.playerCard2)
                ,findViewById(R.id.playerCard3),findViewById(R.id.playerCard4),findViewById(R.id.playerCard5)};
        dealerCards = new ImageView[]{findViewById(R.id.dealerCard1), findViewById(R.id.dealerCard2)
                ,findViewById(R.id.dealerCard3),findViewById(R.id.dealerCard4),findViewById(R.id.dealerCard5)};
        deckImage = findViewById(R.id.deck);
        doubleButton = findViewById(R.id.btDouble);
        newRoundButton = findViewById(R.id.btNewRound);
        user = Session.getCurrentUser();
        username = user.getUsername();
        context = this;
        loadsaveManager = new Loadsave(context);
        blackJackManager = (BlackJackManager) loadsaveManager.loadFromFile(BlackJackStartingActivity.TEMP_SAVE_FILENAME_BLACK_JACK, username);
        hitButton = findViewById(R.id.btHit);
        standButton = findViewById(R.id.btStand);
        addHitButtonListener();
        addNewRoundButtonListener();
        addStandButtonListener();
        addDoubleButtonListener();
    }

    protected void onResume(){
        super.onResume();
        setHandImage(blackJackManager.getBlackJackGame().getPlayerHand(),
                blackJackManager.getBlackJackGame().getDealerHand());

    }

    protected void onPause(){
        super.onPause();
        loadsaveManager.saveToFile(BlackJackStartingActivity.TEMP_SAVE_FILENAME_BLACK_JACK, username, blackJackManager);
        loadsaveManager.saveToFile(BlackJackStartingActivity.SAVE_FILENAME_BLACK_JACK, username, blackJackManager);
    }


    private void addNewRoundButtonListener() {
        newRoundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startNewRound(blackJackManager.getBlackJackGame().getDeck());
                setHandImage(blackJackManager.getBlackJackGame().getPlayerHand(),
                        blackJackManager.getBlackJackGame().getDealerHand());
                Log.d("TAG", "doubledouble" + blackJackManager.getBlackJackGame().getPlayerHand().isAllowDouble() +
                        blackJackManager.getBlackJackGame().getPlayerHand().getPoints());
                if (blackJackManager.getBlackJackGame().getPlayerHand().checkBlackJack()) {
                    newRoundButton.setEnabled(true);
                    hitButton.setEnabled(true);
                    standButton.setEnabled(false);
                }
                else {
                newRoundButton.setEnabled(false);
                hitButton.setEnabled(true);
                standButton.setEnabled(true);}
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
                if(blackJackManager.getBlackJackGame().isOver()) {
                    blackJackManager.settleChips();
                    dealerCards[1].setImageResource(blackJackManager.getBlackJackGame().getDealerHand().getCardBackGround(1));
                    hitButton.setEnabled(false);
                    doubleButton.setEnabled(false);
                    standButton.setEnabled(false);
                    newRoundButton.setEnabled(true);
                }
                }

            }
        );
    }


    private void addStandButtonListener(){
        standButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                blackJackManager.stand();
                int index = 0;
                Log.d("TAG", "DealerHandSizeStand" + blackJackManager.getBlackJackGame().getDealerHand().getHandSize());
                for(Card card :blackJackManager.getBlackJackGame().getDealerHand()){
                    dealerCards[index].setImageResource(card.getBackground());
                    index ++;
                }
                hitButton.setEnabled(false);
                doubleButton.setEnabled(false);
                standButton.setEnabled(false);
                newRoundButton.setEnabled(true);
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
                Log.d("TAG", "DealerHandSizeDouble" + blackJackManager.getBlackJackGame().getDealerHand().getHandSize());
                int index = 0;
                for(Card card :blackJackManager.getBlackJackGame().getDealerHand()){
                    dealerCards[index].setImageResource(card.getBackground());
                    index ++;
                }
                hitButton.setEnabled(false);
                doubleButton.setEnabled(false);
                standButton.setEnabled(false);
                newRoundButton.setEnabled(true);
            }
        });
    }
    private void startNewRound(Deck deck){
        int chips = blackJackManager.getChips();
        deck.shuffle();
        blackJackManager = new BlackJackManager(new BlackJackGame(deck), chips);
        hitButton.setEnabled(false);
        doubleButton.setEnabled(false);
        standButton.setEnabled(false);
        newRoundButton.setEnabled(true);
    }

    private void setHandImage(Hand playerHand, Hand dealerHand){
        int playerViewIndex = 0;
        for(Card playerCard : playerHand){
            playerCards[playerViewIndex].setImageResource(playerCard.getBackground());
            playerViewIndex ++;
        }
        for(int i = playerViewIndex; i < 5; i++){
            playerCards[i].setImageResource(R.drawable.back);
        }
        int dealerViewIndex = 0;
        Log.d("TAG", "DealerHandSize" + dealerHand.getHandSize());
        if (dealerHand.getHandSize() > 2){
            for (Card dealerCard: dealerHand){
                dealerCards[dealerViewIndex].setImageResource(dealerCard.getBackground());
                dealerViewIndex++;
            }
            for (int i = dealerHand.getHandSize(); i < 5; i++){
                dealerCards[i].setImageResource(R.drawable.back);
            }
        }
        else {
            dealerCards[0].setImageResource(dealerHand.getCardBackGround(0));
            for(int i = 1; i < 5; i++){
            dealerCards[i].setImageResource(R.drawable.back);
        }}
        Log.d("TAG", "doubledouble" + blackJackManager.getBlackJackGame().getPlayerHand().isAllowDouble() +
        blackJackManager.getBlackJackGame().getPlayerHand().getPoints());
        if (!blackJackManager.getBlackJackGame().getPlayerHand().isAllowDouble()){
            doubleButton.setEnabled(false);
        }
        else {doubleButton.setEnabled(true);}

        if  (blackJackManager.getBlackJackGame().isOver()){
            newRoundButton.setEnabled(true);
            hitButton.setEnabled(false);
            doubleButton.setEnabled(false);
            standButton.setEnabled(false);
        }
    }
}
