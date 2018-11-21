package Twenty;

import android.support.annotation.NonNull;

import fall2018.csc2017.CoreClasses.Tile;

public class TwentyTile extends Tile {

    /**
     * Constructor for a 2048 tile.
     * Preconditions: <id> is a power of 2
     */
    public TwentyTile(int id, int background){
        // Validate preconditions
        if(Math.log(id) % 1 == 0){
            setId(id);
            setBackground(background);
        }else{
            // Set to a blank tile otherwise.
            setId(0);
        }
    }

    /**
     * Boolean method which tells you if this tile is a blank one.
     */
    public boolean isBlank(){
        return getId() == 0;
    }

    /**
     * TODO: This is never used.
     * Compares two Tiles by id for order.
     * -1 denotes not equal, 0 denotes that Tile <other> is a blank tile,
     * and 1 denotes two tiles are equal
     */
    public int compareTo(@NonNull TwentyTile other) {
        if(other.getId() == 0){
            return 0;
        }else if(other.getId() != this.getId()){
            return -1;
        }else{
            return 1;
        }
    }



}
