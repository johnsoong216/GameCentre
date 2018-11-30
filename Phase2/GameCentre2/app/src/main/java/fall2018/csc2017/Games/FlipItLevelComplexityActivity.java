package fall2018.csc2017.Games;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Toast;
/*
A complexity class that lets the user to choose the game level within each complexity.
 */
public class FlipItLevelComplexityActivity extends AppCompatActivity {

    private FlipManager flipManager = new FlipManager(5, 3);
    private ImageButton levelNumbersBtn;
    private int level;
    private Context context;
    private String username;
    private LoadSave loadSaveManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_complexity);
        context = this;
        loadSaveManager = new LoadSave(context);
        username = Session.getCurrentUser().getUsername();
        levelNumbersBtn = this.findViewById(R.id.levelNumbersBtn);
        chooseGameLevel();
        addStartNumberTiles();
    }

    private void chooseGameLevel() {
        SeekBar levelSeekBar = findViewById(R.id.levelSeekBar);
        levelSeekBar.setProgress(0);
        levelSeekBar.incrementProgressBy(1);
        levelSeekBar.setMax(2);
        levelSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar levelSeekBar, int levelProgress, boolean levelFromUser) {
                level = levelProgress + 1;
                int difficulty = getIntent().getIntExtra("difficulty", 0);
                flipManager = new FlipManager(difficulty, level);
                flipManager.getFlip().setNUM_ROWS(difficulty);
                flipManager.getFlip().setNUM_COLS(difficulty);
            }

            @Override
            public void onStartTrackingTouch(SeekBar levelSeekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar levelSeekBar) {
                Toast.makeText(FlipItLevelComplexityActivity.this, "level" + level + " Selected", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addStartNumberTiles() {
        levelNumbersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int undo = getIntent().getIntExtra("undo", 0);
                flipManager.setUndo(undo);
                loadSaveManager.saveToFile(SlidingTileStartingActivity.TEMP_SAVE_FILE, username, "flip_it", flipManager);
                Intent startGame = new Intent(FlipItLevelComplexityActivity.this, FlipGameActivity.class);
                startActivity(startGame);
            }
        });
    }
}

