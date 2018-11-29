package fall2018.csc2017.slidingtiles;

import java.io.Serializable;

/*
The game board for BlackJack game, storing and managing information related to BlackJackc game
 */
public class BlackJackGame implements Serializable {
    /*
    The current shuffled deck
     */
    private Deck deck;
    /*
    The current player
     */
    private Hand playerHand;
    /*
    The Dealer's hand
     */
    private Hand dealerHand;
    /*
    Current bet for the game
     */
    protected int bet;
    /*
    Check for User Action
     */
    private boolean userEndGame = false;

    public BlackJackGame(Deck deck, int bet) {
        this.deck = deck;
        this.playerHand = new Hand();
        this.dealerHand = new Hand();
        this.bet = bet;
        startingHand();
    }

    public BlackJackGame() {
        this.deck = new Deck();
        this.playerHand = new Hand();
        this.dealerHand = new Hand();
        this.bet = 200;
        startingHand();
    }

    private void startingHand() {
        dealerHand.drawCard(deck);
        dealerHand.drawCard(deck);
        playerHand.drawCard(deck);
        playerHand.drawCard(deck);
        if (playerHand.checkBlackJack() &&
                !dealerHand.checkBlackJack()) {
            inGameBet(1.5);
        } else if (!playerHand.checkBlackJack() &&
                dealerHand.checkBlackJack()) {
            inGameBet(1.5);
        }
    }

    /*
    Return the player's hand
     */
    public Hand getPlayerHand() {
        return this.playerHand;
    }

    /*
    Return the dealer's hand
     */
    public Hand getDealerHand() {
        return this.dealerHand;
    }

    /*
    return the dealer's hand's total point
     */
    public int getDealerPoint() {
        return dealerHand.goBusted() ? 0 : dealerHand.getPoints();
    }

    /*
    return the player's hand's total point
     */
    public int getPlayerPoint() {
        return playerHand.goBusted() ? 0 : playerHand.getPoints();
    }

    /*
    Draw a card for dealer
     */
    public void dealerDrawCard() {
        dealerHand.drawCard(this.deck);
    }

    /*
    Draw a card for dealer
     */
    public void playerDrawCard() {
        playerHand.drawCard(this.deck);
    }

    /*
    Return the game's current bet
     */
    public int getBet() {
        return this.bet;
    }

    /*
    Set the in game bet
     */
    public void inGameBet(double betMultiplier) {
        this.bet = (int) Math.round(bet * betMultiplier);
    }

    /*
    return the deck being used
     */
    public Deck getDeck() {
        return deck;
    }
}
