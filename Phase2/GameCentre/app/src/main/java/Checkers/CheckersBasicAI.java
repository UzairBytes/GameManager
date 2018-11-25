package Checkers;

import fall2018.csc2017.CoreClasses.GameAI;

/**
 * This class exists to provide sensible moves for a computer playing Checkers
 * <p>
 * Use CheckersBasicAI.getMove(checkersBoardManager) to get a move
 * Every other method in the class is just a helper method for getMove
 */
public abstract class CheckersBasicAI extends GameAI {
    /**
     * Return a move that takes a piece, if possible.
     * Otherwise, return a move that avoids giving the opponent the chance to take a piece if possible.
     * Otherwise, return a random move
     *
     * @param checkersBoardManager the boardManager for the current game
     * @return array of four ints representing [row of selected tile, column of selected, row of target, column of target]
     */
    static int[][] getMove(CheckersBoardManager checkersBoardManager) {
        int[][] move = getTakeMove(checkersBoardManager);
        if (move == NO_LEGAL_MOVE) {
            move = getSafeMove(checkersBoardManager);
        }
        if (move == NO_LEGAL_MOVE) {
            CheckersRandomAI.getMove(checkersBoardManager);
        }
        return move;
    }

    /**
     * Return a move that takes a piece if possible, and otherwise returns NO_LEGAL_MOVE
     * @param checkersBoardManager boardManager the current game
     * @return a move if taking a piece is possible. Otherwise, NO_LEGAL_MOVE
     */
    private static int[][] getTakeMove(CheckersBoardManager checkersBoardManager){
        int[][] move = NO_LEGAL_MOVE;
        //TODO finish this once board iterator is settled

        return move;
    }

    /**
     * Return a move that avoids giving opponent a chance to take a piece,
     * and otherwise returns NO_LEGAL_MOVE
     * @param checkersBoardManager boardManager the current game
     * @return a move if not letting opponent take piece is possible. Otherwise, NO_LEGAL_MOVE
     */
    private static int[][] getSafeMove(CheckersBoardManager checkersBoardManager){
        int[][] move = NO_LEGAL_MOVE;
        //TODO finish this once board iterator is settled
        return move;
    }


}

