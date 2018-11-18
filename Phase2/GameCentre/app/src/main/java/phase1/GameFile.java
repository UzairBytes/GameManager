package phase1;

import java.io.Serializable;
import java.util.Stack;

/**
 *
 * @param <T> generic state of game file. Board in sliding tiles.
 */
public abstract class GameFile<T> implements Serializable {

    /**
     * name is the name of GameFile.
     */
    protected String name;

    /**
     * gameStates is a stack of boards, in the case of sliding tiles.
     */
    protected Stack<T> gameStates = new Stack<>();

    /**
     * @return Returns the Stack of game states for this file.
     */
    public Stack getGameStates(){
        return gameStates;
    }

    /**
     * @return The name of this Game File
     */
    public String getName(){
        return name;
    }

}
