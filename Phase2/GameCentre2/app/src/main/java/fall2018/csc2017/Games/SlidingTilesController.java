package fall2018.csc2017.Games;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class SlidingTilesController {

    private LoadSave loadSaveManager;
    private SlidingTileBoardManager boardManager;
    private Session user;
    private String username;
    private Context context;


    SlidingTilesController(Context context){
        this.context = context;
        this.loadSaveManager = new LoadSave(context);
        this.user = Session.getCurrentUser();
        this.username = user.getUsername();
        this.boardManager = (SlidingTileBoardManager) loadSaveManager.loadFromFile(SlidingTileStartingActivity.TEMP_SAVE_FILE, username, "sliding_tiles");

    }


    /**
     * Update the backgrounds on the buttons to match the tiles.
     */
    void updateTileButtons(ArrayList<Button> tileButtons) {
        SlidingTileBoard board = boardManager.getBoard();
        int nextPos = 0;

        for (Button b : tileButtons) {
            int row = nextPos / boardManager.getBoard().getNUM_ROWS();
            int col = nextPos % boardManager.getBoard().getNUM_COLS();
            b.setBackgroundResource(board.getTile(row, col).getBackground());
            nextPos++;
        }
        if (boardManager.isGameOver()) {
            user.setScore(boardManager.getScore());
            loadSaveManager.saveToFile(SlidingTileStartingActivity.TEMP_SAVE_FILE, username, "sliding_tiles", null);
            Intent scoreboard = new Intent(context, ScoreActivity.class);
            scoreboard.putExtra("game", "sliding_tiles");
            context.startActivity(scoreboard);}
        loadSaveManager.saveToFile(SlidingTileStartingActivity.TEMP_SAVE_FILE, username, "sliding_tiles", boardManager);
    }



    /**
     * Activate the undo button
     */
    void addUndoButtonListener(Button undoButton) {
        undoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!boardManager.getMovements().isEmpty()) {
                    boardManager.undo();
                }
            }
        });
    }

    void setScoreAndTimer(int timer, int stepcounter){
        timer++;
        stepcounter++;
        boardManager.setTimer(timer);
        boardManager.setStepCounter(stepcounter);
    }


}
