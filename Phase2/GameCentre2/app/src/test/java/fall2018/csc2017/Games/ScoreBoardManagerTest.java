package fall2018.csc2017.Games;

import android.util.Pair;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ScoreBoardManagerTest {
    /**
     * The score board manager for testing.
     */
    private ScoreBoardManager scoreBoardManager = new ScoreBoardManager();
    private HashMap<Integer, List<String>> scoreToUserBoard = scoreBoardManager.getScoreToUserBoard();
    private HashMap<String, List<Integer>> userToScoreBoard = scoreBoardManager.getUserToScoreBoard();
    /**
     * set up an score board manager
     */
    private void setUpCorrect() {
        scoreBoardManager.addScore("Carol", 10);
        scoreBoardManager.addScore("Carol", 11);
        scoreBoardManager.addScore("Carol", 12);

        scoreBoardManager.addScore("Helen", 6);
        scoreBoardManager.addScore("Eric", 5);
        scoreBoardManager.addScore("Julia", 7);
        scoreBoardManager.addScore("Cici", 7);


    }
    /**
     * test whether the new scores are added to the score board
     */
    @Test
    public void testAddNewScore() {
        setUpCorrect();
        assertEquals("[Eric]", scoreToUserBoard.get(5).toString());
        assertEquals("[Helen]", scoreToUserBoard.get(6).toString());
    }
    /**
     * test whether the new user is added to the score board
     */
    @Test
    public void testAddNewUser() {
        setUpCorrect();
        assertEquals("[Eric]", scoreToUserBoard.get(5).toString());

    }
    /**
     * add an new user with existing score
     */
    @Test
    public void testAddExistingScore() {
        setUpCorrect();
        ArrayList<String> list = new ArrayList<String>();
        list.add("Eric");
        assertEquals(list, scoreToUserBoard.get(5));
    }
    /**
     * add an existing user to the score board
     */
    @Test
    public void testAddExistingUser() {
        setUpCorrect();
        assertEquals("[5]", userToScoreBoard.get("Eric").toString());
        assertEquals("[7]", userToScoreBoard.get("Julia").toString());
    }
    /**
     * test whether return the maximum score with selected user
     */
    @Test
    public void testMaxUserScore() {
        setUpCorrect();
        assertEquals(12, scoreBoardManager.maxUserScore("Carol"));
    }
    /**
     * test whether return the top n score
     */
    @Test
    public void testMaxGameScores() {
        setUpCorrect();
        List<Pair<String, Integer>> b = new ArrayList<Pair<String, Integer>>();
        b.add(new Pair<>("Julia", 7));//[("julia", 7)]
        assertEquals(b.get(0).first, scoreBoardManager.maxGameScores(1).get(0).first);
        assertEquals(b.get(0).second, scoreBoardManager.maxGameScores(1).get(0).second);
    }
}