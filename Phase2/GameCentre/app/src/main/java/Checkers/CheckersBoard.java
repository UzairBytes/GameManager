package Checkers;

import java.util.Iterator;

import fall2018.csc2017.slidingtiles.Board;

public class CheckersBoard extends Board {

    private CheckersTile[][] tiles;

    CheckersBoard() {
        super();
    }

    private void setCanTakePieces(){
        for (int row = 0; row < 8; row++){
            for (int col = 0; col < 8; col++){
                CheckersTile curtile = tiles[row][col];
                int id = curtile.getId();
                if(id == 2 ){
                    curtile.setCanTakePiece(redPawnCanTake(row, col));
                }

                if(id == 3 ){
                    curtile.setCanTakePiece(redKingCanTake(row, col));
                }

                if(id == 4 ){
                    curtile.setCanTakePiece(whitePawnCanTake(row, col));
                }
                if(id == 5 ){
                    curtile.setCanTakePiece(whiteKingCanTake(row, col));
                }
            }
        }
    }

    private boolean redPawnCanTake(int row, int col){
        boolean canTake = false;
        if (emptyTopRight(row, col)){
            canTake = canTake || tiles[row - 1][col - 1].getId() == 4 || tiles[row - 1][col - 1].getId() == 5;
        }
        if (emptyTopLeft(row, col)){
            canTake = canTake || tiles[row - 1][col + 1].getId() == 4 || tiles[row - 1][col + 1].getId() == 5;
        }
        return canTake;
    }

    private boolean whitePawnCanTake(int row, int col){
        boolean canTake = false;
        if (emptyBottomRight(row, col)){
            canTake = canTake || tiles[row + 1][col - 1].getId() == 2 || tiles[row + 1][col - 1].getId() == 3;
        }
        if (emptyBottomLeft(row, col)){
            canTake = canTake || tiles[row + 1][col + 1].getId() == 2 || tiles[row + 1][col + 1].getId() == 3;
        }
        return canTake;
    }

    private boolean redKingCanTake(int row, int col){
        boolean canTake = redPawnCanTake(row, col);
        if (emptyBottomRight(row, col)){
            canTake = canTake || tiles[row + 1][col - 1].getId() == 4 || tiles[row + 1][col - 1].getId() == 5;
        }
        if (emptyBottomLeft(row, col)){
            canTake = canTake || tiles[row + 1][col + 1].getId() == 4 || tiles[row + 1][col + 1].getId() == 5;
        }
        return canTake;
    }

    private boolean whiteKingCanTake(int row, int col){
        boolean canTake = whitePawnCanTake(row, col);
        if (emptyTopRight(row, col)){
            canTake = canTake || tiles[row - 1][col - 1].getId() == 2 || tiles[row - 1][col - 1].getId() == 3;
        }
        if (emptyTopLeft(row, col)){
            canTake = canTake || tiles[row - 1][col + 1].getId() == 2 || tiles[row - 1][col + 1].getId() == 3;
        }
        return canTake;
    }

    private boolean emptyTopLeft(int row, int col){
        if (col < 2 || row < 2) {
            return false;
        }
        return tiles[row - 2][col - 2].getId() == 1;
    }

    private boolean emptyTopRight(int row, int col){
        if (col > 5 || row < 2) {
            return false;
        }
        return tiles[row - 2][col + 2].getId() == 1;
    }

    private boolean emptyBottomLeft(int row, int col){
        if (col < 2 || row > 5) {
            return false;
        }
        return tiles[row + 2][col - 2].getId() == 1;
    }

    private boolean emptyBottomRight(int row, int col){
        if (col > 5 || row > 5) {
            return false;
        }
        return tiles[row + 2][col + 2].getId() == 1;
    }
}
