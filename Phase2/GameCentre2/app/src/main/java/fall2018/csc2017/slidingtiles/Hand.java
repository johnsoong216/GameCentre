package fall2018.csc2017.slidingtiles;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Hand implements Serializable {

    private List<Card> cards = new ArrayList<>();

    public void drawcard(Deck deck){
        cards.add(deck.popCard());
    }

    public int getCardBackGround(int position) {
        return cards.get(position).getBackground();
    }

    public int getPoints(){
        int points = 0;
        boolean hasAces = false;
        for (Card c: cards){
            points += c.getInGameValue();
            /*
            Check for Aces
             */
            if (c.getId() == 1){
                hasAces = true;
            }}
        if (hasAces){
            return points > 11 ? points: points + 10;
        }
        return points;
    }

    public boolean checkBlackJack(){
        return (getPoints() == 21 && getHandSize() == 2);
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
