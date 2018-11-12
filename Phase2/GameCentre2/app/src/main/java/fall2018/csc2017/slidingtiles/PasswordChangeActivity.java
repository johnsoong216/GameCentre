package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Password activity.
 */
public class PasswordChangeActivity extends AppCompatActivity {

    /**
     * Edittext for user interaction.
     */
    private EditText username;
    private EditText newpassword;
    private EditText oldpassword;

    /**
     * Button for display.
     */
    private Button confirmButton;

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
        setContentView(R.layout.activity_password_change);

        manager = new UserManager(this);
        context = this;

        username = findViewById(R.id.CPUsername);
        newpassword = findViewById(R.id.CPNewPassword);
        oldpassword = findViewById(R.id.CPOldPassword);
        confirmButton = findViewById(R.id.CPConfirmBtn);


        changePasswordButton();
    }

    /**
     * Change a user's password.
     */
    private void changePasswordButton() {
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mUser = username.getText().toString();
                String mOPass = oldpassword.getText().toString();
                String mNPass = newpassword.getText().toString();
                boolean userExists = manager.checkLoginValidate(mUser, mOPass);
                if (userExists) {
                    boolean success = manager.changePassword(mUser, mNPass, mOPass);
                    if (success) {
                        Toast.makeText(context, "Success!", Toast.LENGTH_SHORT).show();
                        Intent backtomain = new Intent(context, SignUpSignInActivity.class);
                        context.startActivity(backtomain);
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("Please try again").setNegativeButton("Retry", null)
                                .create().show();
                    }
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Please try again").setNegativeButton("Retry", null)
                            .create().show();
                }

            }
        });
    }
}
