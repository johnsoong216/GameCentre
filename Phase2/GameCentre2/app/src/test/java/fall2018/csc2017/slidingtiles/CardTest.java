package fall2018.csc2017.slidingtiles;

import org.junit.Test;

import static org.junit.Assert.*;

public class CardTest {
    /*
    Cards for test
     */
    Card card1;
    Card card2;
    Card card3;
    Card card4;
    Card card5;
    Card card6;
    /**
     * set up cards for testing
     */
    private void setUpCards(){
        card1 = new Card(0,1);
        card2 = new Card(1,8);
        card3 = new Card(2,13);
        card4 = new Card(2,13);
        card5 = new Card(3,14);
        card6 = card3;
    }
    /**
     * test whether return the value of card 1
     */
    @Test
    public void getInGameValue1() {
        setUpCards();
        assertEquals(1, card1.getInGameValue());
    }
    /**
     * test whether return the value of card 2
     */
    @Test
    public void getInGameValue2() {
        setUpCards();
        assertEquals(8, card2.getInGameValue());
    }
    /**
     * test whether return the value of card 3
     */
    @Test
    public void getInGameValue3() {
        setUpCards();
        assertEquals(10, card3.getInGameValue());
    }
    /**
     * test whether return the id of card 1
     */
    @Test
    public void getId1() {
        setUpCards();
        assertEquals(1, card1.getId());
    }
    /**
     * test whether return the id of card 2
     */
    @Test
    public void getId2() {
        setUpCards();
        assertEquals(21, card2.getId());
    }
    /**
     * test whether return the id of card 3
     */
    @Test
    public void getId3() {
        setUpCards();
        assertEquals(39, card3.getId());
    }
    /**
     * test two cards are not equal
     */
    @Test
    public void equal1() {
        setUpCards();
        assertEquals(false ,card1 == card3);
        assertEquals(false, card2 == card3);
    }
    /**
     * test whether two cards are equal
     */
    @Test
    public void equal2() {
        setUpCards();
        assertEquals(true ,card6 == card3);
    }
}