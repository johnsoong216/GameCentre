package fall2018.csc2017.slidingtiles;

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
     * The board manager.
     */
    private FlipManager flipManager;

    /**
     * The buttons to display.
     */
    private ArrayList<Button> tileButtons;
    private Button undoButton;

    /**
     * Autosaves the game for every five moves taken.
     */
    private int counter;

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

    private Loadsave loadsaveManager;

    /**
     * Set up the background image for each button based on the master list
     * of positions, and then call the adapter to set the view.
     */
    // Display
    public void display() {
        updateTileButtons();
        gridView.setAdapter(new CustomAdapter(tileButtons, columnWidth, columnHeight));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = Session.getCurrentUser();
        username = user.getUsername();
        context = this;
        loadsaveManager = new Loadsave(context);
        flipManager = (FlipManager) loadsaveManager.loadFromFile(FlipStartingActivity.TEMP_SAVE_FILE, username, "flip_it");
        createTileButtons(this);
        setContentView(R.layout.activity_flip_game);


        // Add View to activity
        gridView = findViewById(R.id.flipGrid);
        gridView.setNumColumns(flipManager.getFlip().getNUM_COLS());
        gridView.setGameManager(flipManager);
        flipManager.getFlip().addObserver(this);
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
        undoButton = findViewById(R.id.btUndo);
        undoButton.setEnabled(false);
        addUndoButtonListener();
        scoreStepTimer = findViewById(R.id.ScoreBoard);
        currentScore = findViewById(R.id.currentScore);
        runTimer();
    }

    /**
     * A timer that begins at the start of the game.
     */
    private void runTimer() {
        Thread t = new Thread() {
            @Override
            public void run() {
                while (!flipManager.puzzleSolved()) {
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
     * Create the buttons for displaying the tiles.
     *
     * @param context the context
     */
    private void createTileButtons(Context context) {
        String j = getIntent().getStringExtra("game");
        FlipIt flip = flipManager.getFlip();
        tileButtons = new ArrayList<>();
        for (int row = 0; row != flipManager.getFlip().getNUM_ROWS(); row++) {
            for (int col = 0; col != flipManager.getFlip().getNUM_COLS(); col++) {
                Button tmp = new Button(context);
                tmp.setBackgroundResource(flip.getTile(row, col).getBackground());
                this.tileButtons.add(tmp);
            }
        }
    }

    /**
     * Update the backgrounds on the buttons to match the tiles.
     */
    private void updateTileButtons() {
        FlipIt flip = flipManager.getFlip();
        int nextPos = 0;

        for (Button b : tileButtons) {
            int row = nextPos / flipManager.getFlip().getNUM_ROWS();
            int col = nextPos % flipManager.getFlip().getNUM_COLS();
            b.setBackgroundResource(flip.getTile(row, col).getBackground());
            nextPos++;
        }
        if (flipManager.puzzleSolved()) {
            user.setScore(flipManager.getScore());
            Intent toScore = new Intent(FlipGameActivity.this, ScoreActivity.class);
            toScore.putExtra("game", "flip_it");
            FlipGameActivity.this.startActivity(toScore);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); }
            loadsaveManager.saveToFile(FlipStartingActivity.TEMP_SAVE_FILE, username, "flip_it", flipManager);

    }

    /**
     * Autosaves the game for every five steps a user makes.
     *
     * @return true if the five steps have been taken.
     */
    public boolean autosave() {
        counter++;
        return (counter % 5 == 0);
    }


    /**
     * Activate the undo button
     */
    private void addUndoButtonListener() {
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
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        super.onPause();
        loadsaveManager.saveToFile(FlipStartingActivity.TEMP_SAVE_FILE, username, "flip_it", flipManager);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (!flipManager.getMovements().isEmpty()) {
            undoButton.setEnabled(true);
        } else undoButton.setEnabled(false);
        display();
    }

    @Override
    public void onBackPressed() {
        Intent backToMain = new Intent(FlipGameActivity.this, FlipStartingActivity.class);
        FlipGameActivity.this.startActivity(backToMain);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

    }
}
