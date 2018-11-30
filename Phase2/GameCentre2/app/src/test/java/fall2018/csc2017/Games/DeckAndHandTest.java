package fall2018.csc2017.Games;

import org.junit.Test;

import static org.junit.Assert.*;

public class DeckAndHandTest {
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
    /*
    Deck 1 is shuffled deck1 with 52 cards
    Deck 2 is not shuffled and the first card to be drawn is diamonds king
    Deck 3 is not shuffled and 10 cards are drawn from the deck1, the first card to be drawn is
    diamond 3
    Deck 4 is not shuffled and 12 cards are drawn from the deck1, the first card to be drawn is
    diamond ace
    Card 1 is diamond king
    Card 2 is diamond 3
     */
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

    /*
    Test whether pop cards work
     */
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

    /*
    Test whether counting the remaining cards works
     */
    @Test
    public void testRemainingCard1() {
        setUpDeck();
        assertEquals(52, deck2.remainingCard());
    }
    /*
   Test whether counting the remaining cards works
    */
    @Test
    public void testRemainingCard2() {
        setUpDeck();
        assertEquals(42, deck3.remainingCard());
    }

    /*
    Test whether the deck1 iterator works
     */
    @Test
    public void testDeckIterator() {
        int value = 52;
        setUpDeck();
        for(Card card : deck2){
            assertEquals(value,card.getId());
            value--;
        }
    }

    /*
    Test whether the setting the deck1 to not shuffled deck1 works
     */
    @Test
    public void testSetNotShuffled() {
        setUpDeck();
        deck1.setNotShuffled();
        assertEquals(new Card(3,13), deck1.popCard());
    }

    /*
    Test whether drawing cards from deck1 works
     */
    @Test
    public void testDrawCard1AndGet1() {
        setUpHand();
        assertEquals(21, hand2.getPoints());
    }
    /*
   Test whether drawing cards from deck1 works
    */
    @Test
    public void testDrawCard1AndGet2() {
        setUpHand();
        assertEquals(20, hand1.getPoints());
    }

    /*
    Test whether checking the size of hand works
     */
    @Test
    public void testGetHandSize() {
        setUpHand();
        assertEquals(2, hand1.getHandSize());
    }

    /*
    Test whether checking the hand go busted works
     */
    @Test
    public void testGoBusted1() {
        setUpHand();
        hand1.drawCard(deck2);
        assertEquals(true, hand1.goBusted());
    }
    /*
    Test whether checking the hand go busted works
     */
    @Test
    public void testGoBusted2() {
        setUpHand();
        assertEquals(false, hand2.goBusted());
    }


    /*
    Test whether checking the hand is blackjack method works
     */
    @Test
    public void testCheckBlackJack() {
        setUpHand();
        assertEquals(true, hand2.checkBlackJack());
        assertEquals(false, hand1.checkBlackJack());
    }

    /*
    Test whether checking the first card is first ace method works
     */
    @Test
    public void testFirstAce(){
        setUpHand();
        assertEquals(true, hand2.checkFirstAce());
        assertEquals(false, hand1.checkFirstAce());
    }

    /*
    Test whether the getBackGround method gets the correct background of cards
     */
    @Test
    public void testGetBackGround(){
        setUpHand();
        assertEquals(R.drawable.bj50, hand2.getCardBackGround(1));
    }

    /*
    Test whether checkDouble method works
     */
    @Test
    public void testCheckDouble(){
        setUpHand();
        hand3.getPoints();
        assertEquals(true, hand3.isAllowDouble());
    }

    /*
    Test whether the hand iterator works
     */
    @Test
    public void testHandIterator(){
        setUpHand();
        for(Card card : hand1){
            assertEquals(10, card.getInGameValue());
        }
    }
}