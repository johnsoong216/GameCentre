package blackjack;

public class BlackJackManager {
    private Deck deck;
    private boolean endGame;
    private int gameResult;
    private Hand dealer;
    private Hand player;


    public BlackJackManager() {
        deck = new Deck();
        dealer.drawcard(deck);
        dealer.drawcard(deck);
        player.drawcard(deck);
        player.drawcard(deck);
    }

    public void blackjack() {
        if (player.checkBlackJack() && !dealer.checkBlackJack()) {
            gameResult = 2;
            endGame = true;
        } else if (!player.checkBlackJack() && dealer.checkBlackJack()) {
            gameResult = -2;
            endGame = true;
        } else if (player.checkBlackJack() && dealer.checkBlackJack()) {
            gameResult = 0;
            endGame = true;
        }

    }

    public void hit() {
        player.drawcard(deck);
        if (player.goBusted()) {
            endGame = true;
            gameResult = -1;
        }
    }

    public void douBle() {
        player.drawcard(deck);
        gameResult = 3;
        endGame = true;
    }


    /*
    Player chooses not to draw any more cards
    Add a button listener in the Activity Page
     */
    public void stand() {
        while (dealer.getPoints() < 17 && dealer.getHandSize() < 5) {
            dealer.drawcard(deck);
        }
        normalGameCheck();
    }

    private void normalGameCheck() {
        if (dealer.goBusted()) {
            gameResult = 1;
        }
        else if (player.getPoints() > dealer.getPoints()){
            gameResult = 1;}
        else if (player.getPoints() < dealer.getPoints()){
            gameResult = -1;
            }
        else if (player.getPoints() == dealer.getPoints()){
            gameResult = 0;
        }
        endGame = true;
    }

    public int getGameResult(){
        return gameResult;
    }

    public boolean getEndGame(){
        return endGame;
    }



}


