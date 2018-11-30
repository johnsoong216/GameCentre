package fall2018.csc2017.Games;

import java.io.Serializable;

/**
 * A class of poker cards, it can find the id, background id and the in game value of a card
 */
public class Card implements Serializable {
    /**
     * the value of the card
     * Ace = 1
     * Jack = 11
     * Queen = 12
     * King = 13
     */
    private int value;
    /**
     * suit of the card
     */
    private int suit;
    /**
     * The background image the card corresponding to
     * 0 spades
     * 1 hearts
     * 2 clubs
     * 3 diamonds
     */
    private int background;

    /*
    Creating a new card with background id, suit and value
     */
    Card(int suit, int value) {
        this.suit = suit;
        this.value = value;
//        this.up = up;
        switch (getId()) {
            case 1:
                background = R.drawable.bj1;
                break;
            case 2:
                background = R.drawable.bj2;
                break;
            case 3:
                background = R.drawable.bj3;
                break;
            case 4:
                background = R.drawable.bj4;
                break;
            case 5:
                background = R.drawable.bj5;
                break;
            case 6:
                background = R.drawable.bj6;
                break;
            case 7:
                background = R.drawable.bj7;
                break;
            case 8:
                background = R.drawable.bj8;
                break;
            case 9:
                background = R.drawable.bj9;
                break;
            case 10:
                background = R.drawable.bj10;
                break;
            case 11:
                background = R.drawable.bj11;
                break;
            case 12:
                background = R.drawable.bj12;
                break;
            case 13:
                background = R.drawable.bj13;
                break;
            case 14:
                background = R.drawable.bj_14;
                break;
            case 15:
                background = R.drawable.bj_15;
                break;
            case 16:
                background = R.drawable.bj_16;
                break;
            case 17:
                background = R.drawable.bj_17;
                break;
            case 18:
                background = R.drawable.bj_18;
                break;
            case 19:
                background = R.drawable.bj_19;
                break;
            case 20:
                background = R.drawable.bj_20;
                break;
            case 21:
                background = R.drawable.bj_21;
                break;
            case 22:
                background = R.drawable.bj_22;
                break;
            case 23:
                background = R.drawable.bj_23;
                break;
            case 24:
                background = R.drawable.bj_24;
                break;
            case 25:
                background = R.drawable.bj_25;
                break;
            case 26:
                background = R.drawable.bj_26;
                break;
            case 27:
                background = R.drawable.bj_27;
                break;
            case 28:
                background = R.drawable.bj_28;
                break;
            case 29:
                background = R.drawable.bj_29;
                break;
            case 30:
                background = R.drawable.bj_30;
                break;
            case 31:
                background = R.drawable.bj_31;
                break;
            case 32:
                background = R.drawable.bj_32;
                break;
            case 33:
                background = R.drawable.bj_33;
                break;
            case 34:
                background = R.drawable.bj_34;
                break;
            case 35:
                background = R.drawable.bj_35;
                break;
            case 36:
                background = R.drawable.bj_36;
                break;
            case 37:
                background = R.drawable.bj_37;
                break;
            case 38:
                background = R.drawable.bj_38;
                break;
            case 39:
                background = R.drawable.bj_39;
                break;
            case 40:
                background = R.drawable.bj_40;
                break;
            case 41:
                background = R.drawable.bj_41;
                break;
            case 42:
                background = R.drawable.bj_42;
                break;
            case 43:
                background = R.drawable.bj_43;
                break;
            case 44:
                background = R.drawable.bj44;
                break;
            case 45:
                background = R.drawable.bj45;
                break;
            case 46:
                background = R.drawable.bj46;
                break;
            case 47:
                background = R.drawable.bj47;
                break;
            case 48:
                background = R.drawable.bj48;
                break;
            case 49:
                background = R.drawable.bj49;
                break;
            case 50:
                background = R.drawable.bj50;
                break;
            case 51:
                background = R.drawable.bj51;
                break;
            case 52:
                background = R.drawable.bj52;
                break;
            case 53:
                background = R.drawable.back;
        }
    }

    /**
     * Actual Id: from A to K used for Background Images
     *
     * @return actual id for the card
     */
    int getId() {
        return suit * 13 + value;
    }

    /**
     * Geting the in game value of the card
     *
     * @return in game value of the card
     */
    int getInGameValue() {
        return value > 10 ? 10 : value;
    }


    /**
     * Getting the background id of the card
     * @return background id
     */
    int getBackground() {
        return background;
    }

    @Override
    public boolean equals(Object obj) {
        Card c = (Card) obj;
        return c.getId() == this.getId();
    }
}
