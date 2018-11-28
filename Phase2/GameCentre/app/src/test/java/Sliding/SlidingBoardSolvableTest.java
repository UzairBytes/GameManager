package Sliding;

import org.junit.Test;

import fall2018.csc2017.CoreClasses.Tile;

import static org.junit.Assert.*;

/**Test class for SlidingBoardSolvable.
 *
 */
public class SlidingBoardSolvableTest {

    /**
     * returns a 2D tile array based on input size.
     * @param  size int
     * @return 2D array of tiles.
     */
    private Tile[][] makeTiles(int size){
        Tile[][] tiles = new Tile[size][size];
        final int numTiles = size * size;
        int tileNumber = 0;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                tiles[row][col] = new SlidingTile(tileNumber, numTiles);
                tileNumber++;
            }
        }
    return tiles;
    }

    /**
     * Returns an instance of SlidingBoardSolvable based on input of 2D tile array newTiles.
     *
     * @param newTiles
     * @return SlidingBoardSolvable.
     */
    private SlidingBoardSolvable setUpSolvable(Tile[][] newTiles){
        SlidingBoardSolvable newBoardSolvable = new SlidingBoardSolvable(newTiles);
        return newBoardSolvable;
    }


    /**
     * Tests isBoardSolvable method in SlidingBoardSolvable under 3 cases.
     */
    @Test
    public void isBoardSolvableTest() {
        // Case 1 - odd board size checks if the number of inversions are even.
        Tile[][] newTiles = makeTiles(5);
        SlidingTile temp = (SlidingTile) newTiles[4][4];
        SlidingTile temp2 = (SlidingTile) newTiles[2][3];
        newTiles[2][3] = temp;
        newTiles[4][4] = temp2;
        SlidingBoardSolvable newSolver = setUpSolvable(newTiles);
        assertEquals(true, newSolver.isBoardSolvable());

        //Case 2 - even board size with blank tile on even row from bottom of
        //tile array and an even number of inversions.
        Tile[][] newTiles3 = makeTiles(4);
        SlidingTile temp3 = (SlidingTile) newTiles3[3][3];
        SlidingTile temp4 = (SlidingTile) newTiles3[2][2];
        newTiles3[2][2] = temp3;
        newTiles3[3][3] = temp4;
        SlidingBoardSolvable newSolver2 = setUpSolvable(newTiles3);
        assertEquals(true, newSolver2.isBoardSolvable());

        //Case 3 - even board with blank tile on odd row from bottom of
        //tile array and an even number of inversions.
        Tile[][] newTiles4 = makeTiles(4);
        SlidingTile temp5 = (SlidingTile) newTiles4[3][3];
        SlidingTile temp6 = (SlidingTile) newTiles4[1][2];
        newTiles4[1][2] = temp5;
        newTiles4[3][3] = temp6;
        SlidingBoardSolvable newSolver3 = setUpSolvable(newTiles4);
        assertEquals(false, newSolver3.isBoardSolvable());






    }
}