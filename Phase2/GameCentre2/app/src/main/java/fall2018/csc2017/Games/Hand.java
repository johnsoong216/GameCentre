package fall2018.csc2017.Games;
/*
A hand of maximum five cards, it can draw card from deck and detect double, Blackjack, or insurance
condition
 */

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Hand implements Serializable, Iterable<Card> {
    /*
    A list of Cards
     */
    private List<Card> cards = new ArrayList<>();
    /*
    The boolean to detect if the hand allows double action, default in false contdition
     */
    private boolean allowDouble = false;

    /*
    Return if the hand has 9, 10 or 11 points
     */
    boolean isAllowDouble() {
        return allowDouble;
    }

    /*
    Draw a card from deck
     */
    void drawCard(Deck deck) {
        cards.add(deck.popCard());
    }

    /*
    Return the point value of the hand
     */
    int getPoints() {
        int points = 0;
        boolean hasAces = false;
        boolean hasDouble = false;
        for (Card c : cards) {
            points += c.getInGameValue();
            /*
            Check for Aces
             */
            if (c.getInGameValue() == 1) {
                hasAces = true;
            }

            if (c.getInGameValue() == 10 || c.getInGameValue() == 9 || c.getInGameValue() == 8) {
                hasDouble = true;
            }
        }
        if (points >= 9 && points <= 11) {
            allowDouble = true;
        }
        if (hasAces) {
            if (hasDouble) {
                allowDouble = true;
            }
            return points > 11 ? points : points + 10;
        }
        return points;
    }

    /*
    check if the hand contains a blackjack
     */
    boolean checkBlackJack() {
        return (getPoints() == 21 && getHandSize() == 2);
    }

    /*
    Exceeds 21
     */
    boolean goBusted() {
        return getPoints() > 21;
    }

    /*
    Return the size of the hand
     */
    int getHandSize() {
        return cards.size();
    }

    /*
    Return if the first card is an ace
     */
    boolean checkFirstAce() {
        return cards.get(0).getInGameValue() == 1;
    }

    /*
    Return the background id for the card at the position
     */
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
