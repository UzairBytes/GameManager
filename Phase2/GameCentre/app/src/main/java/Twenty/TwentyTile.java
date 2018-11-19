package Twenty;

import android.support.annotation.NonNull;

import fall2018.csc2017.slidingtiles.Tile;

public class TwentyTile extends Tile {

    /**
     * Constructor for a 2048 tile.
     * Preconditions: <id> is a power of 2
     */
    public TwentyTile(int id, int background){
        // Validate preconditions
        if(Math.log(id) % 1 == 0){
            this.id = id;
            this.background = background;
        }else{
            // Set to a blank tile otherwise.
            this.id = 0;
        }
    }

    /**
     * Boolean method which tells you if this tile is a blank one.
     */
    public boolean isBlank(){
        return this.id == 0;
    }

    /**
     * Compares two Tiles by id for order.
     * -1 denotes not equal, 0 denotes that Tile <other> is a blank tile,
     * and 1 denotes two tiles are equal
     */
    public int compareTo(@NonNull TwentyTile other) {
        if(other.id == 0){
            return 0;
        }else if(other.id != this.id){
            return -1;
        }else{
            return 1;
        }
    }

}
