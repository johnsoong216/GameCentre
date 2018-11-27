package fall2018.csc2017.slidingtiles;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

/*
A deck that contains cards and is iterable
it can pop card from the deck and create a new deck with 52 shuffled card
 */
public class Deck implements Serializable, Iterable<Card> {
    /*
    the stack of cards
     */
    private Stack<Card> cardStack;

    /*
    create a new deck
     */
    private Stack copiedStack;

    Deck() {
        this.cardStack = newDeck();
    }

    /*
    Create a stack of cards with 52 shuffled cards
     */
    private Stack<Card> newDeck() {
        List<Card> cards = new ArrayList<>();
        Stack<Card> allCards = new Stack<>();
        for (int value = 1; value != 14; value++) {
            for (int suit = 0; suit != 4; suit++) {
                Card card = new Card(suit, value);
                cards.add(card);
            }
        }

        Collections.shuffle(cards);
        for (int i = 0; i != 52; i++) {
            allCards.push(cards.get(i));
        }
        return allCards;
    }

    /*
    retrun the first card on the deck
     */
    Card popCard() {
        return cardStack.pop();
    }

    /*
    Return the number of cards remaining in the deck
     */
    int remainingCard() {
        return cardStack.size();
    }

    @NonNull
    @Override
    public Iterator<Card> iterator() {
        copiedStack = (Stack) cardStack.clone();
        return new DeckIterator();
    }

    private class DeckIterator implements Iterator<Card> {
        /**
         * The index of card in hand
         */
        int i = 0;


        @Override
        public boolean hasNext() {

            return i < cardStack.size();
        }

        @Override
        public Card next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            } else {
                Card result = (Card) copiedStack.pop();
                i++;
                return result;
            }


        }
    }
}
