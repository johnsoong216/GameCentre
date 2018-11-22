package blackjack;

public class BlackJackManager {
    private Deck deck;
    private boolean endGame;
    private Hand dealer;
    private Hand player;


    public BlackJackManager() {
        deck = new Deck();
        dealer.drawcard(deck);
        dealer.drawcard(deck);
        player.drawcard(deck);
        player.drawcard(deck);
    }

    public void play() {
        if (player.getHandSize() < 5) {
            player.drawcard(deck);
        }
        if (dealer.getHandSize() < 5 && !player.goBusted()) {
            dealer.drawcard(deck);
        }

        if (player.goBusted() | dealer.goBusted()) {
            endGame = true;
        }
    }

    /*
    Player chooses not to draw any more cards
    Add a button listener in the Activity Page
     */
    public void endGame() {
        while (dealer.getPoints() < 17 && dealer.getHandSize() < 5) {
            dealer.drawcard(deck);
        }
        endGame = true;
    }

    /*
    -1 is player loss
    0 is draw
    1 is player victory
     */
    public int gameResult() {
        if (endGame) {
            if (player.goBusted() | dealer.getPoints() > player.getPoints()) {
                return -1;
            } else if (dealer.goBusted() | dealer.getPoints() < player.getPoints()) {
                return 1;
            }
            return 0;
        }
        return 2;
    }
}


