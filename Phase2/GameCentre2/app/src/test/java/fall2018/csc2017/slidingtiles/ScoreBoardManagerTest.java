package fall2018.csc2017.slidingtiles;

import android.util.Pair;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ScoreBoardManagerTest {
    private ScoreBoardManager scoreBoardManager = new ScoreBoardManager();
    private HashMap<Integer, List<String>> scoreToUserBoard = scoreBoardManager.getScoreToUserBoard();
    private HashMap<String, List<Integer>> userToScoreBoard = scoreBoardManager.getUserToScoreBoard();

    private void setUpCorrect() {
        scoreBoardManager.addScore("Carol", 10);
        scoreBoardManager.addScore("Carol", 11);
        scoreBoardManager.addScore("Carol", 12);

        scoreBoardManager.addScore("Helen", 6);
        scoreBoardManager.addScore("Eric", 5);
        scoreBoardManager.addScore("Julia", 7);
        scoreBoardManager.addScore("Cici", 7);


    }


    @Test
    public void testAddNewScore() {
        setUpCorrect();
        assertEquals("[Eric]", scoreToUserBoard.get(5).toString());
        assertEquals("[Helen]", scoreToUserBoard.get(6).toString());
    }

    @Test
    public void testAddNewUser() {
        setUpCorrect();
        assertEquals("[Eric]", scoreToUserBoard.get(5).toString());

    }

    @Test
    public void testAddExistingScore() {
        setUpCorrect();
        ArrayList<String> list = new ArrayList<String>();
        list.add("Eric");
        assertEquals(list, scoreToUserBoard.get(5));
    }

    @Test
    public void testAddExistingUser() {
        setUpCorrect();
        assertEquals("[5]", userToScoreBoard.get("Eric").toString());
        assertEquals("[7]", userToScoreBoard.get("Julia").toString());
    }

    @Test
    public void testMaxUserScore() {
        setUpCorrect();
        assertEquals(12, scoreBoardManager.maxUserScore("Carol"));
    }

    @Test
    public void testMaxGameScores() {
        setUpCorrect();
        List<Pair<String, Integer>> b = new ArrayList<Pair<String, Integer>>();
        b.add(new Pair<>("Julia", 7));//[("julia", 7)]
        assertEquals(b.get(0).first, scoreBoardManager.maxGameScores(1).get(0).first);
        assertEquals(b.get(0).second, scoreBoardManager.maxGameScores(1).get(0).second);
    }
}