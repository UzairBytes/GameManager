package Checkers;

import fall2018.csc2017.slidingtiles.Board;

public class CheckersBoard extends Board {

    private CheckersTile[][] tiles;

    private boolean redsTurn;

    private CheckersTile highLightedTile;

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

    private int[][] redPawnCanTake(int row, int col){
        int[][] canTakePiece = new int[4][2];
        if (emptyTopRight(row, col) && tiles[row - 1][col + 1].getId().contains("white")){
            canTakePiece[0][0] = row - 1;
            canTakePiece[0][1] = col + 1;
        }
        if (emptyTopLeft(row, col) && tiles[row - 1][col - 1].getId().contains("white")){
            canTakePiece[1][0] = row - 1;
            canTakePiece[1][1] = col - 1;
        }
        return canTakePiece;
    }

    private int[][] whitePawnCanTake(int row, int col){
        int[][] canTakePiece = new int[4][2];
        if (emptyBottomRight(row, col)&& tiles[row + 1][col + 1].getId().contains("red")){
            canTakePiece[0][0] = row + 1;
            canTakePiece[0][1] = col + 1;
        }
        if (emptyBottomLeft(row, col) && tiles[row + 1][col - 1].getId().contains("red")){
            canTakePiece[1][0] = row + 1;
            canTakePiece[1][1] = col - 1;
        }
        return canTakePiece;
    }

    private int[][] redKingCanTake(int row, int col){
        int[][] canTakePiece = redPawnCanTake(row, col);
        if (emptyBottomRight(row, col) && tiles[row + 1][col + 1].getId().contains("white")){
            canTakePiece[2][0] = row + 1;
            canTakePiece[2][1] = col + 1;
        }
        if (emptyBottomLeft(row, col) && tiles[row + 1][col - 1].getId().contains("white")){
            canTakePiece[3][0] = row + 1;
            canTakePiece[3][1] = col - 1;
        }
        return canTakePiece;
    }

    private int[][] whiteKingCanTake(int row, int col){
        int[][] canTakePiece = whitePawnCanTake(row, col);
        if (emptyTopRight(row, col) && tiles[row - 1][col + 1].getId().contains("red")){
            canTakePiece[2][0] = row - 1;
            canTakePiece[2][1] = col + 1;
        }
        if (emptyTopLeft(row, col) && tiles[row - 1][col - 1].getId().contains("red")){
            canTakePiece[3][0] = row - 1;
            canTakePiece[3][1] = col - 1;
        }
        return canTakePiece;
    }

    private boolean emptyTopLeft(int row, int col){
        if (col < 2 || row < 2) {
            return false;
        }
        return tiles[row - 2][col - 2].getId().equals(CheckersTile.EMPTYWHITETILE);
    }

    private boolean emptyTopRight(int row, int col){
        if (col > 5 || row < 2) {
            return false;
        }
        return tiles[row - 2][col + 2].getId().equals(CheckersTile.EMPTYWHITETILE);
    }

    private boolean emptyBottomLeft(int row, int col){
        if (col < 2 || row > 5) {
            return false;
        }
        return tiles[row + 2][col - 2].getId().equals(CheckersTile.EMPTYWHITETILE);
    }

    private boolean emptyBottomRight(int row, int col){
        if (col > 5 || row > 5) {
            return false;
        }
        return tiles[row + 2][col + 2].getId().equals(CheckersTile.EMPTYWHITETILE);
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
        //check if jumps tile to the top left
        if (row2-row1==-2 && col2-col1==-2){
            tiles[row1-1][col1-1].changeTile(CheckersTile.EMPTYWHITETILE);
        }
        //check if jumps tile to the top right
        else if (row2-row1==-2 && col2-col1==2){
            tiles[row1-1][col1+1].changeTile(CheckersTile.EMPTYWHITETILE);

        }
        //check if jumps tile to the bottom left
        else if (row2 - row1==2 && col2-col1 ==-2){
            tiles[row1+1][col1+1].changeTile(CheckersTile.EMPTYWHITETILE);

        }
        //check if jumpts tile to the bottom right
        else if (row2-row1==2 && col2-col1==2){
            tiles[row1+1][col1+1].changeTile(CheckersTile.EMPTYWHITETILE);
        }

        maybeMakeKing(row2,col2);
        highLightedTile.dehighlight();
        highLightedTile = null;
        }

        void setHighLightedTile (CheckersTile tile){
            tile.highlight();
            highLightedTile = tile;
        }

        CheckersTile getHighLightedTile (){
            return highLightedTile;
        }

    /**
     * Return a tile at the requested position
     * @param row row of the requested tile
     * @param col column of the requested tile
     * @return the tile at row, col
     */
    CheckersTile getTile(int row, int col){return tiles[row][col];}

    /**
     * Makes the piece a king if appropriate
     * @param row row of the tile
     * @param col column of the tile
     */
    private void maybeMakeKing(int row, int col){
        if (tiles[row][col].getId().contains("white") && row == numRows -1){
            tiles[row][col].changeTile(CheckersTile.REDKING);
        }
        else if (tiles[row][col].getId().contains("white") && row == 0){
            tiles[row][col].changeTile(CheckersTile.WHITEKING);
        }
    }
}
