
package fall2018.csc2017.Games;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class SignUpSignInController {

    /**
     * The context of this activity.
     */
    private Context context;
    /**
     * A UserManager object.
     */
    private UserManager manager;

    /*
    * The session of the user
     */
    private Session user;


    SignUpSignInController(Context context, UserManager manager){
        this.context = context;
        this.manager = manager;
    }




    /**
     * Link to the activity that allows user to change their password.
     *
     * @param changePasswordLink Textview link.
     */
    void changePasswordLinkListener(TextView changePasswordLink) {
        changePasswordLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cpIntent = new Intent(context, PasswordChangeActivity.class);
                context.startActivity(cpIntent);
            }
        });
    }



    /**
     * A registration link.
     *
     * @param registerLink Textview link.
     */
    void addRegisterLinkListener(TextView registerLink) {
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupIntent = new Intent(context, SignUpActivity.class);
                context.startActivity(signupIntent);
            }
        });
    }
    /**
     * A user performing a login1 action.
     */
    void addSignInButtonListener(Button signInButton, final EditText username, final EditText password) {
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = Session.getCurrentUser();
                user.setUsername(username.getText().toString());
                user.setPassword(password.getText().toString());
                boolean isSuccess = manager.checkLoginValidate(username.getText().toString(), password.getText().toString());
                if (isSuccess && !username.getText().toString().equals("admin")) {
                    Toast.makeText(context, "Successfully Logged In!", Toast.LENGTH_SHORT).show();
                    Intent success = new Intent(context, ChooseGameActivity.class);
                    context.startActivity(success);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Login failed").setNegativeButton("Retry", null)
                            .create().show();
                    user.logout();
                }
            }
        });
    }

}

