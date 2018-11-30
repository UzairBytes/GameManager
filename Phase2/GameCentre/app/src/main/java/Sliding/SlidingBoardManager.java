package Sliding;

import java.time.Instant;
import java.util.Iterator;

import fall2018.csc2017.CoreClasses.Board;
import fall2018.csc2017.CoreClasses.BoardManager;
import fall2018.csc2017.CoreClasses.Tile;
import phase1.AccountManager;
import phase1.GameFile;

import java.util.Stack;


/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
public class SlidingBoardManager extends BoardManager {

    /**
     * The board being managed.
     */
    private SlidingBoard slidingBoard;

    /**
     * The SlidingGameFile holding the data for this board.
     */
    private SlidingGameFile gameFile;

    /**
     * Initialize the data of this game given a GameFile, containing a Stack of Boards
     * (each representing a specific 'game state'), and attributes telling of the game's settings.
     *
     * @param gameFile: Represents a record of data for this game.
     */
    @SuppressWarnings("unchecked")
    public SlidingBoardManager(SlidingGameFile gameFile) {
        this.gameFile = gameFile;
        this.gameStates = gameFile.getGameStates();
        this.remainingUndos = gameFile.getRemainingUndos();
        this.maxUndos = gameFile.getMaxUndos();
        this.numMoves = gameFile.getNumMoves();
        //AccountManager.activeAccount.addGameFile(gameFile);
        if (!gameFile.getGameStates().isEmpty()) {
            this.slidingBoard = (SlidingBoard) gameFile.getGameStates().peek();
        }
    }

    /**
     * Manage a new shuffled board.
     *
     * @param size: The desired size of the board.
     */
    @SuppressWarnings("unchecked")
    SlidingBoardManager(int size) {
        Tile[][] tiles = new Tile[size][size];
        final int numTiles = size * size;
        int tileNumber = 0;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                tiles[row][col] = new SlidingTile(tileNumber, numTiles);
                tileNumber++;
            }
        }

        //Checks tiles if there are in a solvable formation.
        shuffleTiles(tiles);
        SlidingBoardSolvable checkSolvableBoard = new SlidingBoardSolvable(tiles);
        while (!checkSolvableBoard.isBoardSolvable()) {
            shuffleTiles(tiles);
            checkSolvableBoard.setTiles(tiles);
        }
        // Create the board, and specify number of rows, columns
        this.slidingBoard = new SlidingBoard(tiles, size, size);

        // Create a new GameFile, and initialize it with this shuffled board.
        SlidingGameFile gameFile = new SlidingGameFile(this.slidingBoard, Instant.now().toString());

        // Add this new GameFile to the current active account's list of GameFiles.
        //AccountManager.activeAccount.addGameFile(gameFile);
