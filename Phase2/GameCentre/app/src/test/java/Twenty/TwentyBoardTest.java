package Twenty;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TwentyBoardTest {
    @Before
    public void setup(){
    }

    @Test
    public void testIsCollapsable(){
        List<TwentyTile> tiles = new ArrayList<>();
        final int numTiles = 3 * 3;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new TwentyTile(0, 0));
        }
        TwentyBoard twentyBoard = new TwentyBoard(tiles, 3, 3);
        TwentyTile tile1, tile2, tile3, tile4;

        /* First test suite: to see if collapasble vertically. */
        tile1 = new TwentyTile(2, 2);
        tile2 = new TwentyTile(2, 2);
        tile3 = new TwentyTile(2, 2);
        tile4 = new TwentyTile(2, 2);

        twentyBoard.insertTile(0,0, tile1);
        twentyBoard.insertTile(0,1, tile2);
        twentyBoard.insertTile(0,2, tile3);

        assertEquals("twentyBoard.isCollapsable() failed horizontal test 1.", true, twentyBoard.isCollapsable(true));

        twentyBoard = new TwentyBoard(tiles, 3, 3);
        assertEquals("twentyBoard.isCollapsable() failed horizontal test 2.", true, twentyBoard.isCollapsable(true));

        twentyBoard = new TwentyBoard(tiles, 3, 3);
        assertEquals("twentyBoard.isCollapsable() failed horizontal test 2.", true, twentyBoard.isCollapsable(true));

        twentyBoard = new TwentyBoard(tiles, 3, 3);
        tile3 = new TwentyTile(2, 2);
        tile4 = new TwentyTile(4, 2);
        twentyBoard.insertTile(0,0, tile1);
        twentyBoard.insertTile(0,1, tile2);
        assertEquals("twentyBoard.isCollapsable() failed horizontal test 3.", true, twentyBoard.isCollapsable(true));

        twentyBoard = new TwentyBoard(tiles, 3, 3);
        tile1 = new TwentyTile(2, 2);
        tile2 = new TwentyTile(4, 2);
        tile3 = new TwentyTile(6, 2);
        tile4 = new TwentyTile(8, 2);
        twentyBoard.insertTile(0,0, tile1);
        twentyBoard.insertTile(0,1, tile2);
        twentyBoard.insertTile(0,2, tile3);
        assertEquals("twentyBoard.isCollapsable() failed horizontal test 4.", true, twentyBoard.isCollapsable(true));

        twentyBoard = new TwentyBoard(tiles, 3, 3);
        tile1 = new TwentyTile(2, 2);
        tile2 = new TwentyTile(4, 2);
        tile3 = new TwentyTile(6, 2);
        tile4 = new TwentyTile(8, 2);
        for(int row = 0; row < 3; row++){
            twentyBoard.insertTile(row,0, tile1);
            twentyBoard.insertTile(row,1, tile2);
            twentyBoard.insertTile(row,2, tile3);
        }
        assertEquals("twentyBoard.isCollapsable() failed horizontal test 5.", false, twentyBoard.isCollapsable(true));
    }

    @Test
    public void testMergeTiles(){
        // Initialize the board as all blank tiles.
        List<TwentyTile> tiles = new ArrayList<>();
        final int numTiles = 3 * 3;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new TwentyTile(0, 0));
        }
        TwentyBoard twentyBoard = new TwentyBoard(tiles, 3, 3);
        TwentyTile tile1, tile2;

        /* First test suite: to see if collapasble vertically. */
        tile1 = new TwentyTile(2, 2);
        tile2 = new TwentyTile(2, 2);

        twentyBoard.insertTile(0,0, tile1);
        twentyBoard.insertTile(0,1, tile2);

        twentyBoard.mergeTiles(0, 0, 0, 1);

        assertEquals("twentyBoard.mergeTiles failed test 1.", 4, ((TwentyTile)(twentyBoard.getTile(0,0))).getId());
        assertEquals("twentyBoard.mergeTiles failed test 2.", 0, ((TwentyTile)(twentyBoard.getTile(0,1))).getId());

    }

    @Test
    public void testGenerateRandomTile(){
        // Initialize the board as all blank tiles.
        List<TwentyTile> tiles = new ArrayList<>();
        final int numTiles = 3 * 3;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new TwentyTile(0, 0));
        }
        TwentyBoard twentyBoard = new TwentyBoard(tiles, 3, 3);

        // If there's a random tile somewhere in the board (that's not blank)
        boolean ranTileExists = false;
        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 3; col++){
                if(((TwentyTile)twentyBoard.getTile(row, col)).getId() != 0){
                    ranTileExists = true;
                }
            }
        }

        assertEquals("twentyBoard.generateRandomTile failed test 1.", true, ranTileExists);
    }


//     ?
//    public void testGetRandomEmptyPos(){
//        // Initialize the board as all tiles with id 1.
//        List<TwentyTile> tiles = new ArrayList<>();
//        final int numTiles = 3 * 3;
//        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
//            tiles.add(new TwentyTile(1, 1));
//        }
//        TwentyBoard twentyBoard = new TwentyBoard(tiles, 3, 3);
//
//        // Place 1 empty tile in the board
//        TwentyTile tile1 = new TwentyTile(0, 0);
//        twentyBoard.getAllEmptyPos()
//
//
//        assertEquals("twentyBoard.generateRandomTile failed test 1.", true, ranTileExists);
//    }


}
