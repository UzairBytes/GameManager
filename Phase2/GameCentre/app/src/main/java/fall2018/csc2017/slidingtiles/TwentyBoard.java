package fall2018.csc2017.slidingtiles;
import java.util.ArrayList;

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

    /* Get the positions of a random empty tile in this Board. */
    public int[] getRanEmptyPos(){
        int[] ranEmptyPos = new int[2];
        ArrayList<int[]> emptyPositions = getAllEmptyPos();
        if(emptyPositions.isEmpty()){
            // There are no empty tiles. Having a position of '-1' will denote that no empty tile exists.
            ranEmptyPos[0] = -1;
        }else{
            int ranIndex = (int)(Math.random() * emptyPositions.size());
            ranEmptyPos = emptyPositions.get(ranIndex);
        }
        return ranEmptyPos;

    }

    /* Get the positions of all the empty tiles in this Board. */
    public ArrayList<int[]> getAllEmptyPos(){
        ArrayList<int[]> emptyPositions = new ArrayList<>();
        // Iterate through this TwentyBoard to find & retrieve the position of all the empty tiles
        TwentyTile currentTile;
        for(int row = 0; row<this.tiles.length; row++){
            for(int col = 0; col<this.tiles.length; col++){
                currentTile = (TwentyTile)this.tiles[row][col];
                if(currentTile.isBlank()){
                    int emptyPosition[] = {row, col};
                    emptyPositions.add(emptyPosition);
                }
            }
        }
        return emptyPositions;
    }
}
