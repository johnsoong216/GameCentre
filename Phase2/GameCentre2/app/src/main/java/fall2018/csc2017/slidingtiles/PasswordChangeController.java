package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PasswordChangeController {

    /**
     * The context of this activity.
     */
    private Context context;
    /**
     * A UserManager object.
     */
    private UserManager manager;


    PasswordChangeController(Context context, UserManager manager){
        this.context = context;
        this.manager = manager;
    }


    void changePasswordButton(Button confirmButton, final EditText oldPassword, final EditText newPassword,
                                      final EditText username) {
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = username.getText().toString();
                String oPass= oldPassword.getText().toString();
                String nPass = newPassword.getText().toString();

                boolean userExists = manager.checkLoginValidate(user, oPass);
                boolean success = manager.changePassword(user, oPass, nPass);

                if (userExists && success) {
                    Toast.makeText(context, "Success!", Toast.LENGTH_SHORT).show();
                    Intent backToMain = new Intent(context, SignUpSignInActivity.class);
                    context.startActivity(backToMain);
                } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("Please try again").setNegativeButton("Retry", null)
                                .create().show(); }
            }
        });
    }
}
