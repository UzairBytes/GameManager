package Twenty;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TwentyBoardManagerTest {

    @Test
    public void testIsValidMove(){
        // Initialize the board as all blank tiles.
        List<TwentyTile> tiles = new ArrayList<>();
        final int numTiles = 3 * 3;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new TwentyTile(0, 0));
        }
        TwentyBoard twentyBoard = new TwentyBoard(tiles, 3, 3);

        assertEquals("twentyBoardManager.isValidMove() failed test 1.", 0, twentyBoard.isV);
    }


}
