package phase1;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

import Checkers.CheckersGameFile;
import Twenty.TwentyGameFile;

/**
 * Account class manages user accounts.
 */
public class Account implements Serializable {

    /**
     * Name of SlidingTiles -- a type of Game in this GameCenter.
     */
    private final String SLIDING_NAME = "sliding";

    /**
     * Name of Twenty -- a type of Game in this GameCenter.
     */
    private final String TWENTY_NAME = "checkers";

    /**
     * Name of Checkers -- a type of Game in this GameCenter.
     */
    private final String CHECKERS_NAME = "twenty";

    /**
     * This Account's username
     */
    private String username;

    /**
     * This Account's password
     */
    private String password;

    /**
     * A 2-D hashmap of GameFiles that belong to this Account. Each entry is a type of game,
     * and each hashmap inside contains the game files for said game.
     */
    private HashMap<String, HashMap<String, GameFile>> allGameFiles;

    /**
     * The current gameFile being played by this Account.
     */
    public GameFile activeGameFile;

    /**
     * The main save file where the <games> map will be saved, dependent on this account's username.
     */
    private String saveFileName;

    /**
     * Name of the game that is currently active in this account.
     */
    private String currentActiveGame = "";

    public Account(String user, String pass) {
        this.username = user;
        this.password = pass;
        this.allGameFiles = new HashMap<>();
        this.initializeGameFiles();
        // Set the 'save' file name based off of this username.
        this.saveFileName = "/" + this.username + ".ser";
        this.saveAllGameFiles();
    }

    /**
     * Initializes the <allGameFiles> attribute in this class.
     */
    private void initializeGameFiles(){
        HashMap<String, GameFile> slidingMap = new HashMap<String, GameFile>();
        HashMap<String, GameFile> twentyMap = new HashMap<String, GameFile>();
        HashMap<String, GameFile> checkersMap = new HashMap<String, GameFile>();
        this.allGameFiles.put(this.SLIDING_NAME, slidingMap);
        this.allGameFiles.put(this.TWENTY_NAME, twentyMap);
        this.allGameFiles.put(this.CHECKERS_NAME, checkersMap);
    }


    /**
     * Getter for username
     *
     * @return this account's username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Getter for password
     *
     * @return this account's password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Returns hashmap that contains all the account's games and corresponding gamefiles.
     *
     * @return said hashmap
     */
    public HashMap<String, GameFile> getGames(String gameType) {
        System.out.println("ran!");
        this.loadGameFiles();
        return this.allGameFiles.get(gameType);
    }


    /**
     * Adds a SlidingGameFile to the slidingFiles map, contained in the <allGameFiles> hashmap.
     * Afterwards re-serialize the <allGameFiles> map.
     */
    public void addGameFile(SlidingGameFile gameFile) {
        HashMap<String, GameFile> slidingFiles = this.allGameFiles.get(SLIDING_NAME);
        slidingFiles.put(gameFile.getName(), gameFile);
        this.activeGameFile = gameFile;
    }

    /**
     * Adds a CheckersGameFile to the checkersFiles map, contained in the <allGameFiles> hashmap.
     * Afterwards re-serialize the <allGameFiles> map.
     */
    public void addGameFile(CheckersGameFile gameFile) {
        HashMap<String, GameFile> checkersFiles = this.allGameFiles.get(CHECKERS_NAME);
        checkersFiles.put(gameFile.getName(), gameFile);
        this.activeGameFile = gameFile;
    }

    /**
     * Adds a TwentyGameFile to the twentyGameFiles map, contained in the <allGameFiles> hashmap.
     * Afterwards re-serialize the <allGameFiles> map.
     */
    public void addGameFile(TwentyGameFile gameFile) {
        HashMap<String, GameFile> twentyFiles = this.allGameFiles.get(TWENTY_NAME);
        twentyFiles.put(gameFile.getName(), gameFile);
        this.activeGameFile = gameFile;
    }

    /**
     * Overwrite and save the <games> hashmap in a serializable file.
     */
    public void saveAllGameFiles() {
        try {
            String path = AccountManager.contextPath;
            File file = new File(path + this.saveFileName);
            System.out.println("path:" + path + this.saveFileName);
            FileOutputStream output = new FileOutputStream(file);
            ObjectOutputStream outputStream = new ObjectOutputStream(output);
            outputStream.writeObject(this.allGameFiles);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    /**
     * Retrieve the <games> hashmap from the .ser file.
     */
    @SuppressWarnings("unchecked")
    public void loadGameFiles() {
        try {
            String path = AccountManager.contextPath;
            File file = new File(path + this.saveFileName);
            System.out.println("path:" + path + this.saveFileName);
            FileInputStream input = new FileInputStream(file);
            ObjectInputStream inputStream = new ObjectInputStream(input);
            this.allGameFiles = (HashMap<String, HashMap<String, GameFile>>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e1) {
            System.out.println(e1);
        }
    }


}
