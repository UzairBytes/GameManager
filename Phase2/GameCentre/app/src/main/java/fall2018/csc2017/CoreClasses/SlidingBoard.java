package fall2018.csc2017.CoreClasses;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The sliding tiles board.
 */
public class SlidingBoard extends Board implements Iterable<SlidingTile>{

    /**
     * The tiles on the board in row-major order.
     */
    private SlidingTile[][] tiles;

    /**
     * Stores the list of SlidingTile objects when this Board is initialized.
     */
    private List<SlidingTile> tilesList;


    /**
     * Stores the list of SlidingTile Iterator of this Board.
     */
    @NonNull
    @Override
    public Iterator<SlidingTile> iterator() {
        return new SlidingTileIterator();
    }

    /**
     * The tile iterator, iterates through the tiles on the board.
     */
    class SlidingTileIterator implements Iterator<SlidingTile> {
        /**
         * The column of the next Tile.
         */
        private int nextColumn = 0;
        /**
         * The row of the next Tile.
         */
        private int nextRow = 0;

        @Override
        public SlidingTile next() {
            SlidingTile nextTile = tiles[nextRow][nextColumn];
            nextColumn += 1;
            if (nextColumn == numCols) {
                nextColumn = 0;
                nextRow += 1;
            }
            return nextTile;
        }

        @Override
        public boolean hasNext() {
            return (nextRow < numRows);
        }
    }

    /**
     * A new board of tiles in row-major order.
     * Precondition: len(tiles) == NUM_ROWS * NUM_COLS
     *
     * @param tiles the tiles for the board
     */

    public SlidingBoard(List<SlidingTile> tiles, int rows, int cols) {
        numRows = rows;
        numCols = cols;
        this.tiles = new SlidingTile[numRows][numCols];
        this.tilesList = tiles;
        Iterator<SlidingTile> iter = tiles.iterator();

        for (int row = 0; row != numRows; row++) {
            for (int col = 0; col != numCols; col++) {
                this.tiles[row][col] = iter.next();
            }
        }
    }

    /**
     * Returns a deep-copy of this Board.
     */
    @Override
    SlidingBoard createDeepCopy() {
        List<SlidingTile> copiedTiles = new ArrayList<>(this.tilesList);

        SlidingTile[][] copyTile = new SlidingTile[numRows][numCols];
        for (int row = 0; row != numRows; row++) {
            for (int col = 0; col != numCols; col++) {
                copyTile[row][col] = this.tiles[row][col];
            }
        }
        SlidingBoard copiedBoard = new SlidingBoard(copiedTiles, numRows, numCols);
        copiedBoard.tiles = copyTile;
        return copiedBoard;
    }

    /**
     * Return the tile at (row, col)
     *
     * @param row the tile row
     * @param col the tile column
     * @return the tile at (row, col)
     */
    SlidingTile getSlidingTile(int row, int col) {
        return tiles[row][col];
    }


}