package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * A controller superclass that is used for starting activity
 */
abstract class LoadSaveButtonController {
    /**
     * User in use
     */
    Session user;
    /**
     * current user's username
     */
    String username;
    /**
     * current context
     */
    Context context;

    /**
     * file for saving game
     */
    String saveFile;

    /**
     * the type of the game
     */
    String gameType;

    /**
     * the game manager being used
     */
    GameManager gameManager;

    /**
     * current loadSaveManager for loading and saving files
     */
    Loadsave loadSaveManager;
    /**
     * Create a LoadSaveButtonController
     * @param context
     */
    LoadSaveButtonController(Context context, String saveFile){
        this.context =context;
        this.user = Session.getCurrentUser();
        this.username = user.getUsername();
        this.saveFile = saveFile;
        this.loadSaveManager = new Loadsave(context);
    }

    /**
     * Set up the load button for the starting page
     * @param loadButton the button to be set
     */
    void addLoadButtonListener(Button loadButton){
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameManager = loadSaveManager.loadFromFile(saveFile, username, gameType);
                Toast.makeText(context, "Loaded Game", Toast.LENGTH_SHORT).show();
                switchToGame();
            }
        });
    }

    /**
     * add a saveButton
     * @param saveButton
     */
    void addSaveButtonListener(Button saveButton){
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadSaveManager.saveToFile(saveFile, username, gameType, gameManager);
                Toast.makeText(context, "Saved Game", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * add a resume button
     * @param resumeButton
     */
    void addResumeButtonListener(Button resumeButton){
        resumeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameManager = loadSaveManager.loadFromFile(saveFile, username, gameType);
                Toast.makeText(context, "Loaded Game", Toast.LENGTH_SHORT).show();
                switchToGame();
            }
        });
    }

    void resume(Button saveButton, Button resumeButton, Button loadButton){
        gameManager = loadSaveManager.loadFromFile(saveFile, username, gameType);
        if (gameManager == null) {
            loadButton.setEnabled(false);
            saveButton.setEnabled(false);
            resumeButton.setEnabled(false);
        } else {
            loadButton.setEnabled(true);
            saveButton.setEnabled(true);
            resumeButton.setEnabled(true);
        }
    }

    abstract void addNewGameButtonListener(Button newGameButton);

    /**
     * Switch to the GameActivity view to play the game.
     */
    abstract void switchToGame();

    /*
    * Switch to the ComplexityActivity to select complexity
     */
    abstract void switchToComplexity();
}
