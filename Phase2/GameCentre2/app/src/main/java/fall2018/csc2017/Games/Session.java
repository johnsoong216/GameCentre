package fall2018.csc2017.Games;

import java.io.Serializable;

////https://github.com/onurkagan/Singleton-Design-Pattern-Example/blob/master/app/src/main/java/com/onurkagan/singleton_design_pattern_example/UserSingletonModel.java

/**
 * A Session class that identifies current user, it can set/get user's name password and scores
 */
public class Session implements Serializable {

    /**
     * It is a singleton that stores user's information
     * It is set to be static to let classes to access the information of the current user
     * before the user logout
     */
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
