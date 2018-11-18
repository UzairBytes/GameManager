package Checkers;

import java.util.HashMap;

import fall2018.csc2017.slidingtiles.R;
import fall2018.csc2017.slidingtiles.Tile;

/**
 * A Tile in checkers Game
 */
public class CheckersTile extends Tile {

    /**
     * Overwrite id to be a String
     */
    private String id;

    /**
     * Where it can take a piece
     */
    private boolean canTakePiece;

    /**
     * Arraylist of images
     */
    private HashMap<String, Integer> picts = new HashMap<>();

    /**
     * A checker tile with a background id
     */
    public CheckersTile(String id) {
        super();
        this.id = id;
        canTakePiece = false;
        picts.put("empty_white_tile", R.drawable.checkers_empty_white_tile);
        picts.put("empty_black_tile", R.drawable.checkers_empty_black_tile);
        picts.put("red_pawn", R.drawable.checkers_red_pawn);
        picts.put("red_king", R.drawable.checkers_red_king);
        picts.put("white_pawn", R.drawable.checkers_white_pawn);
        picts.put("white_king", R.drawable.checkers_white_king);
        picts.put("red_pawn_highlighted", R.drawable.checkers_red_pawn_highlighted);
        picts.put("red_king_highlighted", R.drawable.checkers_red_king_highlighted);
        picts.put("white_pawn_highlighted", R.drawable.checkers_white_pawn_highlighted);
        picts.put("white_king_highlighted", R.drawable.checkers_white_king_highlighted);
        background = picts.get(id);

    }

    /**
     * Makes the piece a king
     */
    protected void makeKing(){
        if (this.id.contains("white")){
            this.id = "white_king";
        }
        else {this.id = "red_king";}
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
        this.id += "_highlighted";
        background = picts.get(id);
    }

    public void dehighlight() {
        String newId = this.id.replaceAll("_highlighted$","");
    }

    /**
     * Return the tile id.
     *
     * @return the tile id
     */
    protected String getId(){return id;}
}
