package fall2018.csc2017.slidingtiles;

import java.util.HashMap;
import java.util.List;

public class ScordBoardManagerTest {


    ScoreBoardManager scoreBoardManager = new ScoreBoardManager();
    HashMap<String, List<Integer>> scoreToUserBoard = scoreBoardManager.scoreToUserBoard;
    HashMap<String, List<Integer>> userToScoreBoard = scoreBoardManager.userToScoreBoard;

    @test
     public void testAddNewScore(){
         scoreBoardManager.addScore("Carol", 5);
         scoreBoardManager.addScore("Helen", 6);
         assertEquals({"Carol"},scoreToUserBoard[5]);
         assertEquals({"Helen"},scoreToUserBoard[6]);
         assertEquals({5}, userToScoreBoard["Carol"]);
         assertEquals({6}, userToScoreBoard["Helen"]);

     }
    public void testAddExistingScore() {
        scoreBoardManager.addScore("Eric", 5);
        scoreBoardManager.addScore("David", 5);
        assertEquals({"Carol","Eric","David"},scoreToUserBoard[5]);



    }

    @Test
    public void testMaxUserScore(){
        scoreBoardManager.addScore("Carol", 5);
        scoreBoardManager.addScore("Carol", 6);
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
        List<Pair<String, Integer>> b = new List<Pair<String, Integer>>();
        b.add(new pair("Cici",10));
        b.add(new pair("Tiffany", 9));
        b.add(new pair("Helen",8));
        b.add(new pair("Carol", 7));
        assertEquals(b,scoreBoardManager.maxGameScores(4));

    }
}

