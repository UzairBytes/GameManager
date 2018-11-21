package Checkers;

import java.time.Instant;
import java.util.Stack;

import fall2018.csc2017.CoreClasses.BoardManager;
import phase1.AccountManager;

public class CheckersBoardManager extends BoardManager {

    /**
     * The board being managed.
     */
    protected CheckersBoard board;

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
            this.board = (CheckersBoard) gameFile.getGameStates().peek();
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
        this.board = new CheckersBoard(tiles, size, redsTurn);

        // Create a new GameFile, and initialize it with this shuffled board.
        CheckersGameFile gameFile = new CheckersGameFile(this.board, Instant.now().toString());

        // Add this new GameFile to the current active account's list of GameFiles.
        AccountManager.activeAccount.addGameFile(gameFile);
        this.gameFile = gameFile;
        this.gameStates = this.gameFile.getGameStates();
        this.numMoves = gameFile.numMoves;
        this.maxUndos = gameFile.maxUndos;
        save(this.board);
    }

    /**
     * Highlights Tile is if the selected tile belongs to the right player
     * @param position position of the tile
     */
    boolean isValidSelect (int position){
        int row = position / board.numRows;
        int col = position % board.numCols;
        CheckersTile selectedTile = board.getTile(row, col);
        String tileId = selectedTile.getCheckersId();
        if (redsTurn && tileId.contains("red") || tileId.contains("white")){
            board.setHighLightedTile(row, col);
            return true;
        }
        return false;
    }

    boolean isValidMove (int position){
        CheckersTile highLightedTile = board.getHighLightedTile();
        String highId = highLightedTile.getCheckersId();
        int highRow = board.getHighLightedTilePosition()[0];
        int highCol = board.getHighLightedTilePosition()[1];
        int row = position / board.numRows;
        int col = position % board.numCols;
        CheckersTile targetTile = board.getTile(row, col);
        if (!targetTile.getCheckersId().equals(CheckersTile.EMPTYWHITETILE)){
            return false;
        }
        int[][] canTakePiece = highLightedTile.isCanTakePiece();
        for (int i = 0; i < 4; i++){
            if (canTakePiece[i][0] == row && canTakePiece[i][1] == col){
                return true;
            }
        }
        if (col != highCol + 1 && col != highCol - 1){
            return false;
        }
        if ((highId.contains("red") || highId.contains("king")) && row == highRow - 1){
            return true;
        }
        else if ((highId.contains("white") || highId.contains("king")) && row == highRow + 1){
            return true;
        }
        return false;
    }

    boolean isRedsTurn(){return redsTurn;}
}
