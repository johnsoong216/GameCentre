//excluded from tests because it's a (model / view) class
package fall2018.csc2017.Games;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class FlipGameActivity extends AppCompatActivity implements Observer {

    /**
     * The Flip manager.
     */
    private FlipManager flipManager;

    /**
     * The buttons to display.
     */
    private ArrayList<Button> tileButtons;
    private Button undoButton;


    // Grid View and calculated column height and width based on device size
    private FlipGestureDetectGridView gridView;

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


    /*
    *The manager that loads and saves the file
     */
    private LoadSave loadSaveManager;

    /*
    The controller that operates the Activity
    **/
    private FlipItGameController controller;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = Session.getCurrentUser();
        username = user.getUsername();
        context = this;
        loadSaveManager = new LoadSave(context);
        flipManager = (FlipManager) loadSaveManager.loadFromFile(SlidingTileStartingActivity.TEMP_SAVE_FILE, username, "flip_it");
        setContentView(R.layout.activity_board_main);
        controller = new FlipItGameController(context);
        scoreStepTimer = findViewById(R.id.ScoreBoard);
        currentScore = findViewById(R.id.currentScore);
        undoButton = findViewById(R.id.btUndo);
        controller.createTileButtons(context);
        controller.addUndoButtonListener(undoButton);
        controller.setUndo(undoButton);
        gridView = findViewById(R.id.grid);
        flipManager.getFlip().addObserver(this);
        setGridView();
        runTimer();
    }

    private void setGridView() {
        // Add View to activity
        gridView.setNumColumns(flipManager.getFlip().getNUM_COLS());
        gridView.setGameManager(flipManager);
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

                        columnWidth = displayWidth / flipManager.getFlip().getNUM_COLS();
                        columnHeight = displayHeight / flipManager.getFlip().getNUM_ROWS();

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
                while (!flipManager.isGameOver()) {
                    try {
                        Thread.sleep(1000);

                        runOnUiThread(new Runnable() {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void run() {
                                timer = flipManager.getTimer();
                                timer++;
                                flipManager.setTimer(timer);
                                stepcounter = flipManager.getStepCounter();
                                flipManager.setStepCounter(stepcounter);
                                scoreStepTimer.setText("Timer: " + String.valueOf(timer) + "s" + "  " + "Steps: " + stepcounter);
                                currentScore.setText("Current Score: " + String.valueOf(flipManager.getScore()));
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
     * Set up the background image for each button based on the master list
     * of positions, and then call the adapter to set the view.
     */
    public void display(){
        tileButtons = controller.getTileButtons();
        controller.setUndo(undoButton);
        gridView.setAdapter(new CustomAdapter(tileButtons, columnWidth, columnHeight));}
    /**
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        super.onPause();
        loadSaveManager.saveToFile(SlidingTileStartingActivity.TEMP_SAVE_FILE, username, "flip_it", flipManager);
    }

    /*
    Override the android's back button
     */
    @Override
    public void onBackPressed() {
        Intent backToMain = new Intent(FlipGameActivity.this, FlipStartingActivity.class);
        startActivity(backToMain);
    }
    /*
    Override the update Observer memthod
     */
    @Override
    public void update(Observable o, Object arg) {
        loadSaveManager.saveToFile(SlidingTileStartingActivity.TEMP_SAVE_FILE, username, "sliding_tiles", flipManager);
        tileButtons = controller.updateTileButtons(tileButtons);
        display();
    }
}