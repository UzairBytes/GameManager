package Checkers;

import java.util.Iterator;

import fall2018.csc2017.CoreClasses.Board;

public class CheckersBoard extends Board {//implements Iterable<CheckersTile>{

    /**
     * A two-dimensional square matrix of CheckersTiles
     */
    CheckersTile[][] tiles;

    /**
     * Whether it's red's turn
     */
    private boolean redsTurn;

    /**
     * the highlighted (aka "selected") tile
     */
    private CheckersTile highLightedTile;

    /**
     * Location of the highlightedTile in the form [row, column]
     */
    private int[] highLightedTilePosition;

    /**
     * The side length of the square board
     */
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
            CheckersTile nextTile = tiles[nextRow][nextColumn];
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

    CheckersBoard(CheckersTile[][] tiles, int size, boolean redsTurn) {
        super();
        this.redsTurn = redsTurn;
        this.tiles = tiles;
        this.size = size;
        setNumCols(size);
        setNumRows(size);
    }

    /**
     * Set the array of locations where a piece can jump to to take a piece
     */
    void setCanTakePieces() {
        for (int row = 0; row < getNumRows(); row++) {
            for (int col = 0; col < getNumCols(); col++) {
                CheckersTile curtile = tiles[row][col];
                String id = curtile.getCheckersId();
                int[][] canTakePiece = new int[4][2];
                if (id.equals(CheckersTile.RED_PAWN)) {
                    curtile.setCanTakePiece(redPawnCanTake(row, col, canTakePiece));
                }

                if (id.equals("red_king")) {
                    curtile.setCanTakePiece(redKingCanTake(row, col, canTakePiece));
                }

                if (id.equals("white_pawn")) {
                    curtile.setCanTakePiece(whitePawnCanTake(row, col, canTakePiece));
                }
                if (id.equals("white_king")) {
                    curtile.setCanTakePiece(whiteKingCanTake(row, col, canTakePiece));
                }
            }
        }
    }

    private int[][] redPawnCanTake(int row, int col, int[][] canTakePiece) {
        if (emptyTopRight(row, col) && tiles[row - 1][col + 1].getCheckersId().contains("white")) {
            canTakePiece[0][0] = row - 2;
            canTakePiece[0][1] = col + 2;
        }
        if (emptyTopLeft(row, col) && tiles[row - 1][col - 1].getCheckersId().contains("white")) {
            canTakePiece[1][0] = row - 2;
            canTakePiece[1][1] = col - 2;
        }
        return canTakePiece;
    }

    private int[][] whitePawnCanTake(int row, int col, int[][] canTakePiece) {
        if (emptyBottomRight(row, col) && tiles[row + 1][col + 1].getCheckersId().contains("red")) {
            canTakePiece[2][0] = row + 2;
            canTakePiece[2][1] = col + 2;
        }
        if (emptyBottomLeft(row, col) && tiles[row + 1][col - 1].getCheckersId().contains("red")) {
            canTakePiece[3][0] = row + 2;
            canTakePiece[3][1] = col - 2;
        }
        return canTakePiece;
    }

    private int[][] redKingCanTake(int row, int col, int[][] canTakePiece) {
        if (emptyBottomRight(row, col) && tiles[row + 1][col + 1].getCheckersId().contains("white")) {
            canTakePiece[2][0] = row + 2;
            canTakePiece[2][1] = col + 2;
        }
        if (emptyBottomLeft(row, col) && tiles[row + 1][col - 1].getCheckersId().contains("white")) {
            canTakePiece[3][0] = row + 2;
            canTakePiece[3][1] = col - 2;
        }
        return canTakePiece;
    }

    private int[][] whiteKingCanTake(int row, int col, int[][] canTakePiece) {
        if (emptyTopRight(row, col) && tiles[row - 1][col + 1].getCheckersId().contains("red")) {
            canTakePiece[0][0] = row - 2;
            canTakePiece[0][1] = col + 2;
        }
        if (emptyTopLeft(row, col) && tiles[row - 1][col - 1].getCheckersId().contains("red")) {
            canTakePiece[1][0] = row - 2;
            canTakePiece[1][1] = col - 2;
        }
        return canTakePiece;
    }

    private boolean emptyTopLeft(int row, int col) {
        if (col < 2 || row < 2) {
            return false;
        }
        return tiles[row - 2][col - 2].getCheckersId().equals(CheckersTile.EMPTY_WHITE_TILE);
    }

    private boolean emptyTopRight(int row, int col) {
        if (col > 5 || row < 2) {
            return false;
        }
        return tiles[row - 2][col + 2].getCheckersId().equals(CheckersTile.EMPTY_WHITE_TILE);
    }

    private boolean emptyBottomLeft(int row, int col) {
        if (col < 2 || row > 5) {
            return false;
        }
        return tiles[row + 2][col - 2].getCheckersId().equals(CheckersTile.EMPTY_WHITE_TILE);
    }

    private boolean emptyBottomRight(int row, int col) {
        if (col > 5 || row > 5) {
            return false;
        }
        return tiles[row + 2][col + 2].getCheckersId().equals(CheckersTile.EMPTY_WHITE_TILE);
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
        //check if jumps tile to the top left
        if (row2 - row1 == -2 && col2 - col1 == -2) {
            tiles[row1 - 1][col1 - 1].changeTile(CheckersTile.EMPTY_WHITE_TILE);
        }
        //check if jumps tile to the top right
        else if (row2 - row1 == -2 && col2 - col1 == 2) {
            tiles[row1 - 1][col1 + 1].changeTile(CheckersTile.EMPTY_WHITE_TILE);

        }
        //check if jumps tile to the bottom left
        else if (row2 - row1 == 2 && col2 - col1 == -2) {
            tiles[row1 + 1][col1 + 1].changeTile(CheckersTile.EMPTY_WHITE_TILE);

        }
        //check if jumpts tile to the bottom right
        else if (row2 - row1 == 2 && col2 - col1 == 2) {
            tiles[row1 + 1][col1 + 1].changeTile(CheckersTile.EMPTY_WHITE_TILE);
        }

        maybeMakeKing(row2, col2);
        highLightedTile.dehighlight();
        highLightedTile = null;
        highLightedTilePosition = new int[2];
    }

    void setHighLightedTile(int row, int col) {
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
        return tiles[row][col];
    }

    /**
     * Makes the piece a king if appropriate
     *
     * @param row row of the tile
     * @param col column of the tile
     */
    private void maybeMakeKing(int row, int col) {
        if (tiles[row][col].getCheckersId().contains("white") && row == getNumRows() - 1) {
            tiles[row][col].changeTile(CheckersTile.RED_KING);
        } else if (tiles[row][col].getCheckersId().contains("white") && row == 0) {
            tiles[row][col].changeTile(CheckersTile.WHITE_KING);
        }
    }

    /**
     * Returns a deep-copy of this Board.
     */
    public CheckersBoard createDeepCopy() {
        CheckersTile[][] copyTile = new CheckersTile[getNumRows()][getNumCols()];
        for (int row = 0; row != getNumRows(); row++) {
            for (int col = 0; col != getNumCols(); col++) {
                copyTile[row][col] = new CheckersTile(this.tiles[row][col].getCheckersId());
            }
        }
        CheckersBoard copiedBoard = new CheckersBoard(copyTile, this.size, redsTurn);
        copiedBoard.tiles = copyTile;
        return copiedBoard;
    }
}
