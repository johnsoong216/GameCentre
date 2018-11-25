package fall2018.csc2017.slidingtiles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BlackJackGameActivity extends AppCompatActivity {
    Hand playerHand;
    Hand dealerHand;
    BlackJackManager blackJackManager;
    Deck deck = new Deck();
    private Button hitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_jack_game);
    }
    private void addHitButtonListener() {
        hitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blackJackManager.hit();
            }
        });
    }

}
