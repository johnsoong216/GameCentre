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
    Card card1;
    Card card2;
    Hand hand1;
    Hand hand2;
    private void setUpDeck(){
        deck1 = new Deck();
        deck2 = new Deck();
        deck3 = new Deck();
        card1 = new Card(3, 13);
        card2 = new Card(3, 3);
        hand1 = new Hand();
        hand2 = new Hand();
        deck2.setNotShuffled();
        deck3.setNotShuffled();
        for(int i = 0; i < 10; i++){
            deck3.popCard();
        }
    }
    @Test
    public void testPopCard1() {
        setUpDeck();
//        assertEquals(52, deck2.popCard().getId());
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
    public void testDrawCard1AndGet() {
        setUpDeck();
        hand1.drawCard(deck2);
    }
}