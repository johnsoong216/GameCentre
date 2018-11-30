//excluded from tests because it's a (model / view) class
package fall2018.csc2017.Games;
/**
 * A activity that preforms sign up/sign in functions
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignUpSignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_sign_in);


         UserManager manager = new UserManager(this);
         EditText username = findViewById(R.id.etUsername);
         EditText password = findViewById(R.id.etPassword);
         Button signInButton = findViewById(R.id.btSignIn);
        TextView registerLink = findViewById(R.id.tvSignUp);
        TextView changePasswordLink = findViewById(R.id.Changepassword);

        SignUpSignInController controller = new SignUpSignInController(this, manager);
        controller.addRegisterLinkListener(registerLink);
        controller.changePasswordLinkListener(changePasswordLink);
        controller.addSignInButtonListener(signInButton, username, password);
    }
}

