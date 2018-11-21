package Twenty;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TwentyBoardManagerTest {

    @Test
    public void testIsValidMove(){
        // Initialize the board as all blank tiles.
        TwentyBoardManager twentyBoardManager = new TwentyBoardManager(3);

        assertEquals("twentyBoardManager.isValidMove() failed test 1.", true, twentyBoardManager.isValidMove(true));
        assertEquals("twentyBoardManager.isValidMove() failed test 2.", true, twentyBoardManager.isValidMove(false));


        TwentyTile tile1, tile2, tile3, tile4;
        TwentyBoard twentyBoard = twentyBoardManager.twentyBoard;
        tile1 = new TwentyTile(2, 2);
        tile2 = new TwentyTile(4, 2);
        tile3 = new TwentyTile(6, 2);
        tile4 = new TwentyTile(8, 2);
        for(int row = 0; row < 4; row++){
            twentyBoard.insertTile(row,0, tile1);
            twentyBoard.insertTile(row,1, tile2);
            twentyBoard.insertTile(row,2, tile3);
            twentyBoard.insertTile(row,3, tile4);
        }
        assertEquals("twentyBoard.isValidMove() failed test 3.", false, twentyBoard.isCollapsable(true));
    }

    @Test
    public void testTouchMove(){
        // Initialize the board as all blank tiles.
        TwentyBoardManager twentyBoardManager = new TwentyBoardManager(3);

        assertEquals("twentyBoardManager.isValidMove() failed test 1.", true, twentyBoardManager.isValidMove(true));
        assertEquals("twentyBoardManager.isValidMove() failed test 2.", true, twentyBoardManager.isValidMove(false));

        TwentyTile tile1, tile2, tile3, tile4;
        TwentyBoard twentyBoard = twentyBoardManager.twentyBoard;
        tile1 = new TwentyTile(2, 2);
        tile2 = new TwentyTile(4, 2);
        tile3 = new TwentyTile(6, 2);
        tile4 = new TwentyTile(8, 2);
        for(int row = 0; row < 4; row++){
            twentyBoard.insertTile(row,0, tile1);
            twentyBoard.insertTile(row,1, tile2);
            twentyBoard.insertTile(row,2, tile3);
            twentyBoard.insertTile(row,3, tile4);
        }

        twentyBoardManager.touchMove('L');
        assertEquals("twentyBoard.isValidMove() failed test 3.", false, twentyBoard.isCollapsable(true));
    }


}
