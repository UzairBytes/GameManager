package phase1;

import java.io.Serializable;

/**
 * A class for recording the results of completed games.
 */

public class GameScore implements Serializable {

    /**
     * The name of the game, e.g., "SlidingTiles"
     */
    private String gameName;

    /**
     * The name of that particular instance of the game
     */
    private String instanceGameName;

    /**
     * The username of whoever completed the game.
     */
    private String accountName;

    /**
     * The score on completion of the game
     */
    private int score;

    /**
     * A constructor for GameScores
     * @param gameName The name of the game, e.g., "SlidingTiles"
     * @param instanceGameName The name of that particular instance of the game
     * @param accountName The username of whoever completed the game.
     * @param score The score on completion of the game
     */
    public GameScore(String gameName, String instanceGameName, String accountName, int score){
        this.gameName = gameName;
        this.instanceGameName = instanceGameName;
        this.accountName = accountName;
        this.score = score;
    }

    /**
     * A getter for the game name, e.g., "SlidingTiles"
     * @return the name of the game
     */
    String getGameName(){return this.gameName;}

    /**
     * A getter for the username
     * @return username of whoever achieved this score
     */
    String getAccountName(){return this.accountName;}

    /**
     * Provides a means of comparing GameScores
     * @param other GameScore to be compared with
     * @return true if and only if this GameScore has a higher score than the other
     */
    boolean scoredHigherThan(GameScore other){
        return this.score > other.score;
    }

    /**
     * Returns of string representation of the attributes of GameScore
     * @return a string representation of GameScore
     */
    @Override
    public String toString(){
        return gameName + ", " + instanceGameName + ", " + accountName + ", " + score;
    }

}