//        AccountManager.activeAccount.saveAccountGameData();
        this.gameFile = gameFile;
        this.gameStates = this.gameFile.getGameStates();
        this.numMoves = gameFile.getNumMoves();
        this.maxUndos = gameFile.getMaxUndos();
        super.save(this.slidingBoard);
    }

    /**
     * Returns the GameFile managed by this SlidingBoardManager.
     */
    GameFile getGameFile() {
        return this.gameFile;
    }

    /**
     * Return whether the tiles are in row-major order.
     *
     * @return whether the tiles are in row-major order
     */
    boolean puzzleSolved() {
        Iterator<Tile> iter = slidingBoard.iterator();
        int id = iter.next().getId();
        while (iter.hasNext()) {
            int nextId = iter.next().getId();
            if (nextId != (id + 1)) {
                return false;
            }
            id = nextId;
        }
        return true;
    }

    /**
     * Return whether any of the four surrounding tiles is the blank tile.
     *
     * @param position the tile to check
     * @return whether the tile at position is surrounded by a blank tile
     */
    boolean isValidTap(int position) {

        int row = position / slidingBoard.getNumRows();
        int col = position % slidingBoard.getNumCols();
        int blankId = slidingBoard.numTiles();
        // Are any of the 4 the blank tile?
        SlidingTile above = row == 0 ? null : (SlidingTile) slidingBoard.getTile(row - 1, col);
        SlidingTile below = row == slidingBoard.getNumRows() - 1 ? null : (SlidingTile) slidingBoard.getTile(row + 1, col);
        SlidingTile left = col == 0 ? null : (SlidingTile) slidingBoard.getTile(row, col - 1);
        SlidingTile right = col == slidingBoard.getNumCols() - 1 ? null : (SlidingTile) slidingBoard.getTile(row, col + 1);
        return (below != null && below.getId() == blankId)
                || (above != null && above.getId() == blankId)
                || (left != null && left.getId() == blankId)
                || (right != null && right.getId() == blankId);
    }

    /**
     * Process a touch at position in the board, swapping tiles as appropriate.
     *
     * @param position the position
     */
    void touchMove(int position) {
        SlidingBoard newBoard = slidingBoard.createDeepCopy();
        this.slidingBoard = newBoard;
        processMove(position);
        save(newBoard);
        setChanged();
        notifyObservers();
    }

    /**
     * Process move swap tiles at the position of the movement.
     */
    void processMove(int position) {
        int row = position / slidingBoard.getNumRows();
        int col = position % slidingBoard.getNumCols();
        int blankId = slidingBoard.numTiles();
        SlidingTile above = row == 0 ? null : (SlidingTile) slidingBoard.getTile(row - 1, col);
        SlidingTile below = row == slidingBoard.getNumRows() - 1 ? null : (SlidingTile) slidingBoard.getTile(row + 1, col);
        SlidingTile left = col == 0 ? null : (SlidingTile) slidingBoard.getTile(row, col - 1);
        SlidingTile right = col == slidingBoard.getNumCols() - 1 ? null : (SlidingTile) slidingBoard.getTile(row, col + 1);
        addUndos();
        numMoves++;
        gameFile.increaseNumMoves();
        if (below != null && below.getId() == blankId) {
            slidingBoard.swapTiles(row, col, row + 1, col);
        } else if (above != null && above.getId() == blankId) {
            slidingBoard.swapTiles(row, col, row - 1, col);
        } else if (left != null && left.getId() == blankId) {
            slidingBoard.swapTiles(row, col, row, col - 1);
        } else if (right != null && right.getId() == blankId) {
            slidingBoard.swapTiles(row, col, row, col + 1);
        }
    }


    /**
     * Saves a new state of board to game.
     *
     * @param board a board
     */
    @SuppressWarnings("unchecked")
    public void save(SlidingBoard board) {
        super.save(board);
        //AccountManager.activeAccount.saveAccountGameData();
        this.gameFile = (SlidingGameFile) AccountManager.activeAccount.getActiveGameFile();
        this.gameStates = this.gameFile.getGameStates();
        this.slidingBoard = board;
    }

    /**
     * Switches the board back one move, if the user has undos left
     */
    @Override
    public Board undo() {
        // TODO: Use the return value of this fn.
        if (this.remainingUndos > 0) {
            this.slidingBoard = (SlidingBoard) super.undo();
        }
        setChanged();
        notifyObservers();
        return this.slidingBoard;
    }

    /**
     * Returns number of move that the user took to complete the game
     * Returns zero if game is not complete
     */
    @Override
    public int score() {
        if (puzzleSolved()) {
            return (int) (Math.pow(16, slidingBoard.getNumCols()) / ((numMoves + 1) * (maxUndos + 1)));
        }
        return 0;
    }

    /**
     * Return the current board.
     */
    SlidingBoard getBoard() {
        return this.slidingBoard;
    }


    /**
     * @param maxUndoValue: Maximum number of undo tries for this file.
     *                      Also initializes the number of undo's this file currently has (denoted by <remainingUndos>)
     */
    void setMaxUndos(int maxUndoValue) {
        this.gameFile.setMaxUndos(maxUndoValue);
        this.gameFile.setRemainingUndos(0);
        this.maxUndos = maxUndoValue;
        this.remainingUndos = 0;
    }

    /**
     * Shuffles array of tiles.
     *
     * @param shuffleTiles 2D array of tiles
     */
    private void shuffleTiles(Tile[][] shuffleTiles) {
        for (int i = 0; i < shuffleTiles.length; i++) {
            for (int j = 0; j < shuffleTiles[i].length; j++) {
                int i1 = (int) (Math.random() * shuffleTiles.length);
                int j1 = (int) (Math.random() * shuffleTiles.length);

                SlidingTile temp = (SlidingTile) shuffleTiles[i][j];
                shuffleTiles[i][j] = shuffleTiles[i1][j1];
                shuffleTiles[i1][j1] = temp;

            }
        }
    }

    /**
     * Sets Sliding Game File in board manager.
     * @param newFile sliding game file.
     */
    public void setSlidingGameFile(SlidingGameFile newFile){
        this.gameFile = newFile;
        this.gameStates = this.gameFile.getGameStates();
        this.slidingBoard = (SlidingBoard) this.gameFile.getGameStates().peek();
    }

    /**
     * Returns max undos.
     * @return int max undos.
     */
    public int getMaxUndo(){
        return maxUndos;
    }

    /**
     * Add the number of undos to this board.
     */
    @Override
    public void addUndos() {
        super.addUndos();
        this.gameFile.setRemainingUndos(this.remainingUndos);
    }

}
