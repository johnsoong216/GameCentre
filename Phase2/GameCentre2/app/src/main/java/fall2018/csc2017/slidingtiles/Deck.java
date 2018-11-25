package fall2018.csc2017.slidingtiles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fall2018.csc2017.slidingtiles.Stack;

public class Deck {

    private int size;
    private Stack<Card> cardStack;

    public Deck(){
        this.cardStack = newDeck();
        this.size = cardStack.size();
    }

    private Stack<Card> newDeck(){
        List<Card> cards = new ArrayList<>();
        Stack<Card> allCards = new Stack<>();
        for (int value = 1; value != 14; value++) {
            for (int suit = 0; suit != 4; suit++){
            Card card = new Card(suit, value);
            cards.add(card);}
        }

    Collections.shuffle(cards);
    for (int i = 0; i != 52; i++){
        allCards.push(cards.get(i)); }
    return allCards;
    }

    public Card popCard(){
        return cardStack.pop();
    }
    /*
    shuffle when there are 26 cards left
    */

    public void shuffle(){
        if (size <= 26){
            cardStack = newDeck();
        }
    }
}
