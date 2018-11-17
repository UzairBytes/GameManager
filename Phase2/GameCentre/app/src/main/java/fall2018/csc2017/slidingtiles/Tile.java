package fall2018.csc2017.slidingtiles;

import android.support.annotation.NonNull;
import java.io.Serializable;

/**
 * A Tile in any board-game
 */
public abstract class Tile implements Comparable<Tile>, Serializable {

    /**
     * The background id to find the tile image.
     */
    int background;

    /**
     * The id.
     */
    int id;

    /**
     * Return the background id.
     *
     * @return the background id
     */
    int getBackground() {
        return background;
    }

    /**
     * Return the tile id.
     *
     * @return the tile id
     */
    int getId() {
        return id;
    }

    /**
     * A tile with a background id; look up and set the id.
     */
    protected Tile(){}


    /**
     * Compares two Tiles by id for order.
     */
    @Override
    public int compareTo(@NonNull Tile o) {
        return o.id - this.id;
    }
}
