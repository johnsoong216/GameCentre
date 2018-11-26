package fall2018.csc2017.slidingtiles;

import java.io.Serializable;

public class BlackJackManager extends GameManager implements Serializable {


    private BlackJackGame blackJackGame;
    private int chips;
    private boolean insurance;


    public BlackJackManager(BlackJackGame blackJackGame, int chips) {
        this.blackJackGame = blackJackGame;
        this.chips = chips;
    }

//    public void blackjack() {
//        if (blackJackGame.getPlayerHand().checkBlackJack() &&
//                !blackJackGame.getDealerHand().checkBlackJack()) {
//            blackJackGame.inGameBet(1.5);
//        } else if (!blackJackGame.getPlayerHand().checkBlackJack() &&
//                blackJackGame.getDealerHand().checkBlackJack()) {
//            blackJackGame.inGameBet(1.5);}
//    }

    public void hit() {
        if (!blackJackGame.isOver()&& blackJackGame.getPlayerHand().getHandSize() < 5){
            blackJackGame.playerDrawCard();
//            blackJackGame.flip(false, -1);
        }
    }

    public void douBle() {
        if (!blackJackGame.isOver() && blackJackGame.getPlayerHand().isAllowDouble()){
        blackJackGame.playerDrawCard();
        blackJackGame.inGameBet(2);
//        blackJackGame.flip(false, -1);
        blackJackGame.endGame();
        }
    }

    public int getChips() {return chips;}
    /*
    Player chooses not to draw any more cards
    Add a button listener in the Activity Page
     */
    public void stand() {
        while (blackJackGame.getDealerHand().getPoints() < 17 && blackJackGame.getDealerHand().getHandSize() < 5) {
            blackJackGame.dealerDrawCard();
//            blackJackGame.flip(true, -1);
        }
        blackJackGame.endGame();
    }
    /*
    Settle the Amount of Chips if the player buys the insurance the player will reduce its bet on
    the game by one half
    */
    protected void settleChips() {
        if (insurance){
            blackJackGame.setBet(blackJackGame.getBet()/2);
        }
         if (blackJackGame.getPlayerPoint() < blackJackGame.getDealerPoint()) {
            chips -= blackJackGame.getBet();
        } else if (blackJackGame.getPlayerPoint() > blackJackGame.getDealerPoint()){
            chips += blackJackGame.getBet();
        }
    }

    public BlackJackGame getBlackJackGame() {
        return blackJackGame;
    }
}


