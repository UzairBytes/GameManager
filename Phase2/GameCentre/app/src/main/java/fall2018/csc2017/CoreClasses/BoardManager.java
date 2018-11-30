package fall2018.csc2017.CoreClasses;

import java.io.Serializable;
import java.util.Observable;
import java.util.Stack;

public class BoardManager extends Observable implements Serializable, Game {

    /**
     * Holds a stack of boards, with each Board representing a specific game state.
     */
    protected Stack<Board> gameStates;

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
        board = new Board(tiles, size, size);
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
        gameStates.push(board);
    }

    /**
     * Switches the gameState back one move, if the user has undos left
     */
    public Board undo(){
        if (gameFile.getRemainingUndos() >0) {
            gameFile.lowerUndos();
            this.gameStates.pop();
            Board lastBoard = this.gameStates.peek();
            setChanged();
            notifyObservers();
            return lastBoard;
        }
        return board;
    }

    public void touchMove(int position){
    }

    public boolean isValidMove(int position){
        return false;
    }

    public void setGameFile(GameFile gameFile) {
        this.gameFile = gameFile;
        this.gameStates = gameFile.getGameStates();
    }

    /**
     * @param maxUndoValue: Maximum number of undo tries for this file.
     *                      Also initializes the number of undo's this file currently has (denoted by <remainingUndos>)
     */
    public void setMaxUndos(int maxUndoValue) {
        this.gameFile.setMaxUndos(maxUndoValue);
        this.gameFile.setRemainingUndos(0);
    }

    public Board getBoard() {
        return board;
    }


}
