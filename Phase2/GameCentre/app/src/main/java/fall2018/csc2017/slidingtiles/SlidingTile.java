package fall2018.csc2017.slidingtiles;


import java.util.ArrayList;

/**
 * A Tile in a sliding tiles puzzle.
 */
class SlidingTile extends Tile {
    /**
     * A tile with a background id; look up and set the id.
     * @param backgroundId the id of this Tile's background
     * @param boardSize the size of the board in which this Tile is contained in
     */


    public SlidingTile(int backgroundId, int boardSize) {
        super();
        ArrayList<Integer> picts = new ArrayList<>();
        picts.add(R.drawable.slidingtile_1);
        picts.add(R.drawable.slidingtile_2);
        picts.add(R.drawable.slidingtile_3);
        picts.add(R.drawable.slidingtile_4);
        picts.add(R.drawable.slidingtile_5);
        picts.add(R.drawable.slidingtile_6);
        picts.add(R.drawable.slidingtile_7);
        picts.add(R.drawable.slidingtile_8);
        picts.add(R.drawable.slidingtile_9);
        picts.add(R.drawable.slidingtile_10);
        picts.add(R.drawable.slidingtile_11);
        picts.add(R.drawable.slidingtile_12);
        picts.add(R.drawable.slidingtile_13);
        picts.add(R.drawable.slidingtile_14);
        picts.add(R.drawable.slidingtile_15);
        picts.add(R.drawable.slidingtile_16);
        picts.add(R.drawable.slidingtile_17);
        picts.add(R.drawable.slidingtile_18);
        picts.add(R.drawable.slidingtile_19);
        picts.add(R.drawable.slidingtile_20);
        picts.add(R.drawable.slidingtile_21);
        picts.add(R.drawable.slidingtile_22);
        picts.add(R.drawable.slidingtile_23);
        picts.add(R.drawable.slidingtile_24);
        picts.add(R.drawable.slidingtile_empty);

        this.id = backgroundId + 1;
        if (boardSize == 9) {
            if (backgroundId == 8){
                background = picts.get(24);
            }
            else{
                background = picts.get(backgroundId);
            }
        }
        else if (boardSize == 16) {
            if (backgroundId == 15){
                background = picts.get(24);
            }
            else{
                background = picts.get(backgroundId);
            }
        } else if (boardSize == 25) {
            background = picts.get(backgroundId);
        }

    }
}
