package Checkers;


import java.util.ArrayList;
import java.util.Random;


import fall2018.csc2017.CoreClasses.GameAI;

/**
 * This class exists to provide random moves for a computer playing Checkers
 * <p>
 * Use CheckersRandomAI.getMove(checkersBoardManager) to get a random move
 */
abstract class CheckersRandomAI extends GameAI {

    /**
     * Return a complete list of all the moves a player can make
     *
     * @param checkersBoardManager the boardManager for the current game
     * @return ArrayList of values [[row1, column1],[row2, column2]],
     * where row1, column1 is the position of a selectable tile and row2, column2 is where it can go
     */
    private static ArrayList<int[][]> getAllPossibleMoves(CheckersBoardManager checkersBoardManager) {
        ArrayList<int[][]> output = new ArrayList<>();
        CheckersBoard board = checkersBoardManager.board;
        for (int row = 0; row < board.getNumRows(); row++) {
            int start = 0;
            if (row % 2 == 0) {
                start = 1;
            }
            for (int column = start; column < board.getNumCols(); ) {

                if (checkersBoardManager.isValidSelect(row * board.getNumRows() + column)) {
                    output.addAll(getAllPossibleMoves(checkersBoardManager, row, column));
                    column += 2;
                }
            }


        }
        return output;
    }

    /**
     * Choose and return a move based allPossibleMoves
     *
     * @param checkersBoardManager the boardManager for the current game
     * @return array of four ints representing [row of selected tile, column of selected, row of target, column of target]
     */
    static int[][] getMove(CheckersBoardManager checkersBoardManager) {
        ArrayList<int[][]> possibleMoves = getAllPossibleMoves(checkersBoardManager);
        Random random = new Random();
        return possibleMoves.get(random.nextInt(possibleMoves.size()));
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
