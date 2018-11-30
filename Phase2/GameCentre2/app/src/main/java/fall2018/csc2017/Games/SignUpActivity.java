//excluded from tests because it's a (model / view) class
package fall2018.csc2017.Games;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * SignUp Activity.
 */
public class SignUpActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        UserManager manager = new UserManager(this);
        EditText username = findViewById(R.id.etUsername);
        EditText password = findViewById(R.id.etPassword);
        Button registerButton = findViewById(R.id.btRegister);
        TextView signInLink = findViewById(R.id.tvSignIn);
        SignUpController controller = new SignUpController(this, manager);
        controller.addRegisterButtonListener(registerButton, username, password);
        controller.signInLinkListener(signInLink);
    }

//    private void signInLinkListener() {
//        final TextView signInLink = findViewById(R.id.tvSignIn);
//        signInLink.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent signinIntent = new Intent(context, SignUpSignInActivity.class);
//                SignUpActivity.this.startActivity(signinIntent);
//                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
//
//            }
//        });
//    }
//
//    /**
//     * A user's registration.
//     */
//    private void addRegisterButtonListener() {
//
//        RegisterButton.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                String mUser = username.getText().toString();
//                String mPass = password.getText().toString();
//                boolean isSuccess = manager.registerUser(mUser, mPass);
//                if (isSuccess) {
//                    Toast.makeText(context, "Success!", Toast.LENGTH_SHORT).show();
//                    Intent success = new Intent(context, SignUpSignInActivity.class);
//                    SignUpActivity.this.startActivity(success);
//                } else {
//                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                    builder.setMessage("Username is taken").setNegativeButton("Retry", null)
//                            .create().show();
//                }
//            }
//
//        });
//    }
}
