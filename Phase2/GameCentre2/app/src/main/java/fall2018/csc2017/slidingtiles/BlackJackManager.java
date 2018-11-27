package fall2018.csc2017.slidingtiles;

import android.util.Log;

import java.io.Serializable;

public class BlackJackManager extends GameManager implements Serializable {


    private BlackJackGame blackJackGame;
    private int chips;
    private boolean insurance;


    public BlackJackManager(BlackJackGame blackJackGame, int chips) {
        this.blackJackGame = blackJackGame;
        this.chips = chips;
//        blackJackGame.setBet(100);
    }


    public void hit() {
        if (!blackJackGame.isOver()&& blackJackGame.getPlayerHand().getHandSize() < 5){
            blackJackGame.playerDrawCard();
//            blackJackGame.flip(false, -1);
        }
    }

    public void insurance(){
        Hand dealer = blackJackGame.getDealerHand();
        if (dealer.getHandSize() == 2  && dealer.checkFirstAce()){
            blackJackGame.inGameBet(0.5);
        }
    }

    public void douBle() {
        if (!blackJackGame.isOver() && blackJackGame.getPlayerHand().isAllowDouble()){
        blackJackGame.playerDrawCard();
        blackJackGame.inGameBet(2);
//        blackJackGame.flip(false, -1);
        blackJackGame.endGame();
        }
    }

    public int getChips() {return chips;}
    /*
    Player chooses not to draw any more cards
    Add a button listener in the Activity Page
     */
    public void stand() {
        while (blackJackGame.getDealerHand().getPoints() < 17 && blackJackGame.getDealerHand().getHandSize() < 5) {
            blackJackGame.dealerDrawCard();
//            blackJackGame.flip(true, -1);
        }
        blackJackGame.endGame();
    }
    /*
    Settle the Amount of Chips if the player buys the insurance the player will reduce its bet on
    the game by one half
    */
    public void settleChips() {
        if (insurance){
            blackJackGame.setBet(blackJackGame.getBet()/2);
        }
         if (blackJackGame.getPlayerPoint() < blackJackGame.getDealerPoint()) {
            chips -= blackJackGame.getBet();
        } else if (blackJackGame.getPlayerPoint() > blackJackGame.getDealerPoint()){
            chips += blackJackGame.getBet();
        }
    }
    /*
    return the current game being managed
     */
    public BlackJackGame getBlackJackGame() {
        return blackJackGame;
    }
    /*
    return information about the game
     */
    public String getInfo(){return null;
    }

    /*
    return probability for player to not being busted after the new hit
     */

    public double getProbability(){
        Deck deck = blackJackGame.getDeck();
        int remainingCard = deck.remainingCard();
        int pointBeforeBusted = 21 - blackJackGame.getPlayerHand().getPoints();
        int numCardBusted = 0;
        for (Card card : deck){
            if(card.getInGameValue() > pointBeforeBusted){
                numCardBusted++;
                Log.d("ABC", "ABC" + String.valueOf(card.getInGameValue()));
                Log.d("ABC", "ABC" + String.valueOf(numCardBusted));

            }
        }
        return ((double)numCardBusted / (double)remainingCard) * 100.0;}
}


