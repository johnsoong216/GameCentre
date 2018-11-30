package fall2018.csc2017.Games;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BlackJackManagerTest {
    BlackJackManager blackJackManager;
    Deck deck = new Deck();

    private void setUpNotShuffled() {
        deck.setNotShuffled();
        blackJackManager = new BlackJackManager(new BlackJackGame(deck, 200));
    }

    @Test
    public void testInitialBet() {
        setUpNotShuffled();
        blackJackManager.setInitialBet(400);
        assertEquals(400, blackJackManager.getInitialBet());
    }

    @Test
    public void testComplexity() {
        setUpNotShuffled();
        blackJackManager.setComplexity(0.8);
        assertEquals(0, (int)(blackJackManager.getComplexity()-0.8));
    }

    @Test
    public void testHit() {
        setUpNotShuffled();
        blackJackManager.hit();
        assertEquals(3, blackJackManager.getBlackJackGame().getPlayerHand().getHandSize());
    }
}
