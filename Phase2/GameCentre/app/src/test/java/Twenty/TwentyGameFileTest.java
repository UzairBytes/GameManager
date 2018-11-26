package Twenty;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import fall2018.csc2017.CoreClasses.Board;
import phase1.Account;
import phase1.AccountManager;
import phase1.Game;

public class TwentyGameFileTest {

    private TwentyGameFile gameFile;

    @Before
    public void setup(){
        AccountManager.activeAccount = new Account("twentytest", "1234");
        AccountManager.activeAccount.setActiveGameName(Game.TWENTY_NAME);
        TwentyBoardManager twentyBoardManager = new TwentyBoardManager(3);
        TwentyBoard twentyBoard = twentyBoardManager.twentyBoard;
        gameFile = new TwentyGameFile(twentyBoard, "Test");
    }

    @Test
    public void testIncrement(){
        int earlierMoves = gameFile.numMoves;
        gameFile.increaseNumMoves();
        assertEquals("tile.compareTo() failed test 2.", earlierMoves + 1, gameFile.numMoves);

    }
}
