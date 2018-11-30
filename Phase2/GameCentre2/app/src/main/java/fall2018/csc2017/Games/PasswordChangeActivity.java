package fall2018.csc2017.Games;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

/**
 * Password activity.
 */
public class PasswordChangeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_change);

        UserManager manager = new UserManager(this);
        EditText username = findViewById(R.id.CPUsername);
        EditText newPassword = findViewById(R.id.CPNewPassword);
        EditText oldPassword = findViewById(R.id.CPOldPassword);
        Button confirmButton = findViewById(R.id.CPConfirmBtn);

        PasswordChangeController controller = new PasswordChangeController(this, manager);
        controller.changePasswordButton(confirmButton, oldPassword, newPassword, username);
    }

}
