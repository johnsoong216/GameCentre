package fall2018.csc2017.slidingtiles;

import java.util.HashMap;
import java.util.List;

public class ScordBoardManagerTest {


    ScoreBoardManager scoreBoardManager = new ScoreBoardManager();
    scoreToUserBoard = scoreBoardManager.scoreToUserBoard;
    userToScoreBoard = scoreBoardManager.userToScoreBoard;

    @test
     public void testAddNewScore(){
         scoreBoardManager.addScore("Carol", 5);
         scoreBoardManager.addScore("Helen", 6);
         assertEqual({"Carol"},scoreToUserBoard[5]);
         assertEqual({"Helen"},scoreToUserBoard[6]);
         assertEqual({5}, userToScoreBoard["Carol"]);
         assertEqual({6}, userToScoreBoard["Helen"]);

     }
    public void testAddExistingScore() {
        scoreBoardManager.addScore("Eric", 5);
        scoreBoardManager.addScore("David", 5);
        assertEqual({"Carol","Eric","David"},scoreToUserBoard[5]);



    }
}

