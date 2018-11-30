//excluded from tests because it's a (model / view) class
package fall2018.csc2017.Games;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * this the image tile activity that display and allow user to choose different image
 */
public class SlidingTileImageActivity extends AppCompatActivity {

    /**
     * A SlidingTileBoardManager object.
     */
    private SlidingTileBoardManager boardManager;

    /**
     * Number of undo a user wants to make.
     */
    private int numUndo;

    /**
     * The context of this activity.
     */
    private Context context;

    /**
     * The user's username.
     */
    private String username;

    private LoadSave loadsaveManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_tiles);
        context = this;
        loadsaveManager = new LoadSave(context);
        Intent imageintent = getIntent();
        numUndo = imageintent.getIntExtra("numUndo", 3);
        username = Session.getCurrentUser().getUsername();
        ferrisWheelImage();
        dogImage();
    }

    /**
     * Starts a ferrisWheel image sliding tiles game.
     */
    private void ferrisWheelImage() {
        ImageView ferris = findViewById(R.id.ITfw);
        ferris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boardManager = new SlidingTileBoardManager(4, "fw");
                boardManager.setUndo(numUndo);
                loadsaveManager.saveToFile(SlidingTileStartingActivity.TEMP_SAVE_FILE, username, "sliding_tiles", boardManager);
                Intent startgame = new Intent(SlidingTileImageActivity.this, SlidingTileGameActivity.class);
                startActivity(startgame);
            }
        });
    }

    /**
     * Starts a dog image sliding tiles game.
     */
    private void dogImage() {
        ImageView dog = findViewById(R.id.ITdog);
        dog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boardManager = new SlidingTileBoardManager(3, "dog");
                boardManager.setUndo(numUndo);
                loadsaveManager.saveToFile(SlidingTileStartingActivity.TEMP_SAVE_FILE, username, "sliding_tiles", boardManager);
                Intent startgame = new Intent(SlidingTileImageActivity.this, SlidingTileGameActivity.class);
                startActivity(startgame);
            }
        });
    }
}
