package fall2018.csc2017.Games;

import java.io.Serializable;

/**
 * Manage a BlackJack game, including
 * hit, double, stand, settling chips for the ended round
 * giving hint for a player and
 * checking if the game is over
 */
public class BlackJackManager extends GameManager implements Serializable {

    /*
     *The current game being managed
     */
    private BlackJackGame blackJackGame;
    /**
     * Number of the chips the play has
     */
    private int chips;
    /**
     * check for player buying insurance
     */
    private boolean insurance;
    /*
    Check for User Action
    */
    private boolean userEndGame;

    /**
     * Number of wins, draws, losses
     */
    private int[] winDrawLoss;

    /**
     * Complexity
     */

    private double complexity;

    /**
     * Initial Bet
     */


    private int initialBet;


    public int getScore() {
        double score = (chips + 50 * getWinDrawLoss()[0]) * complexity;
        return (int) Math.round(score);
    }

    public void setInitialBet(int initialBet) {
        this.initialBet = initialBet;
        blackJackGame.bet = initialBet;

    }


    /**
     * Create a new blackjack game manager with chips the player currently has
     */
    BlackJackManager(BlackJackGame blackJackGame) {
        this.blackJackGame = blackJackGame;
        this.chips = 1000;
        this.winDrawLoss = new int[3];
//        this.complexity = complexity;
//        this.initialBet = initialBet;
//        blackJackGame.setBet(100);
    }

    void newGame(Deck deck) {
        this.blackJackGame = new BlackJackGame(deck, initialBet);
    }

    /**
     * Draw an additional card
     */
    void hit() {
        if (!isOver() && blackJackGame.getPlayerHand().getHandSize() < 5) {
            blackJackGame.playerDrawCard();
//            blackJackGame.flip(false, -1);
        }
    }

    /**
     * User chooses to end SlidingTileGame
     */
    public void endGame() {
        userEndGame = true;
    }

    public void insurance() {
        Hand dealer = blackJackGame.getDealerHand();
        if (dealer.getHandSize() == 2 && dealer.checkFirstAce()) {
            chips -= initialBet / 2;
        }
        insurance = true;
    }

    /**
     * check the condition for double
     */
    boolean checkDouble() {
        return !isOver() && blackJackGame.getPlayerHand().isAllowDouble();
    }

    /**
     * When the card's value sum up to 9, 10 or 11 The player is allowed to increase the initial
     * bet by up to 100% in exchange for committing to stand after receiving exactly one more card.
     */
    void douBle() {
        if (checkDouble()) {
            blackJackGame.playerDrawCard();
            blackJackGame.inGameBet(2);
            endGame();
        }
    }

    /**
     * return the number of chips for the current player
     *
     * @return chips
     */
    int getChips() {
        return chips;
    }

    /**
     * Player chooses not to draw any more cards
     * Add a button listener in the Activity Page
     */
    void stand() {
        while (blackJackGame.getDealerHand().getPoints() < 17 && blackJackGame.getDealerHand().getHandSize() < 5) {
            blackJackGame.dealerDrawCard();
        }
        endGame();
    }

    /**
     * Settle the Amount of Chips if the player buys the insurance the player will reduce its bet on
     * the game by one half
     */
    void settleChips() {
        if (insurance && blackJackGame.getDealerHand().checkBlackJack()) {
            winDrawLoss[2]++;
        } else if (blackJackGame.getPlayerPoint() < blackJackGame.getDealerPoint()) {
            chips -= blackJackGame.getBet();
            winDrawLoss[2]++;
        } else if (blackJackGame.getPlayerPoint() > blackJackGame.getDealerPoint()) {
            chips += blackJackGame.getBet();
            winDrawLoss[0]++;
        } else {
            winDrawLoss[1]++;
        }

        if (chips <= 0) {
            chips = 0;
        }
        userEndGame = false;
        insurance = false;
    }

    /**
     * @return the current game being managed
     */
    BlackJackGame getBlackJackGame() {
        return blackJackGame;
    }

    /**
     * check if the game is over
     * if player/dealer go busted or player gets a blackjack or the user choose to end game
     * the round is over
     *
     * @Return whether the round is over
     */
    boolean isOver() {
        Hand dealerHand = blackJackGame.getDealerHand();
        Hand playerHand = blackJackGame.getPlayerHand();
        return (dealerHand.goBusted() || playerHand.goBusted() || playerHand.checkBlackJack() ||
                userEndGame);
    }

    /**
     * @Return whether the game is over
     */
    boolean isGameOver() {
        return (winDrawLoss[0] + winDrawLoss[1] + winDrawLoss[2] == 10 || chips == 0);
    }

    /**
     * @return probability for player to not being busted after the new hit
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

    /**
     * @return the complexity for the game
     */
    public double getComplexity() {
        return complexity;
    }

    /**
     * set the complexity for the game
     */
    public void setComplexity(double complexity) {
        this.complexity = complexity;
    }

    /**
     * Getter for Win Draw Loss
     */
    public int[] getWinDrawLoss() {
        return winDrawLoss;
    }

}


