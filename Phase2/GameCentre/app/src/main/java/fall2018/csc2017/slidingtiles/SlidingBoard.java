package fall2018.csc2017.slidingtiles;

import android.support.annotation.NonNull;

import java.util.Iterator;
import java.util.List;

/**
 * The sliding tiles board.
 */
class SlidingBoard extends Board {

    /**
     * The tiles on the board in row-major order.
     */
    private SlidingTile[][] tiles;


    /**
     * Stores the list of SlidingTile Iterator of this Board.
     */
    @NonNull
    @Override
    public Iterator<Tile> iterator() {
        return new SlidingTileIterator();
    }

    /**
     * The tile iterator, iterates through the tiles on the board.
     */
    class SlidingTileIterator implements Iterator<Tile> {
        /**
         * The column of the next Tile.
         */
        private int nextColumn = 0;
        /**
         * The row of the next Tile.
         */
        private int nextRow = 0;

        @Override
        public Tile next() {
            Tile nextTile = tiles[nextRow][nextColumn];
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
        super();
        numRows = rows;
        numCols = cols;
        this.tiles = new SlidingTile[numRows][numCols];
        Iterator<SlidingTile> iter = tiles.iterator();

        for (int row = 0; row != numRows; row++) {
            for (int col = 0; col != numCols; col++) {
                this.tiles[row][col] = iter.next();
            }
        }
    }

}