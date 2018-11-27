package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * The complexity activity.
 */
public class ComplexityActivity extends AppCompatActivity {

    /**
     * The complexity level of the game.
     */
    private int difficulty;

    /**
     * Buttons and EditText for user interaction.
     */
    private ImageButton numbersBtn;
    private EditText numUndo;

    /**
     * A BoardManager object.
     */
    private BoardManager boardManager = new BoardManager(4);
    private FlipManager flipManager = new FlipManager(4);

    /**
     * The context of this activity.
     */
    private Context context;

    /**
     * The username of the user.
     */
    private String username;

    private Loadsave loadsaveManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complexity);
        context = this;
        loadsaveManager = new Loadsave(context);
        username = Session.getCurrentUser().getUsername();
        numbersBtn = this.findViewById(R.id.NumbersBtn);
        numUndo = this.findViewById(R.id.numUndo);
        numUndo.setTransformationMethod(null);
        chooseGameLevel();
        addStartNumberTiles();
        final TextView imageLink = findViewById(R.id.tvSlideImage);
        imageLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imageIntent = new Intent(ComplexityActivity.this, ImageTilesActivity.class);
                imageIntent.putExtra("numUndo", chooseUndo());
                ComplexityActivity.this.startActivity(imageIntent);
            }
        });
    }

    /**
     * Allows user to specify number of steps they wish to undo.
     * @return number of undo steps.
     */
    private int chooseUndo() {
        return numUndo.getText().toString().equals("") ? 3 : Integer.parseInt(numUndo.getText().toString());
    }

    /**
     * Choose a game mode between 3x3, 4x4, 5x5.
     */
    private void chooseGameLevel() {
        SeekBar seekBar = findViewById(R.id.seekBar);

        seekBar.setProgress(0);
        seekBar.incrementProgressBy(1);
        seekBar.setMax(2);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                difficulty = progress + 3;
                boardManager = new BoardManager(difficulty);
                boardManager.getBoard().setNUM_ROWS(difficulty);
                boardManager.getBoard().setNUM_COLS(difficulty);
                }


            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(ComplexityActivity.this, difficulty + " X " +
                        difficulty + " Selected", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Start a new tile game.
     */
    private void addStartNumberTiles() {

        numbersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boardManager.setUndo(chooseUndo());
                loadsaveManager.saveToFile(StartingActivity.TEMP_SAVE_FILE, username, "sliding_tiles", boardManager);
                Intent startGame = new Intent(ComplexityActivity.this, GameActivity.class);
                startActivity(startGame);
                }

        });

    }
}
