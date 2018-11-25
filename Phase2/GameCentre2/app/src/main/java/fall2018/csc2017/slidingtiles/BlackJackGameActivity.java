package fall2018.csc2017.slidingtiles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BlackJackGameActivity extends AppCompatActivity {
    Hand playerHand;
    Hand dealerHand;
    Deck deck = new Deck();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_jack_game);
    }

}
