package Checkers;

import java.util.ArrayList;

import fall2018.csc2017.CoreClasses.GameAI;

abstract class CheckersRandomAI extends GameAI {

    static ArrayList<int[]> getAllPossibleMoves(CheckersBoardManager checkersBoardManager) {
        String activeColor;
        CheckersBoard board = checkersBoardManager.board;
        int size = board.getNumCols();
        if (checkersBoardManager.isRedsTurn()) {
            activeColor = "red";
        } else {
            activeColor = "white";
        }
        for (int row = 0; row < board.getNumRows(); row++) {
            int start = 0;
            if (row % 2 == 0) {
                start = 1;
            }
            for (int column = start; column < board.getNumCols(); ) {
                if (board.getTile(row, column).getId().contains(activeColor)) {
                    //TODO finish this
                    column += 2;
                }
            }



        }
        return new ArrayList<>();
    }

    /**
     * Choose and return a move based allPossibleMoves
     * @param checkersBoardManager the boardManager for the current game
     * @return array of four ints representing [row of selected tile, column of selected, row of target, column of target]
     */
    static int[] selectMove(CheckersBoardManager checkersBoardManager){
        return new int[4];
    }


}
