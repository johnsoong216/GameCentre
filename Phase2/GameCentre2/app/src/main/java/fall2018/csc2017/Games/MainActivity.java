//excluded from tests because it's a (model / view) class
package fall2018.csc2017.Games;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button loginButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginButton = findViewById(R.id.StartButton);
        addStartButtonListener();
    }

    private void addStartButtonListener() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToLogin();
            }
        });
    }

    private void switchToLogin() {
        Intent login = new Intent(this, SignUpSignInActivity.class);
        startActivity(login);
    }
}
