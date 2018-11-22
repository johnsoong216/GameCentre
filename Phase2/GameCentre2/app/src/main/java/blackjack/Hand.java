package blackjack;


import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> cards = new ArrayList<>();
    private boolean house;

    /*
    true if it's the dealer, false if it's the player
    for the dealer, he must keep drawing until the value reaches 17 and stop once it reaches 17
     */
    public Hand(boolean house){
        this.house = house;

    }

    public void drawcard(Deck deck){
        cards.add(deck.popCard());
    }

    public int[] getPoints(){
        int version1 = 0;
        boolean hasAces = false;
        for (Card c: cards){
            version1 += c.getInGameValue();
            /*
            Check for Aces
             */
            if (c.getValue() == 1){
                hasAces = true;
            }}
        if (hasAces){
            return new int[]{version1, version1 + 10};
        }
        return new int[]{version1};
    }

    /*
    Exceeds 21
     */
    public boolean goBusted(){
        if (getPoints().length == 1){
            return (getPoints()[0] > 21);
        }
        else
            return (getPoints()[1] > 21);
    }

    public int getHandSize(){
        return cards.size();
    }
}
