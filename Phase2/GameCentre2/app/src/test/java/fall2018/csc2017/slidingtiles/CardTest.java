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

    private void setUpCards(){
        card1 = new Card(0,1);
        card2 = new Card(1,8);
        card3 = new Card(2,13);
        card4 = new Card(2,13);
    }
    @Test
    public void getInGameValue1() {
        setUpCards();
        assertEquals(1, card1.getInGameValue());
    }

    @Test
    public void getInGameValue2() {
        setUpCards();
        assertEquals(8, card2.getInGameValue());
    }

    @Test
    public void getInGameValue3() {
        setUpCards();
        assertEquals(10, card3.getInGameValue());
    }

    @Test
    public void getId1() {
        setUpCards();
        assertEquals(1, card1.getId());
    }

    @Test
    public void getId2() {
        setUpCards();
        assertEquals(21, card2.getId());
    }

    @Test
    public void getId3() {
        setUpCards();
        assertEquals(39, card3.getId());
    }

    @Test
    public void equal1() {
        setUpCards();
        assertEquals(false ,card1 == card3);
        assertEquals(false, card2 == card3);
    }
}