package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpSignInActivity extends AppCompatActivity {

    /**
     * EditText for user's information.
     */
    private EditText username;
    private EditText password;
    /**
     * SignIn Button.
     */
    private Button SignInButton;
    /**
     * A UserManager object.
     */
    private UserManager manager;
    /**
     * A Session object that holds user's information.
     */
    private Session user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_sign_in);

        manager = new UserManager(this);
        username = findViewById(R.id.etUsername);
        password = findViewById(R.id.etPassword);
        SignInButton = findViewById(R.id.btSignIn);
        final TextView registerLink = findViewById(R.id.tvSignUp);
        final TextView changepasswordLink = findViewById(R.id.Changepassword);

        addregisterLinkListener(registerLink);
        changepasswordLinkListener(changepasswordLink);
        addSignInButtonListener();
    }

    /**
     * Link to the activity that allows user to change their password.
     *
     * @param changepasswordLink Textview link.
     */
    private void changepasswordLinkListener(TextView changepasswordLink) {
        changepasswordLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cpIntent = new Intent(SignUpSignInActivity.this, PasswordChangeActivity.class);
                SignUpSignInActivity.this.startActivity(cpIntent);
            }
        });
    }

    /**
     * A registration link.
     *
     * @param registerLink Textview link.
     */
    private void addregisterLinkListener(TextView registerLink) {
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupIntent = new Intent(SignUpSignInActivity.this, SignUpActivity.class);
                SignUpSignInActivity.this.startActivity(signupIntent);

            }
        });
    }

    /**
     * A user performing a login action.
     */
    private void addSignInButtonListener() {
        SignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mUser = username.getText().toString();
                String mPassword = password.getText().toString();
                boolean isSuccess = manager.checkLoginValidate(mUser, mPassword);
                user = Session.getCurrentUser();
                user.setUsername(mUser);
                user.setPassword(mPassword);
                if (isSuccess && !mUser.equals("admin")) {
                    Toast.makeText(SignUpSignInActivity.this, "Successfully Logged In!", Toast.LENGTH_SHORT).show();
                    Intent success = new Intent(SignUpSignInActivity.this, StartingActivity.class);
                    SignUpSignInActivity.this.startActivity(success);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignUpSignInActivity.this);
                    builder.setMessage("Login failed").setNegativeButton("Retry", null)
                            .create().show();
                    user.logout();
                }
            }
        });
    }
}

