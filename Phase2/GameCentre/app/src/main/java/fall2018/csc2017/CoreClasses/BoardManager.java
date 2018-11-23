package fall2018.csc2017.CoreClasses;

import java.io.Serializable;
import java.util.Observable;
import java.util.Stack;

import phase1.Game;

public abstract class BoardManager extends Observable implements Serializable, Game {

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
     * Save a board to the stack of game files.
     *
     * @param board is a board.
     */
    public void save(Board board) {

    }

    /**
     * Switches the gameState back one move, if the user has undos left
     */
    public void undo() {}

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

}
