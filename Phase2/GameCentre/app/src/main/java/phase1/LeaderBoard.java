package phase1;


import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A class for registering and retrieving the scores of completed games
 * Every account should create its own LeaderBoard when created.
 * Every game should call updateScores when completed.
 * Everything else here is auxiliary.
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
    public static final String PERSONAL = "personalLeaderBoard";

    /**
     * A string to indicate what kind of LeaderBoard we're dealing with
     */
    public static final String GLOBAL = "globalLeaderBoard";

    /**
     * Check if all games are listed in scores, and put a Key-Value pair in if not.
     */
    private static void validateKey(HashMap<String, ArrayList<GameScore>> leaderBoard, String key) {
        if (!leaderBoard.containsKey(key)) {
            leaderBoard.put(key, new ArrayList<GameScore>());
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
    public static void updateScores(GameScore gameScore) {
        String[] types = {GLOBAL, PERSONAL};
        for (String type : types) {
            loadScoresFromFile(type);
            updateTopScores(gameScore, type);
            saveScoresToFile(type);
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
     * @param gameName Name of the game, e.g., "Sliding Tiles"
     * @param type     GLOBAL or PERSONAL LeaderBoard
     * @return ArrayList of Object[]'s sorted from highest to lowest score
     */
    public static ArrayList<GameScore> getTopScores(String gameName, String type) {
        loadScoresFromFile(type);
        if (type.equals(PERSONAL)) {
            return AccountManager.activeAccount.getLeaderBoard().personalScoresMap.get(gameName);
        } else {
            return globalScoresMap.get(gameName);
        }
    }


    /**
     * Load ArrayList of GameScores for the specified type of LeaderBoard
     * Note for all personal LeaderBoards and the global and for every game there will be a
     * different file representing the top scores of that game
     *
     * @param type GLOBAL or PERSONAL LeaderBoard
     */
    @SuppressWarnings("unchecked")
    private static void loadScoresFromFile(String type) {
        Account activeAccount = AccountManager.activeAccount;
        String gameName = activeAccount.getActiveGameName();
        String fileName;
        ArrayList<GameScore> loadedData;
        if (type.equals(PERSONAL)) {
            //fileName = "/" + PERSONAL + "/" + activeAccount.getUsername() + "/" + gameName + SCORES_DOT_SER;
            fileName = "test.ser";
            //TODO: Perhaps improve implementation
            HashMap<String, ArrayList<GameScore>> newMap = new HashMap<>();
            loadedData = (ArrayList<GameScore>) LeaderBoard.load(fileName, newMap);
            validateKey(AccountManager.activeAccount.getLeaderBoard().personalScoresMap, gameName);
            AccountManager.activeAccount.getLeaderBoard().personalScoresMap.replace(AccountManager.activeAccount.getActiveGameName(), loadedData);
        } else {
            fileName = "/" + GLOBAL + "/" + gameName + SCORES_DOT_SER;
            HashMap<String, ArrayList<GameScore>> newMap2 = new HashMap<>();
            loadedData = (ArrayList<GameScore>) LeaderBoard.load(fileName, newMap2);
            validateKey(globalScoresMap, gameName);
            globalScoresMap.replace(gameName, loadedData);
        }

    }


    /**
     * Save ArrayList of GameScores to file
     * Note for all personal LeaderBoards and the global and for every game there will be a
     * different file representing the top scores of that game
     *
     * @param type GLOBAL or PERSONAL LeaderBoard
     */
    private static void saveScoresToFile(String type) {
        Account activeAccount = AccountManager.activeAccount;
        String gameName = activeAccount.getActiveGameName();
        String fileName;
        if (type.equals(PERSONAL)) {
            fileName = "/" + PERSONAL + "/" + activeAccount.getUsername() + "/" + gameName + SCORES_DOT_SER;
            validateKey(activeAccount.getLeaderBoard().personalScoresMap, gameName);
            LeaderBoard.save(fileName, activeAccount.getLeaderBoard().personalScoresMap.get(gameName));
        } else {
            fileName = "/" + GLOBAL + "/" + gameName + SCORES_DOT_SER;
            validateKey(globalScoresMap, gameName);
            LeaderBoard.save(fileName, globalScoresMap.get(gameName));
        }
    }


    /**
     * Overwrite and save the <games> hashMap in a serializable file.
     */
    public static void save(String fileName, ArrayList<GameScore> s) {

        try {
            String path = AccountManager.contextPath;
            File file = new File(path + fileName);
            System.out.println("path:" + path + fileName);
            FileOutputStream output = new FileOutputStream(file);
            ObjectOutputStream outputStream = new ObjectOutputStream(output);
            outputStream.writeObject(s);
            outputStream.close();
        } catch (IOException e) {
            System.out.println(e);
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    /**
     * Retrieve the <games> hashmap from the .ser file, if it exists. If it doesn't, that means
     * that the user has not yet made a game file. In this case, save the existing empty map of
     * game files to the .ser file.
     */
    @SuppressWarnings("unchecked")
    public static ArrayList<GameScore> load(String fileName, HashMap<String, ArrayList<GameScore>>  s){

        try {
            String path = AccountManager.contextPath;
            File file = new File(path + fileName);
            System.out.println("path:" + path + fileName);
            FileInputStream input = new FileInputStream(file);
            ObjectInputStream inputStream = new ObjectInputStream(input);
            return ( ArrayList<GameScore>) inputStream.readObject();
        }catch (IOException e1){
            LeaderBoard.save(fileName, s.get(Game.SLIDING_NAME));
            return LeaderBoard.load(fileName, s);
        }catch (ClassNotFoundException e1 ) {
            System.out.println(e1);
            return null;
        }
    }
}
