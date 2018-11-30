//excluded from tests because it's a (model / view) class
package fall2018.csc2017.Games;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * The game activity.
 */
public class SlidingTileGameActivity extends AppCompatActivity implements Observer {

    /**
     * The board manager.
     */
    private SlidingTileBoardManager boardManager;

    /**
     * The buttons to display.
     */
    private ArrayList<Button> tileButtons;
    private Button undoButton;


    // Grid View and calculated column height and width based on device size
    private BoardGestureDetectGridView gridView;
    private static int columnWidth, columnHeight;

    /**
     * Textview that displays the current score and timer in the game.
     */
    private TextView scoreStepTimer;
    private TextView currentScore;

    /**
     * Timer of the game.
     */
    private int timer;

    /**
     * Step counter for the number of moves taken.
     */
    private int stepcounter;

    /**
     * A session object that holds user information.
     */
    private Session user;

    /**
     * The user's username.
     */
    private String username;

    /**
     * The context of this activity.
     */
    private Context context = this;

    private LoadSave loadSaveManager;

    private SlidingTilesController controller;

    /**
     * Set up the background image for each button based on the master list
     * of positions, and then call the adapter to set the view.
     */
    // Display
    public void display() {
        controller.updateTileButtons(tileButtons);
        gridView.setAdapter(new CustomAdapter(tileButtons, columnWidth, columnHeight));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = Session.getCurrentUser();
        username = user.getUsername();
        context = this;
        loadSaveManager = new LoadSave(context);
        boardManager = (SlidingTileBoardManager) loadSaveManager.loadFromFile(SlidingTileStartingActivity.TEMP_SAVE_FILE, username, "sliding_tiles");
        setContentView(R.layout.activity_board_main);

        scoreStepTimer = findViewById(R.id.ScoreBoard);
        currentScore = findViewById(R.id.currentScore);

        controller = new SlidingTilesController(context);
        createTileButtons(this);

        // Add View to activity
        gridView = findViewById(R.id.grid);
        gridView.setNumColumns(boardManager.getBoard().getNUM_COLS());
        gridView.setGameManager(boardManager);
        boardManager.getBoard().addObserver(this);
        // Observer sets up desired dimensions as well as calls our display function
        gridView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        gridView.getViewTreeObserver().removeOnGlobalLayoutListener(
                                this);
                        int displayWidth = gridView.getMeasuredWidth();
                        int displayHeight = gridView.getMeasuredHeight();

                        columnWidth = displayWidth / boardManager.getBoard().getNUM_COLS();
                        columnHeight = displayHeight / boardManager.getBoard().getNUM_ROWS();

                        display();


                    }
                });
        undoButton = findViewById(R.id.btUndo);
        controller.addUndoButtonListener(undoButton);
        runTimer();
    }

    /**
     * A timer that begins at the start of the game.
     */
    private void runTimer() {
        Thread t = new Thread() {
            @Override
            public void run() {
                while (!boardManager.isGameOver()) {
                    try {
                        Thread.sleep(1000);

                        runOnUiThread(new Runnable() {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void run() {
                                controller.setScoreAndTimer(boardManager.getTimer(), boardManager.getStepCounter());
                                scoreStepTimer.setText("Timer: " + String.valueOf(timer) + "s" + "  " + "Steps: " + stepcounter);
                                currentScore.setText("Current Score: " + String.valueOf(boardManager.getScore()));
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();
    }

    /**
     * Create the buttons for displaying the tiles.
     *
     * @param context the context
     */
    private void createTileButtons(Context context) {

        SlidingTileBoard board = boardManager.getBoard();
        tileButtons = new ArrayList<>();
        for (int row = 0; row != boardManager.getBoard().getNUM_ROWS(); row++) {
            for (int col = 0; col != boardManager.getBoard().getNUM_COLS(); col++) {
                Button tmp = new Button(context);
                tmp.setBackgroundResource(board.getTile(row, col).getBackground());
                this.tileButtons.add(tmp);
            }
        }
    }

    /**
     * Update the backgrounds on the buttons to match the tiles.
     */
    private void updateTileButtons() {
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
            Intent scoreboard = new Intent(SlidingTileGameActivity.this, ScoreActivity.class);
            scoreboard.putExtra("game", "sliding_tiles");
            SlidingTileGameActivity.this.startActivity(scoreboard);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); }
            loadSaveManager.saveToFile(SlidingTileStartingActivity.TEMP_SAVE_FILE, username, "sliding_tiles", boardManager);
    }


    /**
     * Activate the undo button
     */
    private void addUndoButtonListener() {
        undoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!boardManager.getMovements().isEmpty()) {
                    boardManager.undo();
                }
            }
        });
    }

    /**
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        super.onPause();
        loadSaveManager.saveToFile(SlidingTileStartingActivity.TEMP_SAVE_FILE, username, "sliding_tiles", boardManager);
    }

    @Override
    public void update(Observable o, Object arg) {
        display();
    }

    @Override
    public void onBackPressed() {
        Intent backToMain = new Intent(SlidingTileGameActivity.this, SlidingTileStartingActivity.class);
        SlidingTileGameActivity.this.startActivity(backToMain);
    }
}
