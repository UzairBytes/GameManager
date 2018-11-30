package fall2018.csc2017.CoreClasses;

import java.io.Serializable;
import java.util.Stack;

/**
 *
 * @param <T> generic state of game file. Board in sliding tiles.
 */
public abstract class GameFile<T> implements Serializable {

    /**
     * Maximum number of undos for a set of SlidingGameFile.
     */
    protected int maxUndos;


    /**
     * Undos remaining for a set of SlidingGameFile.
     */
    protected int remainingUndos;


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

    public void setRemainingUndos(int remainingUndos){
        this.remainingUndos = remainingUndos;
    }

    public void addUndos(){
        if (remainingUndos <= maxUndos){
            remainingUndos++;
        }
    }

    public void lowerUndos(){
        remainingUndos--;
    }

    public void setMaxUndos(int maxUndos){
        this.maxUndos = maxUndos;
    }


    public int getRemainingUndos(){
        return remainingUndos;

    }

    public int getMaxUndos(){
        return this.maxUndos;
    }
}
