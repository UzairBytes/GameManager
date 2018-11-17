package Checkers;

import fall2018.csc2017.slidingtiles.Tile;

public class CheckersTile extends Tile {

    int backgroundId;
    
    boolean canTakePiece;
    /**
     * A tile with a background id; look up and set the id.
     */
    public CheckersTile(int backgroundId) {
        super();
        this.backgroundId = backgroundId;
        canTakePiece = false;
    }
    protected void makeKing(){
        backgroundId++;
    }

    public void setCanTakePiece(boolean canTakePiece){
        this.canTakePiece = canTakePiece;
    }

    public boolean isCanTakePiece() {
        return canTakePiece;
    }
}
