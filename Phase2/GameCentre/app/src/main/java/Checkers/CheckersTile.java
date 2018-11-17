package Checkers;

import java.util.ArrayList;

import fall2018.csc2017.slidingtiles.Tile;

/**
 * A Tile in checkers Game
 */
public class CheckersTile extends Tile {

    /**
     * The background id to find the tile image.
     */
    int backgroundId;

    /**
     * Where it can take a piece
     */
    boolean canTakePiece;

    /**
     * A checker tile with a background id
     */
    public CheckersTile(int backgroundId) {
        super();
        this.backgroundId = backgroundId;
        canTakePiece = false;
        ArrayList<Integer> picts = new ArrayList<>();
    }

    /**
     * Makes the piece a king by incriminating its backgroundId by 1
     */
    protected void makeKing(){
        backgroundId++;
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
}
