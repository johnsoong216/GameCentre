package fall2018.csc2017.Games;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * The game activity for sliding tile game
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
    private int timer;
    private int stepcounter;

    /**
     * Set up the background image for each button based on the master list
     * of positions, and then call the adapter to set the view.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = Session.getCurrentUser();
        username = user.getUsername();
        context = this;
        loadSaveManager = new LoadSave(context);
        boardManager = (SlidingTileBoardManager) loadSaveManager.loadFromFile(SlidingTileStartingActivity.TEMP_SAVE_FILE, username, "sliding_tiles");
        setContentView(R.layout.activity_board_main);
        controller = new SlidingTilesController(context);

        scoreStepTimer = findViewById(R.id.ScoreBoard);
        currentScore = findViewById(R.id.currentScore);
        undoButton = findViewById(R.id.btUndo);
        gridView = findViewById(R.id.grid);


        controller.setUndo(undoButton);
        controller.createTileButtons(context);

        boardManager.getBoard().addObserver(this);
        setGridView();
        runTimer();
        addUndoButtonListener();
    }
    /*
    * Set the Grid View by creating tile Buttons
     */
    private void setGridView() {
        gridView.setNumColumns(boardManager.getBoard().getNUM_COLS());
        gridView.setGameManager(boardManager);
        tileButtons = controller.getTileButtons();

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
                                timer = boardManager.getTimer();
                                timer++;
                                boardManager.setTimer(timer);
                                stepcounter = boardManager.getStepCounter();
                                boardManager.setStepCounter(stepcounter);
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
    @Override
    protected void onResume(){
        super.onResume();
        controller.setUndo(undoButton);
//        controller.addUndoButtonListener(undoButton);
    }

    /*
    *undo Control
     */
    void addUndoButtonListener() {
        undoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boardManager.undo();}
            });
    }

    /*
    * Display the current View
     */
    public void display() {
        tileButtons = controller.getTileButtons();
        gridView.setAdapter(new CustomAdapter(tileButtons, columnWidth, columnHeight));
        controller.setUndo(undoButton);
//        controller.addUndoButtonListener(undoButton);
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
    public void onBackPressed() {
        Intent backToMain = new Intent(SlidingTileGameActivity.this, SlidingTileStartingActivity.class);
        SlidingTileGameActivity.this.startActivity(backToMain);
    }

    @Override
    public void update(Observable o, Object arg) {
        loadSaveManager.saveToFile(SlidingTileStartingActivity.TEMP_SAVE_FILE, username, "sliding_tiles", boardManager);
        tileButtons = controller.updateTileButtons(tileButtons);
        display();
    }
}
