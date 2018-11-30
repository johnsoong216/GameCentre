package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.MessageFormat;
import java.util.ArrayList;


public class BlackJackGameActivity extends AppCompatActivity {
//    private BlackJackManager blackJackManager;
//    private Session user;
//    private Button hitButton;
//    private Button doubleButton;
//    private Button standButton;
//    private Button newRoundButton;
//    private Button hintButton;
//    private Button insuranceButton;
    private ImageView[] playerCards;
    private ImageView[] dealerCards;
//    private String username;
//    private Loadsave loadSaveManager;
    private Context context;
    private TextView chipsTotal;
//    private double difficulty;
    private Button[] buttonList;
    private BlackJackGameController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_jack_game);
        context = this;
        playerCards = new ImageView[]{findViewById(R.id.playerCard1), findViewById(R.id.playerCard2)
                , findViewById(R.id.playerCard3), findViewById(R.id.playerCard4), findViewById(R.id.playerCard5)};
        dealerCards = new ImageView[]{findViewById(R.id.dealerCard1), findViewById(R.id.dealerCard2)
                , findViewById(R.id.dealerCard3), findViewById(R.id.dealerCard4), findViewById(R.id.dealerCard5)};
//        doubleButton = findViewById(R.id.btDouble);
//        newRoundButton = findViewById(R.id.btNewRound);
//        insuranceButton = findViewById(R.id.btInsurance);
        chipsTotal = findViewById(R.id.chipsTotal);
//        user = Session.getCurrentUser();
//        username = user.getUsername();
//        loadSaveManager = new Loadsave(context);
//        blackJackManager = (BlackJackManager) loadSaveManager.loadFromFile(BlackJackStartingActivity.TEMP_SAVE_FILE, username, "black_jack");
//        hitButton = findViewById(R.id.btHit);
//        standButton = findViewById(R.id.btStand);
//        hintButton = findViewById(R.id.btHint);
        buttonList = new Button[] {findViewById(R.id.btHit), findViewById(R.id.btDouble),
                findViewById(R.id.btStand), findViewById(R.id.btNewRound), findViewById(R.id.btHint),
                findViewById(R.id.btInsurance)};
        controller = new BlackJackGameController(context, buttonList, playerCards, dealerCards, chipsTotal);
//        difficulty = blackJackManager.getComplexity();

        controller.setButtonOnOff(false);
        controller.addHitButtonListener();
        controller.addNewRoundButtonListener();
        controller.addStandButtonListener();
        controller.addDoubleButtonListener();
        controller.addHintButtonListener();
        controller.addInsuranceButtonListener();
    }

    protected void onResume() {
        super.onResume();
        controller.setHandImage();
//        setHandImage(blackJackManager.getBlackJackGame().getPlayerHand(),
//                blackJackManager.getBlackJackGame().getDealerHand());
//        chipsTotal.setText(MessageFormat.format("Total Chips:{0}", blackJackManager.getChips()));
        controller.setChipsTotal();
    }

    protected void onPause() {
        super.onPause();
        controller.saveGame();
//        loadSaveManager.saveToFile(BlackJackStartingActivity.TEMP_SAVE_FILE, username, "black_jack", blackJackManager);
    }

