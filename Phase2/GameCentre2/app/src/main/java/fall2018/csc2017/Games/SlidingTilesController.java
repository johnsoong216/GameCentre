package fall2018.csc2017.Games;

import android.content.Context;
import android.content.Intent;
import android.widget.Button;

import java.util.ArrayList;

public class SlidingTilesController {

    private LoadSave loadSaveManager;
    private BoardManager boardManager;
    private Session user;


    SlidingTilesController(Context context){
        this.loadSaveManager = new LoadSave(context);
        this.user = Session.getCurrentUser();
        this.boardManager = loadSaveManager.loadFromFile(Startinguser.getUsername())

    }


    /**
     * Update the backgrounds on the buttons to match the tiles.
     */
    private void updateTileButtons(ArrayList<Button> tileButtons) {
        Board board = boardManager.getBoard();
        int nextPos = 0;

        for (Button b : tileButtons) {
            int row = nextPos / boardManager.getBoard().getNUM_ROWS();
            int col = nextPos % boardManager.getBoard().getNUM_COLS();
            b.setBackgroundResource(board.getTile(row, col).getBackground());
            nextPos++;
        }
        if (boardManager.isGameOver()) {
            user.setScore(boardManager.getScore());
            loadSaveManager.saveToFile(StartingActivity.TEMP_SAVE_FILE, username, "sliding_tiles", null);
            Intent scoreboard = new Intent(GameActivity.this, ScoreActivity.class);
            scoreboard.putExtra("game", "sliding_tiles");
            GameActivity.this.startActivity(scoreboard);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); }
        loadSaveManager.saveToFile(StartingActivity.TEMP_SAVE_FILE, username, "sliding_tiles", boardManager);
    }
}
