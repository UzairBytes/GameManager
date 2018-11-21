package Checkers;


import java.util.ArrayList;
import java.util.Collections;

import fall2018.csc2017.CoreClasses.GameAI;

/**
 * This class exists to provide random moves for a computer playing Checkers
 * <p>
 * Use CheckersRandomAI.getMove(checkersBoardManager) to get a random move
 */
abstract class CheckersRandomAI extends GameAI {

    /**
     * This is the default move when the active player has no legal move
     */
    private static final int[][] NOLEGALMOVE = {{-1,-1},{-1,-1}};

    /**
     * Choose and return a move based allPossibleSelectableTiles
     *
     * @param checkersBoardManager the boardManager for the current game
     * @return array of four ints representing [row of selected tile, column of selected, row of target, column of target]
     */
    static int[][] getMove(CheckersBoardManager checkersBoardManager) {
        ArrayList<Integer> selectableTiles = getAllSelectableTiles(checkersBoardManager);
        Collections.shuffle(selectableTiles);
        for (Integer tilePosition: selectableTiles){
            int row = tilePosition / checkersBoardManager.board.getNumRows();
            int column = tilePosition % checkersBoardManager.board.getNumCols();
            ArrayList<int[][]> possibleMoves = getAllPossibleMoves(checkersBoardManager,row, column);
            if (!possibleMoves.isEmpty()){
                Collections.shuffle(possibleMoves);
                return possibleMoves.get(0);
            }
        }
        return NOLEGALMOVE;
    }

    /**
     * Return a complete list of tiles the active player can select
     *
     * @param checkersBoardManager the boardManager for the current game
     * @return ArrayList of integers representing the position of selectable tiles
     */
    private static ArrayList<Integer> getAllSelectableTiles(CheckersBoardManager checkersBoardManager){
        ArrayList<Integer> output = new ArrayList<>();
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
                    output.add(row * board.getNumRows() + column);
                    if (checkedPieces == allAPlayersPieces) {
                        return output;
                    }
                    column += 2;
                }
            }
        }
        return output;
    }


    /**
     * Return a list of all the moves a specific piece can make
     *
     * @param checkersBoardManager boardManager for the current game
     * @param row                  row of the specific piece
     * @param column               column of the specific piece
     * @return ArrayList of values [[row1, column1],[row2, column2]],
     * where row1, column1 is the position of the specific piece and row2, column2 is where it can go
     */

    private static ArrayList<int[][]> getAllPossibleMoves(CheckersBoardManager checkersBoardManager, int row, int column) {
        ArrayList<int[][]> output = new ArrayList<>();
        CheckersBoard board = checkersBoardManager.board;
        String selectedTileId = board.getTile(row, column).getId();
        if (selectedTileId.contains("red") && selectedTileId.contains("pawn")) {
            output.addAll(getMovesUp(checkersBoardManager, row, column));
        } else if (selectedTileId.contains("white") && selectedTileId.contains("pawn")) {
            output.addAll(getMovesDown(checkersBoardManager, row, column));
        } else {
            output.addAll(getMovesUp(checkersBoardManager, row, column));
            output.addAll(getMovesDown(checkersBoardManager, row, column));
        }

        return output;
    }


    /**
     * Return the list of possible upward moves of the highlighted tile
     *
     * @param checkersBoardManager the current boardManager
     * @param row                  row of the highlighted tile
     * @param column               column of the highlighted tile
     * @return ArrayList of values [[row1, column1],[row2, column2]],
     * where row1, column1 is the position of the specific piece and row2, column2 is where it can go
     */
    private static ArrayList<int[][]> getMovesUp(CheckersBoardManager checkersBoardManager, int row, int column) {
        ArrayList<int[][]> output = new ArrayList<>();
        CheckersBoard board = checkersBoardManager.board;
        int[][] movesUp = {{row - 2, column - 2}, {row - 1, column - 1}, {row - 1, column + 1}, {row - 2, column + 2}};
        for (int i = 0; i < 4; i++) {
            if (checkersBoardManager.isValidMove(movesUp[i][0] * board.getNumRows() + movesUp[i][1])) {
                int[][] possibleMove = {{row, column}, movesUp[i]};
                output.add(possibleMove);
            }
        }
        return output;
    }

    /**
     * Return the list of possible downward moves of the highlighted tile
     *
     * @param checkersBoardManager the current boardManager
     * @param row                  row of the highlighted tile
     * @param column               column of the highlighted tile
     * @return ArrayList of values [[row1, column1],[row2, column2]],
     * where row1, column1 is the position of the specific piece and row2, column2 is where it can go
     */

    private static ArrayList<int[][]> getMovesDown(CheckersBoardManager checkersBoardManager, int row, int column) {
        ArrayList<int[][]> output = new ArrayList<>();
        CheckersBoard board = checkersBoardManager.board;
        int[][] movesDown = {{row + 2, column - 2}, {row + 1, column - 1}, {row + 1, column + 1}, {row + 2, column + 2}};
        for (int i = 0; i < 4; i++) {
            if (checkersBoardManager.isValidMove(movesDown[i][0] * board.getNumRows() + movesDown[i][1])) {
                int[][] possibleMove = {{row, column}, movesDown[i]};
                output.add(possibleMove);
            }
        }
        return output;
    }
}
