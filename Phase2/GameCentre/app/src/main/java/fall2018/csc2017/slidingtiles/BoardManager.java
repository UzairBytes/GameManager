package fall2018.csc2017.slidingtiles;

import java.io.Serializable;
import java.util.Observable;
import java.util.Stack;

import phase1.Game;
import phase1.SlidingGameFile;

public class BoardManager extends Observable implements Game, Serializable {


    /**
     * The board being managed.
     */
    protected Board board;

    /**
     * Holds a stack of Boards, with each Board representing a specific game state.
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
    // assigned a value from the save file
    protected int numMoves = 0; //removed static.

    /**
     * Returns number of move that the user took to complete the game
     * To be overridden in subclass.
     */
    public int score(){
        return 0;
    }

    /**
     * Saves a new state of board to game.
     * To be overridden in subclass.
     *
     * @param board a board
     */
    public void save(Board board) {
        this.board = board;
    }

    /**
     * Switches the board back one move, if the user has undos left
     * To be overridden in subclass.
     */
    public void undo() {
    }

}
