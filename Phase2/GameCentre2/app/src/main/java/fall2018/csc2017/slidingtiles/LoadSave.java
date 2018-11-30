package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static android.content.Context.MODE_PRIVATE;

/**
 * Loading/Saving class. An utility class for serialization
 */
class LoadSave {

    private Context context;

    public LoadSave(Context context) {
        this.context = context;
    }
    /**
     * Load the board manager from fileName.
     *
     * @param fileName the name of the file
     */
    public GameManager loadFromFile(String fileName, String username, String gameType) {
        try {
            InputStream inputStream = context.openFileInput(username + gameType + fileName);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                GameManager manager = (GameManager) input.readObject();
                inputStream.close();
                return manager;
            }
        } catch (FileNotFoundException e) {
            Log.e("login1 activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login1 activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("login1 activity", "File contained unexpected data type: " + e.toString());
        }
        return null;
    }

    /**
     * Save the board manager to fileName.
     *
     * @param fileName the name of the file
     */
    public void saveToFile(String fileName, String username, String gameType, GameManager manager) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(context.openFileOutput(username + gameType + fileName, MODE_PRIVATE));
            outputStream.writeObject(manager);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}
