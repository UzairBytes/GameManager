package fall2018.csc2017.slidingtiles;

public class TwentyBoard extends Board {

    public void swap(int pos1, int pos2){

    }

    /* Generate a random tile at a given position. */
    public void generateRandomTile(int position[]){
        int tileRow = position[0];
        int tileCol = position[1];

        // Pick a random value from (2^1, 2^2, 2^3)
        int ranExp = (int)(Math.random() * 4 + 1);
        int ranNum = (int)(Math.pow(2, ranExp));

        // TODO: Insert a background image instead of ranNum
        TwentyTile randomTile = new TwentyTile(ranNum, ranNum);

        this.tiles[tileRow][tileCol] = randomTile;

    }

    /* Get the position of an empty tile in this board. */
    public int[] getEmptyTilePos(){
        // Iterate through this TwentyBoard to find & retrieve the position of an empty tile;
        TwentyTile currentTile;
        int emptyPosition[] = new int[2];
        boolean emptyFound = false;
        for(int row = 0; row<this.tiles.length; row++){
            for(int col = 0; col<this.tiles.length; col++){
                currentTile = (TwentyTile)this.tiles[row][col];
                if(currentTile.isBlank()){
                    emptyPosition[0] = row;
                    emptyPosition[1] = col;
                    emptyFound = true;
                }
            }
        }
        // Having a position of '-1' will denote that no such empty tile exists.
        if(!emptyFound){
            emptyPosition[0] = -1;
        }
        return emptyPosition;
    }
}
