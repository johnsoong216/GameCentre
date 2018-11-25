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
    private int bet;
    /*
    Check for User Action
     */
    private boolean userEndGame = false;

    public BlackJackGame() {
        this.deck = new Deck();
        this.playerHand = new Hand();
        this.dealerHand = new Hand();
        startingHand();
    }

    private void startingHand() {
        dealerHand.drawcard(deck);
        dealerHand.drawcard(deck);
        playerHand.drawcard(deck);
        playerHand.drawcard(deck);
        flip(true, 0);
        flip(false, 0);
        flip(false, 1);
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
        return dealerHand.goBusted()? 0: dealerHand.getPoints();
    }

    /*
    return the player's hand's total point
     */
    public int getPlayerPoint() {
        return playerHand.goBusted()? 0: playerHand.getPoints();
    }

    /*
    Return whether the game is over
     */
    public boolean isOver() {
        return (dealerHand.goBusted() || playerHand.goBusted()||playerHand.checkBlackJack()|| dealerHand.checkBlackJack() || userEndGame);
    }

    /*
    User chooses to end Game
     */
    public void endGame(){
        userEndGame = true;
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
    Flip the Card of Hand
     */


    public void inGameBet(double betMultiplier){
        this.bet = (int) Math.round (bet * betMultiplier);
    }


    /*
    Flip Card
     */
    public void flip(boolean house, int position){
        if (house){
            dealerHand.flip(position);
        }
        else {
            playerHand.flip(position);
        }
    }
}
