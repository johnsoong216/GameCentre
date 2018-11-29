package fall2018.csc2017.slidingtiles;

import android.util.Pair;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class ScoreBoardManagerTest {
    private ScoreBoardManager scoreBoardManager = new ScoreBoardManager();
    private HashMap<Integer, List<String>> scoreToUserBoard = scoreBoardManager.getScoreToUserBoard();
    private HashMap<String, List<Integer>> userToScoreBoard = scoreBoardManager.getUserToScoreBoard();

    @Test
    public void testAddNewScore() {
        scoreBoardManager.addScore("Carol", 5);
        scoreBoardManager.addScore("Helen", 6);
        assertEquals("[Carol]", scoreToUserBoard.get(5).toString());
        assertEquals("[Helen]", scoreToUserBoard.get(6).toString());
    }
    @Test
    public void testAddNewUser(){
        scoreBoardManager.addScore("Carol", 5);
        scoreBoardManager.addScore("Helen", 5);
        assertEquals("[Carol, Helen]", scoreToUserBoard.get(5).toString());

    }
    @Test
    public void testAddExistingScore() {
        scoreBoardManager.addScore("Eric", 5);
        scoreBoardManager.addScore("David", 5);
        ArrayList<String> list = new ArrayList<String>();
        list.add("Eric");
        list.add("David");
        assertEquals(list,scoreToUserBoard.get(5));
    }
    @Test
    public void testAddExistingUser(){
        scoreBoardManager.addScore("Eric", 5);
        scoreBoardManager.addScore("David", 7);
        assertEquals("[5]",userToScoreBoard.get("Eric").toString());
        assertEquals("[7]",userToScoreBoard.get("David").toString());
    }
    @Test
    public void testMaxUserScore(){
        scoreBoardManager.addScore("Carol", 5);
        scoreBoardManager.addScore("Helen", 6);
        scoreBoardManager.addScore("Carol", 7);
        assertEquals(7,scoreBoardManager.maxUserScore("Carol"));
    }

    @Test
    public void testMaxGameScores(){
        scoreBoardManager.addScore("Carol", 5);
        scoreBoardManager.addScore("Carol", 6);
        scoreBoardManager.addScore("Carol", 7);
        scoreBoardManager.addScore("Helen", 8);
        scoreBoardManager.addScore("Tiffany", 9);
        scoreBoardManager.addScore("Cici", 10);
        List<Pair<String, Integer>> b = new ArrayList<Pair<String, Integer>>();
        b.add(new Pair<>("Cici", 10));
        b.add(new Pair<>("Tiffany", 9));
        assertEquals(b,scoreBoardManager.maxGameScores(2));
    }
}
