package fall2018.csc2017.slidingtiles;

import org.junit.Test;

import static org.junit.Assert.*;

public class BlackJackGameTest {

    /*
    Create deck and blackjack game for test
     */
    Deck deck1;
    Deck deck2;
    Deck deck3;
    BlackJackGame blackJackGame1;
    BlackJackGame blackJackGame2;
    BlackJackGame blackJackGame3;
    BlackJackGame blackJackGame4;

    /**
     * Set up decks for test
     * deck1 is not shuffled and next cards to be drawn are Ace, King, Queen, Jack ,10, 9
     * deck2 is not shuffled and next cards to be drawn are King, Queen, Jack, 10, 9, 8
     * deck3 is not shuffled and next cards to be drawn are 3, 2, Ace, King
     */
    private void setUpDeck() {
        deck1 = new Deck();
        deck2 = new Deck();
        deck3 = new Deck();
        deck1.setNotShuffled();
        deck2.setNotShuffled();
        deck3.setNotShuffled();
        for(int i = 0; i < 12; i++){
            deck1.popCard();
        }
        for(int i = 0; i < 10; i++){
            deck3.popCard();
        }
    }

    /**
     * Set up the blackjack game
     */
    private void setUpBlackJackGame() {
        setUpDeck();
        blackJackGame1 = new BlackJackGame(deck1, 100);
        blackJackGame2 = new BlackJackGame(deck2, 150);
        blackJackGame3 = new BlackJackGame(deck3, 200);
        blackJackGame4 = new BlackJackGame();
    }

    /**
     * Test whether get dealer's point works
     */
    @Test
    public void getDealerPoint1() {
        setUpBlackJackGame();
        assertEquals(21, blackJackGame1.getDealerPoint());
    }

    @Test
    public void getDealerPoint2() {
        setUpBlackJackGame();
        assertEquals(20, blackJackGame2.getDealerPoint());
        assertEquals(5, blackJackGame3.getDealerPoint());
    }

    @Test
    public void getPlayerPoint() {
        setUpBlackJackGame();
        assertEquals(20, blackJackGame2.getPlayerPoint());
        assertEquals(20, blackJackGame1.getPlayerPoint());
        assertEquals(21, blackJackGame3.getPlayerPoint());
    }

    /**
     * Test whether the draw card method works
     */
    @Test
    public void dealerDrawCard1() {
        setUpBlackJackGame();
        blackJackGame2.dealerDrawCard();
        // the game go busted the dealer's point should be 0
        assertEquals(0, blackJackGame2.getDealerPoint());
    }

    @Test
    public void dealerDrawCard2() {
        setUpBlackJackGame();
        blackJackGame1.dealerDrawCard();
        // the dealer draw a 10 when it has an Ace and 10, the dealer's point should still be 21
        assertEquals(21, blackJackGame1.getDealerPoint());
    }

    /**
     * Test whether playerDrawCard works
     */
    @Test
    public void playerDrawCard1() {
        setUpBlackJackGame();
        blackJackGame1.playerDrawCard();
        assertEquals(0, blackJackGame1.getPlayerPoint());
    }

    @Test
    public void playerDrawCard2() {
        setUpBlackJackGame();
        blackJackGame3.playerDrawCard();
        assertEquals(21, blackJackGame3.getPlayerPoint());
    }

    /**
     * Test whether get bet works
     */
    @Test
    public void getBet1() {
        setUpBlackJackGame();
        assertEquals(150, blackJackGame1.getBet());
    }

    @Test
    public void getBet2() {
        setUpBlackJackGame();
        assertEquals(150, blackJackGame2.getBet());
    }

    /**
     * Test whether the in game bet method works
     */
    @Test
    public void inGameBet() {
        setUpBlackJackGame();
        blackJackGame3.inGameBet(1.5);
        assertEquals(450, blackJackGame3.getBet());
    }
}