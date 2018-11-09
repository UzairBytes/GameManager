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


/**
 * A class for registering and retrieving the scores of completed games
 */
public abstract class LeaderBoard {

    /**
     * An ArrayList of GameScores
     */
    private static ArrayList<GameScore> scores = new ArrayList<>();

    /**
     * The main save file where the ArrayList of scores will be saved.
     */
    private static String SAVE_FILENAME = "/scores.ser";


    /**
     * Add gameScore to scores in order.
     *
     * @param gameScore GameScore to be added
     */
    public static void updateScores(Context context, GameScore gameScore) {

        loadScoresFromFile(context);

        boolean notInserted = true;
        if (scores.size() == 0) {
            scores.add(gameScore);
            notInserted = false;
        }
        int i = 0;
        while (notInserted && i < scores.size()) {
            if (gameScore.scoredHigherThan(scores.get(i))) {
                scores.add(i, gameScore);
                notInserted = false;
            }
            i++;
        }
        if (notInserted) {
            scores.add(gameScore);
        }
        saveScoresToFile(context);

    }


    /**
     * @param gameName Name of the game, e.g., "SlidingTiles"
     * @param n        int number of top n scores
     * @return ArrayList length up to n of Object[]'s sorted from highest to lowest score
     */
    public static ArrayList<GameScore> getTopScores(Context context, String gameName, int n) {
        loadScoresFromFile(context);
        ArrayList<GameScore> output = new ArrayList<>();
        int i = 0;
        while (output.size() < n && i < scores.size()) {
            if (scores.get(i).getGameName().equals(gameName)) {
                output.add(scores.get(i));
            }
            i++;
        }
        return output;
    }


    /**
     * @param accountName username
     * @param gameName    name of instance of the game as a timestamp
     * @param n           int number of top n scores
     * @return ArrayList of length up to n of Object[]'s sorted from highest to lowest score
     */
    public static ArrayList<GameScore> getTopScores(Context context, String gameName, String accountName, int n) {
        loadScoresFromFile(context);
        ArrayList<GameScore> output = new ArrayList<>();
        int i = 0;
        while (output.size() < n && i < scores.size()) {
            if (scores.get(i).getGameName().equals(gameName) &&
                    scores.get(i).getAccountName().equals(accountName)) {
                output.add(scores.get(i));
            }
            i++;
        }
        return output;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (GameScore gameScore : scores) {
            output.append(gameScore.toString());
        }
        return output.toString();
    }

    /**
     * Load scores from scores.ser
     */
    @SuppressWarnings("unchecked")
    private static void loadScoresFromFile(Context context) {
        try {
            String path = context.getFilesDir().getAbsolutePath();
            File file = new File(path + LeaderBoard.SAVE_FILENAME);
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            scores = (ArrayList<GameScore>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException e) {
            Log.e("LeaderBoardActivities", "scores.ser not found.");
        } catch (ClassNotFoundException e) {
            Log.e("LeaderBoardActivities", "scores.ser corrupted.");
        }
    }


    /**
     * Save scores to scores.ser
     */
    private static void saveScoresToFile(Context context) {
        try {
            String path = context.getFilesDir().getAbsolutePath();
            File file = new File(path + LeaderBoard.SAVE_FILENAME);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            objectOutputStream.writeObject(LeaderBoard.scores);
            objectOutputStream.close();
        } catch (IOException e) {
            Log.e("LeaderBoardActivities", "scores.ser not found");

        }

    }
}
