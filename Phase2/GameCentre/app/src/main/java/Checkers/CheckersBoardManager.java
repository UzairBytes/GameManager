package Checkers;

import java.time.Instant;
import java.util.Stack;

import fall2018.csc2017.CoreClasses.Board;
import fall2018.csc2017.CoreClasses.BoardManager;
import phase1.AccountManager;

public class CheckersBoardManager extends BoardManager {

    /**
     * The board being managed.
     */
    protected static CheckersBoard board;

    /**
     * The SlidingGameFile holding the data for this board.
     */
    private CheckersGameFile gameFile;

    /**
     * Color of the player who won the game.
     */
    private String winner;

    private boolean redsTurn;

    protected CheckersBoardManager(CheckersGameFile gameFile) {
        this.gameFile = gameFile;
        this.gameStates = gameFile.getGameStates();
        this.remainingUndos = gameFile.remainingUndos;
        this.remainingUndos = gameFile.maxUndos;
        this.numMoves = gameFile.numMoves;
        AccountManager.activeAccount.setActiveGameFile(gameFile);
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
        int row = position / board.getNumRows();
        int col = position % board.getNumCols();
        CheckersTile selectedTile = board.getCheckersTile(row, col);
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
        int row = position / board.getNumRows();
        int col = position % board.getNumCols();
        CheckersTile targetTile = board.getCheckersTile(row, col);
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

    void touchMove(int position){
//        CheckersBoard newBoard = board.createDeepCopy();
//        this.board = newBoard;
//        CheckersTile highLightedTile = board.getHighLightedTile();
//        String highId = highLightedTile.getCheckersId();
//        int highRow = board.getHighLightedTilePosition()[0];
//        int highCol = board.getHighLightedTilePosition()[1];
//        int row = position / board.getNumRows();
//        int col = position % board.getNumCols();
//        board.swapTiles(highRow, highCol, row, col);
//        save(newBoard);
//        setChanged();
//        notifyObservers();
    }

    //should use an iterator
    boolean gameComplete(){
        boolean redWins = true;
        boolean whiteWins = true;
        for (CheckersTile[] row: board.tiles){
            for (CheckersTile tile: row){
                redWins = redWins && !tile.getCheckersId().contains("white");
                whiteWins = whiteWins && !tile.getCheckersId().contains("red");
                if (!redWins && !whiteWins){
                    return false;
                }
            }
        }
        if (redWins){
            winner = "Red";
        }
        else {
            winner = "White";
        }
        return true;
    }

    String getWinner() {
        return winner;
    }

    boolean isRedsTurn(){return redsTurn;}

    static CheckersBoard getBoard() {
        return board;
    }

    /**
     * Saves a new state of board to game.
     *
     * @param board a board
     */
    @SuppressWarnings("unchecked")
    public void save(CheckersBoard board) {
        CheckersGameFile newGameFile = (CheckersGameFile) AccountManager.activeAccount.getActiveGameFile();
        newGameFile.getGameStates().push(board);
        AccountManager.activeAccount.addGameFile(newGameFile);
        AccountManager.activeAccount.saveAccountGameData();
        this.gameFile = newGameFile;
        this.gameStates = newGameFile.getGameStates();
        this.board = board;
    }

    /**
     * Switches the board back one move, if the user has undos left
     */
    public void undo() {
        if (this.remainingUndos > 0) {
            this.remainingUndos--;
            this.gameStates.pop();
            this.board = (CheckersBoard)this.gameStates.peek();
            setChanged();
            notifyObservers();
        }
    }
}
