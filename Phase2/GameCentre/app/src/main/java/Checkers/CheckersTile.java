package Checkers;

import java.util.ArrayList;

import fall2018.csc2017.slidingtiles.R;
import fall2018.csc2017.slidingtiles.Tile;

/**
 * A Tile in checkers Game
 */
public class CheckersTile extends Tile {
    
    /**
     * Where it can take a piece
     */
    boolean canTakePiece;

    /**
     * Arraylist of images
     */
    ArrayList<Integer> picts = new ArrayList<>();

    /**
     * A checker tile with a background id
     */
    public CheckersTile(int id) {
        super();
        this.id = id;
        canTakePiece = false;

        picts.add(R.drawable.checkerstile_0);
        picts.add(R.drawable.checkerstile_1);
        picts.add(R.drawable.checkerstile_2);
        picts.add(R.drawable.checkerstile_3);
        picts.add(R.drawable.checkerstile_4);
        picts.add(R.drawable.checkerstile_5);
        picts.add(R.drawable.checkerstile_6);
        picts.add(R.drawable.checkerstile_7);
        picts.add(R.drawable.checkerstile_8);
        picts.add(R.drawable.checkerstile_9);
        background = picts.get(id);

    }

    /**
     * Makes the piece a king by incriminating its id by 1
     */
    protected void makeKing(){
        id++;
        background = picts.get(id);
    }

    /**
     * Sets whether a checkerTile can take a piece
     *
     * @param canTakePiece whether a checkerTile can take a piece
     */
    public void setCanTakePiece(boolean canTakePiece){
        this.canTakePiece = canTakePiece;
    }

    /**
     * Return the value of canTakePiece.
     *
     * @return canTakePiece
     */
    public boolean isCanTakePiece() {
        return canTakePiece;
    }
    
    public void highlight() {
        id += 4;
        background = picts.get(id);
    }
    
    public void dehighlight() {
        id -= 4;
        background = picts.get(id);
    }
}
