package Checkers;

import java.util.HashMap;

import fall2018.csc2017.CoreClasses.R;
import fall2018.csc2017.CoreClasses.Tile;

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
     * HashMap of images
     */
    private HashMap<String, Integer> picts = new HashMap<>();

    public static final String EMPTYWHITETILE = "empty_tile";

    static final String REDKING = "red_king";

    static final String WHITEKING = "white_king";

    /**
     * A checker tile with a background id
     */
    public CheckersTile(String id) {
        super();
        this.id = id;
        canTakePiece = false;
        picts.put("empty_tile", R.drawable.checkers_empty_white_tile);
        picts.put("empty_black_tile", R.drawable.checkers_empty_black_tile);
        picts.put("red_pawn", R.drawable.checkers_red_pawn);
        picts.put("red_king", R.drawable.checkers_red_king);
        picts.put("white_pawn", R.drawable.checkers_white_pawn);
        picts.put("white_king", R.drawable.checkers_white_king);
        picts.put("red_pawn_highlighted", R.drawable.checkers_red_pawn_highlighted);
        picts.put("red_king_highlighted", R.drawable.checkers_red_king_highlighted);
        picts.put("white_pawn_highlighted", R.drawable.checkers_white_pawn_highlighted);
        picts.put("white_king_highlighted", R.drawable.checkers_white_king_highlighted);
        if (picts.containsKey(id)){
            background = picts.get(id);}
        else
            {throw new IllegalArgumentException("You're passing an invalid id to the CheckersTile Constructor");
        }


    }


    /**
     * Sets whether a checkerTile can take a piece
     *
     * @param canTakePiece whether a checkerTile can take a piece
     */
    void setCanTakePiece(boolean canTakePiece){
        this.canTakePiece = canTakePiece;
    }

    /**
     * Return the value of canTakePiece.
     *
     * @return canTakePiece
     */
    boolean isCanTakePiece() {
        return canTakePiece;
    }
    
    void highlight() {
        if (!this.id.contains("empty")) {
            this.id += "_highlighted";
            background = picts.get(id);}
    }

   public void dehighlight() {
        String newId = id.replaceAll("_highlighted","");
        id = newId;
        background = picts.get(id);
    }

    /**
     * Return the tile id.
     *
     * @return the tile id
     */
    public String getId(){return id;}

    public void changeTile(String newId){
        id = newId;
        background = picts.get(id);
    }
}
