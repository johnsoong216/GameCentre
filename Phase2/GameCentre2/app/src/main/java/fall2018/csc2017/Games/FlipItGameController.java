package fall2018.csc2017.Games;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class FlipItGameController implements Observer {


    /*
    A manager used to load and save the board manager
     */
    private LoadSave loadSaveManager;

    /*
    The Flip game's board Manager
     */
    private FlipManager flipManager;

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


    FlipItGameController(Context context){
        this.context = context;
        this.loadSaveManager = new LoadSave(context);
        this.user = Session.getCurrentUser();
        this.username = user.getUsername();
        this.flipManager = (FlipManager) loadSaveManager.loadFromFile(SlidingTileStartingActivity.TEMP_SAVE_FILE, username, "flip_it");
        flipManager.getFlip().addObserver(this);
    }




    /**
     * Update the backgrounds on the buttons to match the tiles.
     */
    ArrayList<Button> updateTileButtons(ArrayList<Button> tileButtons) {
        this.flipManager = (FlipManager) loadSaveManager.loadFromFile(SlidingTileStartingActivity.TEMP_SAVE_FILE, username, "flip_it");
        FlipIt flip = flipManager.getFlip();
        int nextPos = 0;

        for (Button b : tileButtons) {
            int row = nextPos / flipManager.getFlip().getNUM_ROWS();
            int col = nextPos % flipManager.getFlip().getNUM_COLS();
            b.setBackgroundResource(flip.getTile(row, col).getBackground());
            nextPos++;
        }
        if (flipManager.isGameOver()) {
            user.setScore(flipManager.getScore());
            loadSaveManager.saveToFile(SlidingTileStartingActivity.TEMP_SAVE_FILE, username, "flip_it", null);
            Intent scoreboard = new Intent(context, ScoreActivity.class);
            scoreboard.putExtra("game", "flip_it");
            context.startActivity(scoreboard);}
        return tileButtons;
    }

    /**
     * Create the buttons for displaying the tiles.
     *
     * @param context the context
     */
    void createTileButtons(Context context) {
        FlipIt flip = flipManager.getFlip();
        tileButtons = new ArrayList<>();
        for (int row = 0; row != flipManager.getFlip().getNUM_ROWS(); row++) {
            for (int col = 0; col != flipManager.getFlip().getNUM_COLS(); col++) {
                Button tmp = new Button(context);
                tmp.setBackgroundResource(flip.getTile(row, col).getBackground());
                tileButtons.add(tmp);
            }
        }
    }

    /**
     * Activate the undo button
     * @param undoButton the UndoButton in SlidingTileGameActivity
     */
    void addUndoButtonListener(Button undoButton) {
        undoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flipManager.getMovements().isEmpty()) {
                    flipManager.undo();
                }
            }
        });
    }
    /**
     * Set the undo button based on condition
     * @param undoButton the UndoButton in SlidingTileGameActivity
     */
    void setUndo(Button undoButton){
        if (flipManager.getMovements().isEmpty()){
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
