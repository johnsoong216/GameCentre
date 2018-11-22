package blackjack;


import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> cards = new ArrayList<>();

    public void drawcard(Deck deck){
        cards.add(deck.popCard());
    }

    public int getPoints(){
        int points = 0;
        boolean hasAces = false;
        for (Card c: cards){
            points += c.getInGameValue();
            /*
            Check for Aces
             */
            if (c.getValue() == 1){
                hasAces = true;
            }}
        if (hasAces){
            return points > 11 ? points: points + 10;
        }
        return points;
    }

    /*
    Exceeds 21
     */
    public boolean goBusted(){
        return getPoints() > 21;
    }

    public int getHandSize(){
        return cards.size();
    }
}
