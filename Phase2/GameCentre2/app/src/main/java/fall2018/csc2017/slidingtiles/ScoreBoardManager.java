package fall2018.csc2017.slidingtiles;

import android.util.Pair;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * ScoreBoardManager class.
 */
class ScoreBoardManager extends GameManager implements Serializable {
    /**
     * The hashmap that contains score as keys and users as values
     * Contains all the scores and the corresponding users of the game
     */
    private HashMap<Integer, List<String>>
            scoreToUserBoard = new HashMap<>(15);
    /**
     * The hashmap that contains users as keys and scores as values
     * Contains all the scores and the corresponding users of the game
     */
    private HashMap<String, List<Integer>>
            userToScoreBoard = new HashMap<>(15);

    /**
     * The scoreboard being managed.
     */

    ScoreBoardManager() {
    }

    /**
     * Add a score associated with the user playing it.
     *
     * @param username current user
     * @param score    final score for the current user
     */

    void addScore(String username, int score) {
        if (scoreToUserBoard.containsKey(score)) {
            List<String> nameList = scoreToUserBoard.get(score);
            nameList.add(username);
            scoreToUserBoard.put(score, nameList);
        } else {
            List<String> z = new ArrayList<>(Collections.singletonList(username));
            scoreToUserBoard.put(score, z);
        }
        if (userToScoreBoard.containsKey(username)) {
            List<Integer> scoreList = userToScoreBoard.get(username);
            scoreList.add(score);
            userToScoreBoard.put(username, scoreList);
        } else {
            List<Integer> k = new ArrayList<>(Collections.singletonList(score));
            userToScoreBoard.put(username, k);
        }

    }

    /**
     * Return a list of Pairs that contains the top n scores and their users
     *
     * @param topN top n scores
     * @return the list of users with their top scores.
     */
    List<Pair<String, Integer>> maxGameScores(int topN) {
        Set<Integer> set = new HashSet<>(scoreToUserBoard.keySet());
        List<Pair<String, Integer>> result = new ArrayList<>();
        int num = 0;
        while (num != topN && !set.isEmpty()) {
            int s = Collections.max(set);
            List<String> nameList = scoreToUserBoard.get(s);
            for (String name : nameList) {
                if (num != topN) {
                    result.add(new Pair<>(name, s));
                    num++;
                }
            }
            set.remove(s);
        }
        return result;
    }

    /**
     * Return the highest score with the given user
     *
     * @param user this user.
     * @return the user's best score.
     */
    int maxUserScore(String user) {
        List<Integer> l = userToScoreBoard.get(user);
        return Collections.max(l);
    }


}
