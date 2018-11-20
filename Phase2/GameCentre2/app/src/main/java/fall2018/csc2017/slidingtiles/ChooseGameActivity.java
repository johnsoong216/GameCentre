package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseGameActivity extends AppCompatActivity {
    private Button st;
    private Button fi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_game);
        st = findViewById(R.id.bt_slidingtiles);
        fi = findViewById(R.id.bt_flipit);
        addSlidingListener();
        addFlipListener();
    }
    private void addSlidingListener() {
        st.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toStarting = new Intent(ChooseGameActivity.this, StartingActivity.class);
                startActivity(toStarting);
            }
        });
    }
    private void addFlipListener() {
        fi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toStarting = new Intent(ChooseGameActivity.this, StartingActivity.class);
                startActivity(toStarting);
            }
        });
    }
}
