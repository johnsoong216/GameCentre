package fall2018.csc2017.Games;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BlackJackManagerTest {
    BlackJackManager blackJackManager1;
    BlackJackManager blackJackManager2;
    BlackJackManager blackJackManager3;
    Deck deck1 = new Deck();
    Deck deck2 = new Deck();
    Deck deck3 = new Deck();

    private void setUpNotShuffled() {
        deck1.setNotShuffled();
        deck2.setNotShuffled();
        deck3.setNotShuffled();
        for(int i = 0; i < 5; i++){
            deck2.popCard();
        }
        for(int i = 0; i < 12; i++){
            deck3.popCard();
        }
        blackJackManager1 = new BlackJackManager(new BlackJackGame(deck1, 200));
        blackJackManager2 = new BlackJackManager(new BlackJackGame(deck2, 300));
        blackJackManager3 = new BlackJackManager(new BlackJackGame(deck3, 150));
    }

    @Test
    public void testComplexity() {
        setUpNotShuffled();
        blackJackManager1.setComplexity(0.8);
        assertEquals(0, (int)(blackJackManager1.getComplexity()-0.8));
    }

    @Test
    public void testHit() {
        setUpNotShuffled();
        blackJackManager1.hit();
        assertEquals(3, blackJackManager1.getBlackJackGame().getPlayerHand().getHandSize());
    }

    @Test
    public void getScore() {
        setUpNotShuffled();
        assertEquals(0, blackJackManager1.getScore());
    }


    @Test
    public void newGame() {
        setUpNotShuffled();
        blackJackManager1.newGame(deck2);
        assertEquals(38, (int)Math.round(blackJackManager1.getProbability()));
    }

    @Test
    public void insurance() {
        setUpNotShuffled();
        blackJackManager3.setInitialBet(150);
        blackJackManager3.insurance();
        blackJackManager3.settleChips();
        assertEquals(true, blackJackManager3.getBlackJackGame().getDealerHand().checkFirstAce());
        assertEquals(2, blackJackManager3.getBlackJackGame().getDealerHand().getHandSize());
        assertEquals(925, blackJackManager3.getChips());
    }

    @Test
    public void checkDouble() {
        setUpNotShuffled();
        assertEquals(true, blackJackManager2.checkDouble());
    }

    @Test
    public void getChips() {
        setUpNotShuffled();
        assertEquals(1000, blackJackManager1.getChips());
    }

    @Test
    public void stand() {
        setUpNotShuffled();
        blackJackManager1.stand();
        blackJackManager1.settleChips();
        assertEquals(1000, blackJackManager1.getChips());
    }

    @Test
    public void settleChips() {
        setUpNotShuffled();
        blackJackManager1.stand();
        blackJackManager2.stand();
        blackJackManager3.stand();
        blackJackManager2.settleChips();
        blackJackManager1.settleChips();
        blackJackManager3.settleChips();
        assertEquals(1000, blackJackManager1.getChips());
        assertEquals(700, blackJackManager2.getChips());
        assertEquals(775, blackJackManager3.getChips());
    }

    @Test
    public void getBlackJackGame() {
    }

    @Test
    public void isOver() {
    }

    @Test
    public void isGameOver1() {
        setUpNotShuffled();
        assertEquals(false, blackJackManager3.isGameOver());
    }

    @Test
    public void isGameOverAndIsOver() {
        setUpNotShuffled();
        blackJackManager2.douBle();
        assertEquals(true, blackJackManager2.isOver());
        assertEquals(false, blackJackManager2.isGameOver());
    }


    @Test
    public void getWinDrawLoss() {
    }
}
