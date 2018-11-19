package fall2018.csc2017.CoreClasses;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import phase1.AccountManager;
import phase1.GameFile;
import phase1.SlidingGameFile;

import java.util.Stack;


/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
public class SlidingBoardManager extends BoardManager {

    /**
     * The board being managed.
     */
    protected SlidingBoard Slidingboard;

    /**
     * The SlidingGameFile holding the data for this board.
     */
    private SlidingGameFile gameFile;

    /**
     * Holds a stack of Boards, with each Board representing a specific game state.
     */
    private Stack<SlidingBoard> gameStates;

    /**
     * Initialize the data of this game given a GameFile, containing a Stack of Boards
     * (each representing a specific 'game state'), and attributes telling of the game's settings.
     *
     * @param gameFile: Represents a record of data for this game.
     */
    @SuppressWarnings("unchecked")
    protected SlidingBoardManager(SlidingGameFile gameFile) {
        this.gameFile = gameFile;
        this.gameStates = gameFile.getGameStates();
        this.remainingUndos = gameFile.remainingUndos;
        this.remainingUndos = gameFile.maxUndos;
        this.numMoves = gameFile.numMoves;
        AccountManager.activeAccount.activeGameFile = gameFile;
        if (!gameFile.getGameStates().isEmpty()) {
            this.Slidingboard = (SlidingBoard) gameFile.getGameStates().peek();
        }
    }

    /**
     * Manage a new shuffled board.
     */
    @SuppressWarnings("unchecked")
    SlidingBoardManager(int size) {
        List<SlidingTile> tiles = new ArrayList<>();
        final int numTiles = size * size;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new SlidingTile(tileNum, numTiles));
        }

        Collections.shuffle(tiles);
        // Create the board, and specify number of rows, columns
        this.Slidingboard = new SlidingBoard(tiles, size, size);

        // Create a new GameFile, and initialize it with this shuffled board.
        SlidingGameFile gameFile = new SlidingGameFile(this.Slidingboard, Instant.now().toString());

        // Add this new GameFile to the current active account's list of GameFiles.
        AccountManager.activeAccount.addGameFile(gameFile);
        this.gameFile = gameFile;
        this.gameStates = this.gameFile.getGameStates();
        this.numMoves = gameFile.numMoves;
        this.maxUndos = gameFile.maxUndos;
        save(this.Slidingboard);
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
        Iterator<SlidingTile> iter = Slidingboard.iterator();
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

        int row = position / Slidingboard.numRows;
        int col = position % Slidingboard.numCols;
        int blankId = Slidingboard.numTiles();
        // Are any of the 4 the blank tile?
        SlidingTile above = row == 0 ? null : Slidingboard.getSlidingTile(row - 1, col);
        SlidingTile below = row == Slidingboard.numRows - 1 ? null : Slidingboard.getSlidingTile(row + 1, col);
        SlidingTile left = col == 0 ? null : Slidingboard.getSlidingTile(row, col - 1);
        SlidingTile right = col == Slidingboard.numCols - 1 ? null : Slidingboard.getSlidingTile(row, col + 1);
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
        SlidingBoard newBoard = Slidingboard.createDeepCopy();
        this.Slidingboard = newBoard;

        int row = position / Slidingboard.numRows;
        int col = position % Slidingboard.numCols;
        int blankId = Slidingboard.numTiles();
        SlidingTile above = row == 0 ? null : Slidingboard.getSlidingTile(row - 1, col);
        SlidingTile below = row == Slidingboard.numRows - 1 ? null : Slidingboard.getSlidingTile(row + 1, col);
        SlidingTile left = col == 0 ? null : Slidingboard.getSlidingTile(row, col - 1);
        SlidingTile right = col == Slidingboard.numCols - 1 ? null : Slidingboard.getSlidingTile(row, col + 1);
        addUndos();
        numMoves++;
        gameFile.increaseNumMoves();
        if (below != null && below.getId() == blankId) {
            Slidingboard.swapTiles(row, col, row + 1, col);
        } else if (above != null && above.getId() == blankId) {
            Slidingboard.swapTiles(row, col, row - 1, col);
        } else if (left != null && left.getId() == blankId) {
            Slidingboard.swapTiles(row, col, row, col - 1);
        } else if (right != null && right.getId() == blankId) {
            Slidingboard.swapTiles(row, col, row, col + 1);
        }
        save(newBoard);
        setChanged();
        notifyObservers();
    }

    /**
     * Add the number of undos to this board.
     */
    private void addUndos() {
        if (this.remainingUndos < this.maxUndos) {
            this.remainingUndos++;
        }
    }

    /**
     * Saves a new state of board to game.
     *
     * @param board a board
     */
    @SuppressWarnings("unchecked")
    public void save(SlidingBoard board) {
        SlidingGameFile newGameFile = (SlidingGameFile) AccountManager.activeAccount.activeGameFile;
        newGameFile.getGameStates().push(board);
        AccountManager.activeAccount.addGameFile(newGameFile);
        AccountManager.activeAccount.saveAllGameFiles();
        this.gameFile = newGameFile;
        this.gameStates = newGameFile.getGameStates();
        this.Slidingboard = board;
    }

    /**
     * Switches the board back one move, if the user has undos left
     */
    public void undo() {
        if (this.remainingUndos > 0) {
            this.remainingUndos--;
            this.gameStates.pop();
            this.Slidingboard = this.gameStates.peek();
            setChanged();
            notifyObservers();
        }
    }

    /**
     * Returns number of move that the user took to complete the game
     * Returns zero if game is not complete
     */
    @Override
    public int score() {
        if (puzzleSolved()) {
            return (int) (Math.pow(16, Slidingboard.numCols) / ((numMoves + 1) * (maxUndos + 1)));
        }
        return 0;
    }

    /**
     * Return the current board.
     */
    SlidingBoard getBoard() {
        return this.Slidingboard;
    }

    /**
     * @param maxUndoValue: Maximum number of undo tries for this file.
     *                      Also initializes the number of undo's this file currently has (denoted by <remainingUndos>)
     */
    void setMaxUndos(int maxUndoValue) {
        this.gameFile.maxUndos = maxUndoValue;
        this.gameFile.remainingUndos = 0;
        this.maxUndos = maxUndoValue;
        this.remainingUndos = 0;
    }

}
