package fall2018.csc2017.slidingtiles;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Manage a BlackJack game, including
 * hit, double, stand, settling chips for the ended round
 * giving hint for a player and
 * checking if the game is over
 */
public class BlackJackManager extends GameManager implements Serializable {

    /*
    The current game being managed
     */
    private BlackJackGame blackJackGame;
    /*
    Number of the chips the play has
     */
    private int chips;
    /*
    check for player buying insurance
     */
    private boolean insurance;
    /*
    Check for User Action
    */
    private boolean userEndGame = false;

    /*
    Number of wins, draws, losses
     */
    private int[] winDrawLoss;

    /*
    Complexity
     */

    private int complexity;


    /*
    Create a new blackjack game manager with chips the player currently has
     */
    BlackJackManager(BlackJackGame blackJackGame, int chips, int[] winDrawLoss) {
        this.blackJackGame = blackJackGame;
        this.chips = chips;
        this.winDrawLoss = winDrawLoss;
//        blackJackGame.setBet(100);
    }

    /*
    Draw an additional card
     */
    void hit() {
        if (!isOver() && blackJackGame.getPlayerHand().getHandSize() < 5) {
            blackJackGame.playerDrawCard();
//            blackJackGame.flip(false, -1);
        }
    }

    /*
User chooses to end Game
 */
    public void endGame() {
        userEndGame = true;
    }

    public void insurance() {
        Hand dealer = blackJackGame.getDealerHand();
        if (dealer.getHandSize() == 2 && dealer.checkFirstAce()) {
            blackJackGame.inGameBet(0.5);
        }
    }

    /*
    check the condition for double
     */
    boolean checkDouble() {
        return !isOver() && blackJackGame.getPlayerHand().isAllowDouble();
    }

    /*
    When the card's value sum up to 9, 10 or 11 The player is allowed to increase the initial
    bet by up to 100% in exchange for committing to stand after receiving exactly one more card.
     */
    void douBle() {
        if (checkDouble()) {
            blackJackGame.playerDrawCard();
            blackJackGame.inGameBet(2);
//        blackJackGame.flip(false, -1);
            endGame();
        }
    }

    int getChips() {
        return chips;
    }
    /*
    Player chooses not to draw any more cards
    Add a button listener in the Activity Page
     */
    void stand() {
        while (blackJackGame.getDealerHand().getPoints() < 17 && blackJackGame.getDealerHand().getHandSize() < 5) {
            blackJackGame.dealerDrawCard();
//            blackJackGame.flip(true, -1);
        }
        endGame();
    }

    /*
    Settle the Amount of Chips if the player buys the insurance the player will reduce its bet on
    the game by one half
    */
    void settleChips() {
        if (insurance) {
            setBet(blackJackGame.getBet() / 2);
        }
        if (blackJackGame.getPlayerPoint() < blackJackGame.getDealerPoint()) {
            chips -= blackJackGame.getBet();
            winDrawLoss[2]++;
        } else if (blackJackGame.getPlayerPoint() > blackJackGame.getDealerPoint()) {
            chips += blackJackGame.getBet();
            winDrawLoss[0]++;
        }
        else {
            winDrawLoss[1]++;
        }
    }

    /*
    return the current game being managed
     */
    BlackJackGame getBlackJackGame() {
        return blackJackGame;
    }

    /*
    return information about the game
     */
    public String getInfo() {
        return null;
    }

    /*
    Return whether the round is over
     */
    boolean isOver() {
        Hand dealerHand = blackJackGame.getDealerHand();
        Hand playerHand = blackJackGame.getPlayerHand();
        return (dealerHand.goBusted() || playerHand.goBusted() || playerHand.checkBlackJack() ||
                dealerHand.checkBlackJack() || userEndGame);
    }

    /*
    Return whether the game is over
     */

    boolean isGameOver(){
        return (winDrawLoss[0] + winDrawLoss[1] + winDrawLoss[2] == 10 || chips <= 0);
    }
    /*
    return probability for player to not being busted after the new hit
     */

    double getProbability() {
        Deck deck = blackJackGame.getDeck();
        int remainingCard = deck.remainingCard();
        int pointBeforeBusted = 21 - blackJackGame.getPlayerHand().getPoints();
        int numCardBusted = 0;
        for (Card card : deck) {
            if (card.getInGameValue() > pointBeforeBusted) {
                numCardBusted++;

            }
        }
        return ((double) numCardBusted / (double) remainingCard) * 100.0;
    }
    /*
    set the new bet for the game
     */
    public void setBet(int bet) {
        blackJackGame.bet = bet;
    }
    /*
    return the complexity for the game
     */
    public int getComplexity() {
        return complexity;
    }

    /*
    set the complexity for the game
     */
    public void setComplexity(int complexity) {
        this.complexity = complexity;
    }
    /*
    Getter for Win Draw Loss
    */
    public int[] getWinDrawLoss() {
        return winDrawLoss;
    }

}


