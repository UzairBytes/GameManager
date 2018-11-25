package phase1;


import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * A class for registering and retrieving the scores of completed games
 */
public class LeaderBoard {

    /**
     * Number of top GameScores to store per game per account
     * and per game on the global LeaderBoard
     */
    private static final int NUM_TOP_SCORES = 20;

    /**
     * A HashMap representing an account specific leaderBoard
     */
    private HashMap<String, ArrayList<GameScore>> personalScoresMap;


    /**
     * An HashMap of GameScores representing the LeaderBoard for each game
     */
    private static HashMap<String, ArrayList<GameScore>> globalScoresMap = new HashMap<>();

    /**
     * The main save file where the ArrayList of scores will be saved.
     */
    private static final String SCORES_DOT_SER = "scores.ser";

    /**
     * A string to indicate what kind of LeaderBoard we're dealing with
     */
    public static final String PERSONAL = "personal";

    /**
     * A string to indicate what kind of LeaderBoard we're dealing with
     */
    public static final String GLOBAL = "global";

    /**
     * Check if all games are listed in scores, and put a Key-Value pair in if not.
     */
    private static void validateKeys(HashMap<String, ArrayList<GameScore>> leaderBoard) {
        Set<String> keys = leaderBoard.keySet();
        for (String key : Game.GAME_NAMES) {
            if (!keys.contains(key)) {
                leaderBoard.put(key, new ArrayList<GameScore>());
            }
        }
    }

    /**
     * A constructor for LeaderBoard
     */
    public LeaderBoard() {
        personalScoresMap = new HashMap<>();
        for (String name : Game.GAME_NAMES) {
            personalScoresMap.put(name, new ArrayList<GameScore>());
        }
    }


    /**
     * Add GameScore to top scores for the type of game if it's high enough.
     *
     * @param gameScore GameScore to be added
     */
    public static void updateScores(Context context, GameScore gameScore) {
        String[] types = {GLOBAL, PERSONAL};
        for (String type : types) {
            loadScoresFromFile(context, type);
            updateTopScores(gameScore, type);
            saveScoresToFile(context, type);
        }
    }

    /**
     * HashMap<String,ArrayList<GameScore>>
     * Update the specified LeaderBoard if gameScore scored high enough
     * Removes the lowest scoring gameScore if adding causes list length to go over NUM_TOP_SCORES
     *
     * @param gameScore the result of the game under consideration
     */
    private static void updateTopScores(GameScore gameScore, String type) {
        ArrayList<GameScore> scoreList;
        if (type.equals(GLOBAL)) {
            scoreList = globalScoresMap.get(gameScore.getGameName());
        } else {
            scoreList = AccountManager.activeAccount.getLeaderBoard().personalScoresMap.get(gameScore.getGameName());
        }

        boolean notInserted = true;
        int size = scoreList.size();
        if (size == 0) {
            scoreList.add(gameScore);
            notInserted = false;
        }
        int i = 0;
        while (notInserted || i < size || i < NUM_TOP_SCORES) {
            if (gameScore.compareTo(scoreList.get(i)) > 0) {
                scoreList.add(i, gameScore);
                notInserted = false;
            }
            i++;
        }
        if (size == NUM_TOP_SCORES) {
            scoreList.remove(NUM_TOP_SCORES);
        }
    }


    /**
     * Get top scores of the chosen type of LeaderBoard for the game with gameName
     *
     * @param context  the current context
     * @param gameName Name of the game, e.g., "Sliding Tiles"
     * @param type     GLOBAL or PERSONAL LeaderBoard
     * @return ArrayList of Object[]'s sorted from highest to lowest score
     */
    public static ArrayList<GameScore> getTopScores(Context context, String gameName, String type) {
        loadScoresFromFile(context, type);
        if (type.equals(PERSONAL)) {
            return AccountManager.activeAccount.getLeaderBoard().personalScoresMap.get(gameName);
        } else {
            return globalScoresMap.get(gameName);
        }


    }


    /**
     * Load scores from globalScoresMap.ser
     */
    @SuppressWarnings("unchecked")
    private static void loadScoresFromFile(Context context, String type) {
        try {
            String path = context.getFilesDir().getAbsolutePath();
            String pathAndFileName;
            if (type.equals(PERSONAL)){
                pathAndFileName = path + "/" + AccountManager.activeAccount.getUsername()
                        + "/" + AccountManager.activeAccount.getActiveGameName() + SCORES_DOT_SER;
            }
            else {
                pathAndFileName = path + "/" + GLOBAL
                        + "/" + AccountManager.activeAccount.getActiveGameName() + SCORES_DOT_SER;
            }
            File file = new File(pathAndFileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            if (type.equals(PERSONAL)) {
                AccountManager.activeAccount.getLeaderBoard().personalScoresMap = (HashMap<String, ArrayList<GameScore>>) objectInputStream.readObject();
                validateKeys(AccountManager.activeAccount.getLeaderBoard().personalScoresMap);
            } else {
                globalScoresMap = (HashMap<String, ArrayList<GameScore>>) objectInputStream.readObject();
                validateKeys(globalScoresMap);
            }

            objectInputStream.close();
        } catch (IOException e) {
            Log.e("LeaderBoardActivities", SCORES_DOT_SER + " not found.");
        } catch (ClassNotFoundException e) {
            Log.e("LeaderBoardActivities", SCORES_DOT_SER + " corrupted.");
        }
    }


    /**
     * Save scores to globalScoresMap.ser
     */
    private static void saveScoresToFile(Context context, String type) {
        try {
            String path = context.getFilesDir().getAbsolutePath();

            String pathAndFileName;
            if (type.equals(PERSONAL)){
                pathAndFileName = path + "/" + AccountManager.activeAccount.getUsername()
                        + "/" + AccountManager.activeAccount.getActiveGameName() + SCORES_DOT_SER;
            }
            else {
                pathAndFileName = path + "/" + GLOBAL
                        + "/" + AccountManager.activeAccount.getActiveGameName() + SCORES_DOT_SER;
            }

            File file = new File(pathAndFileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            if (type.equals(PERSONAL)) {
                objectOutputStream.writeObject(AccountManager.activeAccount.getLeaderBoard().personalScoresMap);
            } else {
                objectOutputStream.writeObject(LeaderBoard.globalScoresMap);
            }

            objectOutputStream.close();
        } catch (IOException e) {
            Log.e("LeaderBoardActivities", SCORES_DOT_SER + " not found");
        }
    }
}
