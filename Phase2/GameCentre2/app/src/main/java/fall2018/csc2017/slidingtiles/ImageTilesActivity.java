package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * this the image tile activity
 */
public class ImageTilesActivity extends AppCompatActivity {

    /**
     * A BoardManager object.
     */
    private BoardManager boardManager;

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

    private Loadsave loadsaveManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_tiles);
        context = this;
        loadsaveManager = new Loadsave(context);
        Intent imageintent = getIntent();
        numUndo = imageintent.getIntExtra("numUndo", 3);
        username = Session.getCurrentUser().getUsername();
        ferrisWheelimage();
        dogimage();
    }

    /**
     * Starts a ferrisWheel image sliding tiles game.
     */
    private void ferrisWheelimage() {
        ImageView ferris = findViewById(R.id.ITfw);
        ferris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boardManager = new BoardManager(4, "fw");
                boardManager.setUndo(numUndo);
                loadsaveManager.saveToFile(StartingActivity.TEMP_SAVE_FILE, username, boardManager);
                Intent startgame = new Intent(ImageTilesActivity.this, GameActivity.class);
                startActivity(startgame);
            }
        });
    }

    /**
     * Starts a dog image sliding tiles game.
     */
    private void dogimage() {
        ImageView dog = findViewById(R.id.ITdog);
        dog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boardManager = new BoardManager(3, "dog");
                boardManager.setUndo(numUndo);
                loadsaveManager.saveToFile(StartingActivity.TEMP_SAVE_FILE, username, boardManager);
                Intent startgame = new Intent(ImageTilesActivity.this, GameActivity.class);
                startActivity(startgame);
            }
        });
    }
}
