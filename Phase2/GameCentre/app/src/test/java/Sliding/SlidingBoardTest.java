package Sliding;

import android.app.Instrumentation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import fall2018.csc2017.CoreClasses.Tile;
import phase1.Account;
import phase1.AccountManager;

import static org.junit.Assert.*;

public class SlidingBoardTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * size of board
     */
    private int size = 4;

    /**
     * Make a set of tiles that are in order.
     * @return a set of tiles that are in order
     */
    private Tile[][] makeTiles() {
        final int numTiles = size * size;
        Tile[][] newTile = new Tile[size][size];
        int tileNum = 0;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                newTile[row][col] = new SlidingTile(tileNum, numTiles);
                tileNum++;
            }
        }
        return newTile;
    }

    /** The board manager for testing. */
    SlidingBoardManager slidingBoardManager;

    /**The sliding board for testing.*/
    SlidingBoard slidingBoard;

    /**
     * Set up a Sliding board manager.
     */
    private void setUpSlidingBoardManager() {
        slidingBoardManager = new SlidingBoardManager(size);
    }

    /**
     * Set up a correct board.
     */
    private void setUpCorrect() {
        Tile[][] tiles = makeTiles();
        slidingBoard = new SlidingBoard(tiles, size, size);
    }


    /**
     * Shuffle a few tiles.
     */
    private void swapFirstTwoTiles() {
        slidingBoard.swapTiles(0, 0, 0, 1);
    }

    /**
     * Test whether swapping two tiles makes a solved board unsolved.
     */
    @Test
    public void testIsSolved() {
        setUpSlidingBoardManager();
        assertEquals(true, slidingBoardManager.puzzleSolved());
        swapFirstTwoTiles();
        assertEquals(false, slidingBoardManager.puzzleSolved());
    }

    /**
     * Test whether swapping the first two tiles works.
     */
    @Test
    public void testSwapFirstTwo() {
        setUpCorrect();
        assertEquals(1, slidingBoard.getTile(0, 0).getId());
        assertEquals(2, slidingBoard.getTile(0, 1).getId());
        slidingBoard.swapTiles(0, 0, 0, 1);
        assertEquals(2, slidingBoard.getTile(0, 0).getId());
        assertEquals(1, slidingBoard.getTile(0, 1).getId());
    }

    /**
     * Test whether swapping the last two tiles works.
     */
    @Test
    public void testSwapLastTwo() {
        setUpCorrect();
        assertEquals(15, slidingBoard.getTile(3, 2).getId());
        assertEquals(16, slidingBoard.getTile(3, 3).getId());
        slidingBoard.swapTiles(3, 3, 3, 2);
        assertEquals(16, slidingBoard.getTile(3, 2).getId());
        assertEquals(15, slidingBoard.getTile(3, 3).getId());
    }

    /**
     * Test whether isValidHelp works.
     */
    @Test
    public void testIsValidTap() {
        setUpSlidingBoardManager();
        assertEquals(true, slidingBoardManager.isValidTap(11));
        assertEquals(true, slidingBoardManager.isValidTap(14));
        assertEquals(false, slidingBoardManager.isValidTap(10));
    }
}