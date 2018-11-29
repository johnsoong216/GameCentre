package fall2018.csc2017.slidingtiles;

import org.junit.Test;

import static org.junit.Assert.*;

public class DeckTest {
    /*
    Create decks and cards for test
     */
    Deck deck1;
    Deck deck2;
    Deck deck3;
    Card card1;
    Card card2;
    private void setUpDeck(){
        deck1 = new Deck();
        deck2 = new Deck();
        deck3 = new Deck();
        card1 = new Card(3, 13);
        card2 = new Card(3, 3);
        deck2.setNotShuffled();
        deck3.setNotShuffled();
        for(int i = 0; i < 10; i++){
            deck3.popCard();
        }
    }
    @Test
    public void popCard1() {
        setUpDeck();
//        assertEquals(52, deck2.popCard().getId());
        assertEquals(card1, deck2.popCard());
    }

    @Test
    public void popCard2() {
        setUpDeck();
        assertEquals(card2, deck3.popCard());
    }

    @Test
    public void remainingCard1() {
        setUpDeck();
        assertEquals(52, deck2.remainingCard());
    }

    @Test
    public void remainingCard2() {
        setUpDeck();
        assertEquals(42, deck3.remainingCard());
    }

    @Test
    public void iterator() {
        int value = 52;
        setUpDeck();
        for(Card card : deck2){
            assertEquals(value,card.getId());
            value--;
        }
    }

    @Test
    public void setNotShuffled() {
        setUpDeck();
        deck1.setNotShuffled();
        assertEquals(new Card(3,13), deck1.popCard());
    }
}