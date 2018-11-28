package Sliding;

import org.junit.Before;
import org.junit.Test;

import fall2018.csc2017.CoreClasses.Tile;

import static org.junit.Assert.*;

public class SlidingBoardSolvableTest {

    /**
     * board containing tile array.
     */
    private SlidingBoard testBoard;

    /**
     * Set up tiles.
     * @param  size int
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

    @Test
    public void areInversionsEven(){
        Tile[][] newTiles = makeTiles(3);
        SlidingBoardSolvable newSolver = new SlidingBoardSolvable(newTiles);
        assertEquals(true,newSolver.areInversionsEven());
        newSolver.calculateInversions();
        assertEquals(true, newSolver.inversions == 0);
        SlidingTile temp = (SlidingTile) newTiles[0][0];
        SlidingTile temp2 = (SlidingTile) newTiles[2][1];
        newTiles[2][1] = temp;
        newTiles[0][0] = temp2;
        assertEquals(false, newSolver.areInversionsEven());

    }

    @Test
    public void isBoardSolvable() {
        Tile[][] newTiles = makeTiles(5);
        SlidingBoardSolvable newSolver = new SlidingBoardSolvable(newTiles);
        SlidingTile temp = (SlidingTile) newTiles[4][4];
        SlidingTile temp2 = (SlidingTile) newTiles[2][3];
        newTiles[2][1] = temp;
        newTiles[0][0] = temp2;
        assertEquals(true, newSolver.isBoardSolvable());


    }
}