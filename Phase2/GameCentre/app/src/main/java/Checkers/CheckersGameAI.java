package Checkers;

import java.util.ArrayList;

/**
 * A class which lays out the basic requirements of all GameAIs
 */
abstract class CheckersGameAI {
    /**
     * This is the default move when the active player has no legal move
     */
    static final int[][] NO_LEGAL_MOVE = {{-1,-1},{-1,-1}};

    /**
     * Get a move based on the specifications of the AI implementing this
     * @return the computer chosen move
     */
    abstract int[][] getMove(CheckersBoardManager checkersBoardManager);

    /**
     * Return a complete list of tiles the active player can select
     *
     * @param checkersBoardManager the boardManager for the current game
     * @return ArrayList of integers representing the position of selectable tiles
     */
    ArrayList<int[]> getAllSelectableTiles(CheckersBoardManager checkersBoardManager){
        ArrayList<int[]> output = new ArrayList<>();
        CheckersBoard board = checkersBoardManager.board;
        int checkedPieces = 0;
        int allAPlayersPieces = (board.getNumCols() * board.getNumRows() / 4) - (board.getNumCols() / 2);
        for (int row = 0; row < board.getNumRows(); row++) {
            int start = 0;
            if (row % 2 == 0) {
                start = 1;
            }
            for (int column = start; column < board.getNumCols(); ) {
                if (checkersBoardManager.isValidSelect(row * board.getNumRows() + column)) {
                    int[] locationToAdd = {row,column};
                    output.add(locationToAdd);
                    if (checkedPieces == allAPlayersPieces) {
                        return output;
                    }
                    column += 2;
                }
            }
        }
        return output;
    }
}
