package fall2018.csc2017.slidingtiles;


import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Hand implements Serializable, Iterable<Card>{
    private List<Card> cards = new ArrayList<>();

    public boolean isAllowDouble() {
        return allowDouble;
    }

    private boolean allowDouble = false;

    public void drawcard(Deck deck){
        cards.add(deck.popCard());
    }

    public int getPoints(){
        int points = 0;
        boolean hasAces = false;
        boolean hasDouble = false;
        for (Card c: cards){
            points += c.getInGameValue();
            /*
            Check for Aces
             */
            if (c.getInGameValue() == 1){
                hasAces = true;
            }

            if (c.getInGameValue() == 10 || c.getInGameValue() == 9 || c.getInGameValue() == 8){
                hasDouble = true;
            }}
        if (points >= 9 && points <= 11){
            allowDouble = true;
        }
        if (hasAces){
            if (hasDouble){
                allowDouble = true;
            }
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

    /*
    Return if the first card is an ace
     */
    public boolean checkFirstAce(){return cards.get(0).getInGameValue() == 1;}

    public int getCardBackGround(int position) {
        return cards.get(position).getBackground();
    }

    @NonNull
    @Override
    public Iterator iterator() {
        return new HandIterator();
    }
    private class HandIterator implements Iterator<Card> {
        /**
         * The index of card in hand
         */
        int i = 0;


        @Override
        public boolean hasNext() {

            return i < getHandSize();
        }

        @Override
        public Card next() {

            if (!hasNext()) {
                throw new NoSuchElementException();
            } else {
                Card result = cards.get(i);
                i++;

                return result;
            }


        }
    }
}
