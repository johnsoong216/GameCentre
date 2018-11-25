package fall2018.csc2017.slidingtiles;

/*
The game board for BlackJack game, storing and managing information related to BlackJackc game
 */
public class BlackJackGame {
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
    private int bet;

    public BlackJackGame() {
        this.deck = new Deck();
        this.playerHand = new Hand();
        this.dealerHand = new Hand();
        dealerHand.drawcard(deck);
        dealerHand.drawcard(deck);
        playerHand.drawcard(deck);
        playerHand.drawcard(deck);
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
        return dealerHand.getPoints();
    }

    /*
    return the player's hand's total point
     */
    public int getPlayerPoint() {
        return playerHand.getPoints();
    }

    /*
    Return whether the game is over
     */
    public boolean isOver() {
        return dealerHand.goBusted() || playerHand.goBusted();
    }

    /*
    Draw a card for dealer
     */
    public void dealerDrawCard() {
        dealerHand.drawcard(this.deck);
    }

    /*
    Draw a card for dealer
     */
    public void playerDrawCard() {
        playerHand.drawcard(this.deck);
    }
    /*
    Return the game's current bet
     */
    public int getBet() {
        return this.bet;
    }

    /*
    set the new bet for the game
     */
    public void setBet(int bet) {
        this.bet = bet;
    }

    /*
    Return the currrent deck for the game
     */
    public Deck getDeck() {
        return this.deck;
    }

}
