package fall2018.csc2017.CoreClasses;

import android.support.annotation.NonNull;
import java.io.Serializable;

/**
 * A Tile in any board-game
 */
public abstract class Tile implements Comparable<Tile>, Serializable {

    /**
     * The background id to find the tile image.
     */
    protected int background;

    /**
     * The id.
     */
    protected int id;

    /**
     * Return the background id.
     *
     * @return the background id
     */
    protected int getBackground() {
        return background;
    }

    /**
     * A tile with a background id; look up and set the id.
     */
    protected Tile(){}


    /**
     * Compares two Tiles by id for order.
     */
    @Override
    public int compareTo(@NonNull Tile other) {
        return other.id - this.id;
    }
}
