package fall2018.csc2017.slidingtiles;

public class BlackJackManager {
    private BlackJackGame blackJackGame;
    private boolean endGame;
    private int gameResult;


    public BlackJackManager(BlackJackGame blackJackGame) {
        this.blackJackGame = blackJackGame;
    }

    public void blackjack() {
        if (blackJackGame.getPlayerHand().checkBlackJack() &&
                !blackJackGame.getDealerHand().checkBlackJack()) {
            gameResult = 2;
            endGame = true;
        } else if (!blackJackGame.getPlayerHand().checkBlackJack() &&
                blackJackGame.getDealerHand().checkBlackJack()) {
            gameResult = -2;
            endGame = true;
        } else if (blackJackGame.getPlayerHand().checkBlackJack() &&
                blackJackGame.getDealerHand().checkBlackJack()) {
            gameResult = 0;
            endGame = true;
        }

    }

    public void hit() {
        blackJackGame.playerDrawCard();
        if (blackJackGame.isOver()) {
            endGame = true;
            gameResult = -1;
        }
    }

    public void douBle() {
        blackJackGame.playerDrawCard();
        gameResult = 3;
        endGame = true;
    }


    /*
    Player chooses not to draw any more cards
    Add a button listener in the Activity Page
     */
    public void stand() {
        while (blackJackGame.getDealerHand().getPoints() < 17 && blackJackGame.getDealerHand().getHandSize() < 5) {
            blackJackGame.dealerDrawCard();
        }
        normalGameCheck();
    }

    private void normalGameCheck() {
        if (blackJackGame.getDealerHand().goBusted()) {
            gameResult = 1;
        } else if (blackJackGame.getPlayerHand().getPoints() > blackJackGame.getDealerHand().getPoints()) {
            gameResult = 1;
        } else if (blackJackGame.getPlayerHand().getPoints() < blackJackGame.getDealerHand().getPoints()) {
            gameResult = -1;
        } else {
            gameResult = 0;
        }
        endGame = true;
    }

    public int getGameResult() {
        return gameResult;
    }

    public boolean getEndGame() {
        return endGame;
    }


}


