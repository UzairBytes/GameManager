package Sliding;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fall2018.csc2017.CoreClasses.Board;
import fall2018.csc2017.CoreClasses.Tile;

/**
 * The sliding tiles board.
 */
public class SlidingBoard extends Board {

//    /**
//     * The tiles on the board in row-major order.
//     */
//    private SlidingTile[][] tiles;
//
//    /**
//     * Stores the list of SlidingTile objects when this Board is initialized.
//     */
//    private List<SlidingTile> tilesList;


//    /**
//     * Stores the list of SlidingTile Iterator of this Board.
//     */
//    @NonNull
//    @Override
//    public Iterator<SlidingTile> iterator() {
//        return new SlidingTileIterator();
//    }
//
//    /**
//     * The tile iterator, iterates through the tiles on the board.
//     */
//    class SlidingTileIterator implements Iterator<SlidingTile> {
//        /**
//         * The column of the next Tile.
//         */
//        private int nextColumn = 0;
//        /**
//         * The row of the next Tile.
//         */
//        private int nextRow = 0;
//
//        @Override
//        public SlidingTile next() {
//            SlidingTile nextTile = tiles[nextRow][nextColumn];
//            nextColumn += 1;
//            if (nextColumn == getNumCols()) {
//                nextColumn = 0;
//                nextRow += 1;
//            }
//            return nextTile;
//        }
//
//        @Override
//        public boolean hasNext() {
//            return (nextRow < getNumRows());
//        }
//    }

    /**
     * A new board of tiles in row-major order.
     * Precondition: len(tiles) == NUM_ROWS * NUM_COLS
     *
     * @param tiles the tiles for the board
     */

    public SlidingBoard(Tile[][] tiles, int rows, int cols) {
        super(tiles, rows, cols);
    }

    /**
     * Returns a deep-copy of this Board.
     */
    @Override
    public SlidingBoard createDeepCopy() {
        Tile[][] tiles = super.createDeepCopy().tiles;
        SlidingBoard copiedBoard = new SlidingBoard(tiles, getNumRows(), getNumCols());
        return copiedBoard;
    }

//    /**
//     * Return the tile at (row, col)
//     *
//     * @param row the tile row
//     * @param col the tile column
//     * @return the tile at (row, col)
//     */
//    SlidingTile getSlidingTile(int row, int col) {
//        return tiles[row][col];
//    }

//    /**
//     * Swap the tiles at (row1, col1) and (row2, col2)
//     *
//     * @param row1 the first tile row
//     * @param col1 the first tile col
//     * @param row2 the second tile row
//     * @param col2 the second tile col
//     */
//    public void swapSlidingTiles(int row1, int col1, int row2, int col2) {
//
//        SlidingTile[][] holdTile = new SlidingTile[1][2];
//        holdTile[0][0] = getSlidingTile(row1, col1);
//        holdTile[0][1] = getSlidingTile(row2, col2);
//        this.tiles[row2][col2] = holdTile[0][0];
//        this.tiles[row1][col1] = holdTile[0][1];
//    }
//
//    /**
//     * Returns tilesList.
//     *
//     * @return list of tiles.
//     */
//    public List<SlidingTile> getTilesList(){
//        return this.tilesList;
//    }



}