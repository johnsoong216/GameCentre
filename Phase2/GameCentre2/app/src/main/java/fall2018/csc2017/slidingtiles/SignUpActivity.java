package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * SignUp Activity.
 */
public class SignUpActivity extends AppCompatActivity {

    /**
     * EditText for the user's information.
     */
    private EditText username;
    private EditText password;
    /**
     * Button for display.
     */
    private Button RegisterButton;

    /**
     * The context of this activity.
     */
    private Context context;
    /**
     * A UserManager object.
     */
    private UserManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        manager = new UserManager(this);
        context = this;

        username = findViewById(R.id.etUsername);
        password = findViewById(R.id.etPassword);
        RegisterButton = findViewById(R.id.btRegister);

        final TextView signInLink = findViewById(R.id.tvSignIn);
        signInLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signinIntent = new Intent(context, SignUpSignInActivity.class);
                SignUpActivity.this.startActivity(signinIntent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

            }
        });
        addRegisterButtonListener();
    }

    /**
     * A user's registration.
     */
    private void addRegisterButtonListener() {

        RegisterButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String mUser = username.getText().toString();
                String mPass = password.getText().toString();
                boolean isSuccess = manager.registerUser(mUser, mPass);
                if (isSuccess) {
                    Toast.makeText(context, "Success!", Toast.LENGTH_SHORT).show();
                    Intent success = new Intent(context, SignUpSignInActivity.class);
                    SignUpActivity.this.startActivity(success);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Username is taken").setNegativeButton("Retry", null)
                            .create().show();
                }
            }

        });
    }
}
