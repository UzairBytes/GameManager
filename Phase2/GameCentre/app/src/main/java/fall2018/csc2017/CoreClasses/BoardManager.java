package fall2018.csc2017.CoreClasses;

import java.io.Serializable;
import java.util.Observable;
import java.util.Stack;

import Checkers.CheckersBoard;
import Sliding.SlidingBoard;
import phase1.AccountManager;
import phase1.Game;
import phase1.GameFile;
import phase1.SlidingGameFile;

public abstract class BoardManager extends Observable implements Serializable, Game {

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
     * Returns the score of a game.
     */
    public int score() {return 0;}

    /**
     * Add the number of undos to this board.
     */
    public void addUndos() {
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
    public void save(Board board) {
        GameFile newGameFile = AccountManager.activeAccount.getActiveGameFile();
        newGameFile.getGameStates().push(board);
        AccountManager.activeAccount.addGameFile(newGameFile);
        AccountManager.activeAccount.saveAccountGameData();
    }

    /**
     * Switches the gameState back one move, if the user has undos left
     */
    public Board undo(){
        this.remainingUndos--;
        this.gameStates.pop();
        Board lastBoard = this.gameStates.peek();
        setChanged();
        notifyObservers();
        return lastBoard;
    }
}
