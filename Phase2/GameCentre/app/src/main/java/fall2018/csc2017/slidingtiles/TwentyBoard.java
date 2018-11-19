package fall2018.csc2017.slidingtiles;

public class TwentyBoard extends Board {

    public void swap(int pos1, int pos2){

    }

    public void generateRandomTile(){
        // Generate a tile with a random value from (2^1, 2^2, 2^3),
        // and insert it at the empty tile.
        int ranExp = (int)(Math.random() * 4 + 1);
        int ranNum = (int)(Math.pow(2, ranExp));
    }

    /* Get all of the empty tiles in this board. */
    public int[] getEmptyTile(){
        // Iterate through this TwentyBoard to find & retrieve the position of an empty tile;
        TwentyTile currentTile;
        int emptyPosition[] = new int[2];
        for(int row = 0; row<this.tiles.length; row++){
            for(int col = 0; col<this.tiles.length; col++){
                currentTile = (TwentyTile)this.tiles[row][col];
                if(currentTile.isBlank()){
                    emptyPosition[0] = row;
                    emptyPosition[1] = col;
                }
            }
        }
        return emptyPosition;
    }
}
