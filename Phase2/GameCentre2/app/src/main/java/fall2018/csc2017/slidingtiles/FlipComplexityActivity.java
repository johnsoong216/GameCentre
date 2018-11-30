package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Toast;

public class FlipComplexityActivity extends AppCompatActivity {

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
    private FlipManager flipManager = new FlipManager(5, 3);

    /**
     * The context of this activity.
     */
    private Context context;

    /**
     * The username of the user.
     */
    private String username;

    private LoadSave loadSaveManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flip_complexity);
        context = this;
        loadSaveManager = new LoadSave(context);
        username = Session.getCurrentUser().getUsername();
        numbersBtn = this.findViewById(R.id.NumbersBtn);
        numUndo = this.findViewById(R.id.numUndo);
        numUndo.setTransformationMethod(null);
        chooseGameLevel();
        addStartNumberTiles();

    }

    /**
     * Allows user to specify number of steps they wish to undo.
     * @return number of undo steps.
     */
    private int chooseUndo() {
        return numUndo.getText().toString().equals("") ? 2 : Integer.parseInt(numUndo.getText().toString());
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
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(FlipComplexityActivity.this, difficulty + " X " +
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
                flipManager.setUndo(chooseUndo());
                loadSaveManager.saveToFile(FlipStartingActivity.TEMP_SAVE_FILE, username, "flip_it", flipManager);
                Intent startGame = new Intent(FlipComplexityActivity.this, LevelComplexityActivity.class);
                startGame.putExtra("difficulty", difficulty);
                startGame.putExtra("undo", chooseUndo());
                startActivity(startGame);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }

        });

    }
}
