package fall2018.csc2017.Games;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class SlidingTilesController implements Observer {


    /*
    A manager used to load and save the board manager
     */
    private LoadSave loadSaveManager;

    /*
    The sliding tiles game's board Manager
     */
    private SlidingTileBoardManager boardManager;

    /*
    Returns the current active user
     */
    private Session user;

    /*
    Returns the currently active user's username
     */
    private String username;

    /*
    The context where this controller will be used in
     */
    private Context context;

    public ArrayList<Button> getTileButtons() {
        return tileButtons;
    }

    private ArrayList<Button> tileButtons;


    SlidingTilesController(Context context){
        this.context = context;
        this.loadSaveManager = new LoadSave(context);
        this.user = Session.getCurrentUser();
        this.username = user.getUsername();
        this.boardManager = (SlidingTileBoardManager) loadSaveManager.loadFromFile(SlidingTileStartingActivity.TEMP_SAVE_FILE, username, "sliding_tiles");
        boardManager.getBoard().addObserver(this);
    }




    /**
     * Update the backgrounds on the buttons to match the tiles.
     */
    ArrayList<Button> updateTileButtons(ArrayList<Button> tileButtons) {
        this.boardManager = (SlidingTileBoardManager) loadSaveManager.loadFromFile(SlidingTileStartingActivity.TEMP_SAVE_FILE, username, "sliding_tiles");
        SlidingTileBoard board = boardManager.getBoard();
        int nextPos = 0;

        for (Button b : tileButtons) {
            int row = nextPos / boardManager.getBoard().getNUM_ROWS();
            int col = nextPos % boardManager.getBoard().getNUM_COLS();
            Log.d("TAG", "Which Tile" + board.getTile(row, col).getId());
            b.setBackgroundResource(board.getTile(row, col).getBackground());
            nextPos++;
        }
        if (boardManager.isGameOver()) {
            user.setScore(boardManager.getScore());
            loadSaveManager.saveToFile(SlidingTileStartingActivity.TEMP_SAVE_FILE, username, "sliding_tiles", null);
            Intent scoreboard = new Intent(context, ScoreActivity.class);
            scoreboard.putExtra("game", "sliding_tiles");
            context.startActivity(scoreboard);}
        return tileButtons;
    }

    /**
     * Create the buttons for displaying the tiles.
     *
     * @param context the context
     */
    void createTileButtons(Context context) {
        SlidingTileBoard board = boardManager.getBoard();
        tileButtons = new ArrayList<>();
        for (int row = 0; row != boardManager.getBoard().getNUM_ROWS(); row++) {
            for (int col = 0; col != boardManager.getBoard().getNUM_COLS(); col++) {
                Button tmp = new Button(context);
                tmp.setBackgroundResource(board.getTile(row, col).getBackground());
                tileButtons.add(tmp);
            }
        }
    }

    /**
     * Set the undo button based on condition
     * @param undoButton the UndoButton in SlidingTileGameActivity
     */
    void setUndo(Button undoButton){
        if (boardManager.getMovements().isEmpty()){
            undoButton.setEnabled(false);
        }
        else {
            undoButton.setEnabled(true);
        }
    }

    /*
    Notify the observer that the board has changed
     */
    @Override
    public void update(Observable observable, Object o) {
        tileButtons = updateTileButtons(tileButtons);
    }
}
