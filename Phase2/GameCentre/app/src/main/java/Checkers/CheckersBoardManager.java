package Checkers;

import java.time.Instant;
import java.util.Stack;

import fall2018.csc2017.CoreClasses.BoardManager;
import phase1.AccountManager;

public class CheckersBoardManager extends BoardManager {

    /**
     * The board being managed.
     */
    protected CheckersBoard Checkersboard;

    /**
     * The SlidingGameFile holding the data for this board.
     */
    private CheckersGameFile gameFile;

    /**
     * Holds a stack of CheckersBoards, with each Board representing a specific game state.
     */
    private Stack<CheckersBoard> gameStates;

    private boolean redsTurn;

    protected CheckersBoardManager(CheckersGameFile gameFile) {
        this.gameFile = gameFile;
        this.gameStates = gameFile.getGameStates();
        this.remainingUndos = gameFile.remainingUndos;
        this.remainingUndos = gameFile.maxUndos;
        this.numMoves = gameFile.numMoves;
        AccountManager.activeAccount.activeGameFile = gameFile;
        if (!gameFile.getGameStates().isEmpty()) {
            this.Checkersboard = (CheckersBoard) gameFile.getGameStates().peek();
        }
    }

    CheckersBoardManager(int size, boolean redsTurn) {
        super();
        CheckersTile[][] tiles = new CheckersTile[size][size];
        String id;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if ((row + col) % 2 == 0){
                    id = "empty_black_tile";
                }
                else if (row < (size/2 - 1) ){
                    id = "white_pawn";
                }
                else if (row > (size/2 + 1) ){
                    id = "red_pawn";
                }
                else{
                    id = "empty_white_tile";
                }
                tiles[row][col] = new CheckersTile(id);
            }
        }
        this.redsTurn = redsTurn;
        this.Checkersboard = new CheckersBoard(tiles, size, redsTurn);

        // Create a new GameFile, and initialize it with this shuffled board.
        CheckersGameFile gameFile = new CheckersGameFile(this.Checkersboard, Instant.now().toString());

        // Add this new GameFile to the current active account's list of GameFiles.
        AccountManager.activeAccount.addGameFile(gameFile);
        this.gameFile = gameFile;
        this.gameStates = this.gameFile.getGameStates();
        this.numMoves = gameFile.numMoves;
        this.maxUndos = gameFile.maxUndos;
        save(this.board);
    }

    boolean isValidSelect (int position){
        int row = position / Checkersboard.numRows;
        int col = position % Checkersboard.numCols;
        CheckersTile selectedTile = Checkersboard.getTile(row, col);
        String tileId = selectedTile.getId();
        if (redsTurn && tileId.contains("red") || tileId.contains("white")){
            selectedTile.highlight();
            return true;
        }
        return false;
    }
}
