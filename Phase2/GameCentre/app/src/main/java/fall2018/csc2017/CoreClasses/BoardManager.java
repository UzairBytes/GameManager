package fall2018.csc2017.CoreClasses;

import java.io.Serializable;
import java.util.Observable;
import java.util.Stack;

import phase1.AccountManager;
import phase1.Game;
import phase1.GameFile;

public class BoardManager extends Observable implements Serializable, Game {

    /**
     * Holds a stack of boards, with each Board representing a specific game state.
     */
    protected Stack<Board> gameStates;

    /**
     * The number of undos the player has left.
     */
    // assigned a value from the save file
    protected int remainingUndos = 0;

    /**
     * The maximum number of undos.
     */
    // assigned a value from the save file
    protected int maxUndos = 3;

    /**
     * The number of moves the player has made.
     */
    protected int numMoves = 0;

    /**
     * The GameFile holding the data for this board.
     */
    private GameFile gameFile;

    /**
     * The board being managed.
     */
    private Board board;

    public BoardManager (GameFile gameFile){
        this.gameFile = gameFile;
        this.gameStates = gameFile.getGameStates();

        if (!gameFile.getGameStates().isEmpty()) {
            this.board = (Board) gameFile.getGameStates().peek();
        }
    }

    public BoardManager (int size){
        Tile[][] tiles = new Tile[size][size];
        this.board = new Board(tiles, size, size);
    }

    public GameFile getGameFile() {
        return gameFile;
    }

    public boolean gameComplete(){
        return false;
    }

    /**
     * Returns the score of a game.
     */
    public int score() {return 0;}


    /**
     * Saves a new state of board to game.
     *
     * @param board a board
     */
    @SuppressWarnings("unchecked")
    public void save(Board board) {
        this.gameStates.push(board);
        //AccountManager.activeAccount.addGameFile(newGameFile);
    }

    /**
     * Switches the gameState back one move, if the user has undos left
     */
    public Board undo(){
        if (gameFile.getRemainingUndos() > 0) {
            gameFile.lowerUndos();
            this.gameStates.pop();
            Board lastBoard = this.gameStates.peek();
            setChanged();
            notifyObservers();
            return lastBoard;
        }
        return board;
    }

    public void setGameFile(GameFile gameFile) {
        this.gameFile = gameFile;
    }
    
}
