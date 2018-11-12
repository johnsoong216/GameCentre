package fall2018.csc2017.slidingtiles;

import java.io.Serializable;

////https://github.com/onurkagan/Singleton-Design-Pattern-Example/blob/master/app/src/main/java/com/onurkagan/singleton_design_pattern_example/UserSingletonModel.java

/**
 * A Session class that identifies current user
 */
public class Session implements Serializable {

    private static Session session;
    /**
     * The user's username.
     */
    private String username;
    /**
     * The user's password.
     */
    private String password;
    /**
     *
     */
    private int score;

    /**
     * Initialize a user session.
     */
    private Session() {
    }

    static Session getCurrentUser() {
        if (session == null) {
            session = new Session();
        }
        return session;
    }

    /**
     * Sign the user out.
     */
    void logout() {
        session = null;
    }

    /**
     * Get the user's username.
     *
     * @return user's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the user's username.
     *
     * @param username the user's username.
     */
    public void setUsername(String username) {
        this.username = username;
    }


    /**
     * Set the user's password.
     *
     * @param password the user's password.
     */
    void setPassword(String password) {
        this.password = password;
    }

    /**
     * Return the user's game score.
     *
     * @return the user's game score.
     */
    int getScore() {
        return score;
    }

    /**
     * Set the user's game score.
     *
     * @param score the user's game score.
     */
    void setScore(int score) {
        this.score = score;
    }
}
