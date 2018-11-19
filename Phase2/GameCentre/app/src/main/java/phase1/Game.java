package phase1;

import fall2018.csc2017.CoreClasses.Board;

public interface Game {

    /**
     * Save a board to the stack of game files.
     *
     * @param board is a board.
     */
    void save(Board board);

    /**
     * Switches the gameState back one move, if the user has undos left
     */
    void undo();

    /**
     * Returns the score of a game.
     */
    int score();

}
