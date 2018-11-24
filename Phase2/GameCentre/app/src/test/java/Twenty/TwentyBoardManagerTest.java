package Twenty;

import android.support.v7.app.AppCompatActivity;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import phase1.Account;
import phase1.AccountManager;
import phase1.Game;

import static org.junit.Assert.assertEquals;

public class TwentyBoardManagerTest {

    private TwentyBoardManager twentyBoardManager;
    private TwentyTile tile1, tile2, tile3, tile4;

    @Test
    public void testIsValidMove(){
        AccountManager.activeAccount = new Account("sid", "1234");
        AccountManager.activeAccount.setActiveGameName(Game.TWENTY_NAME);

//         Initialize the board as all blank tiles.
        twentyBoardManager = new TwentyBoardManager(3);
        assertEquals("twentyBoardManager.isValidMove() failed test 1.", true, twentyBoardManager.isValidMove(true));
        assertEquals("twentyBoardManager.isValidMove() failed test 2.", true, twentyBoardManager.isValidMove(false));

        TwentyBoard twentyBoard = twentyBoardManager.twentyBoard;
        tile1 = new TwentyTile(2, 2);
        tile2 = new TwentyTile(4, 2);
        tile3 = new TwentyTile(8, 2);
        for(int row = 0; row < 3; row++){
            twentyBoard.insertTile(row,0, tile1);
            twentyBoard.insertTile(row,1, tile2);
            twentyBoard.insertTile(row,2, tile3);
        }
        assertEquals("twentyBoard.isValidMove() failed test 3.", false, twentyBoardManager.isValidMove(true));
    }

    @Test
    public void testTouchMove(){
        AccountManager.activeAccount = new Account("sid", "1234");
        AccountManager.activeAccount.setActiveGameName(Game.TWENTY_NAME);

        // Initialize the board as all blank tiles.
        TwentyBoardManager twentyBoardManager = new TwentyBoardManager(3);
        TwentyTile tile1, tile2, tile3;
        TwentyBoard twentyBoard = twentyBoardManager.twentyBoard;
        tile1 = new TwentyTile(2, 2);
        tile2 = new TwentyTile(4, 2);
        tile3 = new TwentyTile(8, 2);
        for(int row = 0; row < 3; row++){
            twentyBoard.insertTile(row,0, tile1);
            twentyBoard.insertTile(row,1, tile2);
            twentyBoard.insertTile(row,2, tile3);
        }

        twentyBoardManager.touchMove('L');
        assertEquals("twentyBoard.touchMove() failed test 1.", false, twentyBoard.isCollapsable(true));
    }


}
