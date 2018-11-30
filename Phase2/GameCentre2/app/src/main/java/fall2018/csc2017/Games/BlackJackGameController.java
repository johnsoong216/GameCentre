package fall2018.csc2017.Games;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.MessageFormat;

/**
 * Controller for blackjack game
 */
public class BlackJackGameController {

    private BlackJackManager blackJackManager;
    private Session user;
    private ImageView[] playerCards;
    private ImageView[] dealerCards;
    private Button hitButton;
    private Button doubleButton;
    private Button standButton;
    private Button newRoundButton;
    private Button hintButton;
    private Button insuranceButton;
    private String username;
    private LoadSave loadSaveManager;
    private Context context;
    private TextView chipsTotal;
    private double difficulty;
    private final String gameType = "black_jack";
    private final String saveFile = "save_game.ser";


    BlackJackGameController(Context context, Button[] buttonList,
                            ImageView[] playerCards, ImageView[] dealerCards, TextView chipsTotal){
        this.context =context;
        this.user = Session.getCurrentUser();
        this.username = user.getUsername();
        this.loadSaveManager = new LoadSave(context);
        blackJackManager = (BlackJackManager) loadSaveManager.loadFromFile(saveFile, username ,gameType);
        this.playerCards = playerCards;
        this.dealerCards = dealerCards;
        this.difficulty = blackJackManager.getComplexity();
        this.chipsTotal = chipsTotal;
        setButton(buttonList);
    }
    /*
    Create a new round button for black jack game that start a new round after clicking
     */
    void addNewRoundButtonListener() {
        newRoundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewRound(blackJackManager.getBlackJackGame().getDeck());
                setHandImage();
                setButtonOnOff(false);
            }
        });
    }

    /**
     * Set up buttons
     * @param buttonList Array of buttons
     */
    private void setButton(Button[] buttonList){
        hitButton = buttonList[0];
        doubleButton = buttonList[1];
        standButton = buttonList[2];
        newRoundButton = buttonList[3];
        hintButton = buttonList[4];
        insuranceButton = buttonList[5];
    }
    /**
     * Create a hit button for blackjack game
     */
    void addHitButtonListener() {
        hitButton.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             blackJackManager.hit();
                                             Hand playerHand = blackJackManager.getBlackJackGame().getPlayerHand();
                                             Hand dealerHand = blackJackManager.getBlackJackGame().getDealerHand();
                                             int index = playerHand.getHandSize() - 1;
                                             Toast.makeText(context, "Your Point is" + playerHand.getPoints(), Toast.LENGTH_SHORT).show();
                                             playerCards[index].setImageResource(playerHand.getCardBackGround(index));
                                             if (blackJackManager.isOver()) {
                                                 dealerCards[1].setImageResource(dealerHand.getCardBackGround(1));
                                                 setButtonOnOff(true);
                                             }
                                             doubleButton.setEnabled(false);
                                         }

                                     }
        );
    }

    /**
     * Create a stand button
     */
    void addStandButtonListener() {
        standButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blackJackManager.stand();
                int index = 0;
                for (Card card : blackJackManager.getBlackJackGame().getDealerHand()) {
                    dealerCards[index].setImageResource(card.getBackground());
                    index++;
                }
                setButtonOnOff(true);
            }
        });
    }

    /**
     * Create a double button
     */
    void addDoubleButtonListener() {
        doubleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blackJackManager.douBle();
                playerCards[2].setImageResource(blackJackManager.getBlackJackGame().getPlayerHand().getCardBackGround(2));
                blackJackManager.stand();
                int index = 0;
                for (Card card : blackJackManager.getBlackJackGame().getDealerHand()) {
                    dealerCards[index].setImageResource(card.getBackground());
                    index++;
                }
                setButtonOnOff(true);
            }
        });
    }

    /**
     * Create a hint button for the user to see hint for the game
     */
    void addHintButtonListener() {
        hintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int probability = (int) blackJackManager.getProbability();
                Toast.makeText(context, "Probability of getting busted is " + String.valueOf(probability) + "%", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Start a new round of game
     * @param deck
     */
    void startNewRound(Deck deck) {
        int oldChips = blackJackManager.getChips();

        blackJackManager.settleChips();

        if (blackJackManager.isGameOver()){
            Intent toSummary = new Intent(context,BlackJackSummaryActivity.class);
            context.startActivity(toSummary);
        }

        int chips = blackJackManager.getChips();

//        int[] winDrawLoss = blackJackManager.getWinDrawLoss();

        Toast.makeText(context, "Your Net Gain for Last Round is: $ " + (chips - oldChips), Toast.LENGTH_SHORT).show();

        if(difficulty == 1.2){
            deck = new Deck();
        }
        else if (deck.remainingCard() < 26) {
            deck = new Deck();
        }

        blackJackManager.newGame(deck);
        chipsTotal.setText(MessageFormat.format("Total Chips:{0}", blackJackManager.getChips()));
    }

    /**
     * Set the player & dealer's hand image with information in the game and enabling or disabling
     *     buttons depends on situation
     */
    void setHandImage() {
        Hand playerHand = blackJackManager.getBlackJackGame().getPlayerHand();
        Hand dealerHand = blackJackManager.getBlackJackGame().getDealerHand();
        int playerViewIndex = 0;
        for (Card playerCard : playerHand) {
            playerCards[playerViewIndex].setImageResource(playerCard.getBackground());
            playerViewIndex++;
        }
        for (int i = playerViewIndex; i < 5; i++) {
            playerCards[i].setImageResource(R.drawable.back);
        }
        int dealerViewIndex = 0;
        if (dealerHand.getHandSize() > 2) {
            for (Card dealerCard : dealerHand) {
                dealerCards[dealerViewIndex].setImageResource(dealerCard.getBackground());
                dealerViewIndex++;
            }
            for (int i = dealerHand.getHandSize(); i < 5; i++) {
                dealerCards[i].setImageResource(R.drawable.back);
            }
        } else {
            dealerCards[0].setImageResource(dealerHand.getCardBackGround(0));
            for (int i = 1; i < 5; i++) {
                dealerCards[i].setImageResource(R.drawable.back);
            }
        }

        if (blackJackManager.isOver()) {
            setButtonOnOff(true);
        }
    }

    /**
     * enabling or disabling button depend on if the game is over or not
     */
    void setButtonOnOff(boolean over) {
        if(difficulty != 0.8) {
            hintButton.setEnabled(false);
        }
        if (over) {
            newRoundButton.setEnabled(true);
            hitButton.setEnabled(false);
            doubleButton.setEnabled(false);
            standButton.setEnabled(false);
            insuranceButton.setEnabled(false);
        } else {
            if (blackJackManager.getBlackJackGame().getPlayerHand().checkBlackJack()) {
                newRoundButton.setEnabled(true);
                hitButton.setEnabled(false);
                standButton.setEnabled(false);
            } else {
                if (blackJackManager.checkDouble()) {
                    doubleButton.setEnabled(true);
                }
                else {
                    doubleButton.setEnabled(false);
                }
                if (blackJackManager.getBlackJackGame().getDealerHand().checkFirstAce()){
                    insuranceButton.setEnabled(true);
                }
                else {
                    insuranceButton.setEnabled(false);
                }
                newRoundButton.setEnabled(false);
                hitButton.setEnabled(true);
                standButton.setEnabled(true);
            }
        }
    }


    /**
     * Create an Insurance Button
     */
    void addInsuranceButtonListener() {
        insuranceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blackJackManager.insurance();
                insuranceButton.setEnabled(false);
            }
        });
    }

    /**
     * set chips view
     */
    void setChipsTotal(){
        chipsTotal.setText(MessageFormat.format("Total Chips:{0}", blackJackManager.getChips()));
    }

    /**
     * save game
     */
    void saveGame(){
        loadSaveManager.saveToFile(saveFile, username, gameType, blackJackManager);
    }
}
