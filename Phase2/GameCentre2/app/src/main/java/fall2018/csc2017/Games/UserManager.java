package fall2018.csc2017.Games;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/**
 * A UserManager class.that help store user's information for registration and signin actions
 */
class UserManager {

    /**
     * SharedPreferences stores users' information.
     */
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    /**
     * Initializes a UserManager object.
     * @param context the activity context.
     */
    UserManager(Context context) {
        String KEY_PREFS = "Database";
        preferences = context.getSharedPreferences(KEY_PREFS, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    /**
     * Checks if the user's information is correct to perform a login1 action.
     * @param username this user's username.
     * @param password this user's password.
     * @return whether or not a user can sign in.
     */
    boolean checkLoginValidate(String username, String password) {
        String realUser = preferences.getString(username+password, "");

        return (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) &&
                (username+password).equals(realUser);
    }

    /**
     * Performs a registration action.
     * @param username this user's username.
     * @param password this user's password.
     * @return whether or not the registration action can be performed.
     */
    boolean registerUser(String username, String password) {

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            return false;
        }
        else if (preferences.getString(username, "").equals("")){
            editor.putString(username, username);
            editor.putString(username+password, username+password);
            return editor.commit();
        }
        return false;
    }

    /**
     * Performs a password change action.
     * @param username this user's username.
     * @param newpassword this user's new password.
     * @param oldpassword this user's old password.
     * @return whether or not this user's password can be changed.
     */
    boolean changePassword(String username, String newpassword, String oldpassword) {

        if (TextUtils.isEmpty(newpassword)) {
            return false;
        }
        else {
            editor.remove(username+oldpassword);
            editor.apply();
            editor.putString(username+newpassword, username+newpassword);
            return editor.commit();
        }
    }
}

