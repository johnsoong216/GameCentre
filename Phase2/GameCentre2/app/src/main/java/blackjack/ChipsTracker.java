package blackjack;

public class ChipsTracker {
    private int chipValue;
    private int stakeValue;

    public int getChipValue() {
        return chipValue;
    }

    public void setChipValue(int chipValue) {
        this.chipValue = chipValue;
    }

    public void setStake(int stakeValue){
        this.stakeValue = stakeValue;
        this.chipValue -= stakeValue;
    }

    public void gameOutcome(BlackJackManager blackJack){
        if (blackJack.getEndGame()){
            switch (blackJack.getGameResult()){
                case 2: chipValue += 2.5 * stakeValue;
                case 1: chipValue += 2 * stakeValue;
                case 0: chipValue += stakeValue;
                case -1: chipValue += 0;
                case -2: chipValue -= 1.5 * stakeValue;
                default: chipValue+= 0;
            }
        }
    }

    public boolean checkBankruptcy(){
        return (chipValue <= 0);
    }
}
