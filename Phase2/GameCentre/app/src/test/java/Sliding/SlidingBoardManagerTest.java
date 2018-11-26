package Sliding;

import org.junit.Before;
import org.junit.Test;

import Twenty.TwentyBoardManager;
import fall2018.csc2017.CoreClasses.Tile;
import phase1.Account;
import phase1.AccountManager;
import phase1.Game;
import phase1.GameFile;

import static org.junit.Assert.assertEquals;

public class SlidingBoardManagerTest {
    private SlidingBoardManager slidingBoardManager;
    private SlidingBoard slidingBoard;

    @Before
    public void setup(){
        AccountManager.contextPath = "/data/user/0/fall2018.csc2017.slidingtiles/files";
        AccountManager.activeAccount = new Account("slidingtest", "1234");
        AccountManager.activeAccount.setActiveGameName(Game.SLIDING_NAME);
        slidingBoardManager = new SlidingBoardManager(3);
        slidingBoard = slidingBoardManager.slidingBoard;
    }

    @Test
    public void testGetGameFile(){
        GameFile gameFile = slidingBoardManager.getGameFile();
        assertEquals("slidingBoardManager.getGameFile() failed test 1.", true, gameFile != null);
    }

    @Test
    public void testPuzzleSolved(){
        // Create a completed board, & check if it's recognized as solved.
        int numIt = 0;
        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                SlidingTile tile = new SlidingTile(numIt, 9);
                slidingBoard.insertTile(i,j,tile);
                numIt++;
            }
        }
        assertEquals("slidingBoardManager.puzzleSolved() failed test 1.", true, slidingBoardManager.puzzleSolved());
    }

}
