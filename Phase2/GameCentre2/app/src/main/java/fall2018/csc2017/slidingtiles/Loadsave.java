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
 * Loading/Saving class.
 */
class Loadsave {

    private Context context;

    public Loadsave(Context context) {
        this.context = context;
    }
    /**
     * Load the board manager from fileName.
     *
     * @param fileName the name of the file
     */
    public BoardManager loadFromFile(String fileName, String username) {

        try {
            InputStream inputStream = context.openFileInput(username + fileName);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                BoardManager manager = (BoardManager) input.readObject();
                inputStream.close();
                return manager;
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
        }
        return null;
    }

    /**
     * Save the board manager to fileName.
     *
     * @param fileName the name of the file
     */
    public void saveToFile(String fileName, String username, BoardManager manager) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(context.openFileOutput(username + fileName, MODE_PRIVATE));
            outputStream.writeObject(manager);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}
