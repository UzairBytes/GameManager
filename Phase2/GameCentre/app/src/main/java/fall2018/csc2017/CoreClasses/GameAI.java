package fall2018.csc2017.CoreClasses;

/**
 * A class which lays out the basic requirements of all GameAIs
 */
public abstract class GameAI {
    /**
     * This is the default move when the active player has no legal move
     */
    protected static final int[][] NO_LEGAL_MOVE = {{-1,-1},{-1,-1}};

    /**
     * Get a move based on the specifications of the AI implementing this
     * @return the computer chosen move
     */
    abstract Object[] getMove();

}
