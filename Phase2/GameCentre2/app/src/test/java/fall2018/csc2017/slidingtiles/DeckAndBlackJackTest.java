package fall2018.csc2017.slidingtiles;

import org.junit.Test;

import static org.junit.Assert.*;

public class DeckAndBlackJackTest {
    /*
    Create decks, cards, and BlackJackGame for test
     */
    Deck deck1;
    Deck deck2;
    Deck deck3;
    Deck deck4;
    Card card1;
    Card card2;
    Hand hand1;
    Hand hand2;
    Hand hand3;
    private void setUpDeck(){
        deck1 = new Deck();
        deck2 = new Deck();
        deck3 = new Deck();
        deck4 = new Deck();
        card1 = new Card(3, 13);
        card2 = new Card(3, 3);
        hand1 = new Hand();
        hand2 = new Hand();
        hand3 = new Hand();
        deck2.setNotShuffled();
        deck3.setNotShuffled();
        deck4.setNotShuffled();
        for(int i = 0; i < 10; i++){
            deck3.popCard();
        }
        for(int i = 0; i < 12; i++){
            deck4.popCard();
        }
    }
    /*
    hand1 contains 2 cards with in game points of 20
    hand2 contains 2 cards with in game poins of 21 which is blackjack
     */
    private void setUpHand(){
        setUpDeck();
        hand1.drawCard(deck2);
        hand1.drawCard(deck2);
        hand2.drawCard(deck4);
        hand2.drawCard(deck2);
        hand3.drawCard(deck2);
    }
    @Test
    public void testPopCard1() {
        setUpDeck();
        assertEquals(card1, deck2.popCard());
    }

    @Test
    public void testPopCard2() {
        setUpDeck();
        assertEquals(card2, deck3.popCard());
    }

    @Test
    public void testRemainingCard1() {
        setUpDeck();
        assertEquals(52, deck2.remainingCard());
    }

    @Test
    public void testRemainingCard2() {
        setUpDeck();
        assertEquals(42, deck3.remainingCard());
    }

    @Test
    public void testDeckIterator() {
        int value = 52;
        setUpDeck();
        for(Card card : deck2){
            assertEquals(value,card.getId());
            value--;
        }
    }

    @Test
    public void testSetNotShuffled() {
        setUpDeck();
        deck1.setNotShuffled();
        assertEquals(new Card(3,13), deck1.popCard());
    }

    @Test
    public void testDrawCard1AndGet1() {
        setUpHand();
        assertEquals(21, hand2.getPoints());
    }
    @Test
    public void testDrawCard1AndGet2() {
        setUpHand();
        assertEquals(20, hand1.getPoints());
    }

    @Test
    public void testGetHandSize() {
        setUpHand();
        assertEquals(2, hand1.getHandSize());
    }

    @Test
    public void testGoBusted() {
        setUpHand();
        hand1.drawCard(deck2);
        assertEquals(true, hand1.goBusted());
    }

    @Test
    public void testCheckBlackJack() {
        setUpHand();
        assertEquals(true, hand2.checkBlackJack());
        assertEquals(false, hand1.checkBlackJack());
    }

    @Test
    public void testFirstAce(){
        setUpHand();
        assertEquals(true, hand2.checkFirstAce());
        assertEquals(false, hand1.checkFirstAce());
    }

    @Test
    public void testGetBackGround(){
        setUpHand();
        assertEquals(R.drawable.bj50, hand2.getCardBackGround(1));
    }

    @Test
    public void testCheckDouble(){
        setUpHand();
        hand3.getPoints();
        assertEquals(true, hand3.isAllowDouble());
    }

    @Test
    public void testHandIterator(){
        setUpHand();
        for(Card card : hand1){
            assertEquals(10, card.getInGameValue());
        }
    }
}