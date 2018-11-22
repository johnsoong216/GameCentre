package blackjack;

public class Card {
    private int value;
    private int suit;

    /*
    0 spades
    1 hearts
    2 clubs
    3 diamonds
     */
    public Card(int suit, int value){
        this.suit = suit;
        this.value = value;
    }

    /*
    Actual Value: from A to K used for Background Images
     */
    public int getValue(){
        return suit * 13 + value;
    }
    /*
    In Game Value: A = 1/11, JQK = 10
     */

    public int getInGameValue(){
        return value > 10 ? 10: value;
    }
    @Override
    public String toString(){
        return "This is Card" + suit + " " + value;
    }
}