//    /*
//    Create a new round button for black jack game that start a new round after clicking
//     */
//    private void addNewRoundButtonListener() {
//        newRoundButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startNewRound(blackJackManager.getBlackJackGame().getDeck());
//                setHandImage(blackJackManager.getBlackJackGame().getPlayerHand(),
//                        blackJackManager.getBlackJackGame().getDealerHand());
//                setButtonOnOff(false);
//            }
//        });
//    }
//
//    /*
//    Create a hit button for blackjack game
//     */
//    private void addHitButtonListener() {
//        hitButton.setOnClickListener(new View.OnClickListener() {
//                                         @Override
//                                         public void onClick(View v) {
//                                             blackJackManager.hit();
//                                             Hand playerHand = blackJackManager.getBlackJackGame().getPlayerHand();
//                                             Hand dealerHand = blackJackManager.getBlackJackGame().getDealerHand();
//                                             int index = playerHand.getHandSize() - 1;
//                                             Toast.makeText(context, "Your Point is" + playerHand.getPoints(), Toast.LENGTH_SHORT).show();
//                                             playerCards[index].setImageResource(playerHand.getCardBackGround(index));
//                                             if (blackJackManager.isOver()) {
//                                                 dealerCards[1].setImageResource(dealerHand.getCardBackGround(1));
//                                                 setButtonOnOff(true);
//                                             }
//                                             doubleButton.setEnabled(false);
//                                         }
//
//                                     }
//        );
//    }
//
//    /**
//     * Create a stand button
//     */
//    private void addStandButtonListener() {
//        standButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                blackJackManager.stand();
//                int index = 0;
//                for (Card card : blackJackManager.getBlackJackGame().getDealerHand()) {
//                    dealerCards[index].setImageResource(card.getBackground());
//                    index++;
//                }
//                setButtonOnOff(true);
//            }
//        });
//    }
//
//    /**
//     * Create a double button
//     */
//    private void addDoubleButtonListener() {
//        doubleButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                blackJackManager.douBle();
//                playerCards[2].setImageResource(blackJackManager.getBlackJackGame().getPlayerHand().getCardBackGround(2));
//                blackJackManager.stand();
//                int index = 0;
//                for (Card card : blackJackManager.getBlackJackGame().getDealerHand()) {
//                    dealerCards[index].setImageResource(card.getBackground());
//                    index++;
//                }
//                setButtonOnOff(true);
//            }
//        });
//    }
//
//    /**
//     * Create a hint button for the user to see hint for the game
//     */
//    private void addHintButtonListener() {
//        hintButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int probability = (int) blackJackManager.getProbability();
//                Toast.makeText(context, "Probability of getting busted is " + String.valueOf(probability) + "%", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    /**
//     * Start a new round of game
//     * @param deck
//     */
//    private void startNewRound(Deck deck) {
//        int oldChips = blackJackManager.getChips();
//
//        blackJackManager.settleChips();
//
//        if (blackJackManager.isGameOver()){
//            Intent toSummary = new Intent(this,BlackJackSummaryActivity.class);
//            startActivity(toSummary);
//        }
//
//        int chips = blackJackManager.getChips();
//
////        int[] winDrawLoss = blackJackManager.getWinDrawLoss();
//
//        Toast.makeText(context, "Your Net Gain for Last Round is: $ " + (chips - oldChips), Toast.LENGTH_SHORT).show();
//
//        if(difficulty == 1.2){
//            deck = new Deck();
//        }
//        else if (deck.remainingCard() < 26) {
//            deck = new Deck();
//        }
//
//        blackJackManager.newGame(deck);
//        chipsTotal.setText(MessageFormat.format("Total Chips:{0}", blackJackManager.getChips()));
//    }
//
//    /**
//     * Set the player & dealer's hand image with information in the game and enabling or disabling
//     *     buttons depends on situation
//     */
//    private void setHandImage(Hand playerHand, Hand dealerHand) {
//        int playerViewIndex = 0;
//        for (Card playerCard : playerHand) {
//            playerCards[playerViewIndex].setImageResource(playerCard.getBackground());
//            playerViewIndex++;
//        }
//        for (int i = playerViewIndex; i < 5; i++) {
//            playerCards[i].setImageResource(R.drawable.back);
//        }
//        int dealerViewIndex = 0;
//        if (dealerHand.getHandSize() > 2) {
//            for (Card dealerCard : dealerHand) {
//                dealerCards[dealerViewIndex].setImageResource(dealerCard.getBackground());
//                dealerViewIndex++;
//            }
//            for (int i = dealerHand.getHandSize(); i < 5; i++) {
//                dealerCards[i].setImageResource(R.drawable.back);
//            }
//        } else {
//            dealerCards[0].setImageResource(dealerHand.getCardBackGround(0));
//            for (int i = 1; i < 5; i++) {
//                dealerCards[i].setImageResource(R.drawable.back);
//            }
//        }
//
//        if (blackJackManager.isOver()) {
//            setButtonOnOff(true);
//        }
//    }
//
//    /**
//     * enabling or disabling button depend on if the game is over or not
//     */
//    private void setButtonOnOff(boolean over) {
//        if (over) {
//            newRoundButton.setEnabled(true);
//            hitButton.setEnabled(false);
//            doubleButton.setEnabled(false);
//            standButton.setEnabled(false);
//            insuranceButton.setEnabled(false);
//        } else {
//            if (blackJackManager.getBlackJackGame().getPlayerHand().checkBlackJack()) {
//                newRoundButton.setEnabled(true);
//                hitButton.setEnabled(false);
//                standButton.setEnabled(false);
//            } else {
//                if (blackJackManager.checkDouble()) {
//                    doubleButton.setEnabled(true);
//                }
//                else {
//                    doubleButton.setEnabled(false);
//                }
//                if (blackJackManager.getBlackJackGame().getDealerHand().checkFirstAce()){
//                    insuranceButton.setEnabled(true);
//                }
//                else {
//                    insuranceButton.setEnabled(false);
//                }
//                newRoundButton.setEnabled(false);
//                hitButton.setEnabled(true);
//                standButton.setEnabled(true);
//            }
//        }
//    }
//
//
//    /**
//     * Create an Insurance Button
//     */
//    private void addInsuranceButtonListener() {
//        insuranceButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                blackJackManager.insurance();
//                insuranceButton.setEnabled(false);
//            }
//        });
//    }
    @Override
    public void onBackPressed() {
        Intent backToMain = new Intent(BlackJackGameActivity.this, BlackJackStartingActivity.class);
        BlackJackGameActivity.this.startActivity(backToMain);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

    }
}
