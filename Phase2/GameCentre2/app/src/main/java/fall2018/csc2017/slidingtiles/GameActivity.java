package fall2018.csc2017.slidingtiles;

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
public class GameActivity extends AppCompatActivity implements Observer {

    /**
     * The board manager.
     */
    private BoardManager boardManager;

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
    private GestureDetectGridView gridView;
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
        boardManager = (BoardManager) loadsaveManager.loadFromFile(StartingActivity.TEMP_SAVE_FILE, username, "sliding_tiles");
        createTileButtons(this);
        setContentView(R.layout.activity_main);


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
                while (!boardManager.puzzleSolved()) {
                    try {
                        Thread.sleep(1000);

                        runOnUiThread(new Runnable() {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void run() {
                                timer = boardManager.getTimer();
                                timer++;
                                boardManager.setTimer(timer);
                                stepcounter = boardManager.getStepcounter();
                                boardManager.setStepcounter(stepcounter);
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

        Board board = boardManager.getBoard();
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
        Board board = boardManager.getBoard();
        int nextPos = 0;

        for (Button b : tileButtons) {
            int row = nextPos / boardManager.getBoard().getNUM_ROWS();
            int col = nextPos % boardManager.getBoard().getNUM_COLS();
            b.setBackgroundResource(board.getTile(row, col).getBackground());
            nextPos++;
        }
        if (boardManager.puzzleSolved()) {
            user.setScore(boardManager.getScore());
            loadsaveManager.saveToFile(StartingActivity.TEMP_SAVE_FILE, username, "sliding_tiles", null);
            Intent scoreboard = new Intent(GameActivity.this, ScoreActivity.class);
            scoreboard.putExtra("game", "sliding_tiles");
            GameActivity.this.startActivity(scoreboard);
        } else if (autosave()) {
            loadsaveManager.saveToFile(StartingActivity.TEMP_SAVE_FILE, username, "sliding_tiles", boardManager);
        }
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
        loadsaveManager.saveToFile(StartingActivity.TEMP_SAVE_FILE, username, "sliding_tiles", boardManager);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (!boardManager.getMovements().isEmpty()) {
            undoButton.setEnabled(true);
        } else undoButton.setEnabled(false);
        display();
    }

    @Override
    public void onBackPressed() {
        Intent backtomain = new Intent(GameActivity.this, StartingActivity.class);
        GameActivity.this.startActivity(backtomain);
    }
}
