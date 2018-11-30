
package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class SignUpController {

    /**
     * The context of this activity.
     */
    private Context context;
    /**
     * A UserManager object.
     */
    private UserManager manager;


    SignUpController(Context context, UserManager manager){
        this.context = context;
        this.manager = manager;
    }


    void signInLinkListener(TextView signInLink) {
        signInLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signinIntent = new Intent(context, SignUpSignInActivity.class);
                context.startActivity(signinIntent);
            }
        });
    }

    /**
     * A user's registration.
     */
    void addRegisterButtonListener(Button registerButton, final EditText username, final EditText password) {

        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean isSuccess = manager.registerUser(username.getText().toString(), password.getText().toString());
                if (isSuccess) {
                    Toast.makeText(context, "Success!", Toast.LENGTH_SHORT).show();
                    Intent success = new Intent(context, SignUpSignInActivity.class);
                    context.startActivity(success);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Username is taken").setNegativeButton("Retry", null)
                            .create().show();
                }
            }

        });
    }
}
