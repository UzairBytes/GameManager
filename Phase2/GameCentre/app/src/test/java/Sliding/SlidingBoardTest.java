package Sliding;

import android.app.Instrumentation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
    private List<SlidingTile> makeTiles() {
        List<SlidingTile> tiles = new ArrayList<>();
        final int numTiles = size * size;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new SlidingTile(tileNum, numTiles));
        }

        return tiles;
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
        List<SlidingTile> tiles = makeTiles();
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
        assertEquals(1, slidingBoard.getSlidingTile(0, 0).getId());
        assertEquals(2, slidingBoard.getSlidingTile(0, 1).getId());
        slidingBoard.swapTiles(0, 0, 0, 1);
        assertEquals(2, slidingBoard.getSlidingTile(0, 0).getId());
        assertEquals(1, slidingBoard.getSlidingTile(0, 1).getId());
    }

    /**
     * Test whether swapping the last two tiles works.
     */
    @Test
    public void testSwapLastTwo() {
        setUpCorrect();
        assertEquals(15, slidingBoard.getSlidingTile(3, 2).getId());
        assertEquals(16, slidingBoard.getSlidingTile(3, 3).getId());
        slidingBoard.swapTiles(3, 3, 3, 2);
        assertEquals(16, slidingBoard.getSlidingTile(3, 2).getId());
        assertEquals(15, slidingBoard.getSlidingTile(3, 3).getId());
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