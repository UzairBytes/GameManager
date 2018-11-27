package Sliding;

import java.io.Serializable;

import fall2018.csc2017.CoreClasses.Board;
import phase1.GameFile;

public class SlidingGameFile extends GameFile implements Serializable {

    /**
     * Constructor called when creating a new game, with it's specified initial game state and game name.
     *
     * @param initialBoard: The initial state of the SlidingTiles board
     * @param name:         The name of this game file
     */
    @SuppressWarnings("unchecked")
    public SlidingGameFile(Board initialBoard, String name) {
        this.gameStates.push(initialBoard);
        this.name = name;
    }

    public void increaseNumMoves(){
        numMoves++;
    }

}
