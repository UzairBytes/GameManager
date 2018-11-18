package Checkers;

import fall2018.csc2017.slidingtiles.Board;

public class CheckersBoard extends Board {

    private CheckersTile[][] tiles;

    private boolean redsTurn;

    CheckersBoard(CheckersTile[][] tiles, int size, boolean redsTurn) {
        super();
        redsTurn = redsTurn;
        tiles = tiles;
        numCols = size;
        numRows = size;
    }


    private void setCanTakePieces(){
        for (int row = 0; row < numRows; row++){
            for (int col = 0; col < numCols; col++){
                CheckersTile curtile = tiles[row][col];
                String id = curtile.getId();
                if(id.equals("red_pawn")){
                    curtile.setCanTakePiece(redPawnCanTake(row, col));
                }

                if(id.equals("red_king")){
                    curtile.setCanTakePiece(redKingCanTake(row, col));
                }

                if(id.equals("white_pawn")){
                    curtile.setCanTakePiece(whitePawnCanTake(row, col));
                }
                if(id.equals("white_king")){
                    curtile.setCanTakePiece(whiteKingCanTake(row, col));
                }
            }
        }
    }

    private boolean redPawnCanTake(int row, int col){
        boolean canTake = false;
        if (emptyTopRight(row, col)){
            canTake = canTake || tiles[row - 1][col - 1].getId().equals("white_pawn") || tiles[row - 1][col - 1].getId().equals("white_king");
        }
        if (emptyTopLeft(row, col)){
            canTake = canTake || tiles[row - 1][col + 1].getId().equals("white_pawn") || tiles[row - 1][col + 1].getId().equals("white_king");
        }
        return canTake;
    }

    private boolean whitePawnCanTake(int row, int col){
        boolean canTake = false;
        if (emptyBottomRight(row, col)){
            canTake = canTake || tiles[row + 1][col - 1].getId().equals("red_pawn") || tiles[row + 1][col - 1].getId().equals("red_king");
        }
        if (emptyBottomLeft(row, col)){
            canTake = canTake || tiles[row + 1][col + 1].getId().equals("red_pawn") || tiles[row + 1][col + 1].getId().equals("red_king");
        }
        return canTake;
    }

    private boolean redKingCanTake(int row, int col){
        boolean canTake = redPawnCanTake(row, col);
        if (emptyBottomRight(row, col)){
            canTake = canTake || tiles[row + 1][col - 1].getId().equals("white_pawn") || tiles[row + 1][col - 1].getId().equals("white_king");
        }
        if (emptyBottomLeft(row, col)){
            canTake = canTake || tiles[row + 1][col + 1].getId().equals("white_pawn") || tiles[row + 1][col + 1].getId().equals("white_king");
        }
        return canTake;
    }

    private boolean whiteKingCanTake(int row, int col){
        boolean canTake = whitePawnCanTake(row, col);
        if (emptyTopRight(row, col)){
            canTake = canTake || tiles[row - 1][col - 1].getId().equals("red_pawn") || tiles[row - 1][col - 1].getId().equals("red_king");
        }
        if (emptyTopLeft(row, col)){
            canTake = canTake || tiles[row - 1][col + 1].getId().equals("red_pawn") || tiles[row - 1][col + 1].getId().equals("red_king");
        }
        return canTake;
    }

    private boolean emptyTopLeft(int row, int col){
        if (col < 2 || row < 2) {
            return false;
        }
        return tiles[row - 2][col - 2].getId().equals("empty_white_tile");
    }

    private boolean emptyTopRight(int row, int col){
        if (col > 5 || row < 2) {
            return false;
        }
        return tiles[row - 2][col + 2].getId().equals("empty_white_tile");
    }

    private boolean emptyBottomLeft(int row, int col){
        if (col < 2 || row > 5) {
            return false;
        }
        return tiles[row + 2][col - 2].getId().equals("empty_white_tile");
    }

    private boolean emptyBottomRight(int row, int col){
        if (col > 5 || row > 5) {
            return false;
        }
        return tiles[row + 2][col + 2].getId().equals("empty_white_tile");
    }

    /**
     * Highlight Tile is if the selected tile belongs to the right player
     * @param position position of the tile
     */
    void selectTile(int position){
        int row = position / numRows;
        int column = position % numCols;
        CheckersTile selectedTile = tiles[row][column];

        if (redsTurn && selectedTile.getId().contains("red")){
            selectedTile.highlight();
        }
    }

    /**
     * Swap the tiles at (row1, col1) and (row2, col2). Remove any jumped piece and maybe make king
     *
     * @param row1 row of the initial position
     * @param col1 column of the initial position
     * @param row2 row of the end position
     * @param col2 column of the end position
     */
    protected void swapTiles(int row1, int col1, int row2, int col2) {
        super.swapTiles(row1, col1, row2, col2);
        //jumps tile to the top left
        if (row1-row2==-2 && col1-col2==2){
            tiles[row1+1][col1-1].changeTile(CheckersTile.emptyWhiteTile);

        }

    }

    /**
     * Return a tile at the requested position
     * @param row
     * @param col
     * @return the tile at row, col
     */
    CheckersTile getTile (int row, int col){return tiles[row][col];}
}
