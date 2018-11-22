package blackjack;

public class Card {
    private int value;
    private int suit;

    public Card(int suit, int value){
        this.suit = suit;
        this.value = value;
    }

    /*
    Actual Value: from A to K
     */
    public int getValue(){
        return value;
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
