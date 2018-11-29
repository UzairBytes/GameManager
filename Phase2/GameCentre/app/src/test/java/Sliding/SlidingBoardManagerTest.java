package Sliding;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import phase1.Account;
import phase1.AccountManager;
import phase1.Game;
import phase1.GameFile;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SlidingBoardManagerTest {

    /**
     * Sliding board manager for test.
     */
    private SlidingBoardManager slidingBoardManager;

    /**
     * sliding board for test.
     */
    private SlidingBoard slidingBoard;

    /**
     * Set up initalizes a context path, activate account and Sliding game name
     * before instantiating a board manager and board for test.
     */
    @Before
    public void setup() {
        AccountManager.contextPath = "/data/user/0/fall2018.csc2017.slidingtiles/files";
        AccountManager.activeAccount = new Account("slidingtest", "1234");
        AccountManager.activeAccount.setActiveGameName(Game.SLIDING_NAME);
        slidingBoardManager = new SlidingBoardManager(3);
        slidingBoard = slidingBoardManager.getBoard();
        makeBoard();
    }

    /**
     * Populates board with tiles.
     */
    private void makeBoard(){
        int numIt = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                SlidingTile tile = new SlidingTile(numIt, 9);
                slidingBoard.insertTile(i, j, tile);
                numIt++;
            }
        }
    }

    /**
     * Tests that Gamefile is not empty.
     */
    @Test
    public void testGetGameFile() {
        GameFile gameFile = slidingBoardManager.getGameFile();
        assertNotNull("slidingBoardManager.getGameFile() failed test 1.", gameFile);
    }

    /**
     * Test if puzzleSolved returns true when tiles are organized in order.
     */
    @Test
    public void testPuzzleSolved() {
        assertTrue("slidingBoardManager.puzzleSolved() failed test 1.", slidingBoardManager.puzzleSolved());
    }

    /**
     * Tests to see if valid move can be made at various positions.
     */
    @Test
    public void testIsValidMove() {
        //  Test if a valid move can be made at various position
        assertTrue("slidingBoardManager.isValidTap() failed test 1.", slidingBoardManager.isValidTap(7));
        assertTrue("slidingBoardManager.isValidTap() failed test 2.", slidingBoardManager.isValidTap(5));
        assertFalse("slidingBoardManager.isValidTap() failed test 3.", slidingBoardManager.isValidTap(2));
    }

    /**
     * Tests helper function process move, which checks if position selected is
     * adjacent to the blank tile.
     */
    @Test
    public void testProcessMove() {
        slidingBoardManager.processMove(7);
        Assert.assertEquals("slidingBoardManager.isValidTap() failed test 1.", 9, slidingBoard.getTile(2, 1).getId());
        assertEquals("slidingBoardManager.isValidTap() failed test 2.", 8, slidingBoard.getTile(2, 2).getId());
    }

//    /**
//     * Tests undosliding method to see if previous state is returned.
//     */
//    @Test
//    public void testUndoSliding(){
//        slidingBoardManager.setMaxUndos(4);
//        SlidingBoard originalBoard = slidingBoardManager.getBoard();
//        slidingBoardManager.touchMove(7);
//        SlidingBoard newBoard = slidingBoardManager.getBoard();
//        assertNotSame(originalBoard, newBoard);
//
//    }


}
