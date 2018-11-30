package Checkers;

import java.util.Iterator;

import fall2018.csc2017.CoreClasses.Board;

public class CheckersBoard extends Board {//implements Iterable<CheckersTile>{


    private CheckersTile highLightedTile;

    private int[] highLightedTilePosition = new int[2];

    private int size;


    /**
     * @return an iterator for the board which iterates over every tile.
     */
//    @Override
//    @NonNull
//    public Iterator<CheckersTile> iterator(){
//        return new CheckersTileIterator();
//    }

    /**
     * An iterator class that iterates through every tile on the board,
     * including the back tiles where no piece can land
     */
    class CheckersTileIterator implements Iterator<CheckersTile> {
        /**
         * The column of the next Tile.
         */
        private int nextColumn = 0;
        /**
         * The row of the next Tile.
         */
        private int nextRow = 0;

        /**
         * @return the next checkers tile on the board
         */
        @Override
        public CheckersTile next() {
            CheckersTile nextTile = getCheckersTile(nextRow,nextColumn);
            nextColumn++;
            if (nextColumn >= tiles[0].length) {
                nextColumn = 0;
                nextRow++;
            }
            return nextTile;
        }

        /**
         * @return true if and only if there is a next tile on the board
         */
        @Override
        public boolean hasNext() {
            return nextRow < tiles.length;
        }
    }

    CheckersBoard(CheckersTile[][] tiles, int size) {
        super();
        this.tiles = tiles;
        this.size = size;
        setNumCols(size);
        setNumRows(size);
    }


    /**
     * Swap the tiles at (row1, col1) and (row2, col2). Remove any jumped piece and maybe make king
     *
     * @param row1 row of the initial position
     * @param col1 column of the initial position
     * @param row2 row of the end position
     * @param col2 column of the end position
     */
    public void swapTiles(int row1, int col1, int row2, int col2) {
        super.swapTiles(row1, col1, row2, col2);
        if (Math.abs(row1 - row2) == 2) {
            getCheckersTile((row1 + row2) / 2,(col1 + col2) / 2).changeTile(CheckersTile.EMPTY_WHITE_TILE);
        }

        maybeMakeKing(row2, col2);
        getCheckersTile(row2, col2).dehighlight();
        highLightedTile = null;
        highLightedTilePosition = new int[2];
    }

    void setHighLightedTile(int row, int col) {
        if (highLightedTile != null) {
            highLightedTile.dehighlight();
        }
        CheckersTile tile = getCheckersTile(row, col);
        tile.highlight();
        highLightedTile = tile;
        highLightedTilePosition[0] = row;
        highLightedTilePosition[1] = col;
    }

    CheckersTile getHighLightedTile() {
        return highLightedTile;
    }

    int[] getHighLightedTilePosition() {
        return highLightedTilePosition;
    }

    /**
     * Return a tile at the requested position
     *
     * @param row row of the requested tile
     * @param col column of the requested tile
     * @return the tile at row, col
     */
    CheckersTile getCheckersTile(int row, int col) {
        return (CheckersTile) tiles[row][col];
    }

    /**
     * Makes the piece a king if appropriate
     *
     * @param row row of the tile
     * @param col column of the tile
     */
    private void maybeMakeKing(int row, int col) {
        if (getCheckersTile(row, col).getCheckersId().contains(CheckersTile.RED) && row == 0) {
            getCheckersTile(row, col).changeTile(CheckersTile.RED_KING);
        } else if (getCheckersTile(row, col).getCheckersId().contains(CheckersTile.WHITE) && row == getNumRows() -1) {
            getCheckersTile(row, col).changeTile(CheckersTile.WHITE_KING);
        }
    }

    /**
     * Returns a deep-copy of this Board.
     */
    public CheckersBoard createDeepCopy() {
        CheckersTile[][] copyTile = new CheckersTile[getNumRows()][getNumCols()];
        for (int row = 0; row != getNumRows(); row++) {
            for (int col = 0; col != getNumCols(); col++) {
                copyTile[row][col] = new CheckersTile(getCheckersTile(row, col).getCheckersId());
            }
        }
        CheckersBoard copiedBoard = new CheckersBoard(copyTile, this.size);
        copiedBoard.tiles = copyTile;
        copiedBoard.highLightedTilePosition = highLightedTilePosition;
        copiedBoard.highLightedTile = highLightedTile;
        return copiedBoard;
    }
}
