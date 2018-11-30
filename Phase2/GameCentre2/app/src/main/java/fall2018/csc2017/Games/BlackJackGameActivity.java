package fall2018.csc2017.Games;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * The black jack game activity in game view
 */

public class BlackJackGameActivity extends AppCompatActivity {
    private ImageView[] playerCards;
    private ImageView[] dealerCards;
    private Context context;
    private TextView chipsTotal;
    private Button[] buttonList;
    private BlackJackGameController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_jack_game);
        context = this;
        playerCards = new ImageView[]{findViewById(R.id.playerCard1), findViewById(R.id.playerCard2)
                , findViewById(R.id.playerCard3), findViewById(R.id.playerCard4), findViewById(R.id.playerCard5)};
        dealerCards = new ImageView[]{findViewById(R.id.dealerCard1), findViewById(R.id.dealerCard2)
                , findViewById(R.id.dealerCard3), findViewById(R.id.dealerCard4), findViewById(R.id.dealerCard5)};
        chipsTotal = findViewById(R.id.chipsTotal);
        buttonList = new Button[]{findViewById(R.id.btHit), findViewById(R.id.btDouble),
                findViewById(R.id.btStand), findViewById(R.id.btNewRound), findViewById(R.id.btHint),
                findViewById(R.id.btInsurance)};
        controller = new BlackJackGameController(context, buttonList, playerCards, dealerCards, chipsTotal);
        controller.setButtonOnOff(false);
        controller.addHitButtonListener();
        controller.addNewRoundButtonListener();
        controller.addStandButtonListener();
        controller.addDoubleButtonListener();
        controller.addHintButtonListener();
        controller.addInsuranceButtonListener();
    }

    protected void onResume() {
        super.onResume();
        controller.setHandImage();
        controller.setChipsTotal();
    }

    protected void onPause() {
        super.onPause();
        controller.saveGame();
    }

    @Override
    public void onBackPressed() {
        Intent backToMain = new Intent(BlackJackGameActivity.this, BlackJackStartingActivity.class);
        BlackJackGameActivity.this.startActivity(backToMain);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

    }
}
