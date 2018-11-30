package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.widget.SeekBar;
import android.widget.Toast;

abstract class ComplexityController {

    LoadSave loadSaveManager;
    Session user;
    String username;
    Context context;
    String saveFile;



    ComplexityController(Context context, String saveFile){
        this.context = context;
        this.user = Session.getCurrentUser();
        this.username = user.getUsername();
        this.saveFile = saveFile;
        this.loadSaveManager = new LoadSave(context);
    }


    /**
     * Choose a game mode between 3x3, 4x4, 5x5.
     */

    void chooseLevel(SeekBar seekBar, final boolean discrete) {
        if (discrete){
            seekBar.incrementProgressBy(1);
            seekBar.setProgress(0);
            seekBar.setMax(2);}
        else{
            seekBar.setMin(0);
            seekBar.setMax(200);
            }
        final int addition = discrete ? 3: 100;
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int item;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                item = progress + addition;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (discrete){
                Toast.makeText(context, item + " X " +
                        item + " Selected", Toast.LENGTH_SHORT).show();}
                else {
                        Toast.makeText(context, item + " bet for each round Selected", Toast.LENGTH_SHORT).show();
                    }
            }
        });
    }
}
