package fall2018.csc2017.CoreClasses;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import Checkers.CheckersBoard;
import Checkers.CheckersTile;

/**
 * The sliding tiles board.
 */

public class Board implements Serializable {

    /**
     * The number of rows.
     */
    protected int numRows;

    /**
     * The number of rows.
     */
    protected int numCols;

    /**
     * The tiles on the board in row-major order.
     */
    public Tile[][] tiles;


    /**
     * Stores the list of Tile Iterator of this Board.
     */
    @NonNull
    public Iterator<Tile> iterator() {
        return new TileIterator();
    }

    /**
     * The tile iterator, iterates through the tiles on the board.
     */
    class TileIterator implements Iterator<Tile> {
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

    ///**
    // * A board constructor which must be extended
    // */
    //protected Board() {
    //}

    /**
     * A board constructor which may be reused
     */
    Board(Tile[][] listOfTiles, int rows, int columns) {
        numRows = rows;
        numCols = columns;
        for (int position = 0; position < numRows * numCols; position++) {
            int row = position / numRows;
            int col = position % numCols;
            tiles[row][col] = listOfTiles[row][col];
        }
    }

    /**
     * Returns a deep-copy of this Board.
     */
    Board createDeepCopy() {
        Tile[][] copyTile = new Tile[getNumRows()][getNumCols()];
        for (int row = 0; row != getNumRows(); row++) {
            for (int col = 0; col != getNumCols(); col++) {
                copyTile[row][col] = this.tiles[row][col];
            }
        }
        Board copiedBoard = new Board(copyTile, getNumRows(), getNumCols());
        copiedBoard.tiles = copyTile;
        return copiedBoard;
    }
//    /**
//     * Returns a deep-copy of this Board.
//     */
//    Board createDeepCopy() {
//        List<Tile> copiedTiles = new ArrayList<>();
//
//        Tile[][] copyTile = new Tile[numRows][numCols];
//        for (int row = 0; row != numRows; row++) {
//            for (int col = 0; col != numCols; col++) {
//                copyTile[row][col] = this.tiles[row][col];
//            }
//        }
//        Board copiedBoard = new Board(copiedTiles, numRows, numCols);
//        copiedBoard.tiles = copyTile;
//        return copiedBoard;
//    }


    /**
     * Return the number of tiles on the board.
     *
     * @return the number of tiles on the board
     */
    public int numTiles() {
        return numRows * numCols;
    }
    //    Iterator<Tile> iter = this.iterator();
    //    int cont = 0;
    //    while (iter.hasNext()) {
    //        iter.next();
    //        cont += 1;
    //    }
    //    return cont;

    //}

    /**
     * Return the tile at (row, col)
     *
     * @param row the tile row
     * @param col the tile column
     * @return the tile at (row, col)
     */
    public Tile getTile(int row, int col) {
        return tiles[row][col];
    }

    /**
     * Returns the string representation of board.
     *
     * @return string of Board.
     */
    @Override
    public String toString() {
        return "Board{" +
                "tiles=" + Arrays.toString(tiles) +
                '}';
    }

    /**
     * Returns the numebr of rows in a board.
     *
     * @return rows in board.
     */
    public int getNumRows(){return numRows;}

    /**
     * Sets number of rows in a board.
     *
     * @param newNumRows int
     */
    public void setNumRows(int newNumRows){ this.numRows = newNumRows;}

    /**
     * Returns the number of columns in a board.
     *
     * @return columns in board.
     */
    public int getNumCols(){return numCols;}

    /**
     * Sets number of columns in a board.
     *
     * @param newNumCols int
     */
    public void setNumCols(int newNumCols) { this.numCols = newNumCols;}

    /**
     * Swap the tiles at (row1, col1) and (row2, col2)
     *
     * @param row1 the first tile row
     * @param col1 the first tile col
     * @param row2 the second tile row
     * @param col2 the second tile col
     */
    public void swapTiles(int row1, int col1, int row2, int col2) {

        Tile[][] holdTile = new Tile[1][2];
        holdTile[0][0] = getTile(row1, col1);
        holdTile[0][1] = getTile(row2, col2);
        this.tiles[row2][col2] = holdTile[0][0];
        this.tiles[row1][col1] = holdTile[0][1];
    }

}