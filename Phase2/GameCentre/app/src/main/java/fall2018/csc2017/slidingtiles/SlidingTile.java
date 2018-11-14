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
        picts.add(R.drawable.tile_1);
        picts.add(R.drawable.tile_2);
        picts.add(R.drawable.tile_3);
        picts.add(R.drawable.tile_4);
        picts.add(R.drawable.tile_5);
        picts.add(R.drawable.tile_6);
        picts.add(R.drawable.tile_7);
        picts.add(R.drawable.tile_8);
        picts.add(R.drawable.tile_9);
        picts.add(R.drawable.tile_10);
        picts.add(R.drawable.tile_11);
        picts.add(R.drawable.tile_12);
        picts.add(R.drawable.tile_13);
        picts.add(R.drawable.tile_14);
        picts.add(R.drawable.tile_15);
        picts.add(R.drawable.tile_16);
        picts.add(R.drawable.tile_17);
        picts.add(R.drawable.tile_18);
        picts.add(R.drawable.tile_19);
        picts.add(R.drawable.tile_20);
        picts.add(R.drawable.tile_21);
        picts.add(R.drawable.tile_22);
        picts.add(R.drawable.tile_23);
        picts.add(R.drawable.tile_24);
        picts.add(R.drawable.tile_empty);

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
