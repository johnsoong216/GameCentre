package fall2018.csc2017.slidingtiles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import static fall2018.csc2017.slidingtiles.BlackJackStartingActivity.TEMP_SAVE_FILENAME_BLACK_JACK;

public class BlackJackGameActivity extends AppCompatActivity {
    private Hand playerHand;
    private Hand dealerHand;
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
    private Button insuranceButton;

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
        newRoundButton = findViewById(R.id.btNewRound);
        user = Session.getCurrentUser();
        username = user.getUsername();
        this.blackJackManager = loadFromFile(TEMP_SAVE_FILENAME_BLACK_JACK, username);
        hitButton = findViewById(R.id.btHit);
        standButton = findViewById(R.id.btStand);
        setHandImage(blackJackManager.getBlackJackGame().getPlayerHand(),
                blackJackManager.getBlackJackGame().getDealerHand());
        addHitButtonListener();
        addNewRoundButtonListener();
        newRoundButton.setEnabled(false);
        addStandButtonListener();
    }

    private void addInsuranceButtonListener() {
        insuranceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blackJackManager.insurance();
                insuranceButton.setEnabled(false);
            }
        });
    }

    private void addNewRoundButtonListener() {
        newRoundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewRound(blackJackManager.getBlackJackGame().getDeck());
                setHandImage(blackJackManager.getBlackJackGame().getPlayerHand(),
                        blackJackManager.getBlackJackGame().getDealerHand());
                newRoundButton.setEnabled(false);
                hitButton.setEnabled(true);
                doubleButton.setEnabled(true);
                standButton.setEnabled(true);
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
            }
        });
    }
    private void startNewRound(Deck deck){
        int chips = blackJackManager.getChips();
        blackJackManager = new BlackJackManager(new BlackJackGame(deck), chips);
        hitButton.setEnabled(false);
        doubleButton.setEnabled(false);
        standButton.setEnabled(false);
        newRoundButton.setEnabled(true);
    }

    public BlackJackManager loadFromFile(String fileName, String username) {

        try {
            InputStream inputStream = this.openFileInput(username + fileName);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                BlackJackManager manager = (BlackJackManager) input.readObject();
                inputStream.close();
                return manager;
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
        }
        return null;
    }

    /**
     * Save the blackjack manager to fileName.
     *
     * @param fileName the name of the file
     */
    public void saveToFile(String fileName, String username, BlackJackManager manager) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(this.openFileOutput(username + fileName, MODE_PRIVATE));
            outputStream.writeObject(manager);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
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
        dealerCards[0].setImageResource(dealerHand.getCardBackGround(0));
        for(int i = 1; i < 5; i++){
            dealerCards[i].setImageResource(R.drawable.back);
        }
    }
}
