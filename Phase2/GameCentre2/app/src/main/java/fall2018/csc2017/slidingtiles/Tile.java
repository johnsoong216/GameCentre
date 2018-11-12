package fall2018.csc2017.slidingtiles;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * A Tile in a sliding tiles puzzle.
 */
public class Tile implements Comparable<Tile>, Serializable {

    /**
     * The background id to find the tile image.
     */
    private int background;

    /**
     * The unique id.
     */
    private int id;

    /**
     * Return the background id.
     *
     * @return the background id
     */
    public int getBackground() {
        return background;
    }


    /**
     * Set the Tile's background.
     * @param background this Tile's background.
     */
    public void setBackground(int background) {
        this.background = background;
    }

    /**
     * Return the tile id.
     *
     * @return the tile id
     */
    public int getId() {
        return id;
    }

    /**
     * A Tile with id and background. The background may not have a corresponding image.
     *
     * @param id         the id
     * @param background the background
     */
    Tile(int id, int background) {
        this.id = id;
        this.background = background;
    }

    /**
     * A tile with a background id; look up and set the id.
     *
     * @param backgroundId the backgroundId of the Tile.
     */
    Tile(int backgroundId) {
        id = backgroundId + 1;


        switch (backgroundId + 1) {
            case 1:
                background = R.drawable.tile_1;
                break;
            case 2:
                background = R.drawable.tile_2;
                break;
            case 3:
                background = R.drawable.tile_3;
                break;
            case 4:
                background = R.drawable.tile_4;
                break;
            case 5:
                background = R.drawable.tile_5;
                break;
            case 6:
                background = R.drawable.tile_6;
                break;
            case 7:
                background = R.drawable.tile_7;
                break;
            case 8:
                background = R.drawable.tile_8;
                break;
            case 9:
                background = R.drawable.tile_9;
                break;
            case 10:
                background = R.drawable.tile_10;
                break;
            case 11:
                background = R.drawable.tile_11;
                break;
            case 12:
                background = R.drawable.tile_12;
                break;
            case 13:
                background = R.drawable.tile_13;
                break;
            case 14:
                background = R.drawable.tile_14;
                break;
            case 15:
                background = R.drawable.tile_15;
                break;
            case 16:
                background = R.drawable.tile_16;
                break;
            case 17:
                background = R.drawable.tile_17;
                break;
            case 18:
                background = R.drawable.tile_18;
                break;
            case 19:
                background = R.drawable.tile_19;
                break;
            case 20:
                background = R.drawable.tile_20;
                break;
            case 21:
                background = R.drawable.tile_21;
                break;
            case 22:
                background = R.drawable.tile_22;
                break;
            case 23:
                background = R.drawable.tile_23;
                break;
            case 24:
                background = R.drawable.tile_24;
                break;
            case 25:
                background = R.drawable.tile_25;
                break;
            default:
                background = R.drawable.tile_25;
        }
    }

    /**
     * A tile with a background id; look up and set the id.
     *
     * @param backgroundId the backgroundId of the Tile.
     * @param image the image of the tiles being arranged.
     */
    Tile(int backgroundId, String image) {
        id = backgroundId + 1;
        if (image.equals("fw")) {
            switch (backgroundId + 1) {
                case 1:
                    background = R.drawable.f1;
                    break;
                case 2:
                    background = R.drawable.f2;
                    break;
                case 3:
                    background = R.drawable.f3;
                    break;
                case 4:
                    background = R.drawable.f4;
                    break;
                case 5:
                    background = R.drawable.f5;
                    break;
                case 6:
                    background = R.drawable.f6;
                    break;
                case 7:
                    background = R.drawable.f7;
                    break;
                case 8:
                    background = R.drawable.f8;
                    break;
                case 9:
                    background = R.drawable.f9;
                    break;
                case 10:
                    background = R.drawable.f10;
                    break;
                case 11:
                    background = R.drawable.f11;
                    break;
                case 12:
                    background = R.drawable.f12;
                    break;
                case 13:
                    background = R.drawable.f13;
                    break;
                case 14:
                    background = R.drawable.f14;
                    break;
                case 15:
                    background = R.drawable.f15;
                    break;
                case 16:
                    background = R.drawable.f16;
                    break;
            }
        }
        else if (image.equals("dog")) {
            switch (backgroundId + 1) {
                case 1:
                    background = R.drawable.d1;
                    break;
                case 2:
                    background = R.drawable.d2;
                    break;
                case 3:
                    background = R.drawable.d3;
                    break;
                case 4:
                    background = R.drawable.d4;
                    break;
                case 5:
                    background = R.drawable.d5;
                    break;
                case 6:
                    background = R.drawable.d6;
                    break;
                case 7:
                    background = R.drawable.d7;
                    break;
                case 8:
                    background = R.drawable.d8;
                    break;
                case 9:
                    background = R.drawable.d9;
                    break;
            }
        }
    }

    @Override
    public int compareTo(@NonNull Tile o) {
        return o.id - this.id;
    }
}
