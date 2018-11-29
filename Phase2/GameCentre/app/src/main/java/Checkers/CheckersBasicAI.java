//package Checkers;
//
//import java.util.ArrayList;
//
///**
// * This class exists to provide sensible moves for a computer playing Checkers
// * <p>
// * Use getMove(checkersBoardManager) to get a move
// * Every other method in the class is just a helper method for getMove
// */
//class CheckersBasicAI extends CheckersGameAI {
//    /**
//     * Return a move that takes a piece, if possible.
//     * Otherwise, return a move that blocks the opponent from taking a piece if possible.
//     * Otherwise, return a move that avoids giving the opponent the chance to take a piece if possible.
//     * Otherwise, return a random move.
//     *
//     * @param checkersBoardManager the boardManager for the current game
//     * @return array of four ints representing [row of selected tile, column of selected, row of target, column of target]
//     */
//    int[][] getMove(CheckersBoardManager checkersBoardManager) {
//        ArrayList<int[]> selectableTiles = getAllSelectableTiles(checkersBoardManager);
//        int[][] move = NO_LEGAL_MOVE;
//        for (int[] selectableTile: selectableTiles){
//            move = getTakeMove(checkersBoardManager, selectableTile[0], selectableTile[1]);
//            if (move != NO_LEGAL_MOVE){return move;}
//        }
//        for (int[] selectableTile: selectableTiles){
//            move = getBlockMove(checkersBoardManager, selectableTile[0], selectableTile[1]);
//            if (move != NO_LEGAL_MOVE){return move;}
//        }
//        for (int[] selectableTile: selectableTiles){
//            move = getSafeMove(checkersBoardManager, selectableTile[0], selectableTile[1]);
//            if (move != NO_LEGAL_MOVE){return move;}
//        }
//        if (move == NO_LEGAL_MOVE) {
//            new CheckersRandomAI().getMove(checkersBoardManager);
//        }
//        return move;
//    }
//
//
//
//    /**
//     * Return a move that blocks an opponent from taking a piece if possible,
//     * and otherwise returns NO_LEGAL_MOVE
//     * @param row row number of the tile under consideration
//     * @param column column number of the tile under consideration
//     * @param checkersBoardManager boardManager the current game
//     * @return a move if not letting opponent take piece is possible. Otherwise, NO_LEGAL_MOVE
//     */
//    private int[][] getBlockMove(CheckersBoardManager checkersBoardManager, int row, int column){
//        int[][] move = NO_LEGAL_MOVE;
//        //TODO finish this once board iterator is settled
//        return move;
//    }
//
//
//    /**
//     * Return a move that avoids giving opponent a chance to take a piece,
//     * and otherwise returns NO_LEGAL_MOVE
//     * @param row row number of the tile under consideration
//     * @param column column number of the tile under consideration
//     * @param checkersBoardManager boardManager the current game
//     * @return a move if not letting opponent take piece is possible. Otherwise, NO_LEGAL_MOVE
//     */
//    private int[][] getSafeMove(CheckersBoardManager checkersBoardManager, int row, int column) {
//        int[][] move = NO_LEGAL_MOVE;
//        //TODO finish this once board iterator is settled
//        return move;
//    }
//
//
//
//}
//
